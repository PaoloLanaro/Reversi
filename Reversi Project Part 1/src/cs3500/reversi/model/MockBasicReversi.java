package cs3500.reversi.model;
import java.util.List;
import java.util.Map;

/**
 * Creates a mock for the purpose of testing and getting output messages confirming
 * that moves are actually working. This is made to support the use of {@link Strategy}s.
 */
public class MockBasicReversi {

  private final List<List<Cell>> mockBoard;
  private final Appendable out;
  private final BasicReversi model;

  /**
   * The constructor for this mock class.
   *
   * @param size the initial size you wish one of the sides of the board to be "long".
   *             This will be the amount of hexagons on each "side".
   * @param out the appendable that messages will be added to.
   */
  public MockBasicReversi(int size, Appendable out) {
    //super(size);
    this.model = new BasicReversi(size);
    this.mockBoard = model.getBoard();
    this.out = out;
  }

  private void appendHelper(String appendable) {
    try {
      out.append(appendable).append('\n');
    } catch (Exception e) {
      System.out.println("cant append to appendable.");
    }
  }

//  @Override
  public void passTurn() {
    appendHelper("Passed turn.");
//    super.passTurn();
    model.passTurn();
  }

//  @Override
  public void makeMove(int row, int col) {
    appendHelper("Moved to row: " + row + " col: " + col);
//    super.makeMove(row, col);
    model.makeMove(row, col);
  }

//  @Override
  public boolean isValidMove(int row, int col) {
    if (row < 0 || col < 0 || row > mockBoard.size() || col > mockBoard.size()) {
      appendHelper("Invalid move.");
    }
    if (model.isValidMove(row, col)) {
      appendHelper("Valid move.");
    } else {
      appendHelper("Invalid move.");
    }
    return model.isValidMove(row, col);
  }

//  @Override
  public String getTurn() {
//    return super.getTurn();
    return model.getTurn();
  }

//  @Override
  public Map<DiscColor, Integer> getScore() {
    if (getTurn().equals("Black's turn")) {
      appendHelper("Score: " + model.getScore().get(DiscColor.BLACK));
    } else {
      appendHelper("Score: " + model.getScore().get(DiscColor.WHITE));
    }
    return model.getScore();
  }

}
