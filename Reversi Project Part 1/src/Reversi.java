//import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.SmarterModel;
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
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
//    if (args.length == 0 || args.length > 3) {
//      throw new IllegalArgumentException("Incorrect amount of command line arguments.");
//    }
//    int gameSize;
//    try {
//      gameSize = Integer.parseInt(args[0]);
//    } catch (Exception e) {
//      throw new IllegalArgumentException("The first argument was not a valid number.");
//    }
//    MutableReversi model = new BasicReversi(gameSize);
//    IView player1View = new ReversiGraphicsView(model);
//    Player player1 = constructPlayer1(args[1], gameSize);
//    ReversiController controller1 = new GameController(model, player1, player1View);
//
//    ProviderModelAdapter adapter = new ProviderModelAdapter(gameSize);
//    ProviderPlayerAdapter player2 = constructPlayer2(args[2], adapter);
//    ReversiModelView delegate = new cs3500.reversi.provider.view.ReversiGraphicsView(adapter,
//            player2);
//    ViewToProviderAdapter player2View = new ViewToProviderAdapter(delegate);
//    ReversiController controller2 = new GameController(model, player2, player2View);
//
//    controller1.playGame();
//    controller2.playGame();
//
//    model.startGame();
    if (args.length == 0 || args.length > 3) {
      throw new IllegalArgumentException("Incorrect amount of command line arguments.");
    }
    int gameSize;
    try {
      gameSize = Integer.parseInt(args[0]);
    } catch (Exception e) {
      throw new IllegalArgumentException("The first argument was not a valid number.");
    }
    MutableReversi delegate = new HexReversi(gameSize);
    ISmarterModel model = new SmarterModel(delegate);
    IView player1View = new ReversiGraphicsView(model);
    IView player2View = new ReversiGraphicsView(model);

    List<Player> players = constructPlayers(args, gameSize);

    ReversiController controller1 = new GameController(model, players.get(0), player1View);
    ReversiController controller2 = new GameController(model, players.get(1), player2View);

    model.startGame();

    controller1.playGame();
    controller2.playGame();
  }

  private static List<Player> constructPlayers(String[] args, int gameSize) {
    List<Player> players = new ArrayList<>();

    players.add(playerFactory(args[1], DiscColor.BLACK, gameSize));
    players.add(playerFactory(args[2], DiscColor.WHITE, gameSize));

    return players;
  }


  private static Player playerFactory(String arg, DiscColor playerColor, int gameSize) {

    switch (arg) {
      case "human":
        return new HumanPlayer(new HexReversi(gameSize), playerColor);
      case "maxpointstrat":
        return new AIPlayer(playerColor, new MaxPointStrategy());
      case "cornerstrat":
        return new AIPlayer(playerColor, new GoForCornersStrategy());
      default:
        throw new IllegalArgumentException("Could not create player with argument: " + arg);
    }
  }

}


