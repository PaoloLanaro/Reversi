package cs3500.reversi.provider.view;

import cs3500.reversi.provider.model.ReversiModel;

/**
 * The textual representation of reversi model.
 * model is rendered by utilizing the print statements of grid.
 */
public class ReversiTextualView {

  private final ReversiModel model;

  /**
   * The constructor for ReversiTextualView.
   * @param model is the given model to be displayed.
   */
  public ReversiTextualView(ReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
  }

  /**
   * Produces a visual rep of our board.
   * @return string rep of our board.
   */
  public String toString() {
    return this.model.toString();
  }
}
