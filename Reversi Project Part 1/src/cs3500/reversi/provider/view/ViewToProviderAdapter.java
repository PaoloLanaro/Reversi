package cs3500.reversi.provider.view;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;

/**
 * The adapter class for the provider's ReversiModelView class to our ReversiGraphicsView class.
 */
public class ViewToProviderAdapter implements IView {

  ReversiModelView delegate;

  /**
   * This constructor creates an adapter class for the view to view adaption of the
   * provider's view to our view.
   *
   * @param delegate ReversiModelView to be set as a delegate.
   */
  public ViewToProviderAdapter(ReversiModelView delegate) {
    this.delegate = delegate;
  }

  @Override
  public void addFeaturesListener(ViewFeatures featuresListener) {
    delegate.addFeatureListener((cs3500.reversi.provider.view.ViewFeatures) featuresListener);
  }

  @Override
  public void setVisible(boolean visible) {
    delegate.setVisible(visible);
  }

  @Override
  public void refresh() {
    delegate.advance();
  }

  @Override
  public void showErrorMessage(String message, Player player) {
    delegate.error(message);
  }
}
