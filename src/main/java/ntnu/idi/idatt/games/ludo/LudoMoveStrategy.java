package ntnu.idi.idatt.games.ludo;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class LudoMoveStrategy implements MovementStrategy {

  @Override
  public void move(Piece piece, int steps, Board board) {
    LudoBoard ludoBoard = (LudoBoard) board;
    Tile nextTile = ludoBoard.getNextTile(piece, steps);
    
    // Handle potential knockouts - if another player's piece is on this tile
    // and it's not a safe tile, knock it out
    if (!ludoBoard.getSafeTiles().containsValue(nextTile)) {
      for (Player otherPlayer : ludoBoard.getPlayers()) {
        if (otherPlayer != piece.getOwner()) {
          for (Piece otherPiece : otherPlayer.getPieces()) {
            if (otherPiece.getCurrentTile().equals(nextTile)) {
              // Knock out this piece
              int playerIndex = ludoBoard.getPlayerIndex(otherPlayer);
              PlayerHome home = ludoBoard.getPlayerHomes().get(playerIndex);
              
              // Find first available home position
              for (int i = 1; i <= 4; i++) {
                if (!home.isTileOccupied(i)) {
                  home.placePiece(otherPiece, i);
                  break;
                }
              }
            }
          }
        }
      }
    }

    // Move the piece
    piece.setCurrentTile(nextTile);
  }
}
