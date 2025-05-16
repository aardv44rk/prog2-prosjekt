package ntnu.idi.idatt.games.ludo;

import java.util.List;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Dice;
import ntnu.idi.idatt.models.GameEngine;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class LudoEngine extends GameEngine {

  private final Dice dice;

  public LudoEngine(List<Player> players, Board board, int currentPlayerIndex, Dice dice) {
    super(players, board, currentPlayerIndex);
    this.dice = dice;
  }


  public void initPieces() {
    if (!(board instanceof LudoBoard)) {
      throw new IllegalArgumentException("Board must be an instance of LudoBoard");
    }

    LudoBoard ludoBoard = (LudoBoard) board;

    for (Player p : players) {
      p.getPieces().clear();

      for (int i = 0; i < 4; i++) {
        Piece piece = new Piece(null, p, new LudoMoveStrategy());
        p.getPieces().add(piece);
      }
    }

    ludoBoard.assignPlayers(players);
  }

  @Override
  public void handleTurn() {
    Player currentPlayer = players.get(currentPlayerIndex);

    dice.roll();
    int rolledNumber = dice.getValue();
    
    boolean extraTurn = rolledNumber == 6;

    boolean moved = false; // to track if a piece was moved, maybe not needed
    List<Piece> pieces = currentPlayer.getPieces();
    for (Piece piece : pieces) {
      LudoBoard ludoBoard = (LudoBoard) board;
      Tile nextTile = ludoBoard.getNextTile(piece, rolledNumber);
      
      piece.setCurrentTile(nextTile);
      moved = true;

      break; // TODO temporary implementation
    }

    if (checkWinCondition() != null) {
      endGame();
      return;
    }
    if (!extraTurn) {
      nextPlayer();
    }
  }

  @Override
  public Player checkWinCondition() {
    for (Player player : players) {
      LudoBoard ludoBoard = (LudoBoard) board;
      int playerIndex = ludoBoard.getPlayerIndex(player);
      PlayerGoal playerGoal = ludoBoard.getPlayerGoals().get(playerIndex);

      // check if all pieces are in the goal for player
      if (playerGoal.isComplete()) {
        return player;
      }
    }

    return null; // no winner yet
  }

  @Override
  public void playGame() {
    while (!isGameOver()) {
      handleTurn();
    }
  }
}
