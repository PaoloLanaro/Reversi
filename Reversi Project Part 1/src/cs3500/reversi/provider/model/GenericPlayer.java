package cs3500.reversi.provider.model;

/**
 * A genericPlayer is an interface that represents
 * the necessary information needed for a human/ai
 * to properly play out a game of Reversi.
 */
public interface GenericPlayer {

  /**
   * Returns the tile color of this player.
   * @return the string rep of the tile color.
   */
  TileType getTileColor();

  /**
   * Returns the opposite color of this hex.
   * @return the opposite tiletype of this hex.
   * @throws IllegalArgumentException if it's a Color.None.
   */
  TileType getOppositeColor() throws IllegalArgumentException;

  /**
   * Gets the status of the player. The status
   * options are move, pass, forcepass, or none.
   * @return an enum of the current status.
   */
  PlayStatus getPlayerStatus();

  /**
   * Updates the player's status to the given
   * playerstatus.
   * @param ps is the desired change in status.
   */
  void updatePlayerStatus(PlayStatus ps);

  /**
   * Gets the score of the player.
   * @return an int representing a player score.
   */
  int getScore();

  /**
   * Updates the player's score to the given score.
   * @param score is an int representation of a given score.
   */
  void updateScore(int score);

  /**
   * Returns the player name.
   * @return a string representation of the player name.
   */
  String getPlayerName();

  /**
   * Checks if the player is an ai.
   * @return true if the player is an ai.
   */
  boolean isAI();

}
