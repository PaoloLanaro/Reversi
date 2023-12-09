package cs3500.reversi.view;

import java.awt.event.KeyEvent;

import cs3500.reversi.model.ReadOnlyReversi;

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
      switch (event.getKeyCode()) {
        case KeyEvent.VK_H:
          hintEnabled = true;
          featureListener.enableHint(getHighlightedHex().getRow(),
                  getHighlightedHex().getCol());
          break;
        default:
          super.keyPressed(event);
          break;
      }
    }
  }

  public void showHints() {
    if (hintEnabled) {

    }
  }


}
