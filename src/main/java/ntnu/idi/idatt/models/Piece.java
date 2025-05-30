package ntnu.idi.idatt.models;

import ntnu.idi.idatt.exceptions.InvalidInputException;
import ntnu.idi.idatt.utility.ArgumentValidator;

/**
 * Represents a movable piece controlled by a player. A piece has a current tile position, an owner,
 * and a movement strategy.
 */
public class Piece {

  private Tile currentTile;
  private final Player owner;
  private MovementStrategy movementStrategy;

  /**
   * Constructs a new Piece.
   *
   * @param currentTile      The tile the piece is currently on (can be null at start).
   * @param owner            The player who owns this piece.
   * @param movementStrategy The movement strategy used by this piece.
   */
  public Piece(Tile currentTile, Player owner, MovementStrategy movementStrategy) {
    if (!isValidPiece(currentTile, owner, movementStrategy)) {
      throw new InvalidInputException("Invalid piece parameters");
    }
    this.currentTile = currentTile;
    this.owner = owner;
    this.movementStrategy = movementStrategy;
  }

  /**
   * Returns the tile this piece is currently on.
   *
   * @return The current tile.
   */
  public Tile getCurrentTile() {
    return currentTile;
  }

  /**
   * Returns the player who owns this piece.
   *
   * @return The owning player.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * Returns the movement strategy used by this piece.
   *
   * @return The movement strategy.
   */
  public MovementStrategy getMovementStrategy() {
    return movementStrategy;
  }

  /**
   * Sets the tile this piece is currently on.
   *
   * @param currentTile The new tile.
   */
  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  /**
   * Sets the movement strategy for this piece.
   *
   * @param movementStrategy The movement strategy to use.
   */
  public void setMovementStrategy(MovementStrategy movementStrategy) {
    this.movementStrategy = movementStrategy;
  }

  /**
   * Moves the piece a given number of steps using its movement strategy.
   *
   * @param steps The number of steps to move.
   * @param board The board on which the piece is moving.
   */
  public void move(int steps, Board board) {
    if (movementStrategy != null) {
      movementStrategy.move(this, steps, board);
    } else {
      throw new IllegalStateException("MovementStrategy not set for piece.");
    }
  }

  /**
   * Validates the parameters for creating a piece.
   *
   * @param currentTile      The tile the piece is currently on.
   * @param owner            The player who owns this piece. 
   * @param movementStrategy The movement strategy used by this piece.
   * @return True if valid, false otherwise.
   */
  public boolean isValidPiece(Tile currentTile, Player owner, MovementStrategy movementStrategy) {
    return ArgumentValidator.isValidObject(currentTile)
        && ArgumentValidator.isValidObject(owner)
        && ArgumentValidator.isValidObject(movementStrategy);
  }
}
