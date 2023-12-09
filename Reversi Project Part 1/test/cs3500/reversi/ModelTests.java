package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.view.TextView;
import cs3500.reversi.model.HexReversi;

/**
 * Tests for model implementations of the reversi game.
 */
public class ModelTests {
  HexReversi model3;
  HexReversi model4;
  TextView viewSize3;
  TextView viewSize4;

  @Before
  public void init() {
    model3 = new HexReversi(3);
    model4 = new HexReversi(4);

    viewSize3 = new TextView(model3);
    viewSize4 = new TextView(model4);

    model3.startGame();
    model4.startGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelThrowsIAEWhenSizeNotValid() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new HexReversi(2));
    new HexReversi(-1);
  }

  @Test
  public void testValidBlackUpperLeftMove() {
    model3.makeMove(1, 1);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackUpperRightMove() {
    model3.makeMove(1, 4);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(1).get(3).getColor());
  }

  @Test
  public void testValidBlackBottomRightMove() {
    model3.makeMove(3, 3);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(3).get(3).getColor());
  }

  @Test
  public void testValidBlackLeftMove() {
    model3.makeMove(1, 1);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackRightMove() {
    model3.makeMove(1, 1);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackBottomLeftMove() {
    model3.makeMove(1, 1);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  //TestingWhiteMoves
  @Test
  public void testValidWhiteUpperLeftMove() {
    model3.makeMove(3, 3);
    model3.makeMove(0, 3);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(0).get(3).getColor());
  }

  @Test
  public void testValidWhiteUpperRightMove() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 4);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(1).get(4).getColor());
  }

  @Test
  public void testValidWhiteLeftMove() {
    model4.makeMove(2, 2);
    model4.makeMove(2, 1);

    List<List<Cell>> boardRepresentation = model4.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(2).get(1).getColor());
  }

  @Test
  public void testValidWhiteRightMove() {
    model4.makeMove(2, 5);
    model4.makeMove(4, 1);
    model4.makeMove(5, 2);
    model4.makeMove(4, 4);
    model4.makeMove(2, 2);
    model4.makeMove(1, 4);

    List<List<Cell>> boardRepresentation = model4.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(4).get(4).getColor());
  }

  @Test
  public void testValidWhiteBottomLeftMove() {
    model3.makeMove(3, 3);
    model3.makeMove(0, 3);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(0).get(3).getColor());
  }

  @Test
  public void testValidWhiteBottomRightMove() {
    model3.makeMove(3, 0);
    model3.makeMove(3, 3);

    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(3).get(3).getColor());
  }

  @Test
  public void testFullGame() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove(0, 3);
    model3.makeMove(1, 4);

    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
  }

  @Test
  public void testDoublePass() {
    model3.passTurn();
    model3.passTurn();

    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("Tied game!", model3.getWinner());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidStartMove() {
    model3.makeMove(0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOutOfBounds() {
    model3.makeMove(-1, -1);
  }

  @Test
  public void testInvalidMoveAlreadyOccupied() {
    model4.makeMove(2, 2);
    Assert.assertFalse(model4.isValidMove(2, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlaceOnNull() {
    model3.makeMove(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingEmptyCell() {
    model3.makeMove(0, 0);
  }

  @Test
  public void testInvalidMoveAfterGameOver() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove(0, 3);
    model3.makeMove(1, 4);

    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 3));
  }

  @Test
  public void testInvalidPassAfterGameOver() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove(0, 3);
    model3.makeMove(1, 4);

    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
    Assert.assertThrows(IllegalStateException.class, () -> model3.passTurn());
  }

  @Test
  public void testFullGameWhiteWon() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove(0, 3);
    model3.makeMove(1, 4);

    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerBeforeGameOver() {
    model3.getWinner();
  }

  @Test
  public void testFullGameBlackWon() {
    model3.makeMove(1, 4);
    model3.makeMove(4, 1);
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(0, 3);

    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("Black won!", model3.getWinner());
  }

  @Test
  public void testPassingWorks() {
    model3.passTurn();
    model3.makeMove(1, 4);

    int blackScore = model3.getScore().get(DiscColor.BLACK);
    int whiteScore = model3.getScore().get(DiscColor.WHITE);

    Assert.assertEquals(2, blackScore);
    Assert.assertEquals(5, whiteScore);
  }

  @Test
  public void testIsValidMoveOnInitialGame() {
    Assert.assertTrue(model3.isValidMove(0, 3));
  }

  @Test
  public void testIsValidMoveInMiddleOfGame() {
    model3.makeMove(1, 4);
    model3.makeMove(4, 1);
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    Assert.assertTrue(model3.isValidMove(3, 3));
    model3.makeMove(3, 3);
    Assert.assertTrue(model3.isValidMove(0, 3));
    model3.makeMove(0, 3);
    Assert.assertTrue(model3.isGameOver());
  }

  @Test
  public void testIsValidMoveInvalidMove() {
    Assert.assertThrows(IllegalArgumentException.class, () -> model3.isValidMove(0, 0));
    Assert.assertFalse(model3.isValidMove(0, 2));
    Assert.assertFalse(model3.isValidMove(0, 4));
    Assert.assertFalse(model3.isValidMove(2, 0));
    Assert.assertFalse(model3.isValidMove(4, 0));
  }

  @Test
  public void testGetTurnBlackTurn() {
    Assert.assertEquals("black", model3.getTurn());
  }

  @Test
  public void testGetTurnWhiteTurn() {
    model3.makeMove(0, 3);
    Assert.assertEquals("white", model3.getTurn());
  }

}
