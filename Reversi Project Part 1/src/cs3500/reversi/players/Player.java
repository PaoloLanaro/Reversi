package cs3500.reversi.players;

import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;

/**
 * Represents a player in a Reversi game. Players can make moves on the game board and
 * are associated with a specific player color (e.g., black or white).
 */
public interface Player {

  /**
   * Makes a move on the provided game board. The move must be a valid move on the
   * board, and the player is responsible for ensuring the validity of the move.
   *
   * @param board the game board on which to make a move.
   */
  void makeMove(ReadOnlyReversi board);

  /**
   * Retrieves the color associated with the player. The player color indicates
   * whether the player is playing as black or white in the game.
   *
   * @return the player's color as a {@link DiscColor}.
   */
  DiscColor getColor();
}