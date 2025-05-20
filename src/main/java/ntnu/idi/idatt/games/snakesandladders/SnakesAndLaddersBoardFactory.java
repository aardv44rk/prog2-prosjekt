package ntnu.idi.idatt.games.snakesandladders;

import java.util.List;
import ntnu.idi.idatt.models.Board;

/**
 * Factory class to create a Snakes and Ladders board.
 */
public class SnakesAndLaddersBoardFactory {

  public static Board createSmallBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(1, 13),
        new SnakesAndLaddersLadder(4, 25),
        new SnakesAndLaddersLadder(15, 29),
        new SnakesAndLaddersLadder(24, 37),
        new SnakesAndLaddersLadder(32, 44),

        new SnakesAndLaddersLadder(20, 9),
        new SnakesAndLaddersLadder(43, 18),
        new SnakesAndLaddersLadder(48, 34)
    );
    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(7, 8, ladders);

    for (SnakesAndLaddersLadder ladder : ladders) {
      board.getTile(ladder.startTileId()).setTileAction(new LadderAction(ladder.endTileId()));
    }
    return board;
  }

  public static Board createStandardBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(5, 15),
        new SnakesAndLaddersLadder(11, 31),
        new SnakesAndLaddersLadder(23, 55),
        new SnakesAndLaddersLadder(33, 65),
        new SnakesAndLaddersLadder(50, 69),
        new SnakesAndLaddersLadder(61, 76),

        new SnakesAndLaddersLadder(28, 6),
        new SnakesAndLaddersLadder(41, 22),
        new SnakesAndLaddersLadder(52, 27),
        new SnakesAndLaddersLadder(62, 13),
        new SnakesAndLaddersLadder(72, 51),
        new SnakesAndLaddersLadder(83, 75)
    );

    SnakesAndLaddersBoard board = new SnakesAndLaddersBoard(9, 10, ladders);

    for (SnakesAndLaddersLadder ladder : ladders) {
      board.getTile(ladder.startTileId()).setTileAction(new LadderAction(ladder.endTileId()));
    }
    return board;
  }

  public static Board createBigBoard() {
    List<SnakesAndLaddersLadder> ladders = List.of(
        new SnakesAndLaddersLadder(4, 22),
        new SnakesAndLaddersLadder(12, 46),
        new SnakesAndLaddersLadder(24, 54),
        new SnakesAndLaddersLadder(41, 55),
        new SnakesAndLaddersLadder(48, 73),
        new SnakesAndLaddersLadder(68, 89),

        new SnakesAndLaddersLadder(28, 8),
        new SnakesAndLaddersLadder(44, 26),
        new SnakesAndLaddersLadder(60, 20),
        new SnakesAndLaddersLadder(76, 64),
        new SnakesAndLaddersLadder(81, 57),
        new SnakesAndLaddersLadder(92, 66)
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
