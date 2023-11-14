package cs3500.reversi.model;

import java.util.Optional;

/**
 * The {@code Strategy} interface defines methods for Reversi game strategies, including
 * choosing moves. Implementing classes are expected to work with the Reversi game model
 * and controller.
 */
public interface Strategy {

  /**
   * Chooses a {@link RowCol} which represents the move which the strategy has "chosen".
   *
   * @param model   the {@link ReadOnlyReversi} model which you wish to choose a move for.
   * @param forWhom the {@link DiscColor} representing the player you wish the
   *                method to choose a move for.
   * @return optionally returns a {@link RowCol}.
   *         It may not return anything in the case that the strategy has not found any valid move.
   */
   Optional<RowCol> chooseMove(ReadOnlyReversi model, DiscColor forWhom);

}
