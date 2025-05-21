package ntnu.idi.idatt.games.thievesAndRobbers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.TileAction;

public class MoneyAction implements TileAction {

  private final int money;

  public MoneyAction(int money) {
    this.money = money;
  }

  @Override
  public void perform(Piece piece, Board board) {
    ((ThievesAndRobbersPiece) piece).addMoney(money);
  }

  public int getMoney() {
    return money;
  }
}
