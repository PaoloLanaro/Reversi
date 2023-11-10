package cs3500.reversi.view;


import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversi;

/**
 * A graphical user interface representation of a game.
 */
public class ReversiGraphicsView extends JFrame implements IView {

  private final ReversiPanel gamePanel;
  public ReversiGraphicsView(ReadOnlyReversi model) {
    super();
    this.setTitle("Reversi game!");
    this.setLocation(100, 100);
    this.setSize(new Dimension(500, 500));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    gamePanel = new ReversiPanel(model);

    this.add(gamePanel);
    gamePanel.setVisible(true);



//    this.getPreferredSize();
  }

  @Override
  public void setListener(ActionListener actionListener) {

  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
  }

  @Override
  public void refresh() {
    this.repaint();
  }


  // empty, waiting for future assignments
}
