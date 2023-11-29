package cs3500.reversi.view;


import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

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
  public void setListener(ActionListener actionListener) {
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

  @Override
  public RowCol getCurrentlySelectedHexagon() throws IllegalStateException {
    if(gamePanel.getHighlightedHex() == null) {
      throw new IllegalStateException("There is no currently selected hexagon on the board.");
    }
    return gamePanel.getHighlightedHex();
  }

  // todo is this supposed to be in features interface? and is IView supposed to extend the
  //  features interface?
  @Override
  public void showErrorMessage(Player player) {
    JOptionPane.showMessageDialog(null, "Illegal move for player " + player.getColor());
  }
}
