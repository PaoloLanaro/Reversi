package cs3500.reversi.view;

import java.io.IOException;
import java.util.List;

import cs3500.reversi.model.Board;
import cs3500.reversi.model.Cell;

/**
 *
 */
public class TextView extends AbstractView {

  private final Appendable out;
  private final Board board;

  public TextView(Appendable out, Board board) {
    this.out = out;
    this.board = board;
  }

  public TextView(Board board) {
    this.out = System.out;
    this.board = board;
  }

  @Override
  public void renderBoard() throws IOException {
    out.append(toString()).append(System.lineSeparator());
  }

  private void appendHelper(char player) {
    try {
      out.append(player);
    } catch (IOException exception) {
      throw new IllegalStateException("Could not write to game board.");
    }
  }

  @Override
  public void updateBoard(Board board) {

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
    switch (cell.getState()) {
      case EMPTY:
        boardRepresentation.append('_');
        boardRepresentation.append(' ');
        break;
      case BLACK:
        boardRepresentation.append('X');
        break;
      case WHITE:
        boardRepresentation.append('O');
    }
  }
}