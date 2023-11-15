package cs3500.reversi.controller;

import cs3500.reversi.model.MutableReversi;

/**
 * Interface that acts as a controller.
 */
public interface ReversiController {

  /**
   * Plays the game.
   *
   * @param model the model to play the game on.
   */
  void playGame(MutableReversi model);

  /**
   * Handles clicks for event listeners.
   *
   * @param row the row to handle the click.
   * @param col the column to handle the click.
   */
  void handleCellClick(int row, int col);

}
