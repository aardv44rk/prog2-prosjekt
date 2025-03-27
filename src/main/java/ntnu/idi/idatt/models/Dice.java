package ntnu.idi.idatt.models;

import java.util.ArrayList;
import java.util.List;

public class Dice {
  private List<Die> dice;

  public Dice(int numberOfDice) {
    dice = new ArrayList<>();
    for (int i = 0; i < numberOfDice; i++) {
      dice.add(new Die());
    }
  }

  public void roll() {
    for (Die die : dice) {
      die.roll();
    }
  }

  public int getTotal() {
    int total = 0;
    for (Die die : dice) {
      total += die.getValue();
    }
    return total;
  }
}
