package cs3500.reversi.model.players;

import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * Abstract player class that will contain duplicated code between player implementations.
 */
public abstract class AbstractPlayer implements Player {

  @Override
  public void makeMove(BasicReversi board) {
    // empty, waiting for future assignments
  }

  @Override
  public DiscColor getColor() {
    // empty, waiting for future assignments
    return null;
  }
}
