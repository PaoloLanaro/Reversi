package cs3500.reversi.controller;

import cs3500.reversi.model.RowCol;

/**
 * Defines methods expected in any class extending Reversi game features for communication with the
 * controller. Specifically, this interface facilitates establishing a callback between a view and
 * the controller, which, in turn, interacts with an underlying mutable model.
 */
public interface ViewFeatures {

  /**
   * Places a disc on the Reversi board at the specified {@link RowCol} coordinates.
   *
   * @param coordinate the coordinates where the disc will be placed.
   */
  void makeMove(RowCol coordinate);

  /**
   * Passes the turn in the current reversi game.
   */
  void passTurn();

  /**
   * Sends an error message for a player.
   *
   * @param errorMessage the message to be conveyed.
   */
  void pushError(String errorMessage);

  /**
   * Enables hints for the player.
   */
  void enableHint(int row, int col);
}
