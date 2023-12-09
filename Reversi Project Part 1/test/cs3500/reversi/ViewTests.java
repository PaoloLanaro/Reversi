package cs3500.reversi;

import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.view.HexTextView;
import cs3500.reversi.view.SquareTextView;

/**
 * Tests for the view components of the project.
 */
public class ViewTests {

  @Test
  public void testHexViewStarts() {
    HexReversi logic3 = new HexReversi(3);
    HexTextView viewSize3 = new HexTextView(logic3);
    String expected = "  _ _ _ \n" +
            " _ X O _ \n" +
            "_ O _ X _ \n" +
            " _ X O _ \n" +
            "  _ _ _ \n";
    Assert.assertEquals(expected, viewSize3.toString());
  }

  @Test
  public void testSquareViewStarts() {
    SquareReversi size4Game = new SquareReversi(4);
    SquareTextView size4View = new SquareTextView(size4Game);
    System.out.println(size4View);
  }

}
