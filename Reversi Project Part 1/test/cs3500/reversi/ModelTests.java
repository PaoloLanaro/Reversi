package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.PlayerColor;
import cs3500.reversi.model.StandardBoard;
import cs3500.reversi.model.StandardGameLogic;


public class ModelTests {

  StandardBoard boardSize3;
  StandardBoard boardSize4;
  StandardGameLogic logicSize3;
  StandardGameLogic logicSize4;

  @Before
  public void init() {
    boardSize3 = new StandardBoard(3);
    boardSize4 = new StandardBoard(4);
    logicSize3 = new StandardGameLogic(boardSize3);
    logicSize4 = new StandardGameLogic(boardSize4);
  }

  @Test
  public void testModelStarts() {
    StandardBoard board = new StandardBoard(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelThrowsIAEWhenSizeNotValid() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new StandardBoard(2));
    new StandardBoard(-1);
  }

  @Test
  public void testValidUpperLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidUpperRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 3);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(3).getColor());
  }

  @Test
  public void testValidBottomRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 3, 3);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(3).get(3).getColor());
  }

  // TODO: IMPLEMENT THESE CHECKS

  @Test
  public void testValidLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidRightMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

  @Test
  public void testValidBottomLeftMove() {
    logicSize3.makeMove(PlayerColor.BLACK, 1, 1);
    List<List<Cell>> boardRepresentation =  boardSize3.getBoard();
    Assert.assertEquals(PlayerColor.BLACK, boardRepresentation.get(1).get(1).getColor());
  }

}
