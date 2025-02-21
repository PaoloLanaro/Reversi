package cs3500.reversi.model;

/**
 * Represents the color of a disc in a Reversi game. Discs can be either white, black, or represent
 * an empty cell on the game board.
 */
public enum DiscColor {
  WHITE,
  BLACK,
  EMPTY;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
