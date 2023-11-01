package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a basic implementation of a basic hexagon Reversi Game. Implements the MutableReversi
 * interface to give methods for making moves, passing turns, and checking game state. The game is
 * played on a hex grid with a board size variable.
 */
public class BasicReversi implements MutableReversi {
  private int passCounter; // Counter for consecutive passes
  private DiscColor turn; // Current player's turn (BLACK or WHITE)
  private final List<List<Cell>> board; // 2D list representing the game board
  private final int initSize; // Initial size of the board
  private static final String UPPER_LEFT = "ul";
  private static final String UPPER_RIGHT = "ur";
  private static final String LEFT = "l";
  private static final String RIGHT = "r";
  private static final String BOTTOM_LEFT = "bl";
  private static final String BOTTOM_RIGHT = "br";

  /**
   * Constructs a new BasicReversi with a specified initial board size.
   *
   * @param initSize side length of the board.
   * @throws IllegalArgumentException if the initial board size is less than or equal to 2.
   */
  public BasicReversi(int initSize) {
    turn = DiscColor.BLACK;
    if (initSize <= 2) {
      throw new IllegalArgumentException("Top side length of the board cannot be less than or " +
              "equal to two.");
    }
    this.initSize = initSize;
    board = initBoard();
  }

  @Override
  public void makeMove(int row, int col) {
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    int maxLength = board.size();
    if (row < 0 || col < 0 || row > maxLength - 1 || col > maxLength - 1) {
      throw new IllegalArgumentException("Illegal row or column input");
    }
    Cell originCell = board.get(row).get(col);
    if (originCell == null) {
      throw new IllegalArgumentException("Invalid move attempt, trying to place on null");
    }
    if (originCell.getColor() != DiscColor.EMPTY) {
      throw new IllegalStateException("Tried to play on a non-empty cell");
    }
    if (requiredPlayerPassCheck()) {
      passTurn();
      throw new IllegalStateException("Cannot move, so your turn has been passed.");
    }
    
    setValidDiscs(originCell, turn);
    turn = turn == DiscColor.BLACK ? DiscColor.WHITE : DiscColor.BLACK;
  }

  @Override
  public void passTurn() {
    if (isGameOver()) {
      throw new IllegalStateException("Can't pass a player's move when the game is already over");
    }
    passCounter++;
  }

  private DiscColor getOppositeColor(DiscColor color) {
    if (color == DiscColor.WHITE) {
      return DiscColor.BLACK;
    } else {
      return DiscColor.WHITE;
    }
  }

