package cs3500.reversi.view;


import java.awt.Dimension;

import javax.swing.*;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.Player;

/**
 * A graphical user interface representation of a game.
 */
public class ReversiGraphicsView extends JFrame implements IView {

  private final ReversiPanel gamePanel;
  private final ReadOnlyReversi model;

  /**
   * Constructs a ReversiGraphicsView object that creates a game panel.
   */
  public ReversiGraphicsView(ReadOnlyReversi model) {
    super();
    this.setTitle("Reversi game!");
    this.setLocation(390, 85);
    this.setSize(new Dimension(500, 500));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    gamePanel = new ReversiPanel(model);
    this.model = model;

    this.add(gamePanel);
  }

  @Override
  public void addFeaturesListener(ViewFeatures featuresListener) {
    gamePanel.addFeaturesListener(featuresListener);
    //for controller.
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
  }

  @Override
  public void refresh() {
    this.setTitle("Reversi player " + model.getTurn() + "'s turn.");
    if (model.isGameOver()) {
      this.setTitle(model.getWinner());
      return;
    }
    this.repaint();
  }

  // todo is this supposed to be in features interface? and is IView supposed to extend the
  //  features interface?
  @Override
  public void showErrorMessage(String message, Player player) {
    JOptionPane.showMessageDialog(null, message + " for player: " + player.getColor());
  }
}
