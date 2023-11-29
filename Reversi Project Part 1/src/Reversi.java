import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.players.HumanPlayer;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.model.strategy.GoForCornersStrategy;
import cs3500.reversi.model.strategy.MaxPointStrategy;
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


//    Player player1 = new HumanPlayer(model, DiscColor.WHITE);
//    Player player2 = new HumanPlayer(model, DiscColor.BLACK);

    List<Player> players = constructPlayers(args);

    ReversiController controller1 = new GameController(model, players.get(0), player1View);
    ReversiController controller2 = new GameController(model, players.get(1), player2View);

    model.startGame();

    controller1.playGame();
    controller2.playGame();
  }

  private static List<Player> constructPlayers(String[] args) {
    if (args.length == 0 || args.length > 2) {
      throw new IllegalArgumentException("Incorrect amount of command line arguments");
    }
    List<Player> players = new ArrayList<>();

    players.add(playerFactory(args[0], DiscColor.BLACK));
    players.add(playerFactory(args[1], DiscColor.WHITE));

    return players;
  }

  private static Player playerFactory(String arg, DiscColor playerColor) {
    switch(arg) {
      case "human":
        return new HumanPlayer(new BasicReversi(6), playerColor);
      case "maxpointstrat":
        return new AIPlayer(playerColor, new MaxPointStrategy());
      case "cornerstrat":
        return new AIPlayer(playerColor, new GoForCornersStrategy());
      default:
        throw new IllegalArgumentException("Could not create player with argument " + arg);
    }
  }

}


