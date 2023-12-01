package cs3500.reversi.model;

/**
 * A singular cell for the reversi game. This cell should be told its neighbors through various
 * setter methods, and anyone can get its neighbors through various getters.
 */
public interface Cell {

  /**
   * Sets the upper left neighbor cell of this cell.
   *
   * @param cell The cell to set as the upper left neighbor.
   */
  void setUpperLeft(Cell cell);

  /**
   * Sets the upper right neighbor cell of this cell.
   *
   * @param cell The cell to set as the upper right neighbor.
   */
  void setUpperRight(Cell cell);

  /**
   * Sets the right neighbor cell of this cell.
   *
   * @param cell The cell to set as the right neighbor.
   */
  void setRight(Cell cell);

  /**
   * Sets the bottom right neighbor cell of this cell.
   *
   * @param cell The cell to set as the bottom right neighbor.
   */
  void setBottomRight(Cell cell);

  /**
   * Sets the bottom left neighbor cell of this cell.
   *
   * @param cell The cell to set as the bottom left neighbor.
   */
  void setBottomLeft(Cell cell);

  /**
   * Sets the left neighbor cell of this cell.
   *
   * @param cell The cell to set as the left neighbor.
   */
  void setLeft(Cell cell);

  /**
   * Retrieves the color of the cell, indicating whether it is empty, occupied by a black disc,
   * or occupied by a white disc.
   *
   * @return the color of the cell as a {@link DiscColor}.
   */
  DiscColor getColor();

  /**
   * Sets the color of the cell to the specified {@link DiscColor}.
   *
   * @param color The color to set the cell to.
   */
  void setDiscColor(DiscColor color);

  /**
   * Retrieves the bottom right neighbor cell of this cell.
   *
   * @return The bottom right neighbor cell.
   */
  Cell getBottomRight();

  /**
   * Retrieves the bottom left neighbor cell of this cell.
   *
   * @return The bottom left neighbor cell.
   */
  Cell getBottomLeft();

  /**
   * Retrieves the right neighbor cell of this cell.
   *
   * @return The right neighbor cell.
   */
  Cell getRight();

  /**
   * Retrieves the left neighbor cell of this cell.
   *
   * @return The left neighbor cell.
   */
  Cell getLeft();

  /**
   * Retrieves the upper right neighbor cell of this cell.
   *
   * @return The upper right neighbor cell.
   */
  Cell getUpperRight();

  /**
   * Retrieves the upper left neighbor cell of this cell.
   *
   * @return The upper left neighbor cell.
   */
  Cell getUpperLeft();

}
