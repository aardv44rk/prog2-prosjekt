package ntnu.idi.idatt;

import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersController;

import java.util.List;

public class SnLTest {

  public static void main(String[] args) {
    SnakesAndLaddersController controller = new SnakesAndLaddersController();

    controller.startNewGame(List.of("Player 1", "Player 2"));
    controller.playCLI();
  }
}
