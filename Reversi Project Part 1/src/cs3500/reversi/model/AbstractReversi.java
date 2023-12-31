package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;

/**
 * An abstract class for the Reversi game.
 * This makes a Hex Reversi game as that is what it is based off.
 */
public abstract class AbstractReversi implements MutableReversi {

  // The following fields are package-private on purpose. This allows for extensions of
  // HexReversi to use these fields without package private getters that would do the same thing.
  int passCounter; // Counter for consecutive passes.
  DiscColor turn; // Current player's turn (BLACK or WHITE).
  List<List<Cell>> board; // 2D list representing the game board.
  int initSize; // Initial size of the board.
  boolean gameStarted;
  List<ModelFeatures> featuresListeners;


  /**
   * Constructs a new BasicReversi with a specified initial board size.
   *
   * @param initSize side length of the board.
   * @throws IllegalArgumentException if the initial board size is less than or equal to 2.
   */
  public AbstractReversi(int initSize) {
    turn = DiscColor.BLACK;
    if (initSize < 3) {
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
  public AbstractReversi(List<List<Cell>> otherBoard, DiscColor currentColor) {
    this.board = new ArrayList<>();
    for (int row = 0; row < otherBoard.size(); row++) {
      this.board.add(new ArrayList<>());
      for (int col = 0; col < otherBoard.get(row).size(); col++) {
        if (otherBoard.get(row).get(col) == null) {
          this.board.get(row).add(null);
          continue;
        }
        Cell otherCell = otherBoard.get(row).get(col);
        Cell newCell = new ReversiCell(otherCell.getColor(), row, col);
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
    if (validRuns.isEmpty()) {
      throw new IllegalArgumentException("(" + row + ", " + col + ") is an invalid move.");
    }

    validRuns.forEach(singleRun -> singleRun.forEach(squareCell -> squareCell.setColor(turn)));
    switchTurn();
    passCounter = 0;

    notifyListeners();

    //    List<List<Cell>> validRuns = findValidRuns(originCell);
    //    setValidDiscs(validRuns);
  }

  // package private so that SquareReversi can use it
  boolean isOutOfBounds(int row, int col) {
    if (row < 0 || col < 0 || row >= board.size() || col >= board.size()) {
      return true;
    }
    return board.get(row).get(col) == null;
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

        moveLogicCheck(row, col, color, validRuns, rowOffset, colOffset);
      }
    }
    return validRuns;
  }

  // package private so that SquareReversi can use it.
  void moveLogicCheck(int row, int col, DiscColor color, List<List<Cell>> validRuns,
                       int rowOffset, int colOffset) {
    int newRow = row + rowOffset;
    int newCol = col + colOffset;

    if (isOutOfBounds(newRow, newCol)) {
      return;
    }

    if (board.get(row).get(col).getColor() != DiscColor.EMPTY) {
      return;
    }

    if (board.get(newRow).get(newCol).getColor() == color
            || board.get(newRow).get(newCol).getColor() == DiscColor.EMPTY) {
      return;
    }

    List<Cell> currentRun = new ArrayList<>();
    currentRun.add(board.get(row).get(col));
    if (directionChecker(newRow, newCol, rowOffset, colOffset, currentRun, color)) {
      validRuns.add(currentRun);
    }
  }

  // package private so that SquareReversi can use it.
  boolean directionChecker(int newRow, int newCol,
                           int rowOffset, int colOffset, List<Cell> currentRun,
                           DiscColor originColor) {
    if (isOutOfBounds(newRow, newCol)
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

  // package private so that SquareReversi can use it
  void notifyListeners() {
    if (featuresListeners == null) {
      return;
    }
    if (!featuresListeners.isEmpty()) {
      for (ModelFeatures listener : featuresListeners) {
        listener.refresh();
      }
    }
  }

  // internally switches the turn from BLACK to WHITE and vice versa.
  // package private so that SquareReversi can call it.
  void switchTurn() {
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
        Cell cell = new ReversiCell(row, col);
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
        if (board.get(row).get(col) == null) {
          deepCopy.get(row).add(null);
          continue;
        }
        deepCopy.get(row).add(new ReversiCell(board.get(row).get(col)));
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
    return validMovesOnBoard == 0;
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
        validMovesList.add(new ReversiCell(currentCell));
      }
    }

    return validMovesList;
  }

  @Override
  public int getScoreFor(int row, int col) {
    if (!gameStarted) {
      throw new IllegalStateException("Can't get a score for a game that hasn't been started");
    }
    if (isOutOfBounds(row, col)) {
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
        // scoredCell ignored as it's just our "counter"
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
    Cell cell = board.get(row).get(col);
    if (cell == null) {
      return null;
    }
    return new ReversiCell(cell);
  }
}
