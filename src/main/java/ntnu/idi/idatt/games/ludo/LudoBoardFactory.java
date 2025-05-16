package ntnu.idi.idatt.games.ludo;

import java.util.List;

import ntnu.idi.idatt.models.Board;

public class LudoBoardFactory {
  
  public static LudoBoard createLudoBoard() {
    LudoBoard board = new LudoBoard();
    return board;
  }

  public static List<Board> getBoards() {
    return List.of(createLudoBoard());
  }

  // potentially add more board types in the futures
}
