package cs3500.reversi.model;

import java.util.List;

public interface GameLogic {
  List<Coordinate> calculateValidMoves(Board board, Player player);
  void makeMove(Board board, Player player, int row, int col);
  boolean isGameOver(Board board);
}