package cs3500.reversi.model;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class Cell {
  private DiscColor color;
  private Cell upperLeft, upperRight, left, right, bottomLeft, bottomRight;

  public Cell() {
    color = DiscColor.EMPTY;
  }

  public Cell(Cell otherCell) {
    color = otherCell.color;
    upperLeft = otherCell.upperLeft;
    upperRight = otherCell.upperRight;
    left = otherCell.left;
    right = otherCell.right;
    bottomLeft = otherCell.bottomLeft;
    bottomRight = otherCell.bottomRight;
  }

  public void setUpperLeft(Cell cell) {
    upperLeft = cell;
  }

  public void setUpperRight(Cell cell) {
    upperRight = cell;
  }

  public void setLeft(Cell cell) {
    left = cell;
  }

  public void setRight(Cell cell) {
    right = cell;
  }

  public void setBottomLeft(Cell cell) {
    bottomLeft = cell;
  }

  public void setBottomRight(Cell cell) {
    bottomRight = cell;
  }

  public Cell getUpperLeft() {
    return upperLeft;
  }

  public Cell getUpperRight() {
    return upperRight;
  }

  public Cell getLeft() {
    return left;
  }

  public Cell getRight() {
    return right;
  }

  public Cell getBottomLeft() {
    return bottomLeft;
  }

  public Cell getBottomRight() {
    return bottomRight;
  }

  /**
   * Retrieves the color of the cell, indicating whether it is empty, occupied by a black disc,
   * or occupied by a white disc.
   *
   * @return the color of the cell as a {@link CellState}.
   */
  public DiscColor getColor() {
    return color;
  }

  public void setDiscColor(DiscColor color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return color.toString();
  }
}