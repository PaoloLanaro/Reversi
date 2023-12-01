package cs3500.reversi.controller;

/**
 * Represents a communication interface between a {@link cs3500.reversi.model.MutableReversi} model
 * and a view.
 */
public interface ModelFeatures {

  /**
   * Callback method to inform the implementing class about changes in the model's state.
   * Typically used when the model updates the view or the overall game state, prompting the view to
   * refresh.
   */
  void refresh();
}
