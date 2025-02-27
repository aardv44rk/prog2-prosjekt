package ntnu.idi.idatt.controllers;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Player;
import java.util.List;

public class BoardGame{

  private Board board;

  private Player currentPlayer;
  
  private List<Player> players;

  private Dice dice;

  public BoardGame(Board board, List<Player> players, Dice dice){
    this.board = board;
    this.players = players;
    this.dice = dice;
  }

  public void play() {

  }

  public void addPlayer(Player player) {
    players.add(player);
  }

  public void createBoard() {

  }

  public void createDice() {

  }

  public Player getWinner() {
    return null;
  }
}
