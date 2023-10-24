package cs3500.reversi.model;

public interface Board {
  void initBoard();
  Cell[][] getBoardState();
  boolean isMoveValid(Player player, int row, int col);
  void makeMove(Player player, int row, int col);
  boolean isGameOver();
  Player getWinner();
}
