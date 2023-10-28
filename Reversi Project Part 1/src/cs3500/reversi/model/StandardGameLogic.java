package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StandardGameLogic implements GameLogic {

  private List<Cell> currentValidMoves;
  private final Board board;

  public StandardGameLogic(Board board) {
    this.board = board;
  }

  @Override
  public List<Cell> calculateValidMoves(PlayerColor color) {
    return currentValidMoves;
  }

  // getValidMovesHelper(PlayerColor.white, row, col)
  private List<Cell> getValidMovesHelper(PlayerColor color, int row, int col) {

    // is current attempted to place color adjacent to opposite color?
    // is

    List<Cell> validMoveList = new ArrayList<>();

    return null;
  }

  @Override
  public void makeMove(PlayerColor color, int row, int col) {
    int gameSize = board.getBoard().size();
    if (row < 0 || col < 0 || row > gameSize || col > gameSize) {
      throw new IllegalArgumentException("Tried to get a cell outside of the game board.");
    }

    Cell cell = board.getBoard().get(row).get(col);
    if (cell == null) {
      throw new IllegalArgumentException("Tried to get an incorrect cell from the internal array.");
    }

    if (isValidMove(cell, color, row, col)) {
      //TODO mutate (another issue)
      return;
    }
  }

  // Check each move from the "cell".
  private boolean isValidMove(Cell cell, PlayerColor color, int row, int col) {
    if (currentValidMoves.contains(cell)) {
      currentValidMoves.remove(cell);
      return true;
    } else {
      return calculateValidMoves(color).contains(cell);
    }
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
