package cs3500.reversi.model.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * A strategy that prioritizes moves that occupy the corners of the Reversi board.
 * If no corner move is available for the player, it will fall back to the {@link MaxPointStrategy}.
 */
public class GoForCornersStrategy extends MaxPointStrategy {

  private ReadOnlyReversi model;

  @Override
  public Optional<RowCol> chooseMove(ReadOnlyReversi inWhich, DiscColor forWhom) {
    this.model = inWhich;
    int boardSideLength = model.getSideLength();
    int middleCellRowCol = boardSideLength - 1;
    List<List<Cell>> board = model.getBoard();

    ReversiCell currentCell = (ReversiCell) board.get(middleCellRowCol).get(middleCellRowCol);
    for (int layer = 0; layer < middleCellRowCol; layer++) {
      currentCell = (ReversiCell) currentCell.getUpperLeft();
    }

    List<ReversiCell> outskirtCells = new ArrayList<>();

    Map<ReversiCell, RowCol> validMoves = new HashMap<>();


    addOuterLayerToList(outskirtCells, currentCell, middleCellRowCol);
    getValidCellsForModel(validMoves);

    for (Map.Entry<ReversiCell, RowCol> entry : validMoves.entrySet()) {
      for (ReversiCell outskirtCell : outskirtCells) {
        if (entry.getKey() == outskirtCell && entry.getKey().getColor() == forWhom) {
          return Optional.ofNullable(entry.getValue());
        }
      }
    }

    return super.chooseMove(model, forWhom);
  }

  private void getValidCellsForModel(Map<ReversiCell, RowCol> validMoves) {
    for (int row = 0; row < model.getBoard().size(); row++) {
      for (int col = 0; col < model.getBoard().size(); col++) {
        if (model.getBoard().get(row).get(col) == null) {
          continue;
        }
        if (model.isValidMove(row, col)) {
          validMoves.put((ReversiCell) model.getBoard().get(row).get(col), new RowCol(row, col));
        }
      }
    }
  }

  private void addOuterLayerToList(List<ReversiCell> outskirtCells, ReversiCell initialOuterCell,
                                   int timesToTravel) {
    ReversiCell currentCell = initialOuterCell;
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getRight();
      outskirtCells.add(currentCell);
    }
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getBottomRight();
      outskirtCells.add(currentCell);
    }
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getBottomLeft();
      outskirtCells.add(currentCell);
    }
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getLeft();
      outskirtCells.add(currentCell);
    }
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getUpperLeft();
      outskirtCells.add(currentCell);
    }
    for (int side = 0; side < timesToTravel; side++) {
      currentCell = (ReversiCell) currentCell.getUpperRight();
      outskirtCells.add(currentCell);
    }
  }
}
