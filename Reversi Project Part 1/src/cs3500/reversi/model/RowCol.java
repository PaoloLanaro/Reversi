package cs3500.reversi.model;

public class RowCol {
  private final int x;
  private final int y;

  public RowCol(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int row() {
    return this.y;
  }

  public int col() {
    return this.x;
  }
}
