package ntnu.idi.idatt.core;

import java.util.List;

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

  public void saveConfig(String filePath) {
  }

  public void savePlayerList(String filePath) {
  }

  public GameConfig loadConfig(String filePath)  {
    return null;
  }

  public List<Player> loadPlayerList(String filePath) {
    return null;
  }
}
