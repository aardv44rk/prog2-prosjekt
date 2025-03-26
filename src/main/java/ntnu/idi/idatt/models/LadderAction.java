package ntnu.idi.idatt.models;

public class LadderAction implements TileAction {
  private int destinationTileId;
  private String description;

  public LadderAction(int destinationTileId, String description) {
    this.destinationTileId = destinationTileId;
    this.description = description;
  }
  
  @Override
  public void perform(Player player) {
    System.out.println(description);

    Board board = player.getGame().getBoard();

    Tile destinationTile = board.getTile(destinationTileId);
    player.placeOnTile(destinationTile);

    System.out.println(player.getName() + " moved to tile " + destinationTileId);
  }
}
