package cs3500.reversi.model;

/**
 * Represents a basic implementation of a basic hexagon Reversi Game. Implements the MutableReversi
 * interface to give methods for making moves, passing turns, and checking game state. The game is
 * played on a hex grid with a board size variable.
 */
public class HexReversi extends AbstractReversi {

  /**
   * Used to construct a hexagonal reversi game.
   * Creates a {@link HexReversi model game.
   * <p>
   * @param initSize the size for the top 'length' to be.
   */
  public HexReversi(int initSize) {
    super(initSize);
  }

}
