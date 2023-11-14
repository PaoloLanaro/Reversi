package cs3500.reversi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MaxPointStrategy implements Strategy {

  private ReadOnlyReversi model;

  @Override
  public Optional<RowCol> chooseMove(ReadOnlyReversi model, DiscColor forWhom) {
    this.model = model;

    Map<Cell, RowCol> validMoves = new HashMap<>();

    for (int row = 0; row < model.getBoard().size(); row++) {
      for (int col = 0; col < model.getBoard().size(); col++) {
        if (model.getBoard().get(row).get(col) == null) {
          continue;
        }
        if(model.isValidMove(row, col)) {
          validMoves.put(model.getBoard().get(row).get(col), new RowCol(row, col));
        }
      }
    }

    if (validMoves.isEmpty()) {
      return Optional.empty();
    }

    return Optional.ofNullable(getBestScore(validMoves, forWhom));

  }

  private RowCol getBestScore(Map<Cell, RowCol> validMoves, DiscColor forWhom) {
    int maxScore = Integer.MIN_VALUE;
    RowCol bestMove = null;

    for (Map.Entry<Cell, RowCol> entry : validMoves.entrySet()) {
      Cell cell = entry.getKey();
      RowCol move = entry.getValue();

      // Simulate the move by updating the copied board directly
      List<List<Cell>> originalBoard = model.getBoard();
      BasicReversi basicReversi = new BasicReversi(originalBoard, forWhom);
      basicReversi.makeMove(move.row(), move.col());

      // Calculate the score after the simulated move
      int score = basicReversi.getScore().get(forWhom);

      // Update the best move if the current move has a higher score
      if (score > maxScore) {
        maxScore = score;
        bestMove = move;
        continue;
      }

      if (score == maxScore) {
        // TODO SEE IF THIS IS AN ISSUE
        if (move.row() < bestMove.row()) {
          bestMove = move;
        }
        if (move.row() == bestMove.row() && move.col() <= bestMove.col()) {
          bestMove = move;
        }
      }
    }
    return bestMove;
  }
}
