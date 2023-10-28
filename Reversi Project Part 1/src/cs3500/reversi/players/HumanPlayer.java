package cs3500.reversi.players;

import cs3500.reversi.model.Board;
import cs3500.reversi.model.Player;
import cs3500.reversi.model.PlayerColor;

/**
 *
 */
public class HumanPlayer extends AbstractPlayer {

  private final PlayerColor color;

  public HumanPlayer(PlayerColor color) {
    this.color = color;
  }

  @Override
  public void makeMove(Board board) {

  }

  @Override
  public PlayerColor getColor() {
    return color;
  }
}
