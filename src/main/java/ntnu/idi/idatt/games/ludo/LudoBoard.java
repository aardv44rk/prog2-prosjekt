package ntnu.idi.idatt.games.ludo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ntnu.idi.idatt.models.Board;
import ntnu.idi.idatt.models.Piece;
import ntnu.idi.idatt.models.Player;
import ntnu.idi.idatt.models.Tile;

public class LudoBoard extends Board {

  private static final int TILE_COUNT = 52;

  private List<Player> players;

  private final Map<Integer, Tile> safeTiles;
  private final Map<Integer, PlayerHome> playerHomes;
  private final Map<Integer, PlayerGoal> playerGoals;

  public LudoBoard() {
    this.tiles = new HashMap<>(); // tiles of the main board (52 tiles)
    this.safeTiles = new HashMap<>();
    this.playerHomes = new HashMap<>();
    this.playerGoals = new HashMap<>();
    initializeBoard();
  }

  @Override
  public void initializeBoard() {
    // first we init main track
    for (int i = 0; i < TILE_COUNT; i++) {
      tiles.put(i, new Tile(i));
    }
    // then we init safe tiles
    for (int i = 0; i < 4; i++) {
      int safeTileId = i * 13; // 0, 13, 26, 39 corresponding to player id
      safeTiles.put(i, tiles.get(safeTileId));
    }

    // then we init player homes
    for (int i =  0; i < 4; i++) {
      int startTileId = i * 13; // same as safe tiles?? this makes sense i think
      playerHomes.put(i, new PlayerHome(null, i, startTileId)); // Owner maybe unneeded, as we have ids for each color?
    }

    // finally goals
    for (int i = 0; i < 4; i++) {
      playerGoals.put(i, new PlayerGoal(null, i));
    }
  }

  public void moveFromHomeToStart(Player player, Piece piece) {
    int playerIndex = getPlayerIndex(player);
    if (playerIndex == -1) return; // maybe be more verbose here

    int startTileId = playerIndex * 13;
    piece.setCurrentTile(tiles.get(startTileId));
  }

  public int getPlayerIndex(Player player) {
    for(Map.Entry<Integer, PlayerHome> entry : playerHomes.entrySet()) {
      if (player.equals(entry.getValue().getOwner())) {
        return entry.getKey();
      }
    }

    return -1; // player not found
  }

  public Tile getNextTile(Piece piece, int steps) {
    Tile currentTile = piece.getCurrentTile();
    int currentTileId = currentTile.getTileId();
    Player owner = piece.getOwner();
    int playerIndex = getPlayerIndex(owner);

    if (currentTileId >= 1000 && currentTileId < 2000) {
      if (steps == 6) {
        return tiles.get(playerIndex * 13); // move to start tile
      } else {
        return currentTile; // stay in home
      }
    }

    int nextId = (currentTileId + steps) % TILE_COUNT;
    return tiles.get(nextId);
  }

  public void assignPlayers(List<Player> players) {
    this.players = players;
    for (int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      PlayerHome home = playerHomes.get(i);
      PlayerGoal goal = playerGoals.get(i);

      home.setOwner(player);
      goal.setOwner(player);

      List<Piece> pieces = player.getPieces();
      for (int j = 0; j < pieces.size(); j++) {
        home.placePiece(pieces.get(j), j + 1);
      }
    }
  }

  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

  public Map<Integer, Tile> getSafeTiles() {
    return safeTiles;
  }

  public Map<Integer, PlayerHome> getPlayerHomes() {
    return playerHomes;
  }

  public Map<Integer, PlayerGoal> getPlayerGoals() {
    return playerGoals;
  }

  public List<Player> getPlayers() {
    return players;
  }
  
}
