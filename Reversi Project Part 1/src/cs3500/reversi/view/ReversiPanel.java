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
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.*;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;

public class ReversiPanel extends JPanel {

  private final ReadOnlyReversi model;
  private final int hexagonRadius = 27;
  private final int hexagonWidth = (int) (Math.sqrt(3) * hexagonRadius); // Width based on pointy-top orientation
  private final int hexagonHeight = 2 * hexagonRadius; // Height based on pointy-top orientation
  private final int hexagonVerticalSpacing = 1;

  public ReversiPanel(ReadOnlyReversi model) {
    this.model = model;
    int sideLength = model.getSideLength();
    int panelWidth = sideLength * hexagonWidth + hexagonWidth / 2; // Adjusted panel width
    int panelHeight = sideLength * (hexagonHeight + hexagonVerticalSpacing);
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
    this.setBackground(Color.DARK_GRAY);
  }

  // draw middle cell before running this
  private void helper(Cell middleCell, Graphics2D g2d) {
    int timesActionRepeated = 1;
    Cell currentCell = middleCell.getRight();
    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);

    double vert = hexagonRadius * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * hexagonRadius;

    while (timesActionRepeated < model.getSideLength()) {
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();

        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();

        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        drawHexagon(g2d, currentCell, currentPoint);
      }
      currentCell = currentCell.getRight();

      double newX = currentPoint.getX() + horiz;
      double newY = currentPoint.getY();

      currentPoint = new Point2D.Double(newX, newY);
      timesActionRepeated++;
    }
  }

  private void drawHexagon(Graphics2D g2d, Cell cell, Point2D point) {
    double vert = hexagonRadius * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * hexagonRadius;

    // point is center point, below code should be to actually draw the hexagon
    double leftStart = point.getX() - horiz / 2;
  }

  private void drawBoard(Graphics2D g2d) {
    int sideLength = model.getSideLength();     // 3
    int centerY = getHeight() / 2;              // Center Y
    int centerX = getWidth() / 2;               // Center X
    System.out.println(getHeight());

    // middlerow = sideLength * 2 - 1 == 5
    //  cellDistanceFromMiddle = go out by middlerow / 2
    //          radius * 2 = 1 hexagon total diameter
    // pixeldistanceFomrMiddle = (diameter - 1) * cellDistFroMMiddle + radius

    double horiz = Math.sqrt(3) * (double) hexagonRadius;
    double vert = ((double) 3 / (double) 2) * (double) hexagonRadius;

    Point2D center = new Point2D.Double(centerX, centerY);
    Point2D rightOne = new Point2D.Double(centerX + horiz,
            centerY);
    Point2D leftOne = new Point2D.Double(centerX - horiz,
            centerY);
    Point2D upperRight = new Point2D.Double(centerX + (horiz / 2),
            centerY + vert);
    Point2D upperLeft = new Point2D.Double(centerX - (horiz / 2),
            centerY + vert);
    Point2D bottomRight = new Point2D.Double(centerX + (horiz / 2),
            centerY - vert);
    Point2D bottomLeft = new Point2D.Double(centerX - (horiz / 2),
            centerY - vert);


    drawPointyHexagon(g2d, center, hexagonRadius);
    drawPointyHexagon(g2d, rightOne, hexagonRadius);
    drawPointyHexagon(g2d, leftOne, hexagonRadius);
    drawPointyHexagon(g2d, upperRight, hexagonRadius);
    drawPointyHexagon(g2d, upperLeft, hexagonRadius);
    drawPointyHexagon(g2d, bottomLeft, hexagonRadius);
    drawPointyHexagon(g2d, bottomRight, hexagonRadius);

//    drawHexagon(g2d, centerX, centerY, model.getBoard().get(sideLength - 1).get(sideLength - 1));
//    drawHexagon(g2d, centerX + hexagonRadius * 2, centerY,
//            model.getBoard().get(sideLength).get(sideLength - 1));
//    drawHexagon(g2d, centerX + hexagonRadius, centerY - hexagonRadius * 3 / 2,
//            model.getBoard().get(sideLength - 1).get(sideLength - 1));

    for (int row = 0; row < sideLength * 2 - 1; row++) {

      for (int col = 0; col < sideLength * 2 - 1; col++) {

      }

    }


    //
//    for (int row = 0; row < (sideLength); row++) { // < 3 == 0, 1, 2
//      int rowHexagonCount = sideLength + row;    // == 3, 4, 5
//      int startX = (getWidth() - rowHexagonCount * hexagonWidth) / 2 + hexagonWidth / 2; // Adjusted startX
//      System.out.println(getWidth());
//
//      for (int col = 0; col < rowHexagonCount; col++) {
//        Cell cell = model.getBoard().get(row).get(col);
//        if (cell != null) {
//          int x = startX + col * hexagonWidth;
//          int y = centerY - row * (hexagonHeight + hexagonVerticalSpacing);
//          drawHexagon(g2d, x, y, cell);
//        }
//      }
//    }
  }

  public void drawPointyHexagon(Graphics g, Point2D center, double size) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.WHITE);
    Path2D hexagon = new Path2D.Double();

    for (int i = 0; i < 6; i++) {
      double angle_deg = 60 * i - 30;
      double angle_rad = Math.toRadians(angle_deg);
      double x = Math.floor(center.getX()) + size * Math.cos(angle_rad);
      double y = Math.floor(center.getY()) + size * Math.sin(angle_rad);
      if (i == 0) {
        hexagon.moveTo(x, y);
      } else {
        hexagon.lineTo(x, y);
      }
    }

    hexagon.closePath();
    g2d.draw(hexagon);
  }

  private void drawHexagon(Graphics2D g2d, int centerX, int centerY, Cell cell) {
    int[] xPoints = new int[6];
    int[] yPoints = new int[6];

    for (int i = 0; i < 6; i++) {
      double angle_deg = 60 * i - 30;
      double angle_rad = Math.PI / 180 * angle_deg;
      xPoints[i] = (int) (centerX + hexagonRadius * Math.cos(angle_rad));
      yPoints[i] = (int) (centerY + hexagonRadius * Math.sin(angle_rad));
    }

    Color color = (cell.getColor() == DiscColor.BLACK) ? Color.BLACK :
            cell.getColor() == DiscColor.EMPTY ? Color.LIGHT_GRAY : Color.WHITE;
    g2d.setColor(color);
    g2d.fillPolygon(xPoints, yPoints, 6);
    g2d.setColor(Color.BLACK); // Outline color
    g2d.drawPolygon(xPoints, yPoints, 6);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    drawBoard(g2d);
  }
}
