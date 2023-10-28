package cs3500.reversi.model;

import java.util.List;

/**
 * Represents the game board for a Reversi game. The board consists of cells
 * in which players make moves during the game. The board keeps track of the
 * game state, valid moves, and the winner.
 */
public interface Board {

  /**
   * Retrieves the current state of the game board, including the status of
   * each cell.
   *
   * @return a 2D array of {@link Cell} objects representing the current state of the board.
   */
  List<List<Cell>> getBoard();

  /**
   * Checks whether a move is valid for a specific player at the given row and column.
   *
   * @param player the {@link Player} for which the move is being checked.
   * @param row    the 0-indexed row to check for a valid move.
   * @param col    the 0-indexed column to check for a valid move.
   * @return {@code true} if the move is valid, {@code false} otherwise.
   */
  boolean isMoveValid(Player player, int row, int col);

  /**
   * Makes a move on the board for a specific player at the specified row and column.
   *
   * @param player the {@link Player} making the move.
   * @param row    the 0-indexed row on which to make the move.
   * @param col    the 0-indexed column on which to make the move.
   */
  void makeMove(Player player, int row, int col);

  /**
   * Determines whether the game is over.
   *
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOver();

  /**
   * Retrieves the winner of the game or returns {@code null} if no player has won yet.
   *
   * @return the winner as a {@link Player}, or {@code null} if the game has no winner.
   */
  Player getWinner();
}
