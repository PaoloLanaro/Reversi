package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;


/**
 * Represents a basic implementation of a basic hexagon Reversi Game. Implements the MutableReversi
 * interface to give methods for making moves, passing turns, and checking game state. The game is
 * played on a hex grid with a board size variable.
 *
 * <p>Class Invariant:
 *
 * <p>1. The passCounter should always be between 0 and 2.
 */
public class HexReversi implements MutableReversi {
  private int passCounter; // Counter for consecutive passes.
  private DiscColor turn; // Current player's turn (BLACK or WHITE).
  private final List<List<Cell>> board; // 2D list representing the game board.
  private final int initSize; // Initial size of the board.
  private boolean gameStarted;

  private List<ModelFeatures> featuresListeners;


  /**
   * Constructs a new BasicReversi with a specified initial board size.
   *
   * @param initSize side length of the board.
   * @throws IllegalArgumentException if the initial board size is less than or equal to 2.
   */
  public HexReversi(int initSize) {
    turn = DiscColor.BLACK;
    if (initSize <= 2) {
      throw new IllegalArgumentException("Top side length of the board cannot be less than or " +
              "equal to two.");
    }
    this.initSize = initSize;
    board = initBoard();
    featuresListeners = new ArrayList<>();
    passCounter = 0;
  }

  /**
   * A public visibility constructor that constructs a {@link HexReversi}
   * model with a pre-made board of {@link Cell}s.
   *
   * @param otherBoard   the 2D {@link List} of {@link Cell}s,
   *                     which the game should be initialized to.
   * @param currentColor the current {@link DiscColor} representation of the player.
   */
  public HexReversi(List<List<Cell>> otherBoard, DiscColor currentColor) {
    this.board = new ArrayList<>();
    for (int row = 0; row < otherBoard.size(); row++) {
      this.board.add(new ArrayList<>());
      for (int col = 0; col < otherBoard.get(row).size(); col++) {
        if (otherBoard.get(row).get(col) == null) {
          this.board.get(row).add(null);
          continue;
        }
        Cell otherCell = otherBoard.get(row).get(col);
        Cell newCell = new HexReversiCell(otherCell.getColor(), row, col);
        this.board.get(row).add(newCell);
      }
    }
    this.initSize = otherBoard.size() / 2 + 1;
    this.turn = currentColor;
    passCounter = 0;
  }

  @Override
  public void makeMove(int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started.");
    }
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    illegalRowColCheck(row, col);
//    isValidMove(row, col);
    if (requiredPlayerPassCheck()) {
      passTurn();
      throw new IllegalStateException("There is no valid move for you, so your turn has been " +
              "passed.");
    }

    List<List<Cell>> validRuns = getValidRuns(row, col, turn);
    if (validRuns.size() != 0) {
      passCounter = 0;
      validRuns.forEach(singleRun -> singleRun.forEach(squareCell -> squareCell.setColor(turn)));
    } else {
      throw new IllegalArgumentException("(" + row + ", " + col + ") is an invalid move.");
    }

    notifyListeners();

//    List<List<Cell>> validRuns = findValidRuns(originCell);
//    setValidDiscs(validRuns);
//    switchTurn();
//    notifyListeners();
  }

  private boolean isInBounds(int row, int col) {
    if (row < 0 || col < 0 || row >= board.size() || col >= board.size()) {
      return false;
    }
    return board.get(row).get(col) != null;
  }

  private List<List<Cell>> getValidRuns(int row, int col, DiscColor color) {
    List<List<Cell>> validRuns = new ArrayList<>();
    int[] offsets = {-1, 0, 1};

    for (int rowOffset : offsets) {
      for (int colOffset : offsets) {

        if ((rowOffset == 1 && colOffset == 1)
                || (rowOffset == -1 && colOffset == -1)) {
          continue;
        }

        int newRow = row + rowOffset;
        int newCol = col + colOffset;

        if (!isInBounds(row, col)) {
          continue;
        }
        if (board.get(newRow).get(newCol).getColor() == color
                || board.get(newRow).get(newCol).getColor() == DiscColor.EMPTY) {
          continue;
        }
        List<Cell> currentRun = new ArrayList<>();
        currentRun.add(board.get(row).get(col));
        if (directionChecker(newRow, newCol, rowOffset, colOffset, currentRun, color)) {
          validRuns.add(currentRun);
        }
      }
    }
    return validRuns;
  }

  private boolean directionChecker(int newRow, int newCol,
                                   int rowOffset, int colOffset, List<Cell> currentRun,
                                   DiscColor originColor) {
    if (!isInBounds(newRow, newCol)
            || board.get(newRow).get(newCol).getColor() == DiscColor.EMPTY) {
      if (currentRun.isEmpty()) {
        return false;
      }
      int lastElement = currentRun.size() - 1;
      return currentRun.get(lastElement).getColor() == originColor;
    }

    currentRun.add(board.get(newRow).get(newCol));

    if (board.get(newRow).get(newCol).getColor() == originColor) {
      return true;
    }

    int nextRow = newRow + rowOffset;
    int nextCol = newCol + colOffset;

    return directionChecker(nextRow, nextCol, rowOffset, colOffset, currentRun, originColor);
  }

  @Override
  public void startGame() {
    if (gameStarted) {
      throw new IllegalStateException("Game's already been started");
    }
    gameStarted = true;
    notifyListeners();
  }

  @Override
  public void addFeaturesListener(ModelFeatures featuresListener) {
    featuresListeners.add(featuresListener);
  }

  private void notifyListeners() {
    if (!featuresListeners.isEmpty()) {
      for (ModelFeatures listener : featuresListeners) {
        listener.refresh();
      }
    }
  }

  // internally switches the turn from BLACK to WHITE and vice versa.
  private void switchTurn() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not been started");
    }
    turn = turn == DiscColor.BLACK ? DiscColor.WHITE : DiscColor.BLACK;
  }

  @Override
  public void passTurn() { // mutable function that allows for passing of current player's turn
    if (isGameOver()) {
      throw new IllegalStateException("Can't pass a player's move when the game is already over");
    }
    passCounter++;
    switchTurn();
    notifyListeners();
  }

  // private helper for getting the opposite color (mainly used for calculating runs)
  private DiscColor getOppositeColor(DiscColor color) {
    return color == DiscColor.WHITE ? DiscColor.BLACK : DiscColor.WHITE;
  }

