package cs3500.reversi.model;

import java.util.List;

public class StandardGameLogic implements GameLogic {
  @Override
  public List<Coordinate> calculateValidMoves(Board board, Player player) {
    return null;
  }

  @Override
  public void makeMove(Board board, Player player, int row, int col) {

  }

  @Override
  public boolean isGameOver(Board board) {
    return false;
  }
}
