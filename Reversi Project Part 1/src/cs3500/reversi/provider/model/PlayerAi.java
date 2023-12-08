package cs3500.reversi.provider.model;

import java.io.IOException;
import java.util.Optional;

/**
 * Represents a basic Ai that could
 * use any strategy. to find a hex that represent a move a player could make.
 */
public interface PlayerAi {


  /**
   * Uses a strategy to find the most optimal
   * The most optimal option is the valid move that flips
   * infallable - return a move or throw.
   * @return hex represting the tile place for the best move
   * @throws IllegalStateException game hasn't started or no valid move available.
   */
  Optional<IHex> findBestMove() throws IllegalStateException, IOException;
}
