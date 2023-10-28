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

  public StandardGameLogic(Board board) {
    this.board = board;
  }

  @Override
  public List<Cell> calculateValidMoves(PlayerColor color) {
    return null;
  }

  @Override
  public void makeMove(PlayerColor color, int row, int col) {
    int maxLength = board.getBoard().size();
    if (row < 0 || col < 0 || row > maxLength - 1 || col > maxLength - 1) {
      throw new IllegalArgumentException("Poo");
    }
    Cell originCell = board.getBoard().get(row).get(col);
    if (originCell == null) {
      throw new IllegalArgumentException("Invalid move attempt, trying to place on null");
    }

    foo(originCell, color);

  }

  private PlayerColor getOppositeColor(Cell cell) {
    if (cell.getColor() == PlayerColor.WHITE) {
      return PlayerColor.BLACK;
    } else {
      return PlayerColor.WHITE;
    }
  }

  private void foo(Cell originCell, PlayerColor playerColor) {
    Map<String, List<Cell>> routesMap = new HashMap<>();
    PlayerColor oppositeColor = getOppositeColor(originCell);
    if (originCell.getUpperLeft().getColor() == oppositeColor) {
      routesMap.put("ul", traverseHelper("ul", playerColor, originCell.getUpperLeft()));
    }
    if (originCell.getUpperRight().getColor() == oppositeColor) {
      routesMap.put("ur", traverseHelper("ur", playerColor, originCell.getUpperRight()));
    }
    if (originCell.getLeft().getColor() == oppositeColor) {
      routesMap.put("l", traverseHelper("l", playerColor, originCell.getLeft()));
    }
    if (originCell.getRight().getColor() == oppositeColor) {
      routesMap.put("r", traverseHelper("r", playerColor, originCell.getRight()));
    }
    if (originCell.getBottomLeft().getColor() == oppositeColor) {
      routesMap.put("bl", traverseHelper("bl", playerColor, originCell.getBottomLeft()));
    }
    if (originCell.getBottomRight().getColor() == oppositeColor) {
      routesMap.put("br", traverseHelper("br", playerColor, originCell.getBottomRight()));
    }

    List<List<Cell>> validRuns = new ArrayList<>();

    if (routesMap.get("ul") != null && !routesMap.get("ul").isEmpty()) {
      List<Cell> run = routesMap.get("ul");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("ur") != null &&!routesMap.get("ur").isEmpty()) {
      List<Cell> run = routesMap.get("ur");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("l") != null &&!routesMap.get("l").isEmpty()) {
      List<Cell> run = routesMap.get("l");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("r") != null &&!routesMap.get("r").isEmpty()) {
      List<Cell> run = routesMap.get("r");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("bl") != null &&!routesMap.get("bl").isEmpty()) {
      List<Cell> run = routesMap.get("bl");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }
    if (routesMap.get("br") != null &&!routesMap.get("br").isEmpty()) {
      List<Cell> run = routesMap.get("br");
      if (run.get(run.size() - 1).getColor() == playerColor) {
        originCell.setDiscColor(playerColor);
        run.add(0, originCell);
        validRuns.add(run);
      }
    }

    // validRuns contains
    // all valid runs

    for (List<Cell> singleRun : validRuns) {
      for (Cell runCell : singleRun) {
        runCell.setDiscColor(playerColor);
      }
    }
  }

  private List<Cell> traverseHelper(String dir, PlayerColor color, Cell currCell) {
    List<Cell> routeList = new ArrayList<>();
    traverse(dir, color, currCell, routeList);
    return  routeList;
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

  @Override
  public boolean isGameOver(Board board) {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }
}
