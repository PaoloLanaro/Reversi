package cs3500.reversi.provider.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * representation of the Rervsi game with its states and rules but it
 * only consists of methods that observer and do not mutate.
 */
public interface ReadOnlyReversiModel {

  /**
   * This method verifies that the given row number is valid.
   * It creates a 6 sided hexagonal shaped grid made out of smaller
   * hex objects. Game will start with by placing black and white in
   * an alternating pattern of 6 hex objects surrounding the intermost
   * hex object, which is empty. Checks if the grid is valid.
   * @param playerNames is the list of 2 player names
   * @throws IllegalArgumentException if the given row is an even number less than
   *              4 or the
   *              hex axis cords are invalid in the grid or if there are more
   *              or less than 2 player names.
   *  @throws IllegalStateException if game has started.
   */
  public void startGame(List<GenericPlayer> playerNames)
          throws IllegalArgumentException, IllegalStateException;


  /**
   * The method returns true if the hex is a valid move for the
   * current player like specified by the readme.
   * @param hex represents the hex the user wants to put a tile on.
   * @param color of current player
   * @return true if the move is valid.
   * @throws IllegalStateException if game hasnt started.
   */
  public boolean validMove(IHex hex, TileType color) throws IllegalStateException;

  /**
   * This method checks if there is a legal move available for the
   * given player. A move is available if there is at least one
   * valid move as described in valid move function.
   * @param player is the given player whose turn it is.
   * @return true if there is a legal move available.
   * @throws IllegalArgumentException if player is null.
   *  @throws IllegalStateException if game hasn't started.
   */
  public boolean legalMoveAvailable(GenericPlayer player) throws IllegalArgumentException,
          IllegalStateException;

  /**
   * Game is over if both players pass.
   * @return true if the game is over.
   */
  public boolean gameOver() throws IllegalStateException;

  /**
   * Returns the player who has the greatest score as defined
   * in the player class.
   * if there is a tie between two players null is returned.
   * @return the winning player. or null if there is tie.
   * @throws IllegalStateException if the game hasnt started or
   *                          the game hasnt ended.
   */
  public GenericPlayer determineWinner() throws IllegalStateException;


  /**
   * Returns the player whose turn it is.
   * @return a player whose turn it is.
   * @throws IllegalStateException if the game hasnt started.
   */
  public GenericPlayer getCurrentPlayer() throws IllegalStateException;

  /**
   * Returns a list of hex objects that contain the players
   * specific tile on them.
   * @param player is a given player.
   * @return a list of hex objects.
   * @throws IllegalStateException if the game hasnt started.
   * @throws IllegalArgumentException if player is null.
   */
  public List<IHex> getPlayerTiles(GenericPlayer player) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Returns a list of hex objects that contain the players
   * specific tile on them.
   * @param color is a given player color.
   * @return a list of hex objects.
   * @throws IllegalStateException if the game hasnt started.
   * @throws IllegalArgumentException if player is null.
   */
  public List<IHex> getPlayerTiles(TileType color) throws IllegalStateException,
          IllegalArgumentException;

  /**
   * Returns the length of the longest row.
   * @return an int representing the longest row.
   * @throws IllegalStateException if the game hasnt started.
   */
  public int getMaxRowLength() throws IllegalStateException;

  public List<List<IHex>> getGridCopy();

  public HashMap<IHex, Integer> legalMoveAvailableList(GenericPlayer player)
          throws IllegalArgumentException, IllegalStateException, IOException;


  void addFeatureListener(ModelFeatures features);

  public int getPassCount();
}
