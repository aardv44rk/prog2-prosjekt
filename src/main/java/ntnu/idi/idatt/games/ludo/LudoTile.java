package ntnu.idi.idatt.games.ludo;

import java.util.ArrayList;
import java.util.List;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class LudoTile extends Tile {

  private final List<Piece> pieces = new ArrayList<>();

  public LudoTile(int tileId) {
    super(tileId);
  }

  public List<Piece> getPieces() {
    return pieces;
  }

  public void addPiece(Piece piece) {
    pieces.add(piece);
  }

  public void removePiece(Piece piece) {
    pieces.remove(piece);
  }

  public boolean hasEnemyBlock(Player player) {
    return pieces.size() > 1 && !pieces.getFirst().getOwner().equals(player);
  }
}
