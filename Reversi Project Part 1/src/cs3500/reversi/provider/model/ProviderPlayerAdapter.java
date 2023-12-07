package cs3500.reversi.provider.model;

import java.util.Objects;

import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.players.HumanPlayer;

import static cs3500.reversi.provider.model.ProviderModelAdapter.tileTypeToDiscColor;

public class ProviderPlayerAdapter extends HumanPlayer implements GenericPlayer {

  String playerColor;

  public ProviderPlayerAdapter(ProviderModelAdapter providerModel, DiscColor discColor) {
    super(providerModel, discColor);
    playerColor = discColor.toString();
  }

  @Override
  public TileType getTileColor() {
    return stringToTileType(playerColor);
  }

  private TileType stringToTileType(String color) {
    if (Objects.equals(color, "white")) {
      return TileType.WHITE;
    } else if (Objects.equals(color, "black")) {
      return TileType.BLACK;
    }
    throw new IllegalStateException("Player was neither black nor white");
  }

  @Override
  public TileType getOppositeColor() throws IllegalArgumentException {
    TileType playerType = stringToTileType(playerColor);
    if (playerType == TileType.BLACK) {
      return TileType.WHITE;
    } else {
      return TileType.BLACK;
    }
  }

  @Override
  public PlayStatus getPlayerStatus() {
    throw new UnsupportedOperationException("Method invocation failed");
  }

  @Override
  public void updatePlayerStatus(PlayStatus ps) {
    throw new UnsupportedOperationException("Method invocation failed");
  }

  @Override
  public int getScore() {
    return 0;
  }

  @Override
  public void updateScore(int score) {

  }

  @Override
  public String getPlayerName() {
    return null;
  }

  @Override
  public boolean isAI() {
    return false;
  }
}
