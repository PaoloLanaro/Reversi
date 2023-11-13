package cs3500.reversi.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockBasicReversi extends BasicReversi {

  private final List<List<Cell>> mockBoard;
  private final Map<DiscColor, Integer> mockScore;
  private final Appendable out;

  public MockBasicReversi(int size, List<List<Cell>> mockBoard, Map<DiscColor, Integer> mockScore,
                          Appendable out) {
    super(size);
    this.mockBoard = super.getBoard();
    this.mockScore = new HashMap<>(mockScore);
    this.out = out;
  }

  private void appendHelper(String appendable) {
    try {
      out.append(appendable);
    } catch (Exception e) {
      System.out.println("lost connection with appendable.");
    }
  }

  @Override
  public void passTurn() {
    appendHelper("Passed turn.");
  }

  @Override
  public void makeMove(int row, int col) {
    appendHelper("Moved to row: " + row + " col: " + col);
  }

  @Override
  public boolean isValidMove(int row, int col) {
    if (row < 0 || col < 0 || row > mockBoard.size() || col > mockBoard.size()) {
      appendHelper("Invalid move.");
    }
    appendHelper("Valid move.");
    return true;
  }

  @Override
  public String getTurn() {
    return super.getTurn();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    DiscColor mockTurn = DiscColor.EMPTY;
    if (getTurn().equals("Black's turn")) {
      mockTurn = DiscColor.BLACK;
    } else if (getTurn().equals("White's turn")) {
      mockTurn = DiscColor.WHITE;
    }
    appendHelper("Score: " + mockScore.get(mockTurn));
    return mockScore;
  }

}
