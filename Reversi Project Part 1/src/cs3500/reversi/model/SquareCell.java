package cs3500.reversi.model;

import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.RowCol;

public interface SquareCell {

  DiscColor getColor();

  void setColor(DiscColor color);

  RowCol getPosition();


}
