package ntnu.idi.idatt.games.snakesandladders;

/**
 * Represents a ladder in the Snakes and Ladders game.
 * A ladder connects two tiles on the board.
 */
public record SnakesAndLaddersLadder(int startTileId, int endTileId) {

  public boolean isAscending() {
    return endTileId > startTileId;
  }
}