//  private List<List<Cell>> findValidRuns(Cell originCell) {
//    Map<String, List<Cell>> routesMap = getRunsForCell(originCell, turn);
//    List<List<Cell>> validRuns = new ArrayList<>();
//
//    validRunChecker(originCell, turn, routesMap, validRuns);
//
//    return validRuns;
//  }

  // sets valid discs based on valid runs
//  private void setValidDiscs(List<List<Cell>> validRuns) {
//
//    // if there are no valid runs, the move is not valid.
//    if (validRuns.isEmpty()) {
//      throw new IllegalStateException("Not a valid move");
//    }
//    // flips the discs for all valid runs.
//    flipValidDiscRuns(turn, validRuns);
//    passCounter = 0;
//  }

  // actually sets the runs to playerColor
//  private void flipValidDiscRuns(DiscColor playerColor, List<List<Cell>> validRuns) {
//    for (List<Cell> singleRun : validRuns) {
//      for (Cell runCell : singleRun) {
//        runCell.setColor(playerColor);
//      }
//    }
//  }

  // checks and adds valid runs to the validRuns list.
//  private void validRunChecker(Cell originCell, DiscColor playerColor, Map<String,
//          List<Cell>> routesMap, List<List<Cell>> validRuns) {
//    for (String key : routesMap.keySet()) {
//      List<Cell> run = routesMap.get(key);
//      if (run != null && !run.isEmpty() && run.get(run.size() - 1).getColor() == playerColor) {
////        originCell.setDiscColor(playerColor);
//        run.add(0, originCell);
//        validRuns.add(run);
//      }
//    }
//  }

  // computes and returns possible runs for the given origin cell and player color.
//  private Map<String, List<Cell>> getRunsForCell(Cell originCell, DiscColor playerColor) {
//    Map<String, List<Cell>> routesMap = new HashMap<>();
//    DiscColor oppositeColor = getOppositeColor(playerColor);
//    checkAndAddDirection(UPPER_LEFT, routesMap, originCell.getUpperLeft(), oppositeColor,
//            playerColor);
//    checkAndAddDirection(UPPER_RIGHT, routesMap, originCell.getUpperRight(), oppositeColor,
//            playerColor);
//    checkAndAddDirection(LEFT, routesMap, originCell.getLeft(), oppositeColor,
//            playerColor);
//    checkAndAddDirection(RIGHT, routesMap, originCell.getRight(), oppositeColor, playerColor);
//    checkAndAddDirection(BOTTOM_LEFT, routesMap, originCell.getBottomLeft(), oppositeColor,
//            playerColor);
//    checkAndAddDirection(BOTTOM_RIGHT, routesMap, originCell.getBottomRight(), oppositeColor,
//            playerColor);
//    return routesMap;
//  }

  // method to traverse in a given direction and add cells in given direction to a list
//  private void checkAndAddDirection(String direction, Map<String, List<Cell>> routesMap, Cell cell,
//                                    DiscColor oppositeColor, DiscColor playerColor) {
//    if (cell != null && cell.getColor() == oppositeColor) {
//      routesMap.put(direction, traverse(direction, playerColor, cell));
//    }
//  }

  // method that calls recursive method helper.
  // used to not muddy up the call to traverse in previous method. also helpful for debugging.
//  private List<Cell> traverse(String dir, DiscColor color, Cell currCell) {
//    List<Cell> routeList = new ArrayList<>();
//    traverseHelper(dir, color, currCell, routeList);
//    return routeList;
//  }

  // recursive method helper to traverse cells in a given direction and add said cells to a list
