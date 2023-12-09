package cs3500.reversi.model;

import java.util.Objects;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class ReversiCell implements Cell {
  private DiscColor color;
  private final int row;
  private final int col;

  /**
   * Constructor for the {@link ReversiCell} object which creates a cell at row and column,
   * with an initial color of empty.
   *
   * @param row the integer representing the row.
   * @param col the integer representing the column.
   */
  public ReversiCell(int row, int col) {
    color = DiscColor.EMPTY;
    this.row = row;
    this.col = col;
  }

  /**
   * Constructor for the {@link ReversiCell} object which simply sets the color of this
   * {@link ReversiCell} alongside the row and column.
   *
   * @param color the color you wish the {@link ReversiCell} to be.
   * @param row   the integer representing the row.
   * @param col   the integer representing the column.
   */
  public ReversiCell(DiscColor color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Copy constructor for {@link ReversiCell}.
   *
   * @param otherCell The Cell to copy the state from.
   */
  public ReversiCell(Cell otherCell) {
    color = otherCell.getColor() == DiscColor.BLACK ? DiscColor.BLACK :
            otherCell.getColor() == DiscColor.WHITE ? DiscColor.WHITE : DiscColor.EMPTY;
    this.row = otherCell.getRow();
    this.col = otherCell.getCol();
  }

  @Override
  public DiscColor getColor() {
    return color;
  }

  @Override
  public void setColor(DiscColor color) {
    this.color = color;
  }

  @Override
  public int getRow() {
    return row;
  }

  @Override
  public int getCol() {
    return col;
  }

  /**
   * Returns a string representation of the cell, indicating its color.
   *
   * @return A string representation of the cell's color.
   */
  @Override
  public String toString() {
    return "(" + row + ", " + col + ")" + " : " + color.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Cell otherCell = (Cell) other;

    return row == otherCell.getRow()
            && col == otherCell.getCol()
            && Objects.equals(getColor(), otherCell.getColor());
  }


}