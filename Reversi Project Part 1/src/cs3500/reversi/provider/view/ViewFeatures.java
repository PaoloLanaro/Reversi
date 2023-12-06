package cs3500.reversi.provider.view;

import cs3500.reversi.provider.model.IHex;

/**
 * Interface to ensure that our view features have
 * uniformity. This interface represents our player
 * action design.
 */
public interface ViewFeatures {

  /**
   * Quits the game and closes out all the views
   * when a player wins.
   */
  void quit();

  /**
   * Is called when a player presses a key to move/pass
   * or when an ai makes a move. Tries to make a
   * move at the given hex/ or pass if it needs.
   * @param h is the given hex to move to. is null
   *          if the player passes.
   * @param pass is true if the player wants to pass.
   */
  void playerMakesMove(IHex h, boolean pass);
}
