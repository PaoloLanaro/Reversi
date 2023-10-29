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
      throw new IllegalStateException("Cannot make any move as there is no legal move to be made.");
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

//  private void foo(Cell originCell, PlayerColor playerColor) {
//    Map<String, List<Cell>> routesMap = getRunsForCell(originCell, playerColor);
//
//    List<List<Cell>> validRuns = new ArrayList<>();
//
//    String[] directions = {"ul", "ur", "l", "r", "bl", "br"};
//
//    for (String direction : directions) {
//      List<Cell> run = routesMap.get(direction);
//      if (run != null && !run.isEmpty() && run.get(run.size() - 1).getColor() == playerColor) {
//        originCell.setDiscColor(playerColor);
//        run.add(0, originCell);
//        validRuns.add(run);
//      }
//    }
//
//    if (validRuns.isEmpty()) {
//      throw new IllegalStateException("Not a valid move");
//    }
//
//    for (List<Cell> singleRun : validRuns) {
//      for (Cell runCell : singleRun) {
//        runCell.setDiscColor(playerColor);
//      }
//    }
//
//    turnCounter++;
//    passCounter = 0;
//  }


  private void foo(Cell originCell, PlayerColor playerColor) {
    Map<String, List<Cell>> routesMap = getRunsForCell(originCell, playerColor);

    List<List<Cell>> validRuns = new ArrayList<>();

    if (routesMap.get("ul") != null && !routesMap.get("ul").isEmpty()) {
      List<Cell> run = routesMap.get("ul");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("ur") != null && !routesMap.get("ur").isEmpty()) {
      List<Cell> run = routesMap.get("ur");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("l") != null && !routesMap.get("l").isEmpty()) {
      List<Cell> run = routesMap.get("l");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("r") != null && !routesMap.get("r").isEmpty()) {
      List<Cell> run = routesMap.get("r");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("bl") != null && !routesMap.get("bl").isEmpty()) {
      List<Cell> run = routesMap.get("bl");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("br") != null && !routesMap.get("br").isEmpty()) {
      List<Cell> run = routesMap.get("br");
      if (run.get(run.size() - 1).getColor() == playerColor) {
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
    if (originCell.getUpperLeft() != null) {
      if (originCell.getUpperLeft().getColor() == oppositeColor) {
        routesMap.put("ul", traverseHelper("ul", playerColor, originCell.getUpperLeft()));
      }
    }
    if (originCell.getUpperRight() != null) {
      if (originCell.getUpperRight().getColor() == oppositeColor) {
        routesMap.put("ur", traverseHelper("ur", playerColor, originCell.getUpperRight()));
      }
    }
    if (originCell.getLeft() != null) {
      if (originCell.getLeft().getColor() == oppositeColor) {
        routesMap.put("l", traverseHelper("l", playerColor, originCell.getLeft()));
      }
    }
    if (originCell.getRight() != null) {
      if (originCell.getRight().getColor() == oppositeColor) {
        routesMap.put("r", traverseHelper("r", playerColor, originCell.getRight()));
      }
    }
    if (originCell.getBottomLeft() != null) {
      if (originCell.getBottomLeft().getColor() == oppositeColor) {
        routesMap.put("bl", traverseHelper("bl", playerColor, originCell.getBottomLeft()));
      }
    }
    if (originCell.getBottomRight() != null) {
      if (originCell.getBottomRight().getColor() == oppositeColor) {
        routesMap.put("br", traverseHelper("br", playerColor, originCell.getBottomRight()));
      }
    }
    return routesMap;
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


  //TODO IMPLEMENT
  @Override
  public List<Cell> calculateValidMoves(PlayerColor color) {
    return null;
  }

  private List<Cell> calculateValidMoves() {
    List<Cell> validMovesList = new ArrayList<>();
    for (List<Cell> row : board.getBoard()) {
      for (Cell cell : row) {
        if (cell == null) {
          continue;
        }
        if (cell.getColor() == PlayerColor.EMPTY) {

          Map<String, List<Cell>> possibleBlackMoves = getRunsForCell(cell, PlayerColor.BLACK);
          Map<String, List<Cell>> possibleWhiteMoves = getRunsForCell(cell, PlayerColor.WHITE);

          for (String key : possibleWhiteMoves.keySet()) {
            if (possibleWhiteMoves.get(key).size() > 0) {
              validMovesList.add(cell);
            }
          }
          for (String key : possibleBlackMoves.keySet()) {
            if (possibleBlackMoves.get(key).size() > 0) {
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

//    return calculateValidMoves(currentPlayerColor).size() == 0;
    return false;
  }

  @Override
  public boolean isGameOver() {
    if (passCounter == 2) {
      return true;
    }

    int validMovesOnBoard = calculateValidMoves().size();
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
    return null;
  }

  @Override
  public Player getWinner() {
    return null;
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
