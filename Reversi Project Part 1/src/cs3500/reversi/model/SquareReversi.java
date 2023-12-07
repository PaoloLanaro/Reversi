package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;

public class SquareReversi implements MutableReversi {

  private int passCounter; // Counter for consecutive passes.
  private DiscColor turn; // Current player's turn (BLACK or WHITE).
  private final List<List<SquareCell>> board;
  private final int initSize;
  private boolean gameStarted;

  private List<ModelFeatures> featuresListeners;


  public SquareReversi(int initSize) {
    this.gameStarted = false;
    if (initSize < 4) {
      throw new IllegalArgumentException("Cannot start game with board size less than four.");
    }
    if (initSize % 2 != 0) {
      throw new IllegalArgumentException("Game board must be of even size.");
    }
    this.initSize = initSize;
    board = initBoard();
    featuresListeners = new ArrayList<>();
    passCounter = 0;
    turn = DiscColor.BLACK;
  }

  private List<List<SquareCell>> initBoard() {
    List<List<SquareCell>> returnBoard = new ArrayList<>();

    for (int row = 0; row < initSize; row++) {
      returnBoard.add(new ArrayList<>());
      List<SquareCell> rowList = returnBoard.get(row);
      for (int col = 0; col < initSize; col++) {
        rowList.add(new SquareReversiCell(new RowCol(row, col), DiscColor.EMPTY));
      }
    }

    int middle = initSize / 2;

    returnBoard.get(middle).get(middle).setColor(DiscColor.BLACK);
    returnBoard.get(middle - 1).get(middle - 1).setColor(DiscColor.BLACK);
    returnBoard.get(middle - 1).get(middle).setColor(DiscColor.WHITE);
    returnBoard.get(middle).get(middle - 1).setColor(DiscColor.WHITE);

    return returnBoard;
  }

  private void gameStartCheck() {
    if (!gameStarted) {
      throw new IllegalStateException("Can't play on a game that hasn't been started");
    }
  }

  @Override
  public void passTurn() throws IllegalStateException {
    gameStartCheck();
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    passCounter++;
    switchTurn();
    notifyListeners();
  }

  private void notifyListeners() {
    for (ModelFeatures feature : featuresListeners) {
      feature.notify();
    }
  }

  private void switchTurn() {
    gameStartCheck();
    turn = turn == DiscColor.BLACK ? DiscColor.WHITE : DiscColor.BLACK;
  }

  @Override
  public void makeMove(int row, int col) throws IllegalStateException, IllegalArgumentException {
    gameStartCheck();
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    illegalRowColCheck(row, col);

    List<List<SquareCell>> validRuns = getValidRuns(row, col);
    validRuns.forEach(singleRun -> singleRun.forEach(squareCell -> squareCell.setColor(turn)));
  }

  private boolean directionChecker(int currentRow, int currentCol, int rowOffset,
                                   int colOffset, List<SquareCell> currentRun) {
    if (!isNotOutOfBounds(currentRow, currentCol)
            || board.get(currentRow).get(currentCol).getColor() == DiscColor.EMPTY) {
      if (currentRun.isEmpty()) {
        return false;
      }
      int lastElement = currentRun.size() - 1;
      return currentRun.get(lastElement).getColor() == turn;
    }

    currentRun.add(board.get(currentRow).get(currentCol));

    if (board.get(currentRow).get(currentCol).getColor() == turn) {
      return true;
    }

    int newRow = currentRow + rowOffset;
    int newCol = currentCol + colOffset;

    return directionChecker(newRow, newCol, rowOffset, colOffset, currentRun);
  }

  private List<List<SquareCell>> getValidRuns(int row, int col) {
    List<List<SquareCell>> validRuns = new ArrayList<>();
    int[] offsets = {-1, 0, 1};

    for (int rowOffset : offsets) {
      for (int colOffset : offsets) {
        int newRow = row + rowOffset;
        int newCol = col + colOffset;
        if (!isNotOutOfBounds(newRow, newCol)) {
          continue;
        }
        if (board.get(newRow).get(newCol).getColor() == turn
                || board.get(newRow).get(newCol).getColor() == DiscColor.EMPTY) {
          continue;
        }
        List<SquareCell> currentRun = new ArrayList<>();
        currentRun.add(board.get(row).get(col));
        if (directionChecker(newRow, newCol, rowOffset, colOffset, currentRun)) {
          validRuns.add(currentRun);
        }
      }
    }
    return validRuns;
  }

  private void illegalRowColCheck(int row, int col) {
    if (row < 0 || col < 0 || row > initSize || col > initSize) {
      throw new IllegalArgumentException("Invalid row and column input.");
    }
    if (board.get(row).get(col) == null) {
      throw new IllegalArgumentException("Row and column are not in the board of playable cells.");
    }
  }

  private boolean isNotOutOfBounds(int row, int col) {
    return row >= 0 && col >= 0 && row < initSize && col < initSize;
  }

  @Override
  public void startGame() throws IllegalStateException {
    if (gameStarted) {
      throw new IllegalStateException("Can't start a game already in progress");
    }
    gameStarted = true;
  }

  @Override
  public void addFeaturesListener(ModelFeatures featuresListener) {

  }

  @Override
  public List<List<Cell>> getBoard() {
    gameStartCheck();
    return null;
  }

  @Override
  public boolean isGameOver() {
    gameStartCheck();

    return false;
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    gameStartCheck();

    return null;
  }

  @Override
  public String getWinner() {
    gameStartCheck();

    return null;
  }

  @Override
  public List<Cell> getValidMoves(DiscColor color) {
    gameStartCheck();

    return null;
  }

  @Override
  public String getTurn() {
    gameStartCheck();

    return null;
  }

  @Override
  public boolean isValidMove(int row, int col) {
    gameStartCheck();

    return false;
  }

  @Override
  public int getSideLength() {
    gameStartCheck();

    return 0;
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    gameStartCheck();

    return null;
  }

  @Override
  public Cell getCellAt(int row, int col) {
    gameStartCheck();

    return null;
  }

  @Override
  public int getColFromCell(Cell cell) {
    gameStartCheck();

    return 0;
  }

  @Override
  public int getRowFromCell(Cell cell) {
    gameStartCheck();

    return 0;
  }
}
