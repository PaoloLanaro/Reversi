package cs3500.reversi.model;

public interface MutableReversi extends ReadOnlyReversi {

  /**
   * Passes the current players turn.
   *
   * @throws IllegalStateException if the game is over.
   */
  void passTurn();

  /**
   * Attempts to make a move on {@code row}, {@code col}.
   *
   * @param row the 0-indexed integer for the row to make the move on.
   * @param col the 0-indexed integer for the column to make the move on.
   * @throws IllegalStateException    if the move is not allowed on the current board.
   * @throws IllegalArgumentException if either {@code row} or {@code col} are not valid arguments.
   */
  void makeMove(int row, int col);
}
