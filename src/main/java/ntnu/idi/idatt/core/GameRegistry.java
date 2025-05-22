package ntnu.idi.idatt.core;

import java.util.ArrayList;
import java.util.List;
import ntnu.idi.idatt.games.ludo.LudoBoardFactory;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.games.thievesandrobbers.ThievesAndRobbersBoardFactory;
import ntnu.idi.idatt.models.GameInfo;

/**
 * GameRegistry is a singleton class that holds a list of available games. It provides methods to
 * retrieve game information and board options.
 */
public class GameRegistry {

  public final static List<GameInfo> games = new ArrayList<>();

  static {
    games.add(
        new GameInfo("Snakes and Ladders",
            """
                Roll the dice to move forward. Green ladders move you up,
                and red ladders move you down. First to the end wins.
                """,
            2, 6,
            SnakesAndLaddersBoardFactory::getBoards
        )
    );
    games.add(
        new GameInfo("Thieves and Robbers",
            """
                Roll the dice to move forward. Steal money from each house (tile),
                and finish a lap to get $100. First to $1000 wins.
                """,
            2, 4,
            ThievesAndRobbersBoardFactory::getBoards
        )
    );
    games.add(
        new GameInfo("Ludo",
            """
                Roll a 6 to move a piece out of the starting squares.
                Make one lap around the board before entering the final stretch.
                Send your opponents pieces back to their starting squares when you land on them.
                """,
            2, 4,
            LudoBoardFactory::getBoards
        )
    );
  }

  public List<GameInfo> getGames() {
    return games;
  }
}
