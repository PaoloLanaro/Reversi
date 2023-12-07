package cs3500.reversi.model;

public class SquareReversiCell implements SquareCell {

  private final RowCol coordinate;
  private DiscColor color;

  public SquareReversiCell(RowCol coordinate) {
    this.coordinate = coordinate;
  }

  public SquareReversiCell(RowCol coordinate, DiscColor color) {
    this.coordinate = coordinate;
    this.color = color;
  }

  @Override
  public DiscColor getColor() {
    if (this.color == null) {
      throw new IllegalStateException("Color has not been set.");
    }
    return color;
  }

  @Override
  public void setColor(DiscColor color) {
    this.color = color;
  }

  @Override
  public RowCol getPosition() {
    return coordinate;
  }
}
