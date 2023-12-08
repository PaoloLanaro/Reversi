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

  /**
   * Placeholder javadoc because this is the provider's code.
   * @return Placeholder javadoc because this is the provider's code.
   */

  public String toString();

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @param color Placeholder javadoc because this is the provider's code.
   * @return Placeholder javadoc because this is the provider's code.
   */
  public boolean sameColor(TileType color);

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @param hex Placeholder javadoc because this is the provider's code.
   * @return Placeholder javadoc because this is the provider's code.
   */
  public boolean sameColor(IHex hex);

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @param val Placeholder javadoc because this is the provider's code.
   * @param whatToMatch Placeholder javadoc because this is the provider's code.
   * @return Placeholder javadoc because this is the provider's code.
   */
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

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @return Placeholder javadoc because this is the provider's code.
   */
  public int getQ();

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @return Placeholder javadoc because this is the provider's code.
   */
  public int getS();

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @return Placeholder javadoc because this is the provider's code.
   */
  public int getR();

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @param tile Placeholder javadoc because this is the provider's code.
   */
  public void switchColor(TileType tile);

  /**
   * Placeholder javadoc because this is the provider's code.
   *
   * @return Placeholder javadoc because this is the provider's code.
   */
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
