package ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ntnu.idi.idatt.controllers.BoardGame;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.Player;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to the Ladder game!");

    Board board = new Board();
    Dice dice = new Dice(1);
    List<Player> players = new ArrayList<>();

    BoardGame game = new BoardGame(board, players, dice);

    game.createBoard();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter number of players: (2 til 4) ");
    int numberOfPlayers = scanner.nextInt();

    scanner.nextLine();

    for (int i = 1; i <= numberOfPlayers; i++) {
      System.out.println("Enter name of player " + i + ": ");
      String name = scanner.nextLine();
      players.add(new Player(name, game));
    }

    game.play();
}
}