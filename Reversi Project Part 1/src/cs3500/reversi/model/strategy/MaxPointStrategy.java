package cs3500.reversi.model.strategy;

import java.util.Optional;

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

    RowCol score = scoreCheck();

    if (score == null) {
      return Optional.empty();
    }

    return Optional.of(score);
  }

  private RowCol scoreCheck() {
    int underlyingLength = this.model.getBoard().size();

    RowCol currentBestCoord = null;
    int currentBestScore = Integer.MIN_VALUE;

    for (int row = 0; row < underlyingLength; row++) {
      for (int col = 0; col < underlyingLength; col++) {
        if (model.getBoard().get(row).get(col) == null) {
          continue;
        }
        int currentScore = model.getScoreFor(row, col);
        if (currentScore > currentBestScore) {
          currentBestScore = currentScore;
          currentBestCoord = new RowCol(row, col);
        }
      }
    }
    if (currentBestScore == 0) {
      return null;
    }
    return currentBestCoord;
  }
}
