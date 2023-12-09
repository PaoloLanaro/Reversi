package cs3500.reversi.view;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.ReversiCell;

public class SquareTextView {
  private final Appendable out;
  private final ReadOnlyReversi model;

  /**
   * Constructs a TextView with a custom output stream and a read-only Reversi board.
   *
   * @param out   The output stream to write the game board to.
   * @param model The read-only Reversi board to display.
   */
  public SquareTextView(Appendable out, ReadOnlyReversi model) {
    this.out = out;
    this.model = model;
  }

  /**
   * Constructs a TextView with the standard output stream and a read-only Reversi board.
   *
   * @param model The read-only Reversi board to display.
   */
  public SquareTextView(ReadOnlyReversi model) {
    this.out = System.out;
    this.model = model;
  }

  @Override
  public String toString() {
    StringBuilder boardRepresentation = new StringBuilder();

    int size = model.getSideLength();

    int middleRow = model.getBoard().size() / 2;

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        Cell cell = model.getCellAt(row, col);
      }
    }

    for (int row = 0; row < model.getBoard().size(); row++) {
      int initialOffset = row - middleRow;
      for (int col = 0; col < model.getBoard().size(); col++) {
        ReversiCell cell = (ReversiCell) model.getBoard().get(row).get(col);
        if (row <= middleRow) {
          if (cell == null) {
            boardRepresentation.append(' ');
            continue;
          }
          getCellStateAsString(boardRepresentation, cell);
        }
        if (row > middleRow) {
          while (initialOffset > 0) {
            boardRepresentation.append(' ');
            initialOffset--;
          }
          if (cell == null) {
            continue;
          }
          getCellStateAsString(boardRepresentation, cell);
        }
      }
      boardRepresentation.append('\n');
    }
    return boardRepresentation.toString();

  }

  private void getCellStateAsString(StringBuilder boardRepresentation, ReversiCell cell) {
    switch (cell.getColor()) {
      case EMPTY:
        boardRepresentation.append('_');
        boardRepresentation.append(' ');
        break;
      case BLACK:
        boardRepresentation.append('X');
        boardRepresentation.append(' ');
        break;
      case WHITE:
        boardRepresentation.append('O');
        boardRepresentation.append(' ');
        break;
      default:
        throw new IllegalStateException("Cell color is null. Not allowed");
    }
  }
}
