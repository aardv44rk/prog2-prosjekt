package ntnu.idi.idatt.models;

public class Die {

  private final int sides;
  private int value;

  public Die(int sides) {
    this.sides = sides;
    this.value = 0;
  }

  public void roll() {
    value = (int) (Math.random() * sides) + 1;
  }

  public int getSides() {
    return sides;
  }

  public int getValue() {
    return value;
  }
}
