package cs3500.reversi.model;

import cs3500.reversi.controller.ModelFeatures;

/**
 * Extends the {@link MutableReversi} interface to provide additional functionality for a smarter
 * model. This interface allows the registration of a features listener, notifying it of player
 * changes.
 */
public interface ISmarterModel extends MutableReversi {

  /**
   * Sets the action listener for this model. The action listener is notified of player changes,
   * enabling the listener to respond accordingly.
   *
   * @param featuresListener the listener to be registered as the action listener.
   */
  void addFeaturesListener(ModelFeatures featuresListener);
}
