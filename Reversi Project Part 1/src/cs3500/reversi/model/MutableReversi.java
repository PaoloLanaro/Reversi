package cs3500.reversi.model;

public interface MutableReversi extends ReadOnlyReversi {

  /**
   * Passes the current players turn.
   */
  public void passTurn();

  /**
   * Attempts to make a move on {@code row}, {@code col}.
   *
   * @throws IllegalStateException if the move is not allowed on the current board.
   * @throws IllegalArgumentException if either {@code row} or {@code col} are not valid arguments.
   *
   * @param row the 0-indexed integer for the row to make the move on.
   * @param col the 0-indexed integer for the column to make the move on.
   */
  public void makeMove(int row, int col);
}
