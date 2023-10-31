package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.Cell;

/**
 *
 */
public class TextView extends AbstractView {

  private final Appendable out;
  private final ReadOnlyReversi board;

  public TextView(Appendable out, ReadOnlyReversi board) {
    this.out = out;
    this.board = board;
  }

  public TextView(ReadOnlyReversi board) {
    this.out = System.out;
    this.board = board;
  }

  @Override
  public void renderBoard() throws IOException {
    out.append(toString()).append(System.lineSeparator());
  }

  @Override
  public void updateBoard(ReadOnlyReversi board) {

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
    }
  }
}