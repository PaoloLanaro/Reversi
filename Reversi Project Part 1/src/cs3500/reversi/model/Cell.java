package cs3500.reversi.model;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class Cell {
  private DiscColor color;
  private Cell upperLeft, upperRight, left, right, bottomLeft, bottomRight;

  /**
   * Constructs a new Cell with an initial state of empty.
   */
  public Cell() {
    color = DiscColor.EMPTY;
  }


  /**
   * Constructs a new Cell by copying the state of another Cell.
   *
   * @param otherCell The Cell to copy the state from.
   */
  public Cell(Cell otherCell) {
    color = otherCell.color;
    upperLeft = otherCell.upperLeft;
    upperRight = otherCell.upperRight;
    left = otherCell.left;
    right = otherCell.right;
    bottomLeft = otherCell.bottomLeft;
    bottomRight = otherCell.bottomRight;
  }

  /**
   * Sets the upper left neighbor cell of this cell.
   *
   * @param cell The cell to set as the upper left neighbor.
   */
  public void setUpperLeft(Cell cell) {
    this.upperLeft = cell;
  }

  /**
   * Sets the upper right neighbor cell of this cell.
   *
   * @param cell The cell to set as the upper right neighbor.
   */
  public void setUpperRight(Cell cell) {
    this.upperRight = cell;
  }

  /**
   * Sets the left neighbor cell of this cell.
   *
   * @param cell The cell to set as the left neighbor.
   */
  public void setLeft(Cell cell) {
    this.left = cell;
  }

  /**
   * Sets the right neighbor cell of this cell.
   *
   * @param cell The cell to set as the right neighbor.
   */
  public void setRight(Cell cell) {
    this.right = cell;
  }

  /**
   * Sets the bottom left neighbor cell of this cell.
   *
   * @param cell The cell to set as the bottom left neighbor.
   */
  public void setBottomLeft(Cell cell) {
    this.bottomLeft = cell;
  }

  /**
   * Sets the bottom right neighbor cell of this cell.
   *
   * @param cell The cell to set as the bottom right neighbor.
   */
  public void setBottomRight(Cell cell) {
    this.bottomRight = cell;
  }

  /**
   * Retrieves the upper left neighbor cell of this cell.
   *
   * @return The upper left neighbor cell.
   */
  public Cell getUpperLeft() {
    return upperLeft;
  }

  /**
   * Retrieves the upper right neighbor cell of this cell.
   *
   * @return The upper right neighbor cell.
   */
  public Cell getUpperRight() {
    return upperRight;
  }

  /**
   * Retrieves the left neighbor cell of this cell.
   *
   * @return The left neighbor cell.
   */
  public Cell getLeft() {
    return left;
  }

  /**
   * Retrieves the right neighbor cell of this cell.
   *
   * @return The right neighbor cell.
   */
  public Cell getRight() {
    return right;
  }

  /**
   * Retrieves the bottom left neighbor cell of this cell.
   *
   * @return The bottom left neighbor cell.
   */
  public Cell getBottomLeft() {
    return bottomLeft;
  }

  /**
   * Retrieves the bottom right neighbor cell of this cell.
   *
   * @return The bottom right neighbor cell.
   */
  public Cell getBottomRight() {
    return bottomRight;
  }

  /**
   * Retrieves the color of the cell, indicating whether it is empty, occupied by a black disc,
   * or occupied by a white disc.
   *
   * @return the color of the cell as a {@link DiscColor}.
   */
  public DiscColor getColor() {
    return color;
  }

  /**
   * Sets the color of the cell to the specified {@link DiscColor}.
   *
   * @param color The color to set the cell to.
   */
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