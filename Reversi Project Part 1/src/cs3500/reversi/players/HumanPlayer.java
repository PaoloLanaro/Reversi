package cs3500.reversi.players;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.DiscColor;

/**
 *
 */
public class HumanPlayer extends AbstractPlayer {

  private final DiscColor color;

  public HumanPlayer(DiscColor color) {
    this.color = color;
  }

  @Override
  public void makeMove(ReadOnlyReversi board) {

  }

  @Override
  public DiscColor getColor() {
    return color;
  }
}
