package cs3500.reversi.players;

import cs3500.reversi.model.Board;

/**
 *
 */
public class HumanPlayer extends AbstractPlayer {

  private final PlacedColor color;

  public HumanPlayer(PlacedColor color) {
    this.color = color;
  }

  @Override
  public void makeMove(Board board) {

  }

  @Override
  public PlacedColor getColor() {
    return color;
  }
}
