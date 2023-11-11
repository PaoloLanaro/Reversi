package cs3500.reversi.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a basic implementation of a basic hexagon Reversi Game. Implements the MutableReversi
 * interface to give methods for making moves, passing turns, and checking game state. The game is
 * played on a hex grid with a board size variable.
 *
 * <p>Class Invariant:
 *
 * <p>1. The passCounter should always be between 0 and 2.
 */
public class BasicReversi implements MutableReversi {
  private int passCounter; // Counter for consecutive passes.
  private DiscColor turn; // Current player's turn (BLACK or WHITE).
  private final List<List<Cell>> board; // 2D list representing the game board.
  private final int initSize; // Initial size of the board.
  private final String UPPER_LEFT = "ul";
  private final String UPPER_RIGHT = "ur";
  private final String LEFT = "l";
  private final String RIGHT = "r";
  private final String BOTTOM_LEFT = "bl";
  private final String BOTTOM_RIGHT = "br";

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

  //copy constructor
  public BasicReversi(BasicReversi other) {
    this.passCounter = other.passCounter;
    this.turn = other.turn;

    this.board = new ArrayList<>();
    for (int row = 0; row < other.board.size(); row++) {
      this.board.add(new ArrayList<>());
      for (int cell = 0; cell < other.board.get(row).size(); cell++) {
        if (other.board.get(row).get(cell) == null) {
          this.board.add(null);
          continue;
        }
        Cell otherCell = other.board.get(row).get(cell);
        Cell newCell = new Cell(otherCell);
        this.board.get(row).add(newCell);
      }
    }

    this.initSize = other.initSize;
  }

  protected BasicReversi(List<List<Cell>> otherBoard, DiscColor currentColor) {
    this.board = new ArrayList<>();
    for (int row = 0; row < otherBoard.size(); row++) {
      this.board.add(new ArrayList<>());
      for (int cell = 0; cell < otherBoard.get(row).size(); cell++) {
        if (otherBoard.get(row).get(cell) == null) {
          this.board.add(null);
          continue;
        }
        Cell otherCell = otherBoard.get(row).get(cell);
        Cell newCell = new Cell(otherCell);
        this.board.get(row).add(newCell);
      }
    }
    this.initSize = otherBoard.size() / 2 + 1;
    this.turn = currentColor;
  }

  @Override
  public void makeMove(int row, int col) {
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    illegalRowColCheck(row, col);
    isValidMove(row, col);
    if (requiredPlayerPassCheck()) {
      passTurn();
      throw new IllegalStateException("Cannot move, so your turn has been passed.");
    }

    Cell originCell = board.get(row).get(col);
    setValidDiscs(originCell, turn);
    switchTurn();
  }

  private void switchTurn() {
    // Switches the turn from BLACK to WHITE and vice versa.
    turn = turn == DiscColor.BLACK ? DiscColor.WHITE : DiscColor.BLACK;
  }

  @Override
  public void passTurn() {
    if (isGameOver()) {
      throw new IllegalStateException("Can't pass a player's move when the game is already over");
    }
    passCounter++;
    switchTurn();
  }

  private DiscColor getOppositeColor(DiscColor color) {
    return color == DiscColor.WHITE ? DiscColor.BLACK : DiscColor.WHITE;
  }

  private void setValidDiscs(Cell originCell, DiscColor playerColor) {
    // Finds and sets valid discs based on the player's color and origin cell.
    Map<String, List<Cell>> routesMap = getRunsForCell(originCell, playerColor);
    List<List<Cell>> validRuns = new ArrayList<>();
    validRunChecker(originCell, playerColor, routesMap, validRuns);

    // If there are no valid runs, the move is not valid.
    if (validRuns.isEmpty()) {
      throw new IllegalStateException("Not a valid move");
    }
    // Flips the discs for all valid runs.
    flipValidDiscRuns(playerColor, validRuns);
    passCounter = 0;
  }

  private void flipValidDiscRuns(DiscColor playerColor, List<List<Cell>> validRuns) {
    for (List<Cell> singleRun : validRuns) {
      for (Cell runCell : singleRun) {
        runCell.setDiscColor(playerColor);
      }
    }
  }

