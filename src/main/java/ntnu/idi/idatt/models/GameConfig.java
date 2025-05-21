package ntnu.idi.idatt.models;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ntnu.idi.idatt.games.ludo.LudoBoard;
import ntnu.idi.idatt.games.ludo.LudoBoardFactory;
import ntnu.idi.idatt.games.snakesandladders.LadderAction;
import ntnu.idi.idatt.games.snakesandladders.LinearMovementStrategy;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoard;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.utility.ArgumentValidator;
import ntnu.idi.idatt.utility.FileUtil;
import ntnu.idi.idatt.utility.JsonUtil;

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
   * @throws IllegalArgumentException if the game configuration is invalid
   */
  public GameConfig(
          List<Player> players,
          Board board,
          int currentPlayerIndex
  ) {
    if (!isValidGameConfig(players, board, currentPlayerIndex)) {
      throw new IllegalArgumentException("Invalid game configuration");
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
   * @throws IOException if an I/O error occurs
   */
  public void saveConfig(String filePath) throws IOException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new IllegalArgumentException("Invalid file path");
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
        piecesArray.add(pieceObj);
      }
      playerObj.add("pieces", piecesArray);

      playersArray.add(playerObj);
    }

    config.add("players", playersArray);

    // finally write to file
    FileUtil.writeString(filePath, config.toString());
    System.out.println("Game configuration saved to " + filePath);
  }

  /**
   * Saves the player list to a file.
   *
   * @param filePath the path to the file
   * @throws IOException if an I/O error occurs
   */
  public void savePlayerList(String filePath) throws IOException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new IllegalArgumentException("Invalid file path");
    }
    List<String> playerNames = new ArrayList<>();
    for (Player player : players) {
      playerNames.add(player.getName());
    }

    JsonUtil.writeToFile(filePath, playerNames);
    System.out.println("Player list saved to: " + filePath);
  }

  /**
   * Loads the game configuration from a file.
   *
   * @param filePath the path to the file
   * @return the loaded game configuration
   * @throws IOException if an I/O error occurs
   */
  public GameConfig loadConfig(String filePath) throws IOException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new IllegalArgumentException("Invalid file path");
    }
    String json = FileUtil.readString(filePath);
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
    } else if (boardType.equals(LudoBoard.class.getName())) {
      board = LudoBoardFactory.createLudoBoard();
      // TODO add ludo specific code here
      // homes etc need to be saved in the config
      System.out.println("Loaded Ludo board");
    } else {
      throw new IllegalArgumentException("Unknown board type: " + boardType);
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
   * @throws IOException if an I/O error occurs
   */
  public List<Player> loadPlayerList(String filePath) throws IOException {
    if (!ArgumentValidator.isValidFilePath(filePath)) {
      throw new IllegalArgumentException("Bad file path");
    }

    Type listType = JsonUtil.getListType(String.class);
    List<String> playerNames = JsonUtil.readFromFile(filePath, listType);

    // Convert names to Player objects with empty piece lists
    List<Player> players = new ArrayList<>();
    for (String name : playerNames) {
      players.add(new Player(name, new ArrayList<>()));
    }

    return players;
  }

  /**
   * Gets the destination tile ID for a given action.
   *
   * @param action the tile action
   * @return the destination tile ID
   */
  protected int getActionDestinationTileId(TileAction action) {
    if (!ArgumentValidator.isValidObject(action)) {
      throw new IllegalArgumentException("Invalid action");
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
