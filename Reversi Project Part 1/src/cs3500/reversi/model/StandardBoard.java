package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StandardBoard implements Board {

  private final int size;
  private List<List<Cell>> board;

  public StandardBoard(int size) {
    if (size <= 2) {
      throw new IllegalArgumentException("Top side length of the board cannot be less than or " +
              "equal to two.");
    }
    this.size = size;
    board = initBoard();
  }

  private List<List<Cell>> initBoard() {
    int diameter = size * 2 - 1;
    int middleRow = diameter / 2;

    List<List<Cell>> initialList = new ArrayList<>(diameter);

    initializeMatrix(diameter, initialList);

    setCellNeighbors(diameter, initialList);

    nullSetSpaghetti(diameter, middleRow, initialList);

    setStarterDiscs(middleRow, initialList);

    return initialList;
  }

  private static void setStarterDiscs(int middleRow, List<List<Cell>> initialList) {
    initialList.get(middleRow).get(middleRow - 1).setDiscColor(PlayerColor.WHITE);
    initialList.get(middleRow - 1).get(middleRow + 1).setDiscColor(PlayerColor.WHITE);
    initialList.get(middleRow + 1).get(middleRow).setDiscColor(PlayerColor.WHITE);

    initialList.get(middleRow).get(middleRow + 1).setDiscColor(PlayerColor.BLACK);
    initialList.get(middleRow - 1).get(middleRow).setDiscColor(PlayerColor.BLACK);
    initialList.get(middleRow + 1).get(middleRow - 1).setDiscColor(PlayerColor.BLACK);
  }

  private static void nullSetSpaghetti(int diameter, int middleRow, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        if (row < middleRow) {
          nonMiddleHelper(col < middleRow - row, initialList, row, col);
        }
        if (row > middleRow) {
          nonMiddleHelper(col >= diameter - row + middleRow, initialList, row, col);
        }
      }
    }
  }

  private static void initializeMatrix(int diameter, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      initialList.add(new ArrayList<>(diameter));
      for (int col = 0; col < diameter; col++) {
        Cell cell = new Cell();
        initialList.get(row).add(cell);
      }
    }
  }

  // Set hexagonal neighbors for each cell
  private static void setCellNeighbors(int diameter, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        Cell cell = initialList.get(row).get(col);
        if (row > 0) {
          cell.setUpperLeft(initialList.get(row - 1).get(col));
          if (col > 0) {
            cell.setUpperRight(initialList.get(row - 1).get(col - 1));
          }
        }
        if (row < diameter - 1) {
          cell.setBottomLeft(initialList.get(row + 1).get(col));
          if (col < diameter - 1) {
            cell.setBottomRight(initialList.get(row + 1).get(col + 1));
          }
        }
        if (col > 0) {
          cell.setLeft(initialList.get(row).get(col - 1));
        }
        if (col < diameter - 1) {
          cell.setRight(initialList.get(row).get(col + 1));
        }
      }
    }
  }

  private static void nonMiddleHelper(boolean nullSet, List<List<Cell>> finalBoard, int row,
                                      int col) {
    if (nullSet) {
      finalBoard.get(row).set(col, null);
    }
  }

  @Override
  public List<List<Cell>> getBoard() {
    return new ArrayList<>(board);
  }

  @Override
  public boolean isMoveValid(Player player, int row, int col) {
    return false;
  }

  @Override
  public void makeMove(Player player, int row, int col) {
  StandardGameLogic logic = new StandardGameLogic(this);
  logic.makeMove(player.getColor(), row, col);
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
