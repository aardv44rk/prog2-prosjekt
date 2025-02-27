package ntnu.idi.idatt.models;

public class Tile {
  private Tile nextTile;
  private int tileId;
  private TileAction landAction;

  public Tile(int tileId) {
    this.tileId = tileId;
  }

  public void landPlayer(Player player) {
    if (landAction != null) {
      landAction.perform(player);
    }
  }

  public void leavePlayer(Player player) {
    // Implement
  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

}
