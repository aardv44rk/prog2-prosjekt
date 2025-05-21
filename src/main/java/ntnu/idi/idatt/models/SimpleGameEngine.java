package ntnu.idi.idatt.models;

import java.util.List;

public class SimpleGameEngine extends GameEngine {

  protected final Dice dice;

  public SimpleGameEngine(List<Player> players, Board board, int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex);

    this.dice = dice;
  }

  public void handleTurn() {
    Player player = getCurrentPlayer();
    Piece piece = player.getPieces().getFirst();

    dice.roll();
    int steps = dice.getValue();

    piece.move(steps, board);

    if (checkWinCondition() != null) {
      endGame();
    }
  }

  public void initPieces() {

  }

  @Override
  public Player checkWinCondition() {
    return null;
  }

  public Dice getDice() {
    return dice;
  }
}
