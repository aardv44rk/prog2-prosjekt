package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.TileAction;

/**
 * Represents an action that adds money to a piece in the Thieves and Robbers game.
 * This action is performed when a piece lands on a tile that grants money.
 */
public class MoneyAction implements TileAction {

  private final int money;

  /**
   * Constructor for the MoneyAction class.
   *
   * @param money The amount of money to be added to the piece.
   */
  public MoneyAction(int money) {
    this.money = money;
  }

  /**
   * Performs the money action on the specified piece and board.
   * Adds the specified amount of money to the piece.
   *
   * @param piece The piece to which the money action is applied.
   * @param board The game board.
   */
  @Override
  public void perform(Piece piece, Board board) {
    ((ThievesAndRobbersPiece) piece).addMoney(money);
  }

  public int getMoney() {
    return money;
  }
}
