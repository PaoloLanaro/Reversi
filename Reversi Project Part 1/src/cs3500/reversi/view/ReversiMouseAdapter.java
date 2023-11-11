package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cs3500.reversi.controller.GameController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.ReadOnlyReversi;

public class ReversiMouseAdapter extends MouseAdapter {
  //  private final ReversiController controller;
//  private ReversiPanel reversiPanel;

  public ReversiMouseAdapter() {
//    this.controller = controller;
//    this.reversiPanel = reversiPanel;

  }

  @Override
  public void mouseClicked(MouseEvent mouseEvent) {
    Point point = mouseEvent.getPoint();

    int row = ReversiPanel.getRowFromPoint(point);
    int col = ReversiPanel.getColFromPoint(point);

    System.out.println(row + " " + col);

//    drawBlueTile(point, Color.CYAN);


    // handle changing hexagon to blue

//    controller.handleCellClick(row, col);
//    reversiPanel.drawBlueTile(point);
//    Hexagon clickedHexagon = reversiPanel.getHexagonAtPoint(point);
//    if (clickedHexagon != null && clickedHexagon.equals(reversiPanel.getSelectedBlueTile())) {
//      // Reset the selected blue tile
//      reversiPanel.resetSelectedBlueTile();
//    } else {
//      // Check if the clicked hexagon is already cyan
//      if (clickedHexagon != null && reversiPanel.isHexagonCyan(clickedHexagon)) {
//        // Reset the selected blue tile
//        reversiPanel.resetSelectedBlueTile();
//      } else {
//        // Handle changing hexagon to blue
//        reversiPanel.drawBlueTile(point);
//      }
//
//    }

  }


}
