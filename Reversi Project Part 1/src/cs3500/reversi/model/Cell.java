package cs3500.reversi.model;

/**
 * A singular cell for the reversi game. This cell should be told its neighbors through various
 * setter methods, and anyone can get its neighbors through various getters.
 */
public interface Cell {

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
  void setColor(DiscColor color);

  /**
   * Gets this cell's current row.
   *
   * @return an integer representation of the row this cell occupies.
   */
  int getRow();

  /**
   * Gets this cell's current column.
   *
   * @return an integer representation of the column this cell occupies.
   */
  int getCol();

}
