package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.PlayerColor;
import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.model.StandardGameLogic;
import cs3500.reversi.view.TextView;


public class ModelTests {

  StandardBoard boardSize3;
  StandardBoard boardSize4;
  StandardGameLogic logicSize3;
  StandardGameLogic logicSize4;
  TextView viewSize3;
  TextView viewSize4;

  @Before
  public void init() {
    boardSize3 = new StandardBoard(3);
    boardSize4 = new StandardBoard(4);
    logicSize3 = new StandardGameLogic(boardSize3);
    logicSize4 = new StandardGameLogic(boardSize4);

    viewSize3 = new TextView(boardSize3);

    viewSize4 = new TextView(boardSize4);
  }

  @Test
  public void testModelStarts() {
    new StandardBoard(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelThrowsIAEWhenSizeNotValid() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new StandardBoard(2));
    new StandardBoard(-1);
  }

  //testing black moves
  @Test
  public void testValidBlackUpperLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackUpperRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 4);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(3).getColor());
  }

  @Test
  public void testValidBlackBottomRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(3).get(3).getColor());
  }

  // TODO: IMPLEMENT THESE CHECKS

  @Test
  public void testValidBlackLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBlackBottomLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  //TestingWhiteMoves
  @Test
  public void testValidWhiteUpperLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 0, 3);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(0).get(3).getColor());
  }

  @Test
  public void testValidWhiteUpperRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 0);
    logicSize3.makeMove(PlayerColor.WHITE, 1, 4);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(1).get(4).getColor());
  }

  @Test
  public void testValidWhiteLeftMove() {
    logicSize4.makeMove(PlayerColor.BLACK, 2, 2);
    logicSize4.makeMove(PlayerColor.WHITE, 2, 1);
    List<List<Cell>> boardRepresentation = boardSize4.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(2).get(1).getColor());
  }

  @Test
  public void testValidWhiteRightMove() {
    logicSize4.makeMove(PlayerColor.BLACK, 2, 5);
    logicSize4.makeMove(PlayerColor.WHITE, 4, 1);
    logicSize4.makeMove(PlayerColor.BLACK, 5, 2);
    logicSize4.makeMove(PlayerColor.WHITE, 4, 4);
    List<List<Cell>> boardRepresentation = boardSize4.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(4).get(4).getColor());
  }

  @Test
  public void testValidWhiteBottomLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 0, 3);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(0).get(3).getColor());
  }

  @Test
  public void testValidWhiteBottomRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 0);
    logicSize3.makeMove(PlayerColor.WHITE, 3, 3);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.WHITE, boardRepresentation.get(3).get(3).getColor());
  }

  @Test (expected = IllegalStateException.class)
  public void testBug() {
    logicSize4.makeMove(PlayerColor.BLACK, 2, 5);
    logicSize4.makeMove(PlayerColor.WHITE, 4, 1);
    logicSize4.makeMove(PlayerColor.BLACK, 5, 3);
  }

  @Test
  public void testFullGame() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 0);
    logicSize3.makeMove(PlayerColor.WHITE, 1, 1);
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 4, 1);
    logicSize3.makeMove(PlayerColor.BLACK, 0, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 1, 4);
    Assert.assertThrows(IllegalStateException.class, () -> logicSize3.makeMove(PlayerColor.BLACK, 0,
            2));
    Assert.assertThrows(IllegalStateException.class, () -> logicSize3.makeMove(PlayerColor.WHITE, 0,
            2));
    Assert.assertTrue(logicSize3.isGameOver());
    Assert.assertEquals("White won!", logicSize3.getWinner());
  }

  @Test
  public void testDoublePass() {
    logicSize3.passTurn();
    logicSize3.passTurn();
    Assert.assertTrue(logicSize3.isGameOver());
    Assert.assertEquals("Tied game!" ,logicSize3.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void testWhiteMoveFirst() {
    logicSize3.makeMove(PlayerColor.WHITE, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOutOfBounds() {
    logicSize3.makeMove(PlayerColor.BLACK, -1, -1);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveAlreadyOccupied() {
    logicSize4.makeMove(PlayerColor.BLACK, 2, 2);
    logicSize4.makeMove(PlayerColor.WHITE,2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlaceOnNull() {
    logicSize3.makeMove(PlayerColor.BLACK, 0, 0);
  }

  @Test
  public void testIsGameOverFilledBoard() {
    logicSize3.makeMove(PlayerColor.BLACK, 4, 1);
    logicSize3.makeMove(PlayerColor.WHITE, 3, 3);
    logicSize3.makeMove(PlayerColor.BLACK, 1, 4);
    logicSize3.makeMove(PlayerColor.WHITE, 3, 0);
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    logicSize3.makeMove(PlayerColor.WHITE, 0, 3);
    List<List<Cell>> boardRepresentation = boardSize3.getBoard();
    TextView view = new TextView(boardSize3);
    System.out.println(view);
  }

  @Test
  public void testInvalidMoveAfterGameOver() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 0);
    logicSize3.makeMove(PlayerColor.WHITE, 1, 1);
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 4, 1);
    logicSize3.makeMove(PlayerColor.BLACK, 0, 3);
    logicSize3.makeMove(PlayerColor.WHITE, 1, 4);
    Assert.assertThrows(IllegalStateException.class, () ->
            logicSize3.makeMove(PlayerColor.BLACK, 0, 2));
    Assert.assertThrows(IllegalStateException.class, () ->
            logicSize3.makeMove(PlayerColor.WHITE, 0, 2));
    Assert.assertTrue(logicSize3.isGameOver());
    Assert.assertEquals("White won!", logicSize3.getWinner());
    Assert.assertThrows(IllegalStateException.class, () -> logicSize3.passTurn());
  }

}
