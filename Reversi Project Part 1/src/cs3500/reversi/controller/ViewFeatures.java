package cs3500.reversi.controller;

import cs3500.reversi.model.RowCol;

/**
 * Methods that should be included in any class that extends features of the Reversi game.
 */
public interface ViewFeatures {

  /**
   * Plays a disc on a reversi board.
   *
   * @param coordinate to place the disc at.
   */
  void makeMove(RowCol coordinate);

  /**
   * Passes the turn in the current reversi game.
   */
  void passTurn();

  /**
   *
   *
   * @param errorMessage
   */
  void pushError(String errorMessage);
}
