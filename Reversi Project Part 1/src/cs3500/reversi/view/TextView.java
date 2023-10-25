package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.model.Board;
import cs3500.reversi.model.Cell;

/**
 *
 */
public class TextView extends AbstractView {

  Appendable appendable;

  public TextView(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void renderBoard(Board board) {
    // get player at each location in board and print out 'X', 'O', '_', or ' '.
    Cell[][] currentBoard = board.getBoardState();
    for (Cell[] cellRow : currentBoard) {
      for (Cell cell : cellRow) {
        switch (cell.getState()) {
          case EMPTY:
            appendHelper('_');
            break;
          case BLACK:
            appendHelper('X');
            break;
          case WHITE:
            appendHelper('O');
          case null:
            appendHelper(' ');
        }
        appendHelper('\n');
      }
    }
    System.out.println("ello");
  }

  private void appendHelper(char player) {
    try {
      appendable.append(player).append(' ');
    } catch (IOException exception) {
      throw new IllegalStateException("Could not write to game board.");
    }
  }

  @Override
  public void updateBoard(Board board) {

  }
}
