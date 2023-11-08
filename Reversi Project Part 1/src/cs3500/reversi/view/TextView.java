package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.Cell;

/**
 * A text-based view for a Reversi game, displaying the game board using characters.
 * Empty cells are represented by '_', black discs by 'X', and white discs by 'O'.
 */
public class TextView {

  private final Appendable out;
  private final ReadOnlyReversi board;

  /**
   * Constructs a TextView with a custom output stream and a read-only Reversi board.
   *
   * @param out   The output stream to write the game board to.
   * @param board The read-only Reversi board to display.
   */
  public TextView(Appendable out, ReadOnlyReversi board) {
    this.out = out;
    this.board = board;
  }

  /**
   * Constructs a TextView with the standard output stream and a read-only Reversi board.
   *
   * @param board The read-only Reversi board to display.
   */
  public TextView(ReadOnlyReversi board) {
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
        Cell cell = board.getBoard().get(row).get(col);
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

  private void getCellStateAsString(StringBuilder boardRepresentation, Cell cell) {
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