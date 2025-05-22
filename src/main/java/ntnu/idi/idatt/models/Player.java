package ntnu.idi.idatt.models;

import java.util.List;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents a player in a board game. Responsible for storing the player's name and pieces.
 */
public class Player {

  private final String name;
  private List<Piece> pieces;

  /**
   * Constructor for the Player class.
   *
   * @param name   The name of the player.
   * @param pieces The pieces of the player.
   * @throws InvalidInputException if the name is null or blank, or if pieces is null or empty.
   */
  public Player(String name, List<Piece> pieces) {
    if (!isValidPlayer(name, pieces)) {
      throw new InvalidInputException("Invalid player parameters");
    }
    this.name = name;
    this.pieces = pieces;
  }

  public String getName() {
    return name;
  }

  public List<Piece> getPieces() {
    return pieces;
  }

  /**
   * Validates if the input is a valid player.
   *
   * @param name   The name of the player.
   * @param pieces The pieces of the player.
   * @return       true if the input is a valid player, false otherwise.
   */
  public boolean isValidPlayer(String name, List<Piece> pieces) {
    return ArgumentValidator.isValidString(name) && ArgumentValidator.isValidObject(pieces);
  }
}