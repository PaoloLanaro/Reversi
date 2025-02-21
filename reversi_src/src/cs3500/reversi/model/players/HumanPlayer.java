package cs3500.reversi.model.players;

import java.util.Optional;

import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * An implementation of the Player interface which represents a human player.
 */
public class HumanPlayer implements Player {

  private final DiscColor playerColor;

  /**
   /**
   * Constructs an {@link Player} object with the player's playing color,
   * and a {@link ReadOnlyReversi} they should follow.
   **
   * @param model the {@link ReadOnlyReversi} model which the player will have access to.
   * @param playerColor the {@link DiscColor} which the {@link Player} will "be".
   */
  public HumanPlayer(ReadOnlyReversi model, DiscColor playerColor) {
    this.playerColor = playerColor;
  }

  @Override
  public Optional<RowCol> getMove(ReadOnlyReversi board) {
    return Optional.empty();
  }

  @Override
  public String getColor() {
    return playerColor.toString();
  }

}
