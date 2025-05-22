package ntnu.idi.idatt.games.thievesandrobbers;

import ntnu.idi.idatt.models.MovementStrategy;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class ThievesAndRobbersPiece extends Piece {

  private int money;

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
