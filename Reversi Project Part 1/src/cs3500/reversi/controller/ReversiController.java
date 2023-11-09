package cs3500.reversi.controller;

import cs3500.reversi.model.MutableReversi;

public interface ReversiController {
  void playGame(MutableReversi model);

  void handleCellClick(int row, int col);

}
