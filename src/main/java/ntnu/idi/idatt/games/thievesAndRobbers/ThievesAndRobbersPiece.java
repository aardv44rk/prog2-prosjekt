package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

/**
 * Represents a piece in the Thieves and Robbers game.
 */
public class ThievesAndRobbersPiece extends Piece {

  private int money;

  /**
   * Constructor for the ThievesAndRobbersPiece class.
   *
   * @param currentTile      The tile where the piece is currently located.
   * @param owner            The player who owns the piece.
   * @param movementStrategy The movement strategy for the piece.
   * @param money            The amount of money the piece has.
   */
  public ThievesAndRobbersPiece(Tile currentTile, Player owner, MovementStrategy movementStrategy,
      int money) {
    super(currentTile, owner, movementStrategy);
    this.money = money;
  }

  public int getMoney() {
    return money;
  }

  public void addMoney(int amount) {
    money += amount;
  }
}
