package cs3500.reversi.model;

import  java.util.List;
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
   * Gets the current score of the game.
   * This is represented as a map of {@link DiscColor} to Integer,
   * where Integer is the score associated with that specific {@link DiscColor},
   * and {@link DiscColor} is the color on the board.
   *
   * @return a map of {@link DiscColor} to Integer.
   */

  Map<DiscColor, Integer> getScore();

  /**
   * Returns a String representation of the winner of the game.
   *
   * @return a string declaring the winning color or a draw.
   * @throws IllegalStateException if the game is not over.
   */
  String getWinner();

  /**
   * Looks for the valid moves available to a player with the specified {@link DiscColor}.
   * A valid move is a sequence of {@link Cell} objects representing the path of cells that
   * can be swapped to the specified player's color if the move is made. The path starts
   * from an empty cell and ends with a cell of the specified color.
   *
   * @param color The {@link DiscColor} of the player for whom to find valid moves.
   * @return A list of {@link Cell} objects representing the valid moves available to the player.
   * Each {@link Cell} in the list corresponds to a potential move that the player can make.
   */
  List<Cell> getValidMoves(DiscColor color);

  /**
   * This method will get the current players turn.
   *
   * @return the current players turn as a string representation.
   */
  String getTurn();

  /**
   * Checks if the disc placement is valid in the current game state.
   *
   * @param row row to move current players disc to.
   * @param col column to move current players disc to.
   * @return boolean representation of whether the move is valid.
   * @throws IllegalArgumentException if row or col are out of bounds.
   * @throws IllegalStateException    if the move is not allowed.
   */
  boolean isValidMove(int row, int col);

  /**
   * Gets the integer representation of one of the sides of the hexagon in {@link Cell}s.
   * Think just one side length of the perimeter of a hexagon shape.
   *
   * @return side length integer.
   */
  int getSideLength();

  /**
   * Gets the contents of the specified cell.
   *
   * @param row the row that should be checked.
   * @param col the column that should be checked.
   * @return The {@link DiscColor} of the cell.
   */
  DiscColor getCellColor(int row, int col);

  /**
   * Get the cell at the coordinate points in the 2D array.
   *
   * @param row the row to get the cell from.
   * @param col the column to get the cell from.
   * @return The {@link Cell} at that location.
   */
  Cell getCellAt(int row, int col);

  /**
   * Gets the column index of the specified cell.
   *
   * @param cell The {@link Cell} for which to get the column index.
   * @return The column index of the cell.
   */
  int getColFromCell(Cell cell);

  /**
   * Gets the row index of the specified cell.
   *
   * @param cell The {@link Cell} for which to get the row index.
   * @return The row index of the cell.
   */
  int getRowFromCell(Cell cell);
}
