package cs3500.reversi.view;

import java.io.IOException;

/**
 * Represents a view for a Reversi game. Views are responsible for rendering and updating
 * the game board to provide a visual representation of the game state.
 */
public interface View {

  /**
   * Renders the current state of the game board as text and appends it to the output stream.
   *
   * @throws IOException If an I/O error occurs while writing to the output stream.
   */
  void renderBoard() throws IOException;

}