package cs3500.reversi.model.players;

import java.util.Optional;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.Strategy;

/**
 * An implementation of the Player interface that represents an AI player.
 */
public class AIPlayer extends AbstractPlayer {
  // empty, waiting for future assignments
  private final DiscColor color;
  private final Strategy strategy;
  public AIPlayer(DiscColor color, Strategy strategy) {
    this.color = color;
    this.strategy = strategy;
  }

  @Override
  public void makeMove(BasicReversi board) {
//    super.makeMove(board);
    Optional<RowCol> bestMove = strategy.chooseMove(board, color);
    if (bestMove.isEmpty()) {
      board.passTurn();
      return;
    }
    board.makeMove(bestMove.get().row(), bestMove.get().col());
  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

}
