package cs3500.reversi.controller;

import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.Player;

/**
 * Methods that should be included in any class that extends features of the Reversi game.
 * More specifically, this is the way that a view can establish a callback with the controller,
 * and then finally with an underlying mutable model.
 */
public interface ViewFeatures {

  /**
   * Plays a disc on a reversi board.
   *
   * @param coordinate the {@link RowCol} pair to place the disc at.
   */
  void makeMove(RowCol coordinate);

  /**
   * Passes the turn in the current reversi game.
   */
  void passTurn();

  /**
   * Pushes an error message for some player.
   *
   * @param errorMessage the message that should be pushed.
   */
  void pushError(String errorMessage);
}
