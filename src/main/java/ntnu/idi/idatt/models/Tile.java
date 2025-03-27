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
    // Implement for game 2?
  }

  public void setNextTile(Tile nextTile) {
    this.nextTile = nextTile;
  }

  public Tile getNextTile() {
    return nextTile;
  }

  public int getTileId() {
    return tileId;
  }

  public void setLandAction(TileAction landAction) {
    this.landAction = landAction;
  }
}