  private void setValidDiscs(Cell originCell, DiscColor playerColor) {
    Map<String, List<Cell>> routesMap = getRunsForCell(originCell, playerColor);

    List<List<Cell>> validRuns = new ArrayList<>();

    String[] directions = {UPPER_LEFT, UPPER_RIGHT, LEFT, RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};

    for (String direction : directions) {
      List<Cell> run = routesMap.get(direction);
      if (run != null && !run.isEmpty() && run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }

    if (validRuns.isEmpty()) {
      throw new IllegalStateException("Not a valid move");
    }

    for (List<Cell> singleRun : validRuns) {
      for (Cell runCell : singleRun) {
        runCell.setDiscColor(playerColor);
      }
    }

    passCounter = 0;
  }

  private Map<String, List<Cell>> getRunsForCell(Cell originCell, DiscColor playerColor) {
    Map<String, List<Cell>> routesMap = new HashMap<>();
    DiscColor oppositeColor = getOppositeColor(playerColor);
    checkAndAddDirection(UPPER_LEFT, routesMap, originCell.getUpperLeft(), oppositeColor,
            playerColor);
    checkAndAddDirection(UPPER_RIGHT, routesMap, originCell.getUpperRight(), oppositeColor,
            playerColor);
    checkAndAddDirection(LEFT, routesMap, originCell.getLeft(), oppositeColor,
            playerColor);
    checkAndAddDirection(RIGHT, routesMap, originCell.getRight(), oppositeColor, playerColor);
    checkAndAddDirection(BOTTOM_LEFT, routesMap, originCell.getBottomLeft(), oppositeColor,
            playerColor);
    checkAndAddDirection(BOTTOM_RIGHT, routesMap, originCell.getBottomRight(), oppositeColor,
            playerColor);
    return routesMap;
  }

  private void checkAndAddDirection(String direction, Map<String, List<Cell>> routesMap, Cell cell,
                                    DiscColor oppositeColor, DiscColor playerColor) {
    if (cell != null && cell.getColor() == oppositeColor) {
      routesMap.put(direction, traverseHelper(direction, playerColor, cell));
    }
  }

  private List<Cell> traverseHelper(String dir, DiscColor color, Cell currCell) {
    List<Cell> routeList = new ArrayList<>();
    traverse(dir, color, currCell, routeList);
    return routeList;
  }


  private void traverse(String dir, DiscColor originalColor, Cell currCell,
                        List<Cell> currentRun) {
    if (currCell.getColor() == originalColor) {
      currentRun.add(currCell);
      return;
    }

    if (currCell.getColor() == DiscColor.EMPTY) {
      return;
    }

    switch (dir) {
      case UPPER_LEFT:
        if (currCell.getUpperLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperLeft(), currentRun);
        break;
      case UPPER_RIGHT:
        if (currCell.getUpperRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperRight(), currentRun);
        break;
      case LEFT:
        if (currCell.getLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getLeft(), currentRun);
        break;
      case RIGHT:
        if (currCell.getRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getRight(), currentRun);
        break;
      case BOTTOM_LEFT:
        if (currCell.getBottomLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getBottomLeft(), currentRun);
        break;
      case BOTTOM_RIGHT:
        if (currCell.getBottomRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getBottomRight(), currentRun);
        break;
    }
  }


  private boolean requiredPlayerPassCheck() {
    return getValidMoves(turn).isEmpty();
  }

  private List<List<Cell>> initBoard() {
    int diameter = initSize * 2 - 1;
    int middleRow = diameter / 2;

    List<List<Cell>> initialList = new ArrayList<>(diameter);

    initializeMatrix(diameter, initialList);

    nonMiddleRowNullSetHelper(diameter, middleRow, initialList);

    setCellNeighbors(diameter, initialList);

    setStarterDiscs(middleRow, initialList);

    return initialList;
  }

  private void setStarterDiscs(int middleRow, List<List<Cell>> initialList) {
    initialList.get(middleRow).get(middleRow - 1).setDiscColor(DiscColor.WHITE);
    initialList.get(middleRow - 1).get(middleRow + 1).setDiscColor(DiscColor.WHITE);
    initialList.get(middleRow + 1).get(middleRow).setDiscColor(DiscColor.WHITE);

    initialList.get(middleRow).get(middleRow + 1).setDiscColor(DiscColor.BLACK);
    initialList.get(middleRow - 1).get(middleRow).setDiscColor(DiscColor.BLACK);
    initialList.get(middleRow + 1).get(middleRow - 1).setDiscColor(DiscColor.BLACK);
  }

  private void nonMiddleRowNullSetHelper(int diameter, int middleRow, List<List<Cell>> initialList) {
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

  private void initializeMatrix(int diameter, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      initialList.add(new ArrayList<>(diameter));
      for (int col = 0; col < diameter; col++) {
        Cell cell = new Cell();
        initialList.get(row).add(cell);
      }
    }
  }

  // Set hexagonal neighbors for each cell
  private Cell getNeighborCell(List<List<Cell>> initialList, int row, int col) {
    try {
      return Objects.requireNonNull(initialList.get(row).get(col));
    } catch (NullPointerException | IndexOutOfBoundsException e) {
      return null;
    }
  }

  private void setCellNeighbors(int diameter, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        Cell currentCell = initialList.get(row).get(col);
        if (currentCell != null) {
          currentCell.setUpperLeft(getNeighborCell(initialList, row - 1, col));
          currentCell.setUpperRight(getNeighborCell(initialList, row - 1, col + 1));
          currentCell.setLeft(getNeighborCell(initialList, row, col - 1));
          currentCell.setRight(getNeighborCell(initialList, row, col + 1));
          currentCell.setBottomLeft(getNeighborCell(initialList, row + 1, col - 1));
          currentCell.setBottomRight(getNeighborCell(initialList, row + 1, col));
        }
      }
    }
  }

  private void nonMiddleHelper(boolean nullSet, List<List<Cell>> finalBoard, int row, int col) {
    if (nullSet) {
      finalBoard.get(row).set(col, null);
    }
  }

  @Override
  public List<List<Cell>> getBoard() {
    List<List<Cell>> deepCopy = new ArrayList<>();
    for (int row = 0; row < board.size(); row++) {
      List<Cell> rowList = new ArrayList<>();
      for (int col = 0; col < board.size(); col++) {
        if (board.get(row).get(col) == null) {
          rowList.add(null);
          continue;
        }
        rowList.add(new Cell(board.get(row).get(col)));
      }
      deepCopy.add(rowList);
    }
    return deepCopy;
  }

  @Override
  public boolean isGameOver() {
    if (passCounter == 2) {
      return true;
    }

    int validMovesOnBoard = getValidMoves(DiscColor.BLACK).size();
    validMovesOnBoard += getValidMoves(DiscColor.WHITE).size();
    if (validMovesOnBoard == 0) {
      return true;
    }

    for (List<Cell> row : board) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == DiscColor.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Gets the winner of the game.
   *
   * @return Whichever player won the game, or {@code null} if no winner could be determined.
   * @throws IllegalStateException if the game isn't over.
   */
  public DiscColor getWinnerColor() {
    if (isGameOver()) {
      Map<DiscColor, Integer> colorScores = getScore();
      int blackScore = colorScores.get(DiscColor.BLACK);
      int whiteScore = colorScores.get(DiscColor.WHITE);
      return blackScore > whiteScore ?
              DiscColor.BLACK : DiscColor.WHITE;
    } else {
      throw new IllegalStateException("Cannot get the winner if the game isn't over.");
    }
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    Map<DiscColor, Integer> playerToScoreMap = new HashMap<>();
    playerToScoreMap.put(DiscColor.BLACK, 0);
    playerToScoreMap.put(DiscColor.WHITE, 0);
    for (List<Cell> row : board) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == DiscColor.BLACK) {
          int blackScore = playerToScoreMap.get(DiscColor.BLACK);
          playerToScoreMap.put(DiscColor.BLACK, ++blackScore);
        }
        if (cell.getColor() == DiscColor.WHITE) {
          int whiteScore = playerToScoreMap.get(DiscColor.WHITE);
          playerToScoreMap.put(DiscColor.WHITE, ++whiteScore);
        }
      }
    }
    return playerToScoreMap;
  }


  @Override
  public String getWinner() {
    if (isGameOver()) {
      Map<DiscColor, Integer> colorScores = getScore();
      int blackScore = colorScores.get(DiscColor.BLACK);
      int whiteScore = colorScores.get(DiscColor.WHITE);
      if (blackScore == whiteScore) {
        return "Tied game!";
      }
      return blackScore > whiteScore ? "Black won!" : "White won!";
    } else {
      throw new IllegalStateException("Can't get winner if the game is not over.");
    }
  }

  @Override
  public List<Cell> getValidMoves(DiscColor color) {
    List<Cell> validMovesList = new ArrayList<>();
    for (List<Cell> row : getBoard()) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == DiscColor.EMPTY) {

          Map<String, List<Cell>> possibleMoves = getRunsForCell(cell, color);

          for (String key : possibleMoves.keySet()) {
            List<Cell> run = possibleMoves.get(key);
            if (run.get(run.size() - 1).getColor() == getOppositeColor(color)) {
              continue;
            }
            if (!possibleMoves.get(key).isEmpty()) {
              validMovesList.add(cell);
            }
          }
        }
      }
    }
    return validMovesList;
  }

}
