package cs3500.reversi.model.players;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;

/**
 * An implementation of the Player interface which represents a human player.
 */
public class HumanPlayer implements Player {

  private final DiscColor playerColor;

  public HumanPlayer(ReadOnlyReversi model, DiscColor playerColor) {
    this.playerColor = playerColor;
  }

  @Override
  public void makeMove(BasicReversi board) {

  }

  @Override
  public DiscColor getColor() {
    return playerColor;
  }


  // empty, waiting for future assignments
}
