package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

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

  }

  private List<Cell> traverse(String dir, PlayerColor originalColor, Cell currCell) {
    List<Cell> run = new ArrayList<>();
    if (currCell.getColor() == originalColor) {
      run.add(currCell);
      return run;
    }

    switch (dir) {
      case "ul":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getUpperLeft());
      break;
      case "ur":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getUpperRight());
        break;
      case "l":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getLeft());
        break;
      case "r":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getRight());
        break;
      case "bl":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getBottomLeft());
        break;
      case "br":
        run.add(currCell);
        traverse(dir, originalColor, currCell.getBottomRight());
        break;
    }
    return run;
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
