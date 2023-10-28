package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cell on a game board in a Reversi game. Cells can be in different
 * colors, such as empty, occupied by a black disc, or occupied by a white disc.
 */
public class Cell {
  private Player occupant;
  private PlayerColor color;
  private Cell upperLeft, upperRight, left, right, bottomLeft, bottomRight;
  private List<Cell> neighbors;

  public Cell() {
    color = PlayerColor.EMPTY;
    occupant = null;
    neighbors = new ArrayList<>();
  }

  public List<Cell> getNeighbors() {
    return neighbors;
  }

  public void setUpperLeft(Cell cell) {
    neighbors.add(cell);
    upperLeft = cell;
  }

  public void setUpperRight(Cell cell) {
    upperRight = cell;
  }

  public void setLeft(Cell cell) {
    left = cell;
  }

  public void setRight(Cell cell) {
    right = cell;
  }

  public void setBottomLeft(Cell cell) {
    bottomLeft = cell;
  }

  public void setBottomRight(Cell cell) {
    bottomRight = cell;
  }

  public Cell getUpperLeft() {
    return upperLeft;
  }

  public Cell getUpperRight() {
    return upperRight;
  }

  public Cell getLeft() {
    return left;
  }

  public Cell getRight() {
    return right;
  }

  public Cell getBottomLeft() {
    return bottomLeft;
  }

  public Cell getBottomRight() {
    return bottomRight;
  }

  /**
   * Retrieves the color of the cell, indicating whether it is empty, occupied by a black disc,
   * or occupied by a white disc.
   *
   * @return the color of the cell as a {@link CellState}.
   */
  public PlayerColor getColor() {
    return color;
  }

  /**
   * Retrieves the player associated with the cell. The player represents the one who placed
   * a disc in the cell.
   *
   * @return the player associated with the cell, or null if the cell is empty.
   */
  public Player getOccupant() {
    return occupant;
  }

  /**
   * Sets the color of the cell to represent a black disc and associates it with the specified
   * player.
   *
   * @param player the player who placed the black disc in the cell.
   */
  public void setBlackDisc(Player player) {
    color = PlayerColor.BLACK;
    occupant = player;
  }

  /**
   * Sets the color of the cell to represent a white disc and associates it with the specified
   * player.
   *
   * @param player the player who placed the white disc in the cell.
   */
  public void setWhiteDisc(Player player) {
    color = PlayerColor.WHITE;
    occupant = player;
  }

  /**
   * Clears the cell, making it empty and removing any associated player.
   */
  public void clear() {
    color = PlayerColor.EMPTY;
    occupant = null;
  }

  @Override
  public String toString() {
    return color + " " + occupant;
  }
}