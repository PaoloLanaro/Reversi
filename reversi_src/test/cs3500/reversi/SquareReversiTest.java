package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.view.SquareTextView;


/**
 * Tests for Square model implementations of the reversi game.
 */
public class SquareReversiTest {

  SquareReversi board4;
  SquareReversi board6;
  SquareTextView view4;

  @Before
  public void init() {
    board4 = new SquareReversi(4);
    board6 = new SquareReversi(6);
    view4 = new SquareTextView(board4);
    board4.startGame();
    board6.startGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGameStartOddBoardTest() {
    SquareReversi oddBoard = new SquareReversi(5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGameStartBelowMinSizeTest() {
    SquareReversi tooSmallBoard = new SquareReversi(3);
  }

  @Test
  public void testDoublePass() {
    board4.passTurn();
    board4.passTurn();

    Assert.assertTrue(board4.isGameOver());
    Assert.assertEquals("Tied game!", board4.getWinner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartMove() {
    board4.makeMove(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOutOfBounds() {
    board4.makeMove(-1, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidMoveAfterGameOverTest() {
    board4.makeMove(1, 3);
    board4.makeMove(2, 3);
    board4.makeMove(3, 3);
    board4.makeMove(0, 1);
    board4.makeMove(1, 0);
    board4.makeMove(0, 3);
    board4.makeMove(0, 2);
    board4.passTurn();
    board4.makeMove(2, 0);
    board4.makeMove(3, 1);
    board4.makeMove(3, 0);
    board4.passTurn();
    board4.makeMove(3, 2);
    board4.passTurn();
    board4.makeMove(0, 0);
    board4.makeMove(1, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidPassAfterGameOver() {
    board4.makeMove(1, 3);
    board4.makeMove(2, 3);
    board4.makeMove(3, 3);
    board4.makeMove(0, 1);
    board4.makeMove(1, 0);
    board4.makeMove(0, 3);
    board4.makeMove(0, 2);
    board4.passTurn();
    board4.makeMove(2, 0);
    board4.makeMove(3, 1);
    board4.makeMove(3, 0);
    board4.passTurn();
    board4.makeMove(3, 2);
    board4.passTurn();
    board4.makeMove(0, 0);
    board4.passTurn();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingEmptyCell() {
    board6.makeMove(0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void getWinnerBeforeGameOverTest() {
    board4.getWinner();
  }

  @Test
  public void getTiedWinner() {
    board4.passTurn();
    board4.passTurn();
    Assert.assertEquals("Tied game!", board4.getWinner());
  }

  @Test
  public void blackWonTest() {
    board4.makeMove(1, 3);
    board4.makeMove(2, 3);
    board4.makeMove(3, 3);
    board4.makeMove(0, 1);
    board4.makeMove(1, 0);
    board4.makeMove(0, 3);
    board4.makeMove(0, 2);
    board4.passTurn();
    board4.makeMove(2, 0);
    board4.makeMove(3, 1);
    board4.makeMove(3, 0);
    board4.passTurn();
    board4.makeMove(3, 2);
    board4.passTurn();
    board4.makeMove(0, 0);
    Assert.assertEquals("Black won!", board4.getWinner());
  }

  @Test
  public void whiteWonTest() {
    board4.makeMove(1, 3);
    board4.makeMove(2, 3);
    board4.passTurn();
    board4.makeMove(0, 1);
    board4.passTurn();
    board4.passTurn();
    Assert.assertEquals("White won!", board4.getWinner());
  }

  @Test
  public void doublePassEndsGameTest() {
    board4.passTurn();
    board4.passTurn();
    Assert.assertTrue(board4.isGameOver());
  }

  @Test
  public void getTurnForBlack() {
    Assert.assertEquals("black", board4.getTurn());
  }

  @Test
  public void getTurnForWhite() {
    board4.makeMove(1, 3);
    Assert.assertEquals("white", board4.getTurn());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRowColChecks() {
    board4.isValidMove(-1, -1);
  }

  @Test
  public void checkingOccupiedCellTest() {
    Assert.assertFalse(board4.isValidMove(1, 1));
  }

  @Test
  public void getCellAtTest() {
    Cell expectedCell = new ReversiCell(DiscColor.BLACK, 1, 1);
    Cell resultCell = board4.getCellAt(1, 1);
    Assert.assertEquals(expectedCell.getColor(), resultCell.getColor());
    Assert.assertEquals(expectedCell.getRow(), resultCell.getRow());
    Assert.assertEquals(expectedCell.getCol(), resultCell.getCol());
  }

  @Test
  public void getCellColorTest() {
    Assert.assertEquals(DiscColor.BLACK, board4.getCellColor(1, 1));
  }

  @Test
  public void testGetScoreFor() {
    Assert.assertEquals(2, board4.getScoreFor(0, 2));
  }

}
