package cs3500.reversi.model.players;

import java.util.Optional;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * An implementation of the Player interface which represents a human player.
 */
public class HumanPlayer implements Player {

  private final DiscColor playerColor;

  public HumanPlayer(ReadOnlyReversi model, DiscColor playerColor) {
    this.playerColor = playerColor;
  }

  @Override
  public Optional<RowCol> getMove(ReadOnlyReversi board) {
    return null;
  }

  @Override
  public String getColor() {
    return playerColor.toString();
  }

  public void addFeaturesListener(ViewFeatures featureListener) {

  }


  // empty, waiting for future assignments
}
