package cs3500.reversi.provider.view;

/**
 * Represents the view for a reversi model. Displays a
 * grid of the given game. Player 1 is represented by black tiles.
 * Player two is represented by pink tiles.
 */
public interface ReversiModelView {
  /**
   * makes the view visible.
   */
  void setVisible(boolean visible);

  /**
   * Adds features to be listened to. The listener
   * is async so it has to be able to receive information
   * and respond to it or choose not to in any order.
   * @param features are the given view features.
   */
  void addFeatureListener(ViewFeatures features);

  /**
   * Display the next color in the sequence.
   */
  void advance();

  /**
   * Tell the user that they guessed wrong.
   */
  void error(String msg);

  /**
   *  Creates the pop up at the end of the game.
   */
  void endGamePopUp();

  /**
   * returns panel utilzied for testing only.
   */
  ReversiPanel getPanel();
}
