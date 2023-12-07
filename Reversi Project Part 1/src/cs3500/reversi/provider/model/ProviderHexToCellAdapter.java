package cs3500.reversi.provider.model;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.RowCol;

import static cs3500.reversi.provider.model.ProviderModelAdapter.tileTypeToDiscColor;

public class ProviderHexToCellAdapter implements IHex {

  TileType color;
  Cell cell;
  RowCol coord;

  public ProviderHexToCellAdapter(Cell cell, RowCol coord) {
    this.cell = cell;
    this.coord = coord;
  }

  @Override
  public void updateColor(TileType tileColor) {
    DiscColor color = tileTypeToDiscColor(tileColor);
    cell.setDiscColor(color);
  }

  @Override
  public String toStringQRS() {
    return "(" + getQ() + ", " + getR() + ")";
  }

  @Override
  public boolean sameColor(TileType color) {
    DiscColor discColor = tileTypeToDiscColor(color);
    return cell.getColor() == discColor;
  }

  @Override
  public boolean sameColor(IHex hex) {
    TileType color = hex.getTileColor();
    return cell.getColor() == tileTypeToDiscColor(color);
  }

  @Override
  public boolean same(int val, String whatToMatch) {
    throw new UnsupportedOperationException("Failed method invocation");
  }

  private boolean oppColor(DiscColor color) {
    return cell.getColor() == DiscColor.BLACK ? color == DiscColor.WHITE
            : cell.getColor() == DiscColor.WHITE && color == DiscColor.BLACK;
  }

  @Override
  public boolean oppositeColor(IHex hex) {
    return oppColor(tileTypeToDiscColor(hex.getTileColor()));
  }

  @Override
  public boolean oppositeColor(TileType hex) {
    return oppColor(tileTypeToDiscColor(hex));
  }

  @Override
  public int getQ() {
    return coord.getRow();
  }

  @Override
  public int getR() {
    return coord.getCol();
  }

  @Override
  public int getS() {
    throw new UnsupportedOperationException("Failed method invocation");
  }

  @Override
  public void switchColor(TileType tile) {
    this.updateColor(tile);
  }

  @Override
  public TileType getTileColor() {
    return cell.getColor() == DiscColor.WHITE ? TileType.WHITE
            : cell.getColor() == DiscColor.BLACK ? TileType.BLACK
            : TileType.NONE;
  }

  @Override
  public boolean colorNone() {
    return cell.getColor() == DiscColor.EMPTY;
  }
}
