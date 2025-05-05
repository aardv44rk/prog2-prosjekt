package ntnu.idi.idatt.models;

import java.util.List;

public class Player {

  private final String name;
  private final List<Piece> pieces;

  public Player(String name, List<Piece> pieces) {
    this.name = name;
    this.pieces = pieces;
  }

  public String getName() {
    return name;
  }

  public List<Piece> getPieces() {
    return pieces;
  }
}