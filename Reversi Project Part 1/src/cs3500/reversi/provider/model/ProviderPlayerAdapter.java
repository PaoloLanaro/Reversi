package cs3500.reversi.provider.model;

import cs3500.reversi.model.players.Player;

public class ProviderPlayerAdapter implements GenericPlayer {

  private Player player;

  public ProviderPlayerAdapter(Player player) {
    this.player = player;
  }

  @Override
  public TileType getTileColor() {
    return null;
  }

  @Override
  public TileType getOppositeColor() throws IllegalArgumentException {
    return null;
  }

  @Override
  public PlayStatus getPlayerStatus() {
    return null;
  }

  @Override
  public void updatePlayerStatus(PlayStatus ps) {

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
