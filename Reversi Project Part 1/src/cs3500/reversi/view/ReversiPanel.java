//package cs3500.reversi.view;
//
//import java.awt.*;
//
//import java.awt.geom.Point2D;
//import java.util.List;
//
//import javax.swing.*;
//
//import cs3500.reversi.model.Cell;
//import cs3500.reversi.model.ReadOnlyReversi;
//
//public class ReversiPanel extends JPanel {
//
//  private final ReadOnlyReversi model;
//
//  public ReversiPanel(ReadOnlyReversi model) {
//    this.model = model;
//    this.setPreferredSize(new Dimension(500, 500));
//    this.setBackground(Color.DARK_GRAY);
//  }
//
//  private void drawBoard(Graphics2D g2d) {
//    List<List<Cell>> gameBoard = model.getBoard();
//    drawHexagon(g2d);
//  }
//
//  private void drawHexagon(Graphics2D g2d) {
//
//  }
//
//  @Override
//  protected void paintComponent(Graphics g) {
//    super.paintComponent(g);
//
//    Graphics2D g2d = (Graphics2D) g.create();
//
//    drawBoard(g2d);
//  }
//
//
//}


package cs3500.reversi.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.*;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;

public class ReversiPanel extends JPanel {

  private final ReadOnlyReversi model;
  private final int HEXAGON_RADIUS = 27;
  private final int hexagonWidth = (int) (Math.sqrt(3) * HEXAGON_RADIUS); // Width based on
  // pointy-top orientation
  private final int hexagonHeight = 2 * HEXAGON_RADIUS; // Height based on pointy-top orientation
  public ReversiPanel(ReadOnlyReversi model) {
    this.model = model;
    int sideLength = model.getSideLength();
    int panelWidth = sideLength * hexagonWidth + hexagonWidth / 2;
    int panelHeight = sideLength * (hexagonHeight + 1);
    this.setMinimumSize(new Dimension(panelWidth, panelHeight));
    this.setBackground(Color.DARK_GRAY);
  }

  // draws full board (onion wrapper)
  private void drawBoard(Cell middleCell, Graphics2D g2d) {
    // set the current "onion layer" to the first layer
    int timesActionRepeated = 1;
    // get initial, MIDDLE, (x, y) --- BASED OFF OF THE CURRENT PANELS WIDTH AND HEIGHT
    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);

    // creates "middle (onion layer 0) hexagon" and draws it
    Hexagon middleHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);
    drawHexagon(g2d, middleCell, middleHexagon.getHexagon(), middleHexagon.getCenter());

    // update the currentCell to the first new "onion" layer
    Cell currentCell = middleCell.getRight();

    // sets math for offsets from one cell to another
    double vert = HEXAGON_RADIUS * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * HEXAGON_RADIUS;

    double initLayerX = currentPoint.getX() + horiz;
    double initLayerY = currentPoint.getY();

    currentPoint = new Point2D.Double(initLayerX, initLayerY);

    while (timesActionRepeated < model.getSideLength()) {
      //
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();

        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();

        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS);

        drawHexagon(g2d, currentCell, currentHexagon.getHexagon(), currentHexagon.getCenter());
      }
      currentCell = currentCell.getRight();

      double newX = currentPoint.getX() + horiz;
      double newY = currentPoint.getY();

      currentPoint = new Point2D.Double(newX, newY);
      timesActionRepeated++;
    }
  }

  // draws individual hexagons, and fills them in with appropriate colored circle
  private void drawHexagon(Graphics2D g2d, Cell cell, Polygon hexagon, Point2D centerPoint) {

    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fill(hexagon);

    Color color = cell.getColor() == DiscColor.WHITE ? Color.WHITE :
            cell.getColor() == DiscColor.BLACK ? Color.BLACK : Color.LIGHT_GRAY;

    g2d.setColor(color);

    int circleX = (int) centerPoint.getX() - 13;
    int circleY = (int) centerPoint.getY() - 13;

    Shape circle = new Ellipse2D.Double(circleX, circleY, HEXAGON_RADIUS, HEXAGON_RADIUS);

    g2d.fill(circle);

    g2d.setColor(Color.BLACK);
    g2d.drawPolygon(hexagon);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    drawBoard(model.getCellAt(model.getSideLength() - 1, model.getSideLength() - 1), g2d);
  }
}
