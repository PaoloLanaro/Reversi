package cs3500.reversi;

import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.view.TextView;

/**
 * Tests for the view components of the project.
 */
public class ViewTests {

  @Test
  public void testViewStarts() {
    BasicReversi logic3 = new BasicReversi(3);
    TextView viewSize3 = new TextView(logic3);
    String expected = "  _ _ _ \n" +
            " _ X O _ \n" +
            "_ O _ X _ \n" +
            " _ X O _ \n" +
            "  _ _ _ \n";
    Assert.assertEquals(expected, viewSize3.toString());
  }

}
