package cs3500.reversi.provider.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cs3500.reversi.provider.model.GenericPlayer;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;

/**
 * The frame for our view. It is a light gray background.
 */
public class ReversiGraphicsView  extends JFrame  implements ReversiModelView {

  private final ReversiPanel panel;

  /**
   * Constructor for ReversiGraphicsView.
   * @param model is the given read only reversi model.
   */
  public ReversiGraphicsView(ReadOnlyReversiModel model, GenericPlayer name) {
    super();
    this.setTitle("Reversi");
    panel = new ReversiPanel(model, name);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel display = new JLabel("this is display");
    this.add(display);
    this.add(panel);
    panel.setFocusable(true);
    requestFocus();
    panel.requestFocusInWindow();

    JLabel jlabel = new JLabel(panel.getPlayerName());
    jlabel.setFont(new Font("Verdana",1,20));
    panel.add(jlabel);
    this.pack();
    this.setSize(500, 500);
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void advance() {
    this.panel.advance();
  }

  @Override
  public void error(String msg) {
    this.panel.error(msg);
  }

  @Override
  public void endGamePopUp() {
//    this.panel.endGamePopUp();
  }

  @Override
  public ReversiPanel getPanel() {
    return this.panel;
  }
}