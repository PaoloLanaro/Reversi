package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

import cs3500.reversi.model.strategy.Strategy;

/**
 * Creates a mock for the purpose of testing and getting output messages confirming
 * that moves are actually working. This is made to support the use of {@link Strategy}s.
 */
public class MockBasicReversi implements MutableReversi {

  private final Appendable out;
  private final BasicReversi delegate;

  /**
   * The constructor for this mock class.
   *
   * @param size the initial size you wish one of the sides of the board to be "long".
   *             This will be the amount of hexagons on each "side".
   * @param out  the appendable that messages will be added to.
   */
  public MockBasicReversi(int size, Appendable out) {
    this.delegate = new BasicReversi(size);
    this.out = out;
  }

  private void appendHelper(String appendable) {
    try {
      out.append(appendable).append('\n');
    } catch (Exception e) {
      System.out.println("cant append to appendable.");
    }
  }

  @Override
  public void passTurn() {
    appendHelper("Passed turn.");
    delegate.passTurn();
  }

  @Override
  public void makeMove(int row, int col) {
    appendHelper("Moved to row: " + row + " col: " + col);
    delegate.makeMove(row, col);
  }

  @Override
  public void startGame() throws IllegalStateException {
    appendHelper("started game");
    delegate.startGame();
  }

  @Override
  public boolean isValidMove(int row, int col) {
    appendHelper(String.format("Checked move at (%d, %d).", row, col));
    return delegate.isValidMove(row, col);
  }

  @Override
  public int getSideLength() {
    appendHelper("Getting side length of board.");
    return delegate.getSideLength();
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    appendHelper(String.format("Getting cell color at (%d, %d)", row, col));
    return delegate.getCellColor(row, col);
  }

  @Override
  public ReversiCell getCellAt(int row, int col) {
    appendHelper(String.format("Getting cell at (%d, %d)", row, col));
    return (ReversiCell) delegate.getCellAt(row, col);
  }

  @Override
  public int getColFromCell(Cell cell) {
    appendHelper(String.format("Getting col for cell with color %s", cell.getColor()));
    return delegate.getColFromCell(cell);
  }

  @Override
  public int getRowFromCell(Cell cell) {
    appendHelper(String.format("Getting row for cell with color %s.", cell.getColor()));
    return delegate.getRowFromCell(cell);
  }

  @Override
  public String getTurn() {
    appendHelper("Getting players turn.");
    return delegate.getTurn();
  }

  @Override
  public List<List<Cell>> getBoard() {
    appendHelper("Getting board state.");
    return delegate.getBoard();
  }

  @Override
  public boolean isGameOver() {
    appendHelper("Checking is game over.");
    return delegate.isGameOver();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    if (getTurn().equals("Black's turn")) {
      appendHelper("Score: " + delegate.getScore().get(DiscColor.BLACK));
    } else {
      appendHelper("Score: " + delegate.getScore().get(DiscColor.WHITE));
    }
    return delegate.getScore();
  }

  @Override
  public String getWinner() {
    appendHelper("Getting winner.");
    return delegate.getWinner();
  }

  @Override
  public List<Cell> getValidMoves(DiscColor color) {
    appendHelper("Getting all valid moves for cell with color" + color);
    return delegate.getValidMoves(color);
  }

}
