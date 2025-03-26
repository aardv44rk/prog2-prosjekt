package ntnu.idi.idatt.models;

import ntnu.idi.idatt.controllers.BoardGame;

public class Player {
  private String name;
  private Tile currentTile;
  private BoardGame game;
  
  public Player(String name, BoardGame game) {
    this.name = name;
    this.game = game;
  }

  public void placeOnTile(Tile tile) {
    this.currentTile = tile;
  }

  public void move(int steps) {
    for (int i = 0; i < steps; i++) {
      if (currentTile.getNextTile() != null) {
        currentTile = currentTile.getNextTile();
      } else { // null => end of board
        break;
      }
    }

    currentTile.landPlayer(this);
  }

  public int getCurrentPosition() {
    return currentTile.getTileId();
  }

  public String getName() {
    return name;
  }

  public BoardGame getGame() {
    return game;
  }
}
