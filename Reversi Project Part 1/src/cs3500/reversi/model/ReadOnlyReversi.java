package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

/**
 * Represents the game board for a Reversi game. The board consists of cells
 * in which players make moves during the game. The board keeps track of the
 * game state, valid moves, and the winner.
 */
public interface ReadOnlyReversi {

  /**
   * Retrieves the current state of the game board, including the status of
   * each cell.
   *
   * @return a 2D array of {@link Cell} objects representing the current state of the board.
   */
  List<List<Cell>> getBoard();

  /**
   * Determines whether the game is over based on the current state of the game board.
   *
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game.
   *
   * @return Whichever player won the game, or {@code null} if no winner could be determined.
   * @throws IllegalStateException if the game isn't over.
   */
  DiscColor getWinnerColor();

  /**
   * Gets the current score of the game.
   * This is represented as a map of {@link DiscColor} to Integer,
   * where Integer is the score associated with that specific {@link DiscColor},
   * and {@link DiscColor} is the color on the board.
   *
   * @return a map of {@link DiscColor} to Integer.
   */
  Map<DiscColor, Integer> getScore();

  List<Cell> getValidMoves(DiscColor color);
}
