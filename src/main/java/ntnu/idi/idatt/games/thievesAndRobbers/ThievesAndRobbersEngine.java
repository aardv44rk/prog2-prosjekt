package ntnu.idi.idatt.games.thievesAndRobbers;

import java.util.List;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.SimpleGameEngine;

public class ThievesAndRobbersEngine extends SimpleGameEngine {

  public ThievesAndRobbersEngine(List<Player> players, Board board,
      int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex, dice);

  }

  @Override
  public void initPieces() {
    for (Player p : players) {
      p.getPieces().clear();
      p.getPieces()
          .add(new ThievesAndRobbersPiece(board.getTile(0), p, new CircularMovementStrategy(), 0));
    }
  }

  @Override
  public Player checkWinCondition() {
    for (Player p : players) {
      if (p.getPieces().stream()
          .mapToInt(piece -> ((ThievesAndRobbersPiece) piece).getMoney())
          .sum() >= 1000) {
        return p;
      }
    }
    return null;
  }
}
