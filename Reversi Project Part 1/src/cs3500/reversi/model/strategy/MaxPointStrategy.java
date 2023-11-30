package cs3500.reversi.model.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Cell;
import cs3500.reversi.model.ReversiCell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * A {@link Strategy} which simply tries to flip the most tiles in one move.
 * If there are multiple moves that capture the same number of pieces,
 * this class breaks ties by choosing the move with the uppermost-leftmost coordinate,
 * where uppermost is weighted more than leftmost.
 */
public class MaxPointStrategy implements Strategy {

  private ReadOnlyReversi model;

  @Override
  public Optional<RowCol> chooseMove(ReadOnlyReversi model, DiscColor forWhom) {
    this.model = model;

    Map<ReversiCell, RowCol> validMoves = new HashMap<>();

    int underlyingLength = this.model.getSideLength() * 2 - 1;

    for (int row = 0; row < underlyingLength; row++) {
      for (int col = 0; col < underlyingLength; col++) {
        if (model.getBoard().get(row).get(col) == null) {
          continue;
        }
        if (model.isValidMove(row, col)) {
          validMoves.put((ReversiCell) model.getBoard().get(row).get(col), new RowCol(row, col));
        }
      }
    }

    if (validMoves.isEmpty()) {
      return Optional.empty();
    }

    return Optional.ofNullable(getBestScore(validMoves, forWhom));

  }

  private RowCol getBestScore(Map<ReversiCell, RowCol> validMoves, DiscColor forWhom) {
    int maxScore = Integer.MIN_VALUE;
    RowCol bestMove = null;

    for (Map.Entry<ReversiCell, RowCol> entry : validMoves.entrySet()) {
      RowCol move = entry.getValue();

      // Simulate the move by updating the copied board directly
      List<List<Cell>> originalBoard = model.getBoard();
      BasicReversi basicReversi = new BasicReversi(originalBoard, forWhom);
      basicReversi.startGame();
      basicReversi.makeMove(move.getRow(), move.getCol());

      // Calculate the score after the simulated move
      int score = basicReversi.getScore().get(forWhom);

      // Update the best move if the current move has a higher score
      if (score > maxScore) {
        maxScore = score;
        bestMove = move;
        continue;
      }

      if (score == maxScore) {
        if (move.getRow() < bestMove.getRow()) {
          bestMove = move;
        }
        if (move.getRow() == bestMove.getRow() && move.getCol() <= bestMove.getCol()) {
          bestMove = move;
        }
      }
    }
    return bestMove;
  }
}
