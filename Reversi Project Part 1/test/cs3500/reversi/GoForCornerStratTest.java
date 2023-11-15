package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.MockBasicReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.strategy.GoForCornersStrategy;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.model.strategy.Strategy;

public class GoForCornerStratTest {

  BasicReversi basicModel;
  Strategy basicStrategy;
  StringBuilder out;
  MockBasicReversi mock;


  @Before
  public void init() {
    basicModel = new BasicReversi(3);
    basicStrategy = new GoForCornersStrategy();
    out = new StringBuilder();
    mock = new MockBasicReversi(3, out);
  }

  @Test
  public void testCornerStratBlackFavorsUpperLeftCornerPiece() {
    Optional<RowCol> rowCol = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(rowCol.get().getRow(), rowCol.get().getCol());
    mock.makeMove(rowCol.get().getRow(), rowCol.get().getCol());
    Assert.assertTrue(out.toString().contains("Moved to row: 0 col: 3"));
  }

  @Test
  public void testCornerStratWhiteChoosesCornerPiece() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(0, 3);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    Assert.assertTrue(out.toString().contains("Moved to row: 1 col: 4"));
  }

}
