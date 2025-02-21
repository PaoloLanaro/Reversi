package cs3500.reversi.view;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.ReadOnlyReversi;

/**
 * A text-based view for a Square Reversi game, displaying the game board using characters.
 * Empty cells are represented by '_', black discs by 'X', and white discs by 'O'.
 */
public class SquareTextView {
  private final Appendable out;
  private final ReadOnlyReversi model;

  /**
   * Constructs a TextView with a custom output stream and a read-only Reversi board.
   * <p>
   * @deprecated Use the SquareReversiFrame class to create a GUI rather than a textual view.
   * <p>
   * @param out   The output stream to write the game board to.
   * @param model The read-only Reversi board to display.
   */
  public SquareTextView(Appendable out, ReadOnlyReversi model) {
    this.out = out;
    this.model = model;
  }

  /**
   * Constructs a TextView with the standard output stream and a read-only Reversi board.
   * <p>
   * @deprecated Use the SquareReversiFrame class to create a GUI rather than a textual view.
   * <p>
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

        getCellStateAsString(boardRepresentation, cell);
      }
      boardRepresentation.append('\n');
    }

    return boardRepresentation.toString();
  }

  private void getCellStateAsString(StringBuilder boardRepresentation, Cell cell) {
    switch (cell.getColor()) {
      case EMPTY -> {
        boardRepresentation.append('-');
        boardRepresentation.append(' ');
      }
      case BLACK -> {
        boardRepresentation.append('X');
        boardRepresentation.append(' ');
      }
      case WHITE -> {
        boardRepresentation.append('O');
        boardRepresentation.append(' ');
      }
      default -> throw new IllegalStateException("Cell color is null. Not allowed");
    }
  }
}
