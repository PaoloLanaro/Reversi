import java.security.Provider;
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
import cs3500.reversi.provider.model.GenericPlayer;
//import cs3500.reversi.provider.model.PlayerAiOne;
//import cs3500.reversi.provider.model.PlayerAiThree;
import cs3500.reversi.provider.model.ProviderModelAdapter;
import cs3500.reversi.provider.model.ProviderPlayerAdapter;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.model.TileType;
import cs3500.reversi.provider.view.ReversiModelView;
import cs3500.reversi.view.IView;
import cs3500.reversi.view.ReversiGraphicsView;
import cs3500.reversi.provider.view.ViewToProviderAdapter;

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
    MutableReversi model = new BasicReversi(gameSize);
    IView player1View = new ReversiGraphicsView(model);
    Player player1 = constructPlayer1(args[1], gameSize);
    ReversiController controller1 = new GameController(model, player1, player1View);

    ProviderModelAdapter adapter = new ProviderModelAdapter(gameSize);
//    ProviderPlayerAdapter player2 = constructPlayer2(args[2], adapter);
    ProviderPlayerAdapter player2 = constructPlayer2("playeraithree", adapter);
    ReversiModelView delegate = new cs3500.reversi.provider.view.ReversiGraphicsView(adapter,
            player2);
    ViewToProviderAdapter player2View = new ViewToProviderAdapter(delegate);
    ReversiController controller2 = new GameController(model, player2, player2View);

    controller1.playGame();
    controller2.playGame();


//    MutableReversi model = new BasicReversi(gameSize);
////    ISmarterModel model = new SmarterModel(delegate);
//    IView player1View = new ReversiGraphicsView(model);
////    IView player2View = new ReversiGraphicsView(model);
//    ProviderModelAdapter providerModel = new ProviderModelAdapter(gameSize);
//    GenericPlayer player = new ProviderPlayerAdapter(providerModel, DiscColor.WHITE);
//
//    cs3500.reversi.provider.view.ReversiGraphicsView player2View = new cs3500.reversi.provider.view.ReversiGraphicsView(providerModel,
//            player);
//
//    ViewToProviderAdapter view = new ViewToProviderAdapter(player2View);
//
//    List<Player> players = constructPlayers(args, gameSize);
//    ProviderPlayerAdapter adaptedPlayer = new ProviderPlayerAdapter(providerModel, DiscColor.WHITE);
//
//    ReversiController controller1 = new GameController(model, players.get(0), player1View);
////    ReversiController controller2 = new GameController(model, players.get(1), player2View);
//    ReversiController controller2 = new GameController( providerModel,
//            adaptedPlayer, view);

    model.startGame();

    controller1.playGame();
//    controller2.playGame();
  }

  private static Player constructPlayer1(String arg, int gameSize) {
    DiscColor playerColor = DiscColor.BLACK;
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

  private static ProviderPlayerAdapter constructPlayer2(String arg, ProviderModelAdapter model) {
    TileType playerColor = TileType.WHITE;
    DiscColor playercolor = DiscColor.WHITE;
    switch (arg) {
      case "playeraione":
        return new ProviderPlayerAdapter(model, playercolor);
      case "playeraithree":
        return new ProviderPlayerAdapter(model, playercolor);
      default:
        throw new IllegalArgumentException("Could not create player with argument" + arg);
    }
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


