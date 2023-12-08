package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.view.TextView;

public class SquareModelTests {

  private MutableReversi model4;
  private MutableReversi model6;
  private TextView viewSize4;
  private TextView viewSize6;


  @Before
  public void init() {
    model4 = new SquareReversi(4);
    model6 = new SquareReversi(6);
    viewSize4 = new TextView(model4);
    viewSize6 = new TextView(model6);
  }

//  @Test
//  public void test() {
//    model.startGame();
//    model.makeMove(0, 2);
//    model.makeMove(0, 0);
////    model.makeMove(0, 3);
//  }

  @Test
  public void initialGameStateTest() {

  }


}
