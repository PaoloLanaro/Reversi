package cs3500.reversi.model.players;

import java.awt.*;
import java.util.Optional;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.strategy.Strategy;

/**
 * An implementation of the {@link Player} interface that represents an AI player.
 * This player will choose and make moves.
 */
public class AIPlayer implements Player {
  private final DiscColor color;
  private final Strategy strategy;
  private ViewFeatures featureListener;

  /**
   * Constructs an {@link AIPlayer} object with the player's playing color,
   * and a {@link Strategy} they should follow.
   *
   * @param color the {@link DiscColor} which the {@link AIPlayer} will "be".
   * @param strategy the {@link Strategy} which the {@link AIPlayer} should use to choose moves.
   */
  public AIPlayer(DiscColor color, Strategy strategy) {
    this.color = color;
    this.strategy = strategy;
  }

  @Override
  public void addFeaturesListener(ViewFeatures featureListener) {
    this.featureListener = featureListener;
  }

  @Override
  public Optional<RowCol> getMove(ReadOnlyReversi model) {
    Optional<RowCol> bestMove = strategy.chooseMove(model, color);
    // Extract the move from Optional and pass it to makeMove
    bestMove.ifPresent(move -> featureListener.makeMove(move));
//    if (bestMove.isPresent()) {
//      RowCol move = bestMove.get();
//      System.out.println("Move found");
//      featureListener.makeMove(move);
//    } else {
//      System.out.println("No move found");
//    }
    return bestMove;
  }

  @Override
  public String getColor() {
    return color.toString();
  }

}
