package ntnu.idi.idatt.components;

public class UISnakesAndLaddersLadder {

  private final int[] from;
  private final int[] to;

  public UISnakesAndLaddersLadder(int[] from, int[] to) {
    this.from = from;
    this.to = to;
  }

  public int[] getFrom() {
    return from;
  }

  public int[] getTo() {
    return to;
  }
}
