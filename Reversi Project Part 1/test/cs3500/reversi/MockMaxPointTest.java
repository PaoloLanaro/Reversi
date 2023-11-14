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

  // should break a tie on an initial 4 sided game,
  // where there are 6 possible moves that all grant the same point value.
  @Test
  public void testInitialMaxPointStratTieBreak() {
    Optional<RowCol> rowCol = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(rowCol.get().getRow(), rowCol.get().getCol());
    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Moved to row: 1 col: 4\n" +
            "Valid move.\n"));
  }

  @Test
  public void makeMaxPointStrategyForWhite() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    Assert.assertEquals(out.toString(), "Moved to row: 1 col: 4\n" +
            "Valid move.\n" +
            "Moved to row: 0 col: 5\n" +
            "Valid move.\n");
  }

  @Test
  public void makeMultipleMovesMaxPointStrategyEndOnBlackMove() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    basicModel.makeMove(0, 5);
    Optional<RowCol> secondBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    Assert.assertEquals(out.toString(), "Moved to row: 1 col: 4\n" +
            "Valid move.\n" +
            "Moved to row: 0 col: 5\n" +
            "Valid move.\n" +
            "Moved to row: 2 col: 2\n" + "Valid move.\n");
  }

  @Test
  public void makeMultipleMovesMaxPointStrategyEndOnWhiteMove() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    basicModel.makeMove(0, 5);
    Optional<RowCol> secondBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    basicModel.makeMove(2, 2);
    Optional<RowCol> secondWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.makeMove(secondWhiteMove.get().getRow(), secondWhiteMove.get().getCol());
    Assert.assertEquals(out.toString(), "Moved to row: 1 col: 4\n" +
            "Valid move.\n" +
            "Moved to row: 0 col: 5\n" +
            "Valid move.\n" +
            "Moved to row: 2 col: 2\n" +
            "Valid move.\n" +
            "Moved to row: 4 col: 1\n" +
            "Valid move.\n");
  }

}
