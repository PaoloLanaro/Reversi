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
    String expected = "- - - - \n" +
            "- X O - \n" +
            "- O X - \n" +
            "- - - - \n";
    Assert.assertEquals(expected, size4View.toString());
  }

  @Test
  public void testSquareViewWorksForBigBoards() {
    SquareReversi size20Game = new SquareReversi(20);
    SquareTextView size20View = new SquareTextView(size20Game);
    String expected = "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - X O - - - - - - - - - \n" +
            "- - - - - - - - - O X - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n" +
            "- - - - - - - - - - - - - - - - - - - - \n";
    Assert.assertEquals(expected, size20View.toString());
  }

}
