package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BasicReversi implements MutableReversi {
  private int passCounter;
  private DiscColor turn;
  private final List<List<Cell>> board;
  private final int initSize;

  public BasicReversi (int initSize) {
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

    String[] directions = {"ul", "ur", "l", "r", "bl", "br"};

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
    checkAndAddDirection("ul", routesMap, originCell.getUpperLeft(), oppositeColor, playerColor);
    checkAndAddDirection("ur", routesMap, originCell.getUpperRight(), oppositeColor, playerColor);
    checkAndAddDirection("l", routesMap, originCell.getLeft(), oppositeColor, playerColor);
    checkAndAddDirection("r", routesMap, originCell.getRight(), oppositeColor, playerColor);
    checkAndAddDirection("bl", routesMap, originCell.getBottomLeft(), oppositeColor, playerColor);
    checkAndAddDirection("br", routesMap, originCell.getBottomRight(), oppositeColor, playerColor);
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
      case "ul":
        if (currCell.getUpperLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperLeft(), currentRun);
        break;
      case "ur":
        if (currCell.getUpperRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperRight(), currentRun);
        break;
      case "l":
        if (currCell.getLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getLeft(), currentRun);
        break;
      case "r":
        if (currCell.getRight() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getRight(), currentRun);
        break;
      case "bl":
        if (currCell.getBottomLeft() == null) {
          return;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getBottomLeft(), currentRun);
        break;
      case "br":
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

    nullSetSpaghetti(diameter, middleRow, initialList);

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

  private void nullSetSpaghetti(int diameter, int middleRow, List<List<Cell>> initialList) {
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
  private void setCellNeighbors(int diameter, List<List<Cell>> initialList) {
    for (int row = 0; row < diameter; row++) {
      for (int col = 0; col < diameter; col++) {
        Cell currentCell = initialList.get(row).get(col);
        if (currentCell == null) {
          continue;
        }
        try {
          currentCell.setUpperLeft(Objects.requireNonNull(initialList.get(row - 1).get(col)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setBottomLeft(null);
        }

        try {
          currentCell.setUpperRight(Objects.requireNonNull(initialList.get(row - 1).get(col + 1)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setBottomRight(null);
        }

        try {
          currentCell.setLeft(Objects.requireNonNull(initialList.get(row).get(col - 1)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setLeft(null);
        }

        try {
          currentCell.setRight(Objects.requireNonNull(initialList.get(row).get(col + 1)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setRight(null);
        }

        try {
          currentCell.setBottomLeft(Objects.requireNonNull(initialList.get(row + 1).get(col - 1)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setBottomLeft(null);
        }

        try {
          currentCell.setBottomRight(Objects.requireNonNull(initialList.get(row + 1).get(col)));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
          currentCell.setBottomRight(null);
        }

      }
    }
  }

  private void nonMiddleHelper(boolean nullSet, List<List<Cell>> finalBoard, int row,
                                      int col) {
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
