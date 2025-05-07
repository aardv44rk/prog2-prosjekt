package ntnu.idi.idatt.models;

import java.util.List;

public class Dice {

  List<Die> dice;

  public Dice(List<Die> dice) {
    this.dice = dice;
  }

  public List<Die> getDice() {
    return dice;
  }

  public void roll() {
    for (Die die : dice) {
      die.roll();
    }
  }

  public int getValue() {
    return dice.stream().mapToInt(Die::getValue).sum();
  }
}
