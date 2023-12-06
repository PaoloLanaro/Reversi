package cs3500.reversi.provider.model;

public class ProviderPlayerAdapter implements GenericPlayer {
  public ProviderPlayerAdapter() {

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
