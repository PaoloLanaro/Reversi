package cs3500.reversi.controller;

/**
 * Interface that acts as a controller.
 */
public interface ReversiController {

  /**
   * Plays the game.
   *
   */
  void playGame();

  /**
   * Handles clicks for event listeners.
   *
   * @param row the row to handle the click.
   * @param col the column to handle the click.
   */
  void handleCellClick(int row, int col);

}
