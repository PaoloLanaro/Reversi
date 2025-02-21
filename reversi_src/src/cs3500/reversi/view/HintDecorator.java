package cs3500.reversi.view;

import java.awt.event.KeyEvent;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * This class isn't actually used in the program.
 * We were trying to get the hint decorator working, but decided to focus on the other facets
 * of the extra credit.
 */
public class HintDecorator extends HexReversiPanel {

  //  private final ReversiPanel reversiPanel;
  private boolean hintEnabled;

  /**
   * Constructs the {@link HexReversiPanel}.
   *
   * @param model the {@link ReadOnlyReversi} model to represent in the JPanel
   */
  public HintDecorator(ReadOnlyReversi model) {
    super(model);
    this.hintEnabled = false;
    this.addKeyListener(new KeyboardAdapter());
  }

  private class KeyboardAdapter extends java.awt.event.KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      if (event.getKeyCode() == KeyEvent.VK_H) {
        hintEnabled = !hintEnabled;
        showHints();
        System.out.println("hint toggled: " + hintEnabled);
        System.out.println(highlightedHex);
      } else {
        super.keyPressed(event);
      }
    }
  }

  /**
   * Would be called from the refresh method of the feature's listener.
   * This would check to see whether the hints are toggled for this view,
   * if so display the hints,
   * otherwise do nothing, and just highlight the hex like normal.
   */
  public void showHints() {
    if (hintEnabled) {
      RowCol highlightedHex = getHighlightedHex();
      if (highlightedHex != null) {
        putHintOnBlueTile(highlightedHex);
      }
    }
  }

  private void putHintOnBlueTile(RowCol highlightedHex) {
    int scoreFor = model.getScoreFor(highlightedHex.getRow(), highlightedHex.getCol());
    System.out.println(scoreFor);
  }


}
