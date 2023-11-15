package cs3500.reversi.model.players;

import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.strategy.Strategy;

/**
 * An implementation of the {@link Player} interface that represents an AI player.
 * This player will choose and make moves.
 */
public class AIPlayer extends AbstractPlayer {
  private final DiscColor color;
  private final Strategy strategy;

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
  public void makeMove(BasicReversi model) {
    // super.makeMove(model);
    Optional<RowCol> bestMove = strategy.chooseMove(model, color);
    if (bestMove.isEmpty()) {
      model.passTurn();
      return;
    }
    model.makeMove(bestMove.get().getRow(), bestMove.get().getCol());
  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

}
