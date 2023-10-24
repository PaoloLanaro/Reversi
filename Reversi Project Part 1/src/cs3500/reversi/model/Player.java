package cs3500.reversi.model;

import cs3500.reversi.players.PlayerColor;

/**
 *
 */
public interface Player {

  void makeMove(Board board);

  PlayerColor getColor();
}
