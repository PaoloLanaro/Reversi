package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

import cs3500.reversi.model.Strategy.Strategy;

/**
 * Creates a mock for the purpose of testing and getting output messages confirming
 * that moves are actually working. This is made to support the use of {@link Strategy}s.
 */
public class MockBasicReversi implements MutableReversi {

  private final Appendable out;
  private final BasicReversi model;

  /**
   * The constructor for this mock class.
   *
   * @param size the initial size you wish one of the sides of the board to be "long".
   *             This will be the amount of hexagons on each "side".
   * @param out  the appendable that messages will be added to.
   */
  public MockBasicReversi(int size, Appendable out) {
    this.model = new BasicReversi(size);
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
    model.passTurn();
  }

  @Override
  public void makeMove(int row, int col) {
    appendHelper("Moved to row: " + row + " col: " + col);
    model.makeMove(row, col);
  }

  @Override
  public boolean isValidMove(int row, int col) {
    appendHelper(String.format("Checked move at (%d, %d).", row, col));
    return model.isValidMove(row, col);
  }

  @Override
  public int getSideLength() {
    appendHelper("Getting side length of board.");
    return model.getSideLength();
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    appendHelper(String.format("Getting cell color at (%d, %d)", row, col));
    return model.getCellColor(row, col);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    appendHelper(String.format("Getting cell at (%d, %d)", row, col));
    return model.getCellAt(row, col);
  }

  @Override
  public int getColFromCell(Cell cell) {
    appendHelper(String.format("Getting col for cell with color %s", cell.getColor()));
    return model.getColFromCell(cell);
  }

  @Override
  public int getRowFromCell(Cell cell) {
    appendHelper(String.format("Getting row for cell with color %s.", cell.getColor()));
    return model.getRowFromCell(cell);
  }

  @Override
  public String getTurn() {
    appendHelper("Getting players turn.");
    return model.getTurn();
  }

  @Override
  public List<List<Cell>> getBoard() {
    appendHelper("Getting board state.");
    return model.getBoard();
  }

  @Override
  public boolean isGameOver() {
    appendHelper("Checking is game over.");
    return model.isGameOver();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    if (getTurn().equals("Black's turn")) {
      appendHelper("Score: " + model.getScore().get(DiscColor.BLACK));
    } else {
      appendHelper("Score: " + model.getScore().get(DiscColor.WHITE));
    }
    return model.getScore();
  }

  @Override
  public String getWinner() {
    appendHelper("Getting winner.");
    return model.getWinner();
  }

  @Override
  public List<Cell> getValidMoves(DiscColor color) {
    appendHelper("Getting all valid moves for cell with color" + color);
    return model.getValidMoves(color);
  }

}
