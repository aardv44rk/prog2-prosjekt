package ntnu.idi.idatt.models;

import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents a single die in a board game. Responsible for rolling the die and providing its value.
 */
public class Die {

  private final int sides;
  private int value;


  /**
   * Constructor for the Die class.
   * 
   * @param sides the number of sides on the die
   * @throws IllegalArgumentException if sides is not a positive integer
   */
  public Die(int sides) {
    if (!isValidDie(sides)) { 
      throw new IllegalArgumentException("Number of sides must be a positive integer");
    }
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

  /**
   * Validates if the input is a valid die.
   * 
   * @param sides the number of sides on the die
   * @return true if the input is a valid die, false otherwise
   */
  protected boolean isValidDie(int sides) {
    return ArgumentValidator.isValidInteger(sides - 1); // take sides - 1 as 0 is invalid for die
  }
}
