package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;

/**
 * Factory class to create a Snakes and Ladders board.
 */

public class SnakesAndLaddersBoardFactory {

  public static Board createSmallBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(2, 14),
        new SnakesAndLaddersLadder(5, 26),
        new SnakesAndLaddersLadder(10, 21),
        new SnakesAndLaddersLadder(16, 30),
        new SnakesAndLaddersLadder(25, 38),
        new SnakesAndLaddersLadder(33, 45),
        new SnakesAndLaddersLadder(44, 19),
        new SnakesAndLaddersLadder(49, 35)
    );
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(7, 8, ladders);

    for (SnakesAndLaddersLadder ladder : ladders) {
      board.getTile(ladder.startTileId()).setTileAction(new LadderAction(ladder.endTileId()));
    }
    return board;
  }

  public static Board createStandardBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(4, 18),
        new SnakesAndLaddersLadder(22, 47),
        new SnakesAndLaddersLadder(41, 60),
        new SnakesAndLaddersLadder(45, 66),
        new SnakesAndLaddersLadder(63, 78),
        new SnakesAndLaddersLadder(20, 6),
        new SnakesAndLaddersLadder(36, 17),
        new SnakesAndLaddersLadder(55, 35),
        new SnakesAndLaddersLadder(65, 52),
        new SnakesAndLaddersLadder(72, 49)
    );
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(9, 10, ladders);

    for (SnakesAndLaddersLadder ladder : ladders) {
      board.getTile(ladder.startTileId()).setTileAction(new LadderAction(ladder.endTileId()));
    }
    return board;
  }

  public static Board createBigBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(4, 40),
        new SnakesAndLaddersLadder(12, 72),
        new SnakesAndLaddersLadder(28, 8),
        new SnakesAndLaddersLadder(48, 93),
        new SnakesAndLaddersLadder(60, 20),
        new SnakesAndLaddersLadder(68, 98),
        new SnakesAndLaddersLadder(99, 57)
    );
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(10, 10, ladders);

    for (SnakesAndLaddersLadder ladder : ladders) {
      board.getTile(ladder.startTileId()).setTileAction(new LadderAction(ladder.endTileId()));
    }
    return board;
  }

  public static List<Board> getBoards() {
    return List.of(createSmallBoard(), createStandardBoard(), createBigBoard());
  }
}
