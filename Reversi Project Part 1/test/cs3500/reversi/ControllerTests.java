package cs3500.reversi;

import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.SmarterModel;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.view.IView;
import cs3500.reversi.view.ReversiGraphicsView;

/**
 * Tests for the controller side of the assignment
 */
public class ControllerTests {

  private IView view;
  private IView view2;
  private MutableReversi model;
  private ISmarterModel smartModel;
  private GameController controller1;
  private GameController controller2;
  private ReadOnlyReversi readOnlyModel;
  private AIPlayer ai;
  private AIPlayer ai2;

  @Before
  public void init() {
    this.model = new BasicReversi(3);
    this.smartModel = new SmarterModel(model);
    this.view = new ReversiGraphicsView(smartModel);
    this.view2 = new ReversiGraphicsView(smartModel);
    this.ai = new AIPlayer(DiscColor.BLACK, new MaxPointStrategy());
    this.ai2 = new AIPlayer(DiscColor.WHITE, new MaxPointStrategy());
    this.controller1 = new GameController(smartModel, ai, view);
    this.controller2 = new GameController(smartModel, ai2, view2);
  }

  @Test
  public void tests() {

  }

}
