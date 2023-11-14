package cs3500.reversi.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBasicReversi extends BasicReversi {

  private final List<List<Cell>> mockBoard;
  private final Appendable out;

  public MockBasicReversi(int size, Appendable out) {
    super(size);
    this.mockBoard = super.getBoard();
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
    super.passTurn();
  }

  @Override
  public void makeMove(int row, int col) {
    appendHelper("Moved to row: " + row + " col: " + col);
    super.makeMove(row, col);
  }

  @Override
  public boolean isValidMove(int row, int col) {
    if (row < 0 || col < 0 || row > mockBoard.size() || col > mockBoard.size()) {
      appendHelper("Invalid move.");
    }
    if (super.isValidMove(row, col)) {
      appendHelper("Valid move.");
    } else {
      appendHelper("Invalid move.");
    }
    return super.isValidMove(row, col);
  }

  @Override
  public String getTurn() {
    return super.getTurn();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    if (getTurn().equals("Black's turn")) {
      appendHelper("Score: " + super.getScore().get(DiscColor.BLACK));
    } else {
      appendHelper("Score: " + super.getScore().get(DiscColor.WHITE));
    }
    return super.getScore();
  }

}
