//package cs3500.reversi.model.strategy;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import cs3500.reversi.model.Cell;
//import cs3500.reversi.model.DiscColor;
//import cs3500.reversi.model.ReadOnlyReversi;
//import cs3500.reversi.model.RowCol;
//
///**
// * A strategy that prioritizes moves that occupy the corners of the Reversi board.
// * If no corner move is available for the player, it will fall back to the {@link MaxPointStrategy}.
// */
//public class GoForCornersStrategy extends MaxPointStrategy {
//
//  private ReadOnlyReversi model;
//
//  @Override
//  public Optional<RowCol> chooseMove(ReadOnlyReversi inWhich, DiscColor forWhom) {
//    this.model = inWhich;
//    int boardSideLength = model.getSideLength();
//    int middleCellRowCol = boardSideLength - 1;
//    List<List<Cell>> board = model.getBoard();
//
//    Cell currentCell = (Cell) board.get(middleCellRowCol).get(middleCellRowCol);
//    for (int layer = 0; layer < middleCellRowCol; layer++) {
//      currentCell = (Cell) currentCell.getUpperLeft();
//    }
//
//    List<Cell> outskirtCells = new ArrayList<>();
//
//    Map<Cell, RowCol> validMoves = new HashMap<>();
//
//    addOuterLayerToList(outskirtCells, currentCell, middleCellRowCol);
//    getValidCellsForModel(validMoves);
//
//    for (Map.Entry<Cell, RowCol> entry : validMoves.entrySet()) {
//      for (Cell outskirtCell : outskirtCells) {
//        if (entry.getKey() == outskirtCell && entry.getKey().getColor() == forWhom) {
//          return Optional.ofNullable(entry.getValue());
//        }
//      }
//    }
//
//    return super.chooseMove(model, forWhom);
//  }
//
//  private void getValidCellsForModel(Map<Cell, RowCol> validMoves) {
//    for (int row = 0; row < model.getBoard().size(); row++) {
//      for (int col = 0; col < model.getBoard().size(); col++) {
//        if (model.getBoard().get(row).get(col) == null) {
//          continue;
//        }
//        if (model.isValidMove(row, col)) {
//          validMoves.put((Cell) model.getBoard().get(row).get(col), new RowCol(row, col));
//        }
//      }
//    }
//  }
//
//  private void addOuterLayerToList(List<Cell> outskirtCells, Cell initialOuterCell,
//                                   int timesToTravel) {
//    Cell currentCell = initialOuterCell;
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getRight();
//      outskirtCells.add(currentCell);
//    }
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getBottomRight();
//      outskirtCells.add(currentCell);
//    }
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getBottomLeft();
//      outskirtCells.add(currentCell);
//    }
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getLeft();
//      outskirtCells.add(currentCell);
//    }
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getUpperLeft();
//      outskirtCells.add(currentCell);
//    }
//    for (int side = 0; side < timesToTravel; side++) {
//      currentCell = (Cell) currentCell.getUpperRight();
//      outskirtCells.add(currentCell);
//    }
//  }
//}
