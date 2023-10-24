package cs3500.reversi.model;

/**
 *
 */
public class StandardBoard implements Board {

  private final int BOARD_SIZE = 5;

  Cell[][] board;

  public StandardBoard() {
    board = new Cell[BOARD_SIZE][BOARD_SIZE];
    initBoard();
  }

  @Override
  public void initBoard() {

    // https://www.redblobgames.com/grids/hexagons/#coordinates-axial
    // this link will be useful as to the board initialization and most other stuff related to
    // the coordinates
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        board[-row][col] = new Cell();
        board[row][-col] = new Cell();
        board[-row][-col] = new Cell();
      }
    }
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
