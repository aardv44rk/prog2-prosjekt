package ntnu.idi.idatt.models;

import ntnu.idi.idatt.controllers.BoardGame;

public class Player {
  private String name;
  private Tile currentTile;
  
  public Player(String name, BoardGame game) {
    this.name = name;
  }

  public void placeOnTile(Tile tile) {

  }

  public void move(int steps) {
    
  }
}
