package cs3500.reversi.model;

/**
 * A simple container class for two integers representing a row and a column.
 */
public class RowCol {
  private final int row;
  private final int col;

  /**
   * Constructs a {@link RowCol} object with the values passed in as arguments.
   *
   * @param row the integer representation of a row.
   * @param col the integer representation of a column.
   */
  public RowCol(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Gets this {@link RowCol}'s row.
   *
   * @return the row of this {@link RowCol}.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Gets this {@link RowCol}'s column.
   *
   * @return the column of this {@link RowCol}.
   */
  public int getCol() {
    return this.col;
  }
}
