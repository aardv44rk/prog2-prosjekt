package ntnu.idi.idatt.core;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ntnu.idi.idatt.games.snakesandladders.LinearMovementStrategy;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoard;
import ntnu.idi.idatt.utility.FileUtil;
import ntnu.idi.idatt.utility.JsonUtil;

public class GameConfig {

  private final List<Player> players;
  private final Board board;
  private final int currentPlayerIndex;

  public GameConfig(List<Player> players, Board board, int currentPlayerIndex) {
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

  public void saveConfig(String filePath) throws IOException {
    JsonObject config = new JsonObject();

    config.addProperty("currentPlayerIndex", currentPlayerIndex);

    config.addProperty("boardType", board.getClass().getName());

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

    FileUtil.writeString(filePath, config.toString());
    System.out.println("Game configuration saved to " + filePath);    
  }

  public void savePlayerList(String filePath) throws IOException {
    List<String> playerNames = new ArrayList<>();
    for (Player player : players) {
      playerNames.add(player.getName());
    }
      
    JsonUtil.writeToFile(filePath, playerNames);
    System.out.println("Player list saved to: " + filePath);
  }

  public GameConfig loadConfig(String filePath) throws IOException {
    String json = FileUtil.readString(filePath);
    JsonObject config = JsonParser.parseString(json).getAsJsonObject();

    int currentPlayerIndex = config.get("currentPlayerIndex").getAsInt();

    String boardType = config.get("boardType").getAsString();
    Board board;

    if (boardType.endsWith("SnakesAndLaddersBoard")) {
      board = new SnakesAndLaddersBoard();
    } else {
      // Handle other board types here
      board = new SnakesAndLaddersBoard(); // Default to SnakesAndLaddersBoard
    }

    List<Player> players = new ArrayList<>();
    JsonArray playersArray = config.getAsJsonArray("players");

    for (int i = 0; i < playersArray.size(); i++) {
      JsonObject playerObj = playersArray.get(i).getAsJsonObject();
      String playerName = playerObj.get("name").getAsString();


      List<Piece> pieces = new ArrayList<>();
      Player player = new Player(playerName, pieces);

      JsonArray piecesArray = playerObj.getAsJsonArray("pieces");

      for (int j = 0; j < piecesArray.size(); j++) {
        JsonObject pieceObj = piecesArray.get(j).getAsJsonObject();
        int tileId = pieceObj.get("tileId").getAsInt();

        Tile tile = board.getTile(tileId);

        MovementStrategy strategy = new LinearMovementStrategy();
        Piece piece = new Piece(tile, player, strategy);
        pieces.add(piece);
      }
      players.add(player);
    }    
    return new GameConfig(players, board, currentPlayerIndex);
  }

  public List<Player> loadPlayerList(String filePath) throws IOException {
    Type listType = JsonUtil.getListType(String.class);
    List<String> playerNames = JsonUtil.readFromFile(filePath, listType);
    
    // Convert names to Player objects with empty piece lists
    List<Player> players = new ArrayList<>();
    for (String name : playerNames) {
      players.add(new Player(name, new ArrayList<>()));
    }
    
    return players;
  }
}