//  private void traverseHelper(String dir, DiscColor originalColor, Cell currCell,
//                              List<Cell> currentRun) {
//    if (currCell.getColor() == originalColor) {
//      currentRun.add(currCell);
//      return;
//    }
//
//    if (currCell.getColor() == DiscColor.EMPTY) {
//      return;
//    }
//
//    switch (dir) {
//      case UPPER_LEFT:
//        if (currCell.getUpperLeft() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getUpperLeft(), currentRun);
//        break;
//      case UPPER_RIGHT:
//        if (currCell.getUpperRight() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getUpperRight(), currentRun);
//        break;
//      case LEFT:
//        if (currCell.getLeft() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getLeft(), currentRun);
//        break;
//      case RIGHT:
//        if (currCell.getRight() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getRight(), currentRun);
//        break;
//      case BOTTOM_LEFT:
//        if (currCell.getBottomLeft() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getBottomLeft(), currentRun);
//        break;
//      case BOTTOM_RIGHT:
//        if (currCell.getBottomRight() == null) {
//          return;
//        }
//        currentRun.add(currCell);
//        traverseHelper(dir, originalColor, currCell.getBottomRight(), currentRun);
//        break;
//      default:
//        // There should never be a case where someone calls traverseHelper without the above
//        // string, so we tried implementing a new error we found while looking through autofill.
//        throw new IllegalCallerException("Fatal error");
//    }
//  }

  private boolean requiredPlayerPassCheck() {
    return getValidMoves(turn).isEmpty();
  }

  // initializes the board to be initSize length
  private List<List<Cell>> initBoard() {
    int diameter = initSize * 2 - 1;
    int middleRow = diameter / 2; // diameter is effectively the distance from the center of the
    // reversi to an edge to the right or left of the middle.

    List<List<Cell>> initialList = new ArrayList<>(diameter);

    initializeMatrix(diameter, initialList); // init 2d array

    nonMiddleRowNullSetHelper(diameter, middleRow, initialList); // sets the top left and
    // bottom right corners which need to be unplayable to null to simulate no cell.

    setStarterDiscs(middleRow, initialList); // sets the center alternating discs of black and white

    return initialList;
  }

  private void setStarterDiscs(int middleRow, List<List<Cell>> initialList) {
    initialList.get(middleRow).get(middleRow - 1).setColor(DiscColor.WHITE);
    initialList.get(middleRow - 1).get(middleRow + 1).setColor(DiscColor.WHITE);
    initialList.get(middleRow + 1).get(middleRow).setColor(DiscColor.WHITE);

    initialList.get(middleRow).get(middleRow + 1).setColor(DiscColor.BLACK);
    initialList.get(middleRow - 1).get(middleRow).setColor(DiscColor.BLACK);
    initialList.get(middleRow + 1).get(middleRow - 1).setColor(DiscColor.BLACK);
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
        Cell cell = new HexReversiCell(row, col);
        initialList.get(row).add(cell);
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
      deepCopy.add(new ArrayList<>());
      for (int col = 0; col < board.size(); col++) {
        deepCopy.get(row).add(new HexReversiCell(board.get(row).get(col)));
      }
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

    for (int row = 0; row < board.size(); row++) {
      for (int col = 0; col < board.size(); col++) {
        Cell currentCell = board.get(row).get(col);
        // are we in the null sections of the board?
        if (currentCell == null) {
          continue;
        }
        // is the cell not empty?
        if (currentCell.getColor() != DiscColor.EMPTY) {
          continue;
        }
        // get all the valid runs for the current row col
        List<List<Cell>> runsForCurrentCell = getValidRuns(row, col, color);
        // are their no valid runs for this row col?
        if (runsForCurrentCell.isEmpty()) {
          continue;
        }
        validMovesList.add(new HexReversiCell(currentCell));
      }
    }

    return validMovesList;
  }

  @Override
  public int getScoreFor(int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Can't get a score for a game that hasn't been started");
    }
    if (!isInBounds(row, col)) {
      return 0;
    }
    if (!isValidMove(row, col)) {
      return 0;
    }
    List<List<Cell>> runs = getValidRuns(row, col, turn);
//    List<List<Cell>> validRuns = findValidRuns(cell);

    int scoreForMove = 0;
    for (List<Cell> validRun : runs) {
      for (Cell scoredCell : validRun) {
        scoreForMove++;
      }
      scoreForMove--;
    }
//    for (int i = 0; i < validRuns.size() - 1; i++) {
//      scoreForMove--;
//    }

    return scoreForMove;
  }

  @Override
  public String getTurn() {
    return turn == DiscColor.BLACK ? "black" : "white";
  }

  @Override
  public boolean isValidMove(int row, int col) {
    illegalRowColCheck(row, col);
    Cell originCell = board.get(row).get(col);

    if (originCell == null) {
      throw new IllegalArgumentException("Invalid move attempt, trying to place on null");
    }

    if (originCell.getColor() != DiscColor.EMPTY) {
      return false;
    }

    List<List<Cell>> runs = getValidRuns(row, col, turn);

    return !runs.isEmpty();
  }

  private void illegalRowColCheck(int row, int col) {
    if (row < 0 || col < 0 || row > board.size() - 1 || col > board.size() - 1) {
      throw new IllegalArgumentException("Invalid row and column input.");
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
    return new HexReversiCell(board.get(row).get(col));
  }

}
