package cs3500.reversi.view;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.ReversiCell;

/**
 * A text-based view for a Hex Reversi game, displaying the game board using characters.
 * Empty cells are represented by '_', black discs by 'X', and white discs by 'O'.
 */
public class HexTextView {

  private final Appendable out;
  private final ReadOnlyReversi board;

  /**
   * Constructs a TextView with a custom output stream and a read-only Reversi board.
   * <p>
   * @deprecated Use the HexReversiFrame class to create a GUI rather than a textual view.
   * <p>
   * @param out   The output stream to write the game board to.
   * @param board The read-only Reversi board to display.
   */
  public HexTextView(Appendable out, ReadOnlyReversi board) {
    this.out = out;
    this.board = board;
  }

  /**
   * Constructs a TextView with the standard output stream and a read-only Reversi board.
   * <p>
   * @deprecated Use the HexReversiFrame class to create a GUI rather than a textual view.
   * <p>
   * @param board The read-only Reversi board to display.
   */
  public HexTextView(ReadOnlyReversi board) {
    this.out = System.out;
    this.board = board;
  }

  @Override
  public String toString() {
    StringBuilder boardRepresentation = new StringBuilder();

    int middleRow = board.getBoard().size() / 2;

    for (int row = 0; row < board.getBoard().size(); row++) {
      int initialOffset = row - middleRow;
      for (int col = 0; col < board.getBoard().size(); col++) {
        ReversiCell cell = (ReversiCell) board.getBoard().get(row).get(col);
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
      case EMPTY -> {
        boardRepresentation.append('_');
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