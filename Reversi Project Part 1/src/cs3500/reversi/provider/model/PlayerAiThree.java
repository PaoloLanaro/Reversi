package cs3500.reversi.provider.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * An advances Strategy AI in which the AIPlayer favors corner
 * plays over other available moves.
 */
//public class PlayerAiThree implements GenericPlayer, PlayerAi  {
//
//  private final ReadOnlyReversiModel model;
//  private final GenericPlayer player;
//
//  /**
//   * The constructor for player ai one.
//   * @param model is the given read only model of the board.
//   * @param color is the color of this players tiles.
//   */
//  public PlayerAiThree(ReadOnlyReversiModel model, TileType color) {
//    this.model = model;
//    this.player = new Player(color, "ai game");
//  }
//
//  @Override
//  public TileType getTileColor() {
//    return this.player.getTileColor();
//  }
//
//  @Override
//  public TileType getOppositeColor() throws IllegalArgumentException {
//    if (this.player.getTileColor() == TileType.BLACK) {
//      return TileType.WHITE;
//    }
//    if (this.player.getTileColor() == TileType.WHITE) {
//      return TileType.BLACK;
//    }
//    throw new IllegalArgumentException("invalid color input");
//  }
//
//  /**
//   * finds the best possible trying first to go into the corners.
//   * if going into the corners isn't possible return empty optional hex.
//   * @return Hex object that should make move.
//   * @throws IllegalStateException if there is no valid move available.
//   */
//  @Override
//  public Optional<IHex> findBestMove() throws IllegalStateException, IOException {
//    HashMap<IHex, Integer> moves = this.model.legalMoveAvailableList((Player) this.player);
//    IHex cornerHex = null;
//    List<List<IHex>> grid = model.getGridCopy();
//    int rConverter = (model.getMaxRowLength() - 1 ) / 2;
//    int max = -1;
//    for (IHex h : moves.keySet()) {
//      int r = h.getR() + rConverter;
//      List<IHex> gridRow = grid.get(r);
//      IHex corner1 = gridRow.get(0);
//      IHex corner2 = gridRow.get(gridRow.size() - 1);
//      if (h.equals(corner2) || h.equals(corner1)) {
//        if (moves.get(h) > max) {
//          max = moves.get(h);
//          cornerHex = h;
//        }
//      }
//    }
//    if (cornerHex == null) {
//      return Optional.empty();
//    }
//    return Optional.of(cornerHex);
//  }
//}
