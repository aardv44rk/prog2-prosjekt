package ntnu.idi.idatt.games.ludo;

import java.util.List;
import ntnu.idi.idatt.models.Piece;

public class LudoTurnOptions {

  private final int roll;
  private final List<Piece> movable;
  private final boolean skip;
  private final boolean reRoll;

  public LudoTurnOptions(int roll, List<Piece> movable) {
    this.roll = roll;
    this.movable = movable;
    this.skip = movable.isEmpty();
    this.reRoll = roll == 6 && !skip;
  }

  public int getRoll() {
    return roll;
  }

  public List<Piece> getMovable() {
    return movable;
  }

  public boolean isSkip() {
    return skip;
  }

  public boolean isReRoll() {
    return reRoll;
  }

  @Override
  public String toString() {
    return "Roll: " + roll +
        ", Movable: " + movable.size() +
        ", Skip: " + skip +
        ", Re-roll: " + reRoll;
  }

}
