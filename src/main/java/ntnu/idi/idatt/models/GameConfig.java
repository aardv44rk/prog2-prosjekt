package ntnu.idi.idatt.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ntnu.idi.idatt.exceptions.ConfigurationException;
import ntnu.idi.idatt.exceptions.FileHandlingException;
import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.exceptions.ReadException;
import ntnu.idi.idatt.exceptions.WriteException;
import ntnu.idi.idatt.games.ludo.LudoBoard;
import ntnu.idi.idatt.games.snakesandladders.LadderAction;
import ntnu.idi.idatt.games.snakesandladders.LinearMovementStrategy;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoard;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.games.thievesandrobbers.CircularMovementStrategy;
import ntnu.idi.idatt.games.thievesandrobbers.MoneyAction;
import ntnu.idi.idatt.games.thievesandrobbers.ThievesAndRobbersBoard;
import ntnu.idi.idatt.games.thievesandrobbers.ThievesAndRobbersBoardFactory;
import ntnu.idi.idatt.games.thievesandrobbers.ThievesAndRobbersPiece;
import ntnu.idi.idatt.utility.ArgumentValidator;
import ntnu.idi.idatt.utility.CsvUtil;
import ntnu.idi.idatt.utility.FileUtil;

/**
 * Represents the configuration of a game, including players, board, and current player index.
 * Provides methods to save and load game configurations and player lists.
 */
public class GameConfig {

  private final List<Player> players;
  private final Board board;
  private final int currentPlayerIndex;

  /**
   * Constructor for the GameConfig class.
   *
   * @param players a list of players
   * @param board the game board
   * @param currentPlayerIndex the index of the current player
   * @throws ConfigurationException if the game configuration is invalid
   */
  public GameConfig(
          List<Player> players,
          Board board,
          int currentPlayerIndex
  ) {
    if (!isValidGameConfig(players, board, currentPlayerIndex)) {
      throw new ConfigurationException("Invalid game configuration");
    }
    this.players = players;
    this.board = board;
    this.currentPlayerIndex = currentPlayerIndex;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public Board getBoard() {
    return board;
  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  /**
   * Saves the game configuration to a file.
   *
   * @param filePath the path to the file
   * @throws FileHandlingException if an I/O error occurs
   * @throws InvalidInputException if the file path is invalid
   */
  public void saveConfig(String filePath) throws FileHandlingException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new InvalidInputException("Invalid file path");
    }
    JsonObject config = new JsonObject();

    // Add current player index and board type to the config
    config.addProperty("currentPlayerIndex", currentPlayerIndex);
    config.addProperty("boardType", board.getClass().getName());

    // As SNL has more board information, we add it here
    if (board instanceof SnakesAndLaddersBoard) {
      SnakesAndLaddersBoard snlBoard = (SnakesAndLaddersBoard) board;
      config.addProperty("boardRows", snlBoard.getRows());
      config.addProperty("boardColumns", snlBoard.getColumns());
    } else if (board instanceof LudoBoard) {
      ThievesAndRobbersBoard tarBoard = (ThievesAndRobbersBoard) board;
      config.addProperty("boardWith", tarBoard.getWidth());
      config.addProperty("boardHeight", tarBoard.getHeight());

      JsonArray tileMoneyArray = new JsonArray();

      for (int i = 0; i < tarBoard.getTiles().size(); i++) {
        Tile tile = tarBoard.getTile(i);
        if (tile != null && tile.getTileAction() instanceof MoneyAction) {
          tileMoneyArray.add(((MoneyAction) tile.getTileAction()).getMoney());
        } else {
          tileMoneyArray.add(0); // Default value for tiles without MoneyAction
        }
      }
      config.add("tileMoney", tileMoneyArray);
    }

    // Save detailed board information
    JsonArray tilesArray = new JsonArray();
    Map<Integer, Tile> tiles = board.getTiles();
    for (Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
      int tileId = entry.getKey();
      Tile tile = entry.getValue();
      TileAction action = tile.getTileAction();

      JsonObject tileObj = new JsonObject();
      tileObj.addProperty("id", tileId);

      if (action != null) {
        JsonObject actionObj = new JsonObject();

        if (action instanceof LadderAction) {
          int destinationTileId = getActionDestinationTileId(action);
          actionObj.addProperty("type", "ladder");
          actionObj.addProperty("destinationTileId", destinationTileId);
        } else if (action instanceof MoneyAction) {
          actionObj.addProperty("type", "money");
        } else {
          actionObj.addProperty("type", "default"); // Default value
        }

        tileObj.add("action", actionObj);
      }

      tilesArray.add(tileObj);
    }

    config.add("tiles", tilesArray);

    // Save player & piece information
    JsonArray playersArray = new JsonArray();
    for (Player player : players) {
      JsonObject playerObj = new JsonObject();
      playerObj.addProperty("name", player.getName());
      
      JsonArray piecesArray = new JsonArray();
      for (Piece piece : player.getPieces()) {
        JsonObject pieceObj = new JsonObject();
        pieceObj.addProperty("tileId", piece.getCurrentTile().getTileId());
        if (piece instanceof ThievesAndRobbersPiece) {
          pieceObj.addProperty("pieceMoney", ((ThievesAndRobbersPiece) piece).getMoney());
          pieceObj.addProperty("movementStrategy", CircularMovementStrategy.class.getName());
        } else {
          pieceObj.addProperty("movementStrategy", LinearMovementStrategy.class.getName());
        }
        piecesArray.add(pieceObj);
      }
      playerObj.add("pieces", piecesArray);

      playersArray.add(playerObj);
    }

    config.add("players", playersArray);

    // finally write to file
    try {
      FileUtil.writeString(filePath, config.toString());
      System.out.println("Game configuration saved to " + filePath);
    } catch (IOException e) {
      throw new WriteException("Error writing game configuration to file: " + filePath, e);
    }
  }

