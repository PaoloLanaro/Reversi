package cs3500.reversi.view;

import java.awt.event.KeyEvent;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

public class HintDecorator extends JPanel {

  private final ReversiPanel reversiPanel;
  private boolean hintEnabled;

  /**
   * Constructs the {@link ReversiPanel}.
   *
   * @param model the {@link ReadOnlyReversi} model to represent in the JPanel
   */
  public HintDecorator(ReadOnlyReversi model) {
    this.reversiPanel = new ReversiPanel(model);
    this.hintEnabled = false;
    this.addKeyListener(new KeyboardAdapter());
  }




  private class KeyboardAdapter extends java.awt.event.KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          RowCol highlightedHex = reversiPanel.getHighlightedHex();
          if (highlightedHex == null) {
            reversiPanel.featureListener.pushError("There is no currently highlighted " +
                    "cell in the game.");
          } else {
            reversiPanel.featureListener.makeMove(reversiPanel.getHighlightedHex());
          }
          repaint();
          break;
        case KeyEvent.VK_BACK_SPACE:
          reversiPanel.featureListener.passTurn();
          break;
        case KeyEvent.VK_H:
          reversiPanel.featureListener.enableHint(reversiPanel.getHighlightedHex().getRow(),
                  reversiPanel.getHighlightedHex().getCol());
          break;
        default:
          System.out.println("Not a valid key");
          break;
      }
    }
  }


}
