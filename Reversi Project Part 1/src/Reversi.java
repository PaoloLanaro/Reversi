import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.players.HumanPlayer;
import cs3500.reversi.model.players.Player;
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
    MutableReversi model = new BasicReversi(3);
    IView player1View = new ReversiGraphicsView(model);
    IView player2View = new ReversiGraphicsView(model);
    Player player1 = new HumanPlayer();
    Player player2 = new HumanPlayer();
    ReversiController controller1 = new GameController(model, player1, player1View);
    ReversiController controller2 = new GameController(model, player2, player2View);

    model.startGame();
    controller1.playGame();
    controller2.playGame();

//    view.setVisible(true);
  }
}


