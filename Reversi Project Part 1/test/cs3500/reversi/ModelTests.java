package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.view.TextView;
import cs3500.reversi.model.BasicReversi;


public class ModelTests {
  BasicReversi model3;
  BasicReversi model4;
  TextView viewSize3;
  TextView viewSize4;

  @Before
  public void init() {
    model3 = new BasicReversi(3);
    model4 = new BasicReversi(4);

    viewSize3 = new TextView(model3);

    viewSize4 = new TextView(model4);
  }

  @Test
  public void testModelStarts() {
    new BasicReversi(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelThrowsIAEWhenSizeNotValid() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new BasicReversi(2));
    new BasicReversi(-1);
  }

  @Test
  public void testValidBlackUpperLeftMove() {
    model3.makeMove( 1, 1);
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
    model3.makeMove( 3, 3);
    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.BLACK, boardRepresentation.get(3).get(3).getColor());
  }

  // TODO: IMPLEMENT THESE CHECKS

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
    model4.makeMove( 2, 1);
    List<List<Cell>> boardRepresentation = model4.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(2).get(1).getColor());
  }

  @Test
  public void testValidWhiteRightMove() {
    model4.makeMove( 2, 5);
    model4.makeMove( 4, 1);
    model4.makeMove( 5, 2);
    model4.makeMove( 4, 4);
    List<List<Cell>> boardRepresentation = model4.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(4).get(4).getColor());
  }

  @Test
  public void testValidWhiteBottomLeftMove() {
    model3.makeMove(3, 3);
    model3.makeMove(0, 3);
    System.out.println(viewSize3);
    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(0).get(3).getColor());
  }

  @Test
  public void testValidWhiteBottomRightMove() {
    model3.makeMove(3, 0);
    model3.makeMove(3, 3);
    System.out.println(viewSize3);
    List<List<Cell>> boardRepresentation = model3.getBoard();
    Assert.assertEquals(DiscColor.WHITE, boardRepresentation.get(3).get(3).getColor());
  }

//  @Test (expected = IllegalStateException.class)
//  public void testBug() {
//    model4.makeMove(PlayerColor.BLACK, 2, 5);
//    model4.makeMove(PlayerColor.WHITE, 4, 1);
//    model4.makeMove(PlayerColor.BLACK, 5, 3);
//  }

  @Test
  public void testFullGame() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove(0, 3);
    model3.makeMove(1, 4);
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0,
            2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove(0,
            2));
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

  @Test(expected = IllegalStateException.class)
  public void testWhiteMoveFirst() {
    model3.makeMove(0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOutOfBounds() {
    model3.makeMove(-1, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveAlreadyOccupied() {
    model4.makeMove( 2, 2);
    model4.makeMove(2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlaceOnNull() {
    model3.makeMove(0, 0);
  }

  @Test
  public void testIsGameOverFilledBoard() {
    model3.makeMove(4, 1);
    model3.makeMove(3, 3);
    model3.makeMove( 1, 4);
    model3.makeMove(3, 0);
    model3.makeMove( 1, 1);
    model3.makeMove( 0, 3);
    TextView view = new TextView(model3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovingEmptyCell() {
    model3.makeMove( 0, 0);
  }

  @Test
  public void testInvalidMoveAfterGameOver() {
    model3.makeMove( 3, 0);
    model3.makeMove( 1, 1);
    model3.makeMove(3, 3);
    model3.makeMove( 4, 1);
    model3.makeMove( 0, 3);
    model3.makeMove( 1, 4);
    Assert.assertThrows(IllegalStateException.class, () ->
            model3.makeMove( 0, 2));
    Assert.assertThrows(IllegalStateException.class, () ->
            model3.makeMove( 0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
    Assert.assertThrows(IllegalStateException.class, () ->
            model3.makeMove(0, 3));
  }

  @Test
  public void testInvalidPassAfterGameOver() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove( 0, 3);
    model3.makeMove( 1, 4);
    Assert.assertThrows(IllegalStateException.class, () ->
            model3.makeMove(0, 2));
    Assert.assertThrows(IllegalStateException.class, () ->
            model3.makeMove(0, 2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals("White won!", model3.getWinner());
    Assert.assertThrows(IllegalStateException.class, () -> model3.passTurn());
    System.out.println(viewSize3);
  }

  @Test
  public void testFullGameWhiteWon() {
    model3.makeMove(3, 0);
    model3.makeMove(1, 1);
    model3.makeMove(3, 3);
    model3.makeMove(4, 1);
    model3.makeMove( 0, 3);
    model3.makeMove( 1, 4);
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove( 0,
            2));
    Assert.assertThrows(IllegalStateException.class, () -> model3.makeMove( 0,
            2));
    Assert.assertTrue(model3.isGameOver());
    Assert.assertEquals(DiscColor.WHITE, model3.getWinnerColor());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerColorWhenGameIsNotOver() {
    model3.makeMove(3, 0);
    model3.makeMove( 1, 4);
    model3.getWinnerColor();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerBeforeGameOver() {
    model3.getWinner();
  }

  @Test
  public void testCantMutateByReversiGetBoardMethod() {
    model3.getBoard().get(2).set(1, new Cell());
    String expected = "  _ _ _ \n" +
            " _ X O _ \n" +
            "_ O _ X _ \n" +
            " _ X O _ \n" +
            "  _ _ _ \n";
    Assert.assertEquals(expected, viewSize3.toString());
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
    Assert.assertEquals(DiscColor.BLACK, model3.getWinnerColor());
  }

}
