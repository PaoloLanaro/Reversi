package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;

public class SquareReversi extends AbstractReversi {

  public SquareReversi(int initSize) {
    super(initSize);
    if (initSize < 4) {
      throw new IllegalArgumentException("The size for a square reversi game must be at least 4.");
    }
    if (initSize % 2 != 0) {
      throw new IllegalArgumentException("The size for a square reversi game must be even.");
    }
    board = initBoard();
  }

  private List<List<Cell>> initBoard() {
    List<List<Cell>> initialList = new ArrayList<>();
    initializeBoard(initialList, super.initSize);
    setStarterDiscs(initialList, super.initSize);
    return initialList;
  }

  private void initializeBoard(List<List<Cell>> initialList, int initSize) {
    for (int row = 0; row < initSize; row++) {
      initialList.add(new ArrayList<>());
      for (int col = 0; col < initSize; col++) {
        initialList.get(row).add(new ReversiCell(row, col));
      }
    }
  }

  private void setStarterDiscs(List<List<Cell>> initialList, int initSize) {
    int middle = initSize / 2;
    initialList.get(middle).get(middle).setColor(DiscColor.BLACK);
    initialList.get(middle - 1).get(middle - 1).setColor(DiscColor.BLACK);
    initialList.get(middle - 1).get(middle).setColor(DiscColor.WHITE);
    initialList.get(middle).get(middle - 1).setColor(DiscColor.WHITE);
  }

  private void gameStartedCheck() {
    if (!super.gameStarted) {
      throw new IllegalStateException("Game hasn't been started");
    }
  }

  private void gameOverCheck() {
    if (isGameOver()) {
      throw new IllegalStateException("The game is over.");
    }
  }

  @Override
  public void passTurn() throws IllegalStateException {
    super.passTurn();
  }

  @Override
  public void makeMove(int row, int col) throws IllegalStateException, IllegalArgumentException {
    gameStartedCheck();
    gameOverCheck();
    illegalRowColCheck(row, col);

    List<List<Cell>> validRuns = getValidRuns(row, col, turn);
    if (validRuns.isEmpty()) {
      throw new IllegalArgumentException(
              "No valid move could be made at: (" + row + ", " + col + ")"
      );
    }

    validRuns.forEach(singleRun -> singleRun.forEach(squareCell -> squareCell.setColor(turn)));
    switchTurn();
    super.passCounter = 0;
  }

  private List<List<Cell>> getValidRuns(int row, int col, DiscColor color) {
    List<List<Cell>> validRuns = new ArrayList<>();
    int[] offsets = {-1, 0, 1};

    for (int rowOffset : offsets) {
      for (int colOffset : offsets) {

        moveLogicCheck(row, col, color, validRuns, rowOffset, colOffset);
      }
    }
    return validRuns;
  }

  private void illegalRowColCheck(int row, int col) {
    if (row < 0 || col < 0 || row >= initSize || col >= initSize) {
      throw new IllegalArgumentException("Invalid row and column input.");
    }
    if (board.get(row).get(col) == null) {
      throw new IllegalArgumentException("Row and column are not in the board of playable cells.");
    }
  }

  @Override
  public void startGame() throws IllegalStateException {
    super.startGame();
  }

  @Override
  public void addFeaturesListener(ModelFeatures featuresListener) {
    super.addFeaturesListener(featuresListener);
  }

  @Override
  public List<List<Cell>> getBoard() {      // todo super duper?
    return super.getBoard();
  }

  @Override
  public boolean isGameOver() {             // todo super duper?
    return super.isGameOver();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    return super.getScore();                            // todo super duper?
  }

  @Override
  public String getWinner() {               // todo super duper?
    return super.getWinner();
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
  public String getTurn() {                 // todo super duper?
    return super.getTurn();
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

  @Override
  public int getSideLength() {            // todo super duper?
    return super.getSideLength();
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    return super.getCellColor(row, col);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return super.getCellAt(row, col);
  }

  @Override
  public int getScoreFor(int row, int col) {
    return 0;
  }
}
