package cs3500.reversi.model;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class HexReversiCell implements Cell {
  private DiscColor color;
  private final int row;
  private final int col;

  /**
   * Constructor for the {@link HexReversiCell} object which creates a cell at row and column,
   * with an initial color of empty.
   *
   * @param row the integer representing the row.
   * @param col the integer representing the column.
   */
  public HexReversiCell(int row, int col) {
    color = DiscColor.EMPTY;
    this.row = row;
    this.col = col;
  }

  /**
   * Constructor for the {@link HexReversiCell} object which simply sets the color of this
   * {@link HexReversiCell} alongside the row and column.
   *
   * @param color the color you wish the {@link HexReversiCell} to be.
   * @param row the integer representing the row.
   * @param col the integer representing the column.
   */
  public HexReversiCell(DiscColor color, int row, int col) {
    this.color = color;
    this.row = row;
    this.col = col;
  }

  /**
   * Copy constructor for {@link HexReversiCell}.
   *
   * @param otherCell The Cell to copy the state from.
   */
  public HexReversiCell(Cell otherCell) {
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
    return "(" + row + ", " + ")" + " : " + color.toString();
  }
}