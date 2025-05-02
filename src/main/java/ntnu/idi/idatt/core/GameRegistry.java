package ntnu.idi.idatt.core;

import java.util.ArrayList;
import java.util.List;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersBoardFactory;
import ntnu.idi.idatt.games.snakesandladders.SnakesAndLaddersEngine;
import ntnu.idi.idatt.models.GameInfo;

public class GameRegistry {

  public final static List<GameInfo> games = new ArrayList<>();

  static {
    games.add(
        new GameInfo("Snakes and Ladders",
            "Roll the dice to move forward. Green ladders move you up, and red ladders move you down. First to the end wins.",
            2, 4,
            config -> new SnakesAndLaddersEngine(config.getPlayers(), config.getBoard(),
                config.getCurrentPlayerIndex()),
            SnakesAndLaddersBoardFactory::getBoards
        )
    );
    games.add(
        new GameInfo("Ludo",
            "Roll a 6 to move a piece out of the starting squares. Make one lap around the board before entering the final stretch. Send your opponents pieces back to their starting squares when you land on them.",
            2, 4,
            config -> new SnakesAndLaddersEngine(config.getPlayers(), config.getBoard(),
                config.getCurrentPlayerIndex()),
            SnakesAndLaddersBoardFactory::getBoards
        )
    );
  }

  public List<GameInfo> getGames() {
    return games;
  }
}
