package cs3500.reversi.model;


import cs3500.reversi.controller.ModelFeatures;

/**
 * Represents a mutable Reversi game with methods to modify the game state.
 */
public interface MutableReversi extends ReadOnlyReversi {

  /**
   * Passes the current player's turn.
   *
   * @throws IllegalStateException if the game is over.
   */
  void passTurn() throws IllegalStateException;

  /**
   * Attempts to make a move on {@code row}, {@code col}.
   *
   * @param row the 0-indexed integer for the row to make the move on.
   * @param col the 0-indexed integer for the column to make the move on.
   * @throws IllegalStateException    if move isn't allowed on the current board, or game is over.
   * @throws IllegalStateException    if the game hasn't been started.
   * @throws IllegalArgumentException if either {@code row} or {@code col} are not valid arguments.
   */
  void makeMove(int row, int col) throws IllegalStateException, IllegalArgumentException;

  /**
   * Initiates the gameplay after setting up the game.
   *
   * @throws IllegalStateException if the game has already started.
   */
  void startGame() throws IllegalStateException;

  /**
   * Sets the action listener for this model. The action listener is notified of player changes,
   * enabling the listener to respond accordingly.
   *
   * @param featuresListener the listener to be registered as the action listener.
   */
  void addFeaturesListener(ModelFeatures featuresListener);

}
