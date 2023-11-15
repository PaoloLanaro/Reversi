import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.view.IView;
import cs3500.reversi.view.ReversiGraphicsView;

/**
 * The main class for the Reversi project.
 */
public final class Reversi {
  /**
   * Creates and runs a GUI game of Reversi. This currently includes just the GUI and no actual
   * game play functionality.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    BasicReversi model = new BasicReversi(4);
    IView view = new ReversiGraphicsView(model);
    view.setVisible(true);
  }
}


