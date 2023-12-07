package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.SquareReversi;

public class SquareModelTests {

  private MutableReversi model;


  @Before
  public void init() {
    model = new SquareReversi(4);
  }

  @Test
  public void test() {
    model.startGame();
    model.makeMove(0, 2);
    model.makeMove(0, 0);
//    model.makeMove(0, 3);
  }



}
