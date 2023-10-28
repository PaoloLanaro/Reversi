package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StandardBoard implements Board {

  private final int size;

  List<List<Cell>> board;

  public StandardBoard(int size) {
    this.size = size;
    board = initBoard();
  }

  private List<List<Cell>> initBoard() {

    int diameter = size * 2 - 1;
    int middleRow = diameter / 2;

    List<List<Cell>> finalBoard = new ArrayList<>(diameter);

    for (int row = 0; row < diameter; row++) {
      finalBoard.add(new ArrayList<>(diameter));
      for (int col = 0; col < diameter; col++) {
        if (row == middleRow) {
          finalBoard.get(row).add(new Cell());
        }
        if (row < middleRow) {
          nonMiddleHelper(col < middleRow - row, finalBoard, row);
        }
        if (row > middleRow) {
          nonMiddleHelper(col >= diameter - row + middleRow, finalBoard, row);
        }
      }
    }

    return finalBoard;
  }

  private static void nonMiddleHelper(boolean col, List<List<Cell>> finalBoard, int row) {
    if (col) {
      finalBoard.get(row).add(null);
    } else {
      finalBoard.get(row).add(new Cell());
    }
  }

  @Override
  public List<List<Cell>> getBoardState() {
    return new ArrayList<>(board);
  }

  @Override
  public boolean isMoveValid(Player player, int row, int col) {
    return false;
  }

  @Override
  public void makeMove(Player player, int row, int col) {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }
}
