package ntnu.idi.idatt.models;

import java.util.List;

import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents a collection of dice in a board game. Responsible for rolling the dice and
 * calculating their total value.
 */
public class Dice {

  List<Die> dice;

  /**
   * Constructor for the Dice class.
   *
   * @param dice List of Die objects to be used in the Dice.
   * @throws IllegalArgumentException if the dice list is null or empty.
   */
  public Dice(List<Die> dice) throws IllegalArgumentException {
    if (!ArgumentValidator.isValidObject(dice)) {
      throw new IllegalArgumentException("Dice list cannot be null or empty");
    }
    this.dice = dice;
  }

  public List<Die> getDice() {
    return dice;
  }

  /**
   * Rolls all the dice in the list of dice.
   */
  public void roll() {
    for (Die die : dice) {
      die.roll();
    }
  }

  /**
   * Returns the total value of all the dice in list of dice.
   *
   * @return the total value of all the dice as integer
   */
  public int getValue() {
    return dice.stream().mapToInt(Die::getValue).sum();
  }

  /**
   * Returns the values of all the dice in the list of dice.
   *
   * @return an array of integers representing the values of all the dice
   */
  public int[] getValues() {
    return dice.stream().mapToInt(Die::getValue).toArray();
  }
}
