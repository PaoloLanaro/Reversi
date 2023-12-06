package cs3500.reversi.provider.model;

public class ProviderHexToCellAdapter implements IHex {

  TileType color;

  public ProviderHexToCellAdapter() {

  }

  @Override
  public void updateColor(TileType tileColor) {

  }

  @Override
  public String toStringQRS() {
    return null;
  }

  @Override
  public boolean sameColor(TileType color) {
    return false;
  }

  @Override
  public boolean sameColor(IHex hex) {
    return false;
  }

  @Override
  public boolean same(int val, String whatToMatch) {
    return false;
  }

  @Override
  public boolean oppositeColor(IHex hex) {
    return false;
  }

  @Override
  public boolean oppositeColor(TileType hex) {
    return false;
  }

  @Override
  public int getQ() {
    return 0;
  }

  @Override
  public int getS() {
    return 0;
  }

  @Override
  public int getR() {
    return 0;
  }

  @Override
  public void switchColor(TileType tile) {

  }

  @Override
  public TileType getTileColor() {
    return null;
  }

  @Override
  public boolean colorNone() {
    return false;
  }
}
