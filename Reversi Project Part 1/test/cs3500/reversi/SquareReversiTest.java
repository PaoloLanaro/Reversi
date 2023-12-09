package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.model.SquareReversi;


/**
 * Tests for Hex model implementations of the reversi game.
 */
public class SquareReversiTest {

  SquareReversi board4;
  SquareReversi board6;


  @Before
  public void init() {
    board4 = new SquareReversi(4);
    board6 = new SquareReversi(6);
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
  public void moveAfterGameOverTest() {

  }

}
