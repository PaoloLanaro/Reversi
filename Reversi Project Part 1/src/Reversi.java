import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.players.HumanPlayer;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.model.strategy.GoForCornersStrategy;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.view.IView;
import cs3500.reversi.view.HexReversiFrame;
import cs3500.reversi.view.SquareReversiFrame;

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
    if (args.length == 0 || args.length > 4) {
      throw new IllegalArgumentException("Incorrect amount of command line arguments.");
    }
    int gameSize;
    try {
      gameSize = Integer.parseInt(args[0]);
    } catch (Exception e) {
      throw new IllegalArgumentException("The first argument was not a valid number.");
    }

    MutableReversi game = makeGame(args[1], gameSize);
    List<IView> views = constructViews(args[1], game);
    List<Player> players = constructPlayers(args, gameSize);

    ReversiController controller1 = new GameController(game, players.get(0), views.get(0));
    ReversiController controller2 = new GameController(game, players.get(1), views.get(1));

    game.startGame();

    controller1.playGame();
    controller2.playGame();

  }

  private static MutableReversi makeGame(String arg, int gameSize) {
    switch (arg) {
      case "hex":
        return new HexReversi(gameSize);
      case "square":
        return new SquareReversi(gameSize);
      default:
        throw new IllegalArgumentException("Can't make a game of type: " + arg);
    }
  }

  private static List<IView> constructViews(String arg, MutableReversi game) {
    List<IView> views = new ArrayList<>();

    views.add(viewFactory(arg, game));
    views.add(viewFactory(arg, game));

    return views;
  }

  private static IView viewFactory(String arg, MutableReversi model) {
    switch (arg) {
      case "hex":
        return new HexReversiFrame(model);
      case "square":
        return new SquareReversiFrame(model);
      default:
        throw new IllegalArgumentException("Couldn't create GUI of type: " + arg);
    }
  }

  private static List<Player> constructPlayers(String[] args, int gameSize) {
    List<Player> players = new ArrayList<>();

    players.add(playerFactory(args[2], DiscColor.BLACK, gameSize));
    players.add(playerFactory(args[3], DiscColor.WHITE, gameSize));

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


