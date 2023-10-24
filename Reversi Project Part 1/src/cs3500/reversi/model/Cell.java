package cs3500.reversi.model;


/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * states, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class Cell {
  private Player occupant;
  private CellState state;

  public Cell() {
    state = CellState.EMPTY;
    occupant = null;
  }

  /**
   * Retrieves the state of the cell, indicating whether it is empty, occupied by a black disc,
   * or occupied by a white disc.
   *
   * @return the state of the cell as a {@link CellState}.
   */
  public CellState getState() {
    return state;
  }

  /**
   * Retrieves the player associated with the cell. The player represents the one who placed
   * a disc in the cell.
   *
   * @return the player associated with the cell, or null if the cell is empty.
   */
  public Player getOccupant() {
    return occupant;
  }

  /**
   * Sets the state of the cell to represent a black disc and associates it with the specified player.
   *
   * @param player the player who placed the black disc in the cell.
   */
  public void setBlackDisc(Player player) {
    state = CellState.BLACK;
    occupant = player;
  }

  /**
   * Sets the state of the cell to represent a white disc and associates it with the specified player.
   *
   * @param player the player who placed the white disc in the cell.
   */
  public void setWhiteDisc(Player player) {
    state = CellState.WHITE;
    occupant = player;
  }

  /**
   * Clears the cell, making it empty and removing any associated player.
   */
  public void clear() {
    state = CellState.EMPTY;
    occupant = null;
  }
}
