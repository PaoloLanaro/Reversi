package cs3500.reversi.model;

/**
 * Represents a mutable reversi game.
 * Contains methods that will mutate the reversi game.
 */
public interface MutableReversi extends ReadOnlyReversi {

  /**
   * Passes the current players turn.
   *
   * @throws IllegalStateException if the game is over.
   */
  void passTurn() throws IllegalStateException;

  /**
   * Attempts to make a move on {@code row}, {@code col}.
   *
   * @param row the 0-indexed integer for the row to make the move on.
   * @param col the 0-indexed integer for the column to make the move on.
   * @throws IllegalStateException    if move isn't allowed on the current board, or game is over.
   * @throws IllegalStateException    if the game hasn't been started.
   * @throws IllegalArgumentException if either {@code row} or {@code col} are not valid arguments.
   */
  void makeMove(int row, int col) throws IllegalStateException, IllegalArgumentException;

  /**
   * Actually plays the game after having set up the game.
   *
   * @throws IllegalStateException if the game's already been started.
   */
  void startGame() throws IllegalStateException;
}
