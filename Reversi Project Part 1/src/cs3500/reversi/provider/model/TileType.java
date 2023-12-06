package cs3500.reversi.provider.model;

/**
 * Represents a type of tile.
 */
public enum TileType {
  WHITE,
  BLACK,
  NONE;

  /**
   * Returns a toString representation of the
   * enum.
   * @return X,O, or _.
   */
  public String toString() {
    if (TileType.BLACK == this) {
      return "X";
    }
    if (TileType.WHITE == this) {
      return "O";
    }
    return "_";
  }
}
