package cs3500.reversi.provider.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * The interface for Reversi Model.
 * This represents the necessary rules and state updates and observers
 * to properly upkeep the rules and state of game as described in README
 */
public interface ReversiModel extends ReadOnlyReversiModel {

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
   * @param color is the tile color of the player
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

  void addFeatureListener(ModelFeatures features);

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
   * Allows the player to take its two possible actions: choose to
   * pass and switch to opposite players turn or to place their tile
   * in a legal way as described in validMove.
   * @param player is the given player whose turn it is.
   * @param pass is true if a player is passing.
   * @param hex is the current hex that the player tries to switch.
   * @throws IllegalStateException if the game hasnt started or the
   *                              hex is null when the player isnt passing,
   *                              or the move is invalid.
   */
  public void playerTurn(GenericPlayer player, boolean pass, IHex hex, TileType currentPlayer)
          throws IllegalStateException, IllegalArgumentException;

  /**
   * Switch to the opposite players turn.
   * @throws IllegalStateException if the game hasnt started.
   */
  public void switchTurn() throws IllegalStateException;

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

  /**
   * Returns a non-mutable copy of the grid.
   * @return a list of list of hexes.
   */
  public List<List<IHex>> getGridCopy();

  /**
   * Gets the hex based on its cords.
   * @param q is the top left cord.
   * @param r is the bottom right cord.
   * @param s is the left bottom cord.
   * @return the hex at this location.
   */
  public IHex getSpecificHex(int q, int r, int s);


  /**
   * finds all the possible legal move a player with a specific tile can make based on the board.
   * @param player the player that checking for moves for
   * @return a hashmap of possible move hexes and how many tiles they flip
   * @throws IllegalArgumentException if player is null
   * @throws IllegalStateException if games hasn't started or no moves available
   */
  public HashMap<IHex, Integer> legalMoveAvailableList(GenericPlayer player)
          throws IllegalArgumentException, IllegalStateException, IOException;

  public int getPassCount();
}
