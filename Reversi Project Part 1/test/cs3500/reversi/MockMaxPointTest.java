package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.MaxPointStrategy;
import cs3500.reversi.model.MockBasicReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.Strategy;

public class MockMaxPointTest {
  BasicReversi basicModel;
  Strategy basicStrategy;
  StringBuilder out;
  MockBasicReversi mock;

  @Before
  public void init() {
    basicModel = new BasicReversi(4);
    basicStrategy = new MaxPointStrategy();
    out = new StringBuilder();
    mock = new MockBasicReversi(4, out);
  }

  @Test
  public void test() {
    Optional<RowCol> rowCol = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(rowCol.get().row(), rowCol.get().col());
    Assert.assertTrue(out.toString().contains("Moved to row: 1 col: 4\n" +
            "Valid move.\n"));
  }

  @Test

}
