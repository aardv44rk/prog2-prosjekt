package ntnu.idi.idatt.models;

import java.util.List;

import ntnu.idi.idatt.exceptions.InvalidInputException;
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
   * @throws InvalidInputException if the dice list is null or empty.
   */
  public Dice(List<Die> dice) throws InvalidInputException {
    if (!isValidDice(dice)) {
      throw new InvalidInputException("Dice list cannot be null or empty");
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

  /**
   * Validates dice input.
   * 
   * @param dice the list of dice to validate
   * @return true if the input is a valid list of dice, false otherwise
   */
  public boolean isValidDice(List<Die> dice) {
    return ArgumentValidator.isValidList(dice);
  }
}
