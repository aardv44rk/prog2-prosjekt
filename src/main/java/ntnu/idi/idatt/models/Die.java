package ntnu.idi.idatt.models;

import java.util.Random;

public class Die {
  private int lastRolledValue;
  private final Random random = new Random();
  private final int SIDES = 6;

  public int roll() {
    lastRolledValue = random.nextInt(SIDES) + 1;
    return lastRolledValue;
  }

  public int getValue() {
    return lastRolledValue;
  }
}