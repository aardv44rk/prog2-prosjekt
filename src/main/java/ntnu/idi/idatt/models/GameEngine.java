package ntnu.idi.idatt.models;

import java.util.List;

/**
 * Represents the base game engine for board games. Handles players, board, and game flow. Concrete
 * games must implement specific behavior.
 */
public abstract class GameEngine {

  protected Board board;
  protected List<Player> players;
  protected int currentPlayerIndex;
  protected boolean gameOver;

  /**
   * Initializes the game engine with players and a board.
   *
   * @param players The list of players.
   * @param board   The board used in the game.
   */
  public GameEngine(List<Player> players, Board board, int currentPlayerIndex) {
    this.players = players;
    this.board = board;
    this.currentPlayerIndex = currentPlayerIndex;
    this.gameOver = false;
  }

  /**
   * Plays the game. Concrete implementation defines game loop.
   */
  public abstract void playGame();

  /**
   * Handles the current player's turn.
   */
  public abstract void handleTurn();

  /**
   * Checks if a player has won the game.
   *
   * @return The winning player, or null if no winner yet.
   */
  public abstract Player checkWinCondition();

  /**
   * Ends the game and performs any cleanup.
   */
  public void endGame() {
    this.gameOver = true;
  }

  /**
   * Returns the current player.
   *
   * @return The current player.
   */
  public Player getCurrentPlayer() {
    return players.get(currentPlayerIndex);
  }

  public Player getLastPlayer() {
    return (currentPlayerIndex == 0) ? players.getLast() : players.get(currentPlayerIndex - 1);
  }

  /**
   * Advances to the next player's turn.
   */
  public void nextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
  }

  /**
   * Returns the board used in the game.
   *
   * @return The game board.
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Returns the list of players in the game.
   *
   * @return The list of players.
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Checks whether the game is over.
   *
   * @return true if the game is over, false otherwise.
   */
  public boolean isGameOver() {
    return gameOver;
  }

}
