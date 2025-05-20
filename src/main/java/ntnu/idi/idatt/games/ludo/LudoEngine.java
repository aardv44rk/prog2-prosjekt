package ntnu.idi.idatt.games.ludo;

import java.util.List;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.GameEngine;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

public class LudoEngine extends GameEngine {

  private final Dice dice;

  public LudoEngine(List<Player> players, Board board, int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex);
    this.dice = dice;
  }

  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();

      for (int i = 0; i < 4; i++) {
        Piece piece = new Piece(null, p, new LudoMovementStrategy());
        p.getPieces().add(piece);
      }
    }
  }

  @Override
  public Player checkWinCondition() {
    return players.stream()
        .filter(p -> ((LudoBoard) board).getGoalTile(p).getPieces().size() == 4)
        .findFirst().orElse(null);
  }

  public LudoTurnOptions startTurn() {
    dice.roll();
    int roll = dice.getValue();

    return new LudoTurnOptions(roll, movablePieces(roll));
  }

  public List<Piece> movablePieces(int steps) {
    return getCurrentPlayer().getPieces().stream()
        .filter(p -> ((LudoBoard) board).getNextTile(p, steps) != null)
        .toList();
  }

  public void movePiece(Piece piece, int steps) {
    piece.move(steps, board);
  }
}
