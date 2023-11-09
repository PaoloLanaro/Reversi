package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.ReadOnlyReversi;

public class ReversiMouseAdapter extends MouseAdapter {
  private final ReversiController controller;

  public ReversiMouseAdapter(ReversiController controller) {
    this.controller = controller;
  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
    Point point = mouseEvent.getPoint();

    int row = ReversiPanel.getRowFromPoint(point);
    int col = ReversiPanel.getColFromPoint(point);

    controller.handleCellClick(row, col);
  }
}