  /**
   * Saves the player list to a file.
   *
   * @param filePath the path to the file
   * @throws WriteException if a write / input error occurs
   * @throws InvalidInputException if the file path is invalid
   */
  public void savePlayerList(String filePath) throws InvalidInputException, WriteException {
    try {
      if (!ArgumentValidator.isValidFilePath(filePath)) {
        throw new InvalidInputException("Invalid file path");
      }
      List<String[]> playerNames = new ArrayList<>();
      for (Player player : players) {
        playerNames.add(new String[]{player.getName()});
      }

      CsvUtil.writeCsv(filePath, playerNames);
      System.out.println("Player list saved to: " + filePath);
    } catch (IOException e) {
      throw new WriteException("Error writing player list to file: " + filePath, e);
    }
  }

  /**
   * Loads the game configuration from a file.
   *
   * @param filePath the path to the file
   * @return the loaded game configuration
   * @throws ReadException if an I/O error occurs
   * @throws InvalidInputException if the file path is invalid
   */
  public GameConfig loadConfig(String filePath) throws ReadException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new InvalidInputException("Invalid file path");
    }

    String json = "";
    try {
      json = FileUtil.readString(filePath);
    } catch (IOException e) {
      throw new ReadException("File not found: " + filePath, e);
    }
    JsonObject config = JsonParser.parseString(json).getAsJsonObject();

    int currentPlayerIndex = config.get("currentPlayerIndex").getAsInt();

    String boardType = config.get("boardType").getAsString();
    Board board;
    if (boardType.equals(SnakesAndLaddersBoard.class.getName())) {
    // Check if dimensions are saved in the config
      if (config.has("boardRows") && config.has("boardColumns")) {
        int rows = config.get("boardRows").getAsInt();
        int columns = config.get("boardColumns").getAsInt();

        // Find the matching board based on dimensions
        if (rows == 7 && columns == 8) {
          board = SnakesAndLaddersBoardFactory.createSmallBoard();
          System.out.println("Loaded small Snakes and Ladders board");
        } else if (rows == 10 && columns == 10) {
          board = SnakesAndLaddersBoardFactory.createBigBoard();
          System.out.println("Loaded big Snakes and Ladders board");
        } else {
          board = SnakesAndLaddersBoardFactory.createStandardBoard();
          System.out.println("Loaded standard Snakes and Ladders board");
        }
      } else {
        board = SnakesAndLaddersBoardFactory.createStandardBoard();
        System.out.println("Loaded default Snakes and Ladders board");
      }
    } else if (boardType.equals(ThievesAndRobbersBoard.class.getName())) {
      if (config.has("boardWidth") && config.has("boardHeight")) {
        int width = config.get("boardWidth").getAsInt();
        int height = config.get("boardHeight").getAsInt();
        if (width == 8 && height == 6) {
          board = ThievesAndRobbersBoardFactory.createSmallBoard();
          System.out.println("Loaded small Thieves and Robbers board");
        } else if (width == 8 && height == 8) {
          board = ThievesAndRobbersBoardFactory.createStandardBoard();
          System.out.println("Loaded standard Thieves and Robbers board");
        } else if (width == 10 && height == 10) {
          board = ThievesAndRobbersBoardFactory.createBigBoard();
          System.out.println("Loaded big Thieves and Robbers board");
        } else {
          board = ThievesAndRobbersBoardFactory.createStandardBoard();
          System.out.println("Loaded default Thieves and Robbers board");
        }
      } else {
        board = ThievesAndRobbersBoardFactory.createStandardBoard();
        System.out.println("Loaded default Thieves and Robbers board");
      }

      if (config.has("tileMoney")) {
        JsonArray tileMoneyArray = config.getAsJsonArray("tileMoney");

        if (tileMoneyArray.size() == board.getTiles().size()) {
          for (int i = 0; i < tileMoneyArray.size(); i++) {
            Tile tile = board.getTile(i);
            if (tile != null) tile.setTileAction(new MoneyAction(tileMoneyArray.get(i).getAsInt()));
          }
        } else {
        System.err.println("No tile money information found in the config");
        }
      } 
    } else {
      throw new ConfigurationException("Unknown board type: " + boardType);
    }

    if (config.has("tiles")) {
      JsonArray tilesArray = config.getAsJsonArray("tiles");

      for (JsonElement element : tilesArray) {
        JsonObject tileObj = element.getAsJsonObject();
        int tileId = tileObj.get("id").getAsInt();
        Tile tile = board.getTile(tileId);

        if (tile != null && tileObj.has("action")) {
          JsonObject actionObj = tileObj.getAsJsonObject("action");
          String actionType = actionObj.get("type").getAsString();

          if ("ladder".equals(actionType)) {
            int destinationTileId = actionObj.get("destinationTileId").getAsInt();
            TileAction action = new LadderAction(destinationTileId);
            tile.setTileAction(action);
          } else {
            // Handle other action types if needed
          }
        }
      }
    }

    // Load players & pieces
    List<Player> players = new ArrayList<>();
    if (config.has("players")) {
      JsonArray playersArray = config.getAsJsonArray("players");

      for (int i = 0; i < playersArray.size(); i++) {
        JsonObject playerObj = playersArray.get(i).getAsJsonObject();
        String playerName = playerObj.get("name").getAsString();

        List<Piece> pieces = new ArrayList<>();
        Player player = new Player(playerName, pieces);

        if (playerObj.has("pieces")) {
          // Load pieces for the player
          JsonArray piecesArray = playerObj.getAsJsonArray("pieces");
          for (int j = 0; j < piecesArray.size(); j++) {
            JsonObject pieceObj = piecesArray.get(j).getAsJsonObject();
            int tileId = pieceObj.get("tileId").getAsInt();

            Tile tile = board.getTile(tileId);

            MovementStrategy strategy = new LinearMovementStrategy();
            Piece piece = new Piece(tile, player, strategy);
            pieces.add(piece);
          }
        }
        players.add(player);
      }
    }
    return new GameConfig(players, board, currentPlayerIndex);
  }

  /**
   * Loads the player list from a file.
   *
   * @param filePath the path to the file
   * @return the loaded player list
   * @throws ReadException if an I/O error occurs
   * @throws InvalidInputException if the file path is invalid
   */
  public List<Player> loadPlayerList(String filePath) throws ReadException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new InvalidInputException("Bad file path");
    }

    try {
     List<String[]> playerData = CsvUtil.readCsv(filePath);
     List<Player> loadedPlayers = new ArrayList<>();
     
     for (String[] data : playerData) {
       if (data.length > 0) {
         String playerName = data[0];
         loadedPlayers.add(new Player(playerName, new ArrayList<>()));
        }
      }
      return loadedPlayers;
    } catch (IOException e) {
      throw new ReadException("File not found: " + filePath, e);
    }
  }

  /**
   * Gets the destination tile ID for a given action.
   *
   * @param action the tile action
   * @return the destination tile ID
   */
  protected int getActionDestinationTileId(TileAction action) {
    if (!ArgumentValidator.isValidObject(action)) {
      throw new InvalidInputException("Invalid action");
    }

    if (action instanceof LadderAction) {
      return ((LadderAction) action).getDestinationTileId();
    }
    return -1; // or handle other action types
  }

  /**
   * Validates the game configuration.
   *
   * @param players a list of players
   * @param board the game board
   * @param currentPlayerIndex the index of the current player
   * @return true if the game configuration is valid, false otherwise
   */
  public boolean isValidGameConfig(
    List<Player> players,
    Board board,
    int currentPlayerIndex
  ) {
    if (!ArgumentValidator.isValidList(players)) {
      return false;
    }
    if (!ArgumentValidator.isValidObject(board)) {
      return false;
    }
    if (!ArgumentValidator.isValidIndex(currentPlayerIndex)) {
      return false;
    }
    return true;
  }
}
