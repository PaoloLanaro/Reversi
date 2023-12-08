package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.model.MockBasicReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.strategy.Strategy;

/**
 * Test class for MockMaxPointStrat.
 */
public class MockMaxPointStratTest {
  HexReversi basicModel;
  Strategy basicStrategy;
  StringBuilder out;
  MockBasicReversi mock;


  @Before
  public void init() {
    basicModel = new HexReversi(4);
    basicStrategy = new MaxPointStrategy();
    out = new StringBuilder();
    mock = new MockBasicReversi(4, out);
    basicModel.startGame();
    mock.startGame();
  }

  // should break a tie on an initial 4 sided game,
  // where there are 6 possible moves that all grant the same point value.
  @Test
  public void testInitialMaxPointStratTieBreak() {
    Optional<RowCol> rowCol = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(rowCol.get().getRow(), rowCol.get().getCol());
    mock.makeMove(rowCol.get().getRow(), rowCol.get().getCol());
    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Checked move at (1, 4).\n" +
            "Moved to row: 1 col: 4\n"));
  }

  @Test
  public void makeMaxPointStrategyForWhite() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    //Checking that the white move chooses a hexagon that will flip the most disc.
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    Assert.assertTrue(out.toString().contains("Checked move at (1, 4).\n" +
            "Moved to row: 1 col: 4\n" +
            "Checked move at (0, 5).\n" +
            "Moved to row: 0 col: 5"));
  }

  @Test
  public void makeMultipleMovesMaxPointStrategyEndOnBlackMove() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    basicModel.makeMove(0, 5);
    //Checking that the third and final black move chooses a hexagon that will flip the most disc.
    Optional<RowCol> secondBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    Assert.assertTrue(out.toString().contains("Checked move at (1, 4).\n" +
            "Moved to row: 1 col: 4\n" +
            "Checked move at (1, 4).\n" +
            "Moved to row: 0 col: 5\n" +
            "Checked move at (1, 4).\n" +
            "Moved to row: 2 col: 2\n"));
  }

  @Test
  public void makeMultipleMovesMaxPointStrategyEndOnWhiteMove() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(1, 4);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    mock.isValidMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    basicModel.makeMove(0, 5);
    Optional<RowCol> secondBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.makeMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    mock.isValidMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    basicModel.makeMove(2, 2);
    //Checking that the fourth and final white move chooses a hexagon that will flip the most disc.
    Optional<RowCol> secondWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(secondWhiteMove.get().getRow(), secondWhiteMove.get().getCol());
    mock.makeMove(secondWhiteMove.get().getRow(), secondWhiteMove.get().getCol());
    System.out.println(out);
    Assert.assertTrue(out.toString().contains("Moved to row: 1 col: 4\n" +
            "Checked move at (1, 4).\n" +
            "Moved to row: 0 col: 5\n" +
            "Checked move at (0, 5).\n" +
            "Moved to row: 2 col: 2\n" +
            "Checked move at (2, 2).\n" +
            "Checked move at (4, 1).\n" +
            "Moved to row: 4 col: 1\n"));
  }

}
