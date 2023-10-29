package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

/**
 * Represents the game logic for a Reversi game. Game logic is responsible for
 * calculating valid moves, applying moves to the game board, and determining
 * when the game is over.
 */
public interface GameLogic {

  /**
   * Calculates the valid moves available to a specific player on the given game board.
   * The method returns a list of valid coordinates where the player can make their move.
   *
   * @param board  the game board on which to calculate valid moves.
   * @param player the player for whom valid moves are calculated.
   * @return a list of {@link Coordinate} objects representing valid moves for the player.
   */

  List<Cell> calculateValidMoves(PlayerColor color);

  /**
   * Makes a move on the game board for a specific player at the specified row and column.
   *
   * @param board  the game board on which to make the move.
   * @param player the player making the move.
   * @param row    the 0-indexed row where the move is made.
   * @param col    the 0-indexed column where the move is made.
   */
  void makeMove(PlayerColor color, int row, int col);

  void passTurn();

  /**
   * Determines whether the game is over based on the current state of the game board.
   *
   * @param board the game board to check for the game's end condition.
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOver();

  PlayerColor getWinnerColor();

  Player getWinner();

  Map<PlayerColor, Integer> getScore();
}