  private void validRunChecker(Cell originCell, DiscColor playerColor, Map<String,
          List<Cell>> routesMap, List<List<Cell>> validRuns) {
    // Checks and adds valid runs to the validRuns list.
    for (String key : routesMap.keySet()) {
      List<Cell> run = routesMap.get(key);
      if (run != null && !run.isEmpty() && run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
  }

  private Map<String, List<Cell>> getRunsForCell(Cell originCell, DiscColor playerColor) {
    // Computes and returns possible runs for the given cell and player color.
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

  // Method to traverse in a given direction and add cells in given direction to a list
  private void checkAndAddDirection(String direction, Map<String, List<Cell>> routesMap, Cell cell,
                                    DiscColor oppositeColor, DiscColor playerColor) {
    // Checks the given direction and adds cells to the routesMap if they form a valid run.
    if (cell != null && cell.getColor() == oppositeColor) {
      routesMap.put(direction, traverse(direction, playerColor, cell));
    }
  }

  // Method that calls recursive method helper
  private List<Cell> traverse(String dir, DiscColor color, Cell currCell) {
    List<Cell> routeList = new ArrayList<>();
    traverseHelper(dir, color, currCell, routeList);
    return routeList;
  }

  // Recursive method helper to traverse cells in a given direction and add said cells to a list
  private void traverseHelper(String dir, DiscColor originalColor, Cell currCell,
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
        traverseHelper(dir, originalColor, currCell.getUpperLeft(), currentRun);
        break;
      case UPPER_RIGHT:
        if (currCell.getUpperRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverseHelper(dir, originalColor, currCell.getUpperRight(), currentRun);
        break;
      case LEFT:
        if (currCell.getLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverseHelper(dir, originalColor, currCell.getLeft(), currentRun);
        break;
      case RIGHT:
        if (currCell.getRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverseHelper(dir, originalColor, currCell.getRight(), currentRun);
        break;
      case BOTTOM_LEFT:
        if (currCell.getBottomLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverseHelper(dir, originalColor, currCell.getBottomLeft(), currentRun);
        break;
      case BOTTOM_RIGHT:
        if (currCell.getBottomRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverseHelper(dir, originalColor, currCell.getBottomRight(), currentRun);
        break;
      default:
        // There should never be a case where someone calls traverseHelper without the above
        // string, so we tried implementing a new error we found while looking through auto-fill.
        throw new IllegalCallerException("Fatal error");
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

    setStarterDiscs(middleRow, initialList);

    setCellNeighbors(diameter, initialList);

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

  private void nonMiddleRowNullSetHelper(int diameter, int middleRow,
                                         List<List<Cell>> initialList) {
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

  // Helper for setting the cell neighbors.
  private Cell getNeighborCell(List<List<Cell>> initialList, int row, int col) {
    try {
      return Objects.requireNonNull(initialList.get(row).get(col));
    } catch (NullPointerException | IndexOutOfBoundsException e) {
      return null;
    }
  }

  // Set hexagonal neighbors for each cell
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

  public int getRowFromCell(Cell cell) {
    int diameter = initSize * 2 - 1;
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        if (board.get(row).get(col) == null) {
          continue;
        }
        if (board.get(row).get(col) == cell) {
          return row;
        }
      }
    }
    throw new IllegalArgumentException("This cell is not in the board");
  }

  public int getColFromCell(Cell cell) {
    int diameter = initSize * 2 - 1;
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        if (board.get(row).get(col) == null) {
          continue;
        }
        if (board.get(row).get(col) == cell) {
          return col;
        }
      }
    }
    throw new IllegalArgumentException("This cell is not in the board");
  }

  @Override
  public String getTurn() {
    return turn == DiscColor.BLACK ? "Black's turn" : "White's turn";
  }

  @Override
  public boolean isValidMove(int row, int col) {
    illegalRowColCheck(row, col);
    Cell originCell = board.get(row).get(col);
    if (originCell == null) {
      throw new IllegalArgumentException("Invalid move attempt, trying to place on null");
    }
    if (originCell.getColor() != DiscColor.EMPTY) {
      throw new IllegalStateException("Tried to play on a non-empty cell");
    }
    Map<String, List<Cell>> runsForCell = getRunsForCell(originCell, turn);
    List<List<Cell>> validRuns = new ArrayList<>();
    for (String key : runsForCell.keySet()) {
      List<Cell> run = runsForCell.get(key);
      if (run != null && !run.isEmpty() && run.get(run.size() - 1).getColor() == turn) {
        validRuns.add(run);
      }
    }

    return !validRuns.isEmpty();
  }

  private void illegalRowColCheck(int row, int col) {
    if (row < 0 || col < 0 || row > board.size() - 1 || col > board.size() - 1) {
      throw new IndexOutOfBoundsException("Invalid row and column input.");
    }
    if (board.get(row).get(col) == null) {
      throw new IllegalArgumentException("Row and column are not in the board of playable cells.");
    }
  }

  @Override
  public int getSideLength() {
    return initSize;
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    illegalRowColCheck(row, col);
    return board.get(row).get(col).getColor();
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return new Cell(board.get(row).get(col));
  }

}
