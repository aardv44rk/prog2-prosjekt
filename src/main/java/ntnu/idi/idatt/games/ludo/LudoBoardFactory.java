package ntnu.idi.idatt.games.ludo;

import java.util.List;

import ntnu.idi.idatt.models.Board;

public class LudoBoardFactory {

  public static LudoBoard createLudoBoard() {
    return new LudoBoard(List.of());
  }

  public static List<Board> getBoards() {
    return List.of(createLudoBoard());
  }
}
