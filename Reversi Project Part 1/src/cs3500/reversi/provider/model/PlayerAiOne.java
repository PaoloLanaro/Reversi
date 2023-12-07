package cs3500.reversi.provider.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * A player ai that on each move picks the position
 * that will capture the most pieces as possible.
 * (Strategy 1)
 */
//public class PlayerAiOne implements GenericPlayer, PlayerAi {
//
//  private final ReadOnlyReversiModel model;
//  private final GenericPlayer player;
//
//  /**
//   * The constructor for player ai one.
//   * @param model is the given read only model of the board.
//   * @param color is the color of this players tiles.
//   */
//  public PlayerAiOne(ReadOnlyReversiModel model, TileType color) {
//    this.model = model;
//    this.player = new Player(color, "ai game");
//  }
//
//  @Override
//  public Optional<IHex> findBestMove() throws IOException {
//    HashMap<IHex, Integer> moves = this.model.legalMoveAvailableList((Player) this.player);
//    int max = -1;
//    IHex chosen = null;
//    for (IHex h : moves.keySet()) {
//      if (moves.get(h) > max) {
//        max = moves.get(h);
//        chosen = h;
//      }
//    }
//    if (chosen == null) {
//      throw new IllegalStateException("no move available");
//    }
//    return Optional.of(chosen);
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
//  //model or read only model
//
//  //all valid/legal moves storred in dictionary
//  //if dic empty then pass
//
//}
