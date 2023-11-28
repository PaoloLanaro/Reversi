package cs3500.reversi.model;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class ReversiCell implements Cell {
  private DiscColor color;
  private Cell upperLeft;
  private Cell upperRight;
  private Cell left;
  private Cell right;
  private Cell bottomLeft;
  private Cell bottomRight;

  /**
   * Constructs a new Cell with an initial state of empty.
   */
  public ReversiCell() {
    color = DiscColor.EMPTY;
  }


  /**
   * Constructs a new Cell by copying the state of another Cell.
   *
   * @param otherCell The Cell to copy the state from.
   */
  public ReversiCell(Cell otherCell) {
    color = otherCell.getColor() == DiscColor.BLACK ? DiscColor.BLACK :
            otherCell.getColor() == DiscColor.WHITE ? DiscColor.WHITE : DiscColor.EMPTY;
    upperLeft = otherCell.getUpperLeft();
    upperRight = otherCell.getUpperRight();
    left = otherCell.getLeft();
    right = otherCell.getRight();
    bottomLeft = otherCell.getBottomLeft();
    bottomRight = otherCell.getBottomRight();
  }

  /**
   * Constructor for the {@link ReversiCell} object which simply sets the color of this {@link ReversiCell}.
   *
   * @param color the color you wish the {@link ReversiCell} to be.
   */
  public ReversiCell(DiscColor color) {
    this.color = color;
  }

  @Override
  public void setUpperLeft(Cell cell) {
    this.upperLeft = cell;
  }

  @Override
  public void setUpperRight(Cell cell) {
    this.upperRight = cell;
  }

  @Override
  public void setLeft(Cell cell) {
    this.left = cell;
  }

  @Override
  public void setRight(Cell cell) {
    this.right = cell;
  }

  @Override
  public void setBottomLeft(Cell cell) {
    this.bottomLeft = cell;
  }

  @Override
  public void setBottomRight(Cell cell) {
    this.bottomRight = cell;
  }

  @Override
  public Cell getUpperLeft() {
    return upperLeft;
  }

  @Override
  public Cell getUpperRight() {
    return upperRight;
  }

  @Override
  public Cell getLeft() {
    return left;
  }

  @Override
  public Cell getRight() {
    return right;
  }

  @Override
  public Cell getBottomLeft() {
    return bottomLeft;
  }

  @Override
  public Cell getBottomRight() {
    return bottomRight;
  }

  @Override
  public DiscColor getColor() {
    return color;
  }

  @Override
  public void setDiscColor(DiscColor color) {
    this.color = color;
  }

  /**
   * Returns a string representation of the cell, indicating its color.
   *
   * @return A string representation of the cell's color.
   */
  @Override
  public String toString() {
    return color.toString();
  }
}