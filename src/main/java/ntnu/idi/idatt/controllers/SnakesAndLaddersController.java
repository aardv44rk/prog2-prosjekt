package ntnu.idi.idatt.controller;

import ntnu.idi.idatt.core.*;
import ntnu.idi.idatt.games.snakesandladders.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Snakes and Ladders game. Manages game setup and turn progression.
 */
public class SnakesAndLaddersController {

  private SnakesAndLaddersEngine engine;

  /**
   * Sets up and starts a new Snakes and Ladders game.
   *
   * @param playerNames Names of the players.
   */
  public void startNewGame(List<String> playerNames) {
    // Create board
    Board board = SnakesAndLaddersBoardFactory.createStandardBoard();

    // Create players
    List<Player> players = new ArrayList<>();

    for (String name : playerNames) {
      List<Piece> pieces = new ArrayList<>();
      Player player = new Player(name, pieces);

      Piece piece = new Piece(board.getTile(0), player, new LinearMovementStrategy());
      pieces.add(piece);

      players.add(player);
    }

    // Create engine
    engine = new SnakesAndLaddersEngine(players, board, 0);
  }

  /**
   * Plays the game as CLI.
   */
  public void playCLI() {
    if (engine == null) {
      System.out.println("Game not started!");
      return;
    }

    engine.startGame();
  }

  /**
   * Returns current game engine.
   */
  public SnakesAndLaddersEngine getEngine() {
    return engine;
  }
}
