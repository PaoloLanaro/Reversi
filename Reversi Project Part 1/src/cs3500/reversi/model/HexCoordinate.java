package cs3500.reversi.model;

/**
 * Represents a hexagonal coordinate in a Reversi game. This class is used to
 * represent the position of cells on a hexagonal game board. Hex coordinates
 * typically consist of row and column values.
 */
public class HexCoordinate {
  // constructor that takes in two ints. one for row, one for col
  private final int s, q, r;

  public HexCoordinate(int q, int r) {
    this.r = r;
    // gwkki
    this.q = q;
    this.s = -q - r; //may nto need this, could delegate responsibility to getters.
  }

  //getters for both
  public int getR() {
    return 0;
  }

  public int getQ() {
    return 0;
  }

}
