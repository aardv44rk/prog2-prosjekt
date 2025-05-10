package ntnu.idi.idatt.games.snakesandladders;

public record SnakesAndLaddersLadder(int startTileId, int endTileId) {

  public boolean isAscending() {
    return endTileId > startTileId;
  }
}
