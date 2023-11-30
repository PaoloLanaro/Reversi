package cs3500.reversi.model;

import cs3500.reversi.controller.ModelFeatures;

/**
 * The smarter model interface which extends a MutableReversi to add functionality without modifying
 * preexisting interfaces.
 */
public interface ISmarterModel extends MutableReversi {
  /**
   * Sets the action listener for this model. The action listener is notified of player changes,
   * allowing the listener to respond accordingly.
   *
   * @param featuresListener the listener to be registered as the Action Listener.
   */
  void addFeaturesListener(ModelFeatures featuresListener);
}
