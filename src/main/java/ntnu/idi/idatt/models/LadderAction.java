package ntnu.idi.idatt.models;

public class LadderAction implements TileAction {
  private int destionationTileId;

  public LadderAction(int destinationTileId, String description) {
    this.destionationTileId = destinationTileId;
  }
  
  @Override
  public void perform(Player player) {
  }
}
