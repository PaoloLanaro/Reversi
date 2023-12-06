package cs3500.reversi.provider.model;

public interface IHex {

  /**
   * Updates the color to the given color.
   * @param tileColor is the given new tile color.
   */
  public void updateColor(TileType tileColor);

  /**
   * Creates a string of the q r and s of
   * this hex.
   * @return a string rep of this hex.
   */
  public String toStringQRS();

  public String toString();

  public boolean sameColor(TileType color);

  public boolean sameColor(IHex hex);

  public boolean same(int val, String whatToMatch);

  /**
   * Returns if the given hex is the opposite color
   * of this hex.
   * @param hex the given hex.
   * @return true if the hex is opposite color.
   */
  public boolean oppositeColor(IHex hex);

  /**
   * Returns if the given hex is the opposite color
   * of this tileType.
   * @param hex the given hex.
   * @return true if the hex is opposite color.
   */
  public boolean oppositeColor(TileType hex);

  public int getQ();

  public int getS();

  public int getR();

  public void switchColor(TileType tile);

  public TileType getTileColor();

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  /**
   * checks if color is none.
   * @return true if none.
   */
  public boolean colorNone();
}
