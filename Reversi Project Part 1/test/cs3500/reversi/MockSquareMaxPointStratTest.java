package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.MockSquareReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.model.strategy.MaxPointStrategy;
import cs3500.reversi.model.strategy.Strategy;

public class MockSquareMaxPointStratTest {
  SquareReversi basicModel;
  Strategy basicStrategy;
  StringBuilder out;
  MockSquareReversi mock;

  @Before
  public void init() {
    basicModel = new SquareReversi(4);
    basicStrategy = new MaxPointStrategy();
    out = new StringBuilder();
    mock = new MockSquareReversi(4, out);
    basicModel.startGame();
    mock.startGame();
  }

  @Test
  public void testMaxPointStratBlackFavorsUpperLeftCornerPiece() {
    Optional<RowCol> rowCol = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    System.out.println(rowCol);
    mock.isValidMove(rowCol.get().getRow(), rowCol.get().getCol());
    mock.makeMove(rowCol.get().getRow(), rowCol.get().getCol());
    Assert.assertTrue(out.toString().contains("Moved to row: 0 col: 2"));
  }

  @Test
  public void testMaxPointStrategyForWhite() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(0, 2);
    //Checking that the white move chooses a hexagon that will flip the most disc.
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    Assert.assertTrue(out.toString().contains("Checked move at (0, 2).\n" +
            "Moved to row: 0 col: 2\n" +
            "Checked move at (0, 1).\n" +
            "Moved to row: 0 col: 1"));
  }

  @Test
  public void makeMultipleMovesMaxPointStrategyEndOnBlackMove() {
    Optional<RowCol> firstBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    mock.makeMove(firstBlackMove.get().getRow(), firstBlackMove.get().getCol());
    basicModel.makeMove(0, 2);
    Optional<RowCol> firstWhiteMove = basicStrategy.chooseMove(basicModel, DiscColor.WHITE);
    mock.isValidMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    mock.makeMove(firstWhiteMove.get().getRow(), firstWhiteMove.get().getCol());
    basicModel.makeMove(0, 1);
    //Checking that the third and final black move chooses a hexagon that will flip the most disc.
    Optional<RowCol> secondBlackMove = basicStrategy.chooseMove(basicModel, DiscColor.BLACK);
    mock.isValidMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    mock.makeMove(secondBlackMove.get().getRow(), secondBlackMove.get().getCol());
    Assert.assertTrue(out.toString().contains("Checked move at (0, 2).\n" +
            "Moved to row: 0 col: 2\n" +
            "Checked move at (0, 1).\n" +
            "Moved to row: 0 col: 1\n" +
            "Checked move at (0, 0).\n" +
            "Moved to row: 0 col: 0\n"));
  }

}
