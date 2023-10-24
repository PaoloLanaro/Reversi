package cs3500.reversi.model;

public class StandardBoard implements Board {
  @Override
  public void initBoard() {

  }

  @Override
  public Cell[][] getBoardState() {
    return new Cell[0][];
  }

  @Override
  public boolean isMoveValid(Player player, int row, int col) {
    return false;
  }

  @Override
  public void makeMove(Player player, int row, int col) {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }
}
