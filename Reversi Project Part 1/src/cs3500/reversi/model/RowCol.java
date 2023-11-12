package cs3500.reversi.model;

public class RowCol {
  private final int row;
  private final int col;

  public RowCol(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int row() {
    return this.col;
  }

  public int col() {
    return this.row;
  }
}
