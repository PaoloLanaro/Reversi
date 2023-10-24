package cs3500.reversi.view;

import cs3500.reversi.model.Board;

/**
 * Represents a view for a Reversi game. Views are responsible for rendering and updating
 * the game board to provide a visual representation of the game state.
 */
public interface View {

  /**
   * Renders the provided game board, displaying its current state visually.
   *
   * @param board the game board to render.
   */
  void renderBoard(Board board);

  /**
   * Updates the visual representation of the provided game board, reflecting any
   * changes in the game state since the last render.
   *
   * @param board the game board to update.
   */
  void updateBoard(Board board);
}