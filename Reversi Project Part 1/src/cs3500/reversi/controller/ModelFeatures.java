package cs3500.reversi.controller;

/**
 * A way for a {@link cs3500.reversi.model.MutableReversi} model to communicate with the view.
 */
public interface ModelFeatures {

  /**
   * Callback method that allows the model to communicate with some ModelFeatures implementation.
   * <p>
   * This is mainly used when the model has changed some state about the view or overall game,
   * and want the view to update it's state.
   */
  void refresh();
}
