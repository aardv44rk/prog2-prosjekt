package ntnu.idi.idatt.games.ludo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;

/**
 * LudoBoard represents the game board for Ludo. 
 */
public class LudoBoard extends Board {

  private static final int MAIN_PATH_LENGTH = 52;
  private static final int FINAL_STRETCH_LENGTH = 6;

  private final Map<Integer, LudoTile> mainTiles;
  private final Map<Player, LudoTile> entryTiles;
  private final Map<Player, List<LudoTile>> finalStretches;
  private final Map<Player, LudoTile> goalTiles;
  private final List<Player> players;

  public LudoBoard(List<Player> players) {
    this.tiles = new HashMap<>();
    this.mainTiles = new HashMap<>();
    this.entryTiles = new HashMap<>();
    this.finalStretches = new HashMap<>();
    this.goalTiles = new HashMap<>();
    this.players = players;
    initializeBoard();
  }

  @Override
  public void initializeBoard() {

    for (int i = 0; i < MAIN_PATH_LENGTH; i++) {
      LudoTile tile = new LudoTile(i);
      mainTiles.put(i, tile);
      tiles.put(i, tile);
    }

    for (int i = 0; i < players.size(); i++) {
      int entryIndex = i * 13;
      Player player = players.get(i);
      LudoTile entryTile = mainTiles.get(entryIndex);
      entryTiles.put(player, entryTile);

      List<LudoTile> finalStretch = new ArrayList<>();
      for (int j = 0; j < FINAL_STRETCH_LENGTH; j++) {
        LudoTile finalStretchTile = new LudoTile(1000 + i * 10 + j);
        finalStretch.add(finalStretchTile);
        tiles.put(finalStretchTile.getTileId(), finalStretchTile);
      }
      finalStretches.put(player, finalStretch);
      goalTiles.put(player, finalStretch.getLast());
    }
  }

  public LudoTile getNextTile(Piece piece, int steps) {
    LudoTile currentTile = (LudoTile) piece.getCurrentTile();
    Player player = piece.getOwner();

    if (currentTile == null) {
      return (steps == 6) ? entryTiles.get(player) : null;
    }

    if (mainTiles.containsValue(currentTile)) {
      int currentId = currentTile.getTileId();
      int entryId = entryTiles.get(player).getTileId();
      int distanceToFinal = (entryId - currentId + MAIN_PATH_LENGTH) % MAIN_PATH_LENGTH;

      if (steps < distanceToFinal) {
        return mainTiles.get((currentId + steps) % MAIN_PATH_LENGTH);
      } else {
        int intoFinal = steps - distanceToFinal;
        List<LudoTile> finalStretch = finalStretches.get(player);
        return intoFinal < finalStretch.size() ? finalStretch.get(intoFinal) : null;
      }
    }

    return null;
  }

  public LudoTile getEntryTile(Player player) {
    return entryTiles.get(player);
  }

  public List<LudoTile> getFinalStretch(Player player) {
    return finalStretches.get(player);
  }

  public LudoTile getGoalTile(Player player) {
    return goalTiles.get(player);
  }
}
