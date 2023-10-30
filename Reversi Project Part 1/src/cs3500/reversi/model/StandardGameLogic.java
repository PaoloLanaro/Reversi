package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class StandardGameLogic implements GameLogic {

  private final Board board;
  private int turnCounter;
  private int passCounter;

  public StandardGameLogic(Board board) {
    this.board = board;
    turnCounter = 0;
  }

  @Override
  public void makeMove(PlayerColor color, int row, int col) {
    if (isGameOver()) {
      throw new IllegalStateException("Tried to move on a finished game.");
    }
    if (turnCounter % 2 == 0 && color != PlayerColor.BLACK) {
      throw new IllegalStateException("It's black player's turn");
    }
    int maxLength = board.getBoard().size();
    if (row < 0 || col < 0 || row > maxLength - 1 || col > maxLength - 1) {
      throw new IllegalArgumentException("Poo");
    }
    Cell originCell = board.getBoard().get(row).get(col);
    if (originCell == null) {
      throw new IllegalArgumentException("Invalid move attempt, trying to place on null");
    }
    if (originCell.getColor() != PlayerColor.EMPTY) {
      throw new IllegalArgumentException("Tried to play on a non-empty cell");
    }
    if (playerPassCheck()) {
      passTurn();
      throw new IllegalStateException("Cannot move, so your turn has been passed.");
    }

    foo(originCell, color);
  }

  @Override
  public void passTurn() {
    if (isGameOver()) {
      throw new IllegalStateException("Can't pass a player's move when the game is already over");
    }
    turnCounter++;
    passCounter++;
  }

  private PlayerColor getOppositeColor(PlayerColor color) {
    if (color == PlayerColor.WHITE) {
      return PlayerColor.BLACK;
    } else {
      return PlayerColor.WHITE;
    }
  }

  private void foo(Cell originCell, PlayerColor playerColor) {
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

    turnCounter++;
    passCounter = 0;
  }

  private Map<String, List<Cell>> getRunsForCell(Cell originCell, PlayerColor playerColor) {
    Map<String, List<Cell>> routesMap = new HashMap<>();
    PlayerColor oppositeColor = getOppositeColor(playerColor);
    checkAndAddDirection("ul", routesMap, originCell.getUpperLeft(), oppositeColor, playerColor);
    checkAndAddDirection("ur", routesMap, originCell.getUpperRight(), oppositeColor, playerColor);
    checkAndAddDirection("l", routesMap, originCell.getLeft(), oppositeColor, playerColor);
    checkAndAddDirection("r", routesMap, originCell.getRight(), oppositeColor, playerColor);
    checkAndAddDirection("bl", routesMap, originCell.getBottomLeft(), oppositeColor, playerColor);
    checkAndAddDirection("br", routesMap, originCell.getBottomRight(), oppositeColor, playerColor);
    return routesMap;
  }

  private void checkAndAddDirection(String direction, Map<String, List<Cell>> routesMap, Cell cell,
                                    PlayerColor oppositeColor, PlayerColor playerColor) {
    if (cell != null && cell.getColor() == oppositeColor) {
      routesMap.put(direction, traverseHelper(direction, playerColor, cell));
    }
  }

  private List<Cell> traverseHelper(String dir, PlayerColor color, Cell currCell) {
    List<Cell> routeList = new ArrayList<>();
    traverse(dir, color, currCell, routeList);
    return routeList;
  }


  private List<Cell> traverse(String dir, PlayerColor originalColor, Cell currCell,
                              List<Cell> currentRun) {
    if (currCell.getColor() == originalColor) {
      currentRun.add(currCell);
      return currentRun;
    }

    if (currCell.getColor() == PlayerColor.EMPTY) {
      return currentRun;
    }

    switch (dir) {
      case "ul":
        if (currCell.getUpperLeft() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperLeft(), currentRun);
        break;
      case "ur":
        if (currCell.getUpperRight() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getUpperRight(), currentRun);
        break;
      case "l":
        if (currCell.getLeft() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getLeft(), currentRun);
        break;
      case "r":
        if (currCell.getRight() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getRight(), currentRun);
        break;
      case "bl":
        if (currCell.getBottomLeft() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getBottomLeft(), currentRun);
        break;
      case "br":
        if (currCell.getBottomRight() == null) {
          return currentRun;
        }
        currentRun.add(currCell);
        traverse(dir, originalColor, currCell.getBottomRight(), currentRun);
        break;
    }
    return currentRun;
  }

  @Override
  public List<Cell> calculateValidMoves(PlayerColor color) {
    List<Cell> validMovesList = new ArrayList<>();
    for (List<Cell> row : board.getBoard()) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == PlayerColor.EMPTY) {

          Map<String, List<Cell>> possibleMoves = getRunsForCell(cell, color);

          for (String key : possibleMoves.keySet()) {
            List<Cell> run = possibleMoves.get(key);
            if (run.get(run.size() - 1).getColor() == getOppositeColor(color)) {
              continue;
            }
            if (possibleMoves.get(key).size() > 0) {
              validMovesList.add(cell);
            }
          }
        }
      }
    }
    return validMovesList;
  }

  private boolean playerPassCheck() {
    PlayerColor currentPlayerColor = (turnCounter % 2 == 0) ? PlayerColor.BLACK : PlayerColor.WHITE;

    return calculateValidMoves(currentPlayerColor).size() == 0;
  }

  @Override
  public boolean isGameOver() {
    if (passCounter == 2) {
      return true;
    }

    int validMovesOnBoard = calculateValidMoves(PlayerColor.BLACK).size();
    validMovesOnBoard += calculateValidMoves(PlayerColor.WHITE).size();
    if (validMovesOnBoard == 0) {
      return false;
    }

    for (List<Cell> row : board.getBoard()) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == PlayerColor.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public PlayerColor getWinnerColor() {
    if (isGameOver()) {
      Map<PlayerColor, Integer> colorScores = getScore();
      int blackScore = colorScores.get(PlayerColor.BLACK);
      int whiteScore = colorScores.get(PlayerColor.WHITE);
      if (blackScore == whiteScore) {

      }
      return  blackScore >  whiteScore ?
              PlayerColor.BLACK : PlayerColor.WHITE;
    } else {
      throw new IllegalStateException("Cannot get the winner if the game isn't over.");
    }
  }

  public String getWinner() {
    if (isGameOver()) {
      Map<PlayerColor, Integer> colorScores = getScore();
      int blackScore = colorScores.get(PlayerColor.BLACK);
      int whiteScore = colorScores.get(PlayerColor.WHITE);
      if (blackScore == whiteScore) {
        return "Tied game!";
      }
      return  blackScore >  whiteScore ? "Black won!" : "White won!";
    } else {
      throw new IllegalStateException("lksadfj");
    }
  }

  @Override
  public Map<PlayerColor, Integer> getScore() {
    Map<PlayerColor, Integer> playerToScoreMap = new HashMap<>();
    playerToScoreMap.put(PlayerColor.BLACK, 0);
    playerToScoreMap.put(PlayerColor.WHITE, 0);
    for (List<Cell> row : board.getBoard()) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == PlayerColor.BLACK) {
          int blackScore = playerToScoreMap.get(PlayerColor.BLACK);
          playerToScoreMap.put(PlayerColor.BLACK, ++blackScore);
        }
        if (cell.getColor() == PlayerColor.WHITE) {
          int whiteScore = playerToScoreMap.get(PlayerColor.WHITE);
          playerToScoreMap.put(PlayerColor.WHITE, ++whiteScore);
        }
      }
    }
    return playerToScoreMap;
  }
}
