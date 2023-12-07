import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.SmarterModel;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.players.HumanPlayer;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.model.strategy.GoForCornersStrategy;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.provider.model.GenericPlayer;
import cs3500.reversi.provider.model.ProviderModelAdapter;
import cs3500.reversi.provider.model.ProviderPlayerAdapter;
import cs3500.reversi.provider.view.ReversiModelView;
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
    if (args.length == 0 || args.length > 3) {
      throw new IllegalArgumentException("Incorrect amount of command line arguments.");
    }
    int gameSize;
    try {
      gameSize = Integer.parseInt(args[0]);
    } catch (Exception e) {
      throw new IllegalArgumentException("The first argument was not a valid number.");
    }
    MutableReversi delegate = new BasicReversi(gameSize);
    ISmarterModel model = new SmarterModel(delegate);
    IView player1View = new ReversiGraphicsView(model);
//    IView player2View = new ReversiGraphicsView(model);
    Player delegatePlayer = new HumanPlayer(model, DiscColor.BLACK);
    ProviderModelAdapter providerModel = new ProviderModelAdapter(gameSize);
    GenericPlayer player = new ProviderPlayerAdapter(delegatePlayer);
    ReversiModelView second = new cs3500.reversi.provider.view.ReversiGraphicsView(providerModel, player);

    List<Player> players = constructPlayers(args, gameSize);

    ReversiController controller1 = new GameController(model, players.get(0), player1View);
//    ReversiController controller2 = new GameController(model, players.get(1), player2View);
    ReversiController controller2 = new GameController(model, delegatePlayer, second);
    second.setVisible(true);
    //second.addFeatureListener(this);

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
        return new HumanPlayer(new BasicReversi(gameSize), playerColor);
      case "maxpointstrat":
        return new AIPlayer(playerColor, new MaxPointStrategy());
      case "cornerstrat":
        return new AIPlayer(playerColor, new GoForCornersStrategy());
      default:
        throw new IllegalArgumentException("Could not create player with argument " + arg);
    }
  }

}


