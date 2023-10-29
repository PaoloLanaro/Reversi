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
}
