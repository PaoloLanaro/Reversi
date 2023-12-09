package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;


/**
 * Represents a basic implementation of a basic hexagon Reversi Game. Implements the MutableReversi
 * interface to give methods for making moves, passing turns, and checking game state. The game is
 * played on a hex grid with a board size variable.
 *
 * <p>Class Invariant:
 *
 * <p>1. The passCounter should always be between 0 and 2.
 */
public class HexReversi extends AbstractReversi {
  
  public HexReversi(int initSize) {
    super(initSize);
  }

  public HexReversi(List<List<Cell>> otherBoard, DiscColor currentColor) {
    super(otherBoard, currentColor);
  }
}
