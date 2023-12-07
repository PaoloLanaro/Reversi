package cs3500.reversi.provider.view;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;

public class ViewToProviderAdapter implements IView {

  ReversiModelView delegate;

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
