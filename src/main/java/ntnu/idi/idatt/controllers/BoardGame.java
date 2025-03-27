package ntnu.idi.idatt.controllers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

import java.util.List;
import java.util.Scanner;

public class BoardGame {

  private Board board;
  private Player currentPlayer;
  private List<Player> players;
  private Dice dice;
  private final int BOARD_SIZE = 100;
  private final int WIN_POSITION = 99;
  private boolean gameOver = false;

  public BoardGame(Board board, List<Player> players, Dice dice){
    this.board = board;
    this.players = players;
    this.dice = dice;
  }

  public void play() {
    for (Player player : players) {
      player.placeOnTile(board.getTile(0));
    }

    int playerIndex = 0;
    Scanner scanner = new Scanner(System.in);

    while (!gameOver) {
      currentPlayer = players.get(playerIndex);
      System.out.println(currentPlayer.getName() + "'s turn. Press enter to roll the dice.");
      scanner.nextLine();

      dice.roll();
      int steps = dice.getTotal();
      System.out.println(currentPlayer.getName() + " rolled " + steps);

      if (currentPlayer.getCurrentPosition() + steps <= WIN_POSITION) {
        currentPlayer.move(steps);
      } else {
        System.out.println(currentPlayer.getName() + " rolled too high and must wait for next turn.");
      }

      if (currentPlayer.getCurrentPosition() == WIN_POSITION) {
        System.out.println(currentPlayer.getName() + " won the game!");
        gameOver = true;
      }

      playerIndex = (playerIndex + 1) % players.size();
    }

    scanner.close();
  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void createBoard() {
    board = new Board();

    for (int i = 0; i < BOARD_SIZE; i++) {
      Tile tile = new Tile(i);
      board.addTile(tile);

      if (i > 1) {
        board.getTile(i-1).setNextTile(tile);
      }
    }

    // ladder logic (randomized?)
  }

  public void createDice() {
    dice = new Dice(1);
  }

  public Player getWinner() {
    for (Player player : players) {
      if (player.getCurrentPosition() == WIN_POSITION) {
        return player;
      }
    }
    return null;
  }

  public Board getBoard() {
    return board;
  }
}
