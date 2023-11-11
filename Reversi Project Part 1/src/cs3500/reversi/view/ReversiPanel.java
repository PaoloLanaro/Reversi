package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

import javax.swing.*;

import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;

public class ReversiPanel extends JPanel {

  private static ReadOnlyReversi model;
  private final int HEXAGON_RADIUS = 27;
  private final int hexagonWidth = (int) (Math.sqrt(3) * HEXAGON_RADIUS); // Width based on
  // pointy-top orientation
  private final int hexagonHeight = 2 * HEXAGON_RADIUS; // Height based on pointy-top orientation

  private static Map<Hexagon, Cell> map;
  //m private final List<Hexagon> hexagonList;
  // private final List<Cell> cellList;

  private static List<List<Cell>> underlyingBoard;
  private Hexagon selectedBlueTile;

  private boolean isBlueTileSelected;


  public ReversiPanel(ReadOnlyReversi model) {
    this.model = model;
    int sideLength = model.getSideLength();
    int panelWidth = sideLength * hexagonWidth + hexagonWidth / 2;
    int panelHeight = sideLength * (hexagonHeight + 1);
    this.setMinimumSize(new Dimension(panelWidth, panelHeight));
    this.setBackground(Color.DARK_GRAY);
    map = new HashMap<>();
    underlyingBoard = model.getBoard();
    this.addMouseListener(new MouseAdapter());
  }

  // draws full board (onion wrapper)
  private void drawBoard(Cell middleCell, Graphics2D g2d) {
    // set the current "onion layer" to the first layer
    int timesActionRepeated = 1;
    // get initial, MIDDLE, (x, y) --- BASED OFF OF THE CURRENT PANELS WIDTH AND HEIGHT
    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);

    // creates "middle (onion layer 0) hexagon" and draws it
    Hexagon middleHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, Color.LIGHT_GRAY);
    drawHexagon(g2d, middleCell, middleHexagon, middleHexagon.getCenter());
    map.put(middleHexagon, middleCell);

    // update the currentCell to the first new "onion" layer
    Cell currentCell = middleCell.getRight();

    // sets math for offsets from one cell to another
    double vert = HEXAGON_RADIUS * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * HEXAGON_RADIUS;

    double initLayerX = currentPoint.getX() + horiz;
    double initLayerY = currentPoint.getY();

    currentPoint = new Point2D.Double(initLayerX, initLayerY);

    while (timesActionRepeated < model.getSideLength()) {

      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();

        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();

        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      currentCell = currentCell.getRight();

      double newX = currentPoint.getX() + horiz;
      double newY = currentPoint.getY();

      currentPoint = new Point2D.Double(newX, newY);
      timesActionRepeated++;
    }
  }

  private static Color convertColor(Cell cell) {
    if (cell == null) {
      return Color.BLACK;
    }
    return cell.getColor() == DiscColor.BLACK ? Color.BLACK : cell.getColor() == DiscColor.WHITE
            ? Color.WHITE : Color.LIGHT_GRAY;
  }

  // draws individual hexagons, and fills them in with appropriate colored circle
  private void drawHexagon(Graphics2D g2d, Cell cell, Hexagon hexagon, Point2D centerPoint) {

    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fill(hexagon.getHexagon());

    if (hexagon.getColor() == Color.CYAN) {
      if (cell.getColor() == DiscColor.EMPTY) {
        g2d.setColor(Color.CYAN);
        g2d.fill(hexagon.getHexagon());
      }
    }
    if (cell.getColor() != DiscColor.EMPTY) {
      Color color = cell.getColor() == DiscColor.WHITE ? Color.WHITE :
              cell.getColor() == DiscColor.BLACK ? Color.BLACK : Color.LIGHT_GRAY;
      g2d.setColor(color);
      int circleX = (int) centerPoint.getX() - (int) Math.ceil(HEXAGON_RADIUS / (double) 2);
      int circleY = (int) centerPoint.getY() - (int) Math.ceil(HEXAGON_RADIUS / (double) 2);

      Shape circle = new Ellipse2D.Double(circleX, circleY, HEXAGON_RADIUS, HEXAGON_RADIUS);

      g2d.fill(circle);
    }

    g2d.setColor(Color.BLACK);
    g2d.drawPolygon(hexagon.getHexagon());
  }

  private void updateBoard(Cell middleCell, Graphics2D g2d) {
    // updates existing board. you would still have to draw out the whole board in onion layer
    // with rotating center position or it won't scale with the window.

//    // set the current "onion layer" to the first layer
//    int timesActionRepeated = 1;
//    // get initial, MIDDLE, (x, y) --- BASED OFF OF THE CURRENT PANELS WIDTH AND HEIGHT
//    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);
//
//    // creates "middle (onion layer 0) hexagon" and draws it
//    Hexagon middleHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, Color.LIGHT_GRAY);
//    drawHexagon(g2d, middleCell, middleHexagon, middleHexagon.getCenter());
//    map.put(middleHexagon, middleCell);
//
//    // update the currentCell to the first new "onion" layer
//    Cell currentCell = middleCell.getRight();
//
//    // sets math for offsets from one cell to another
//    double vert = HEXAGON_RADIUS * (double) 3 / (double) 2;
//    double horiz = Math.sqrt(3) * HEXAGON_RADIUS;
//
//    double initLayerX = currentPoint.getX() + horiz;
//    double initLayerY = currentPoint.getY();
//
//    currentPoint = new Point2D.Double(initLayerX, initLayerY);
//
//    while (timesActionRepeated < model.getSideLength()) {
//
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getBottomLeft();
//
//        double newX = currentPoint.getX() - horiz / 2;
//        double newY = currentPoint.getY() + vert;
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getLeft();
//
//        double newX = currentPoint.getX() - horiz;
//        double newY = currentPoint.getY();
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getUpperLeft();
//
//        double newX = currentPoint.getX() - horiz / 2;
//        double newY = currentPoint.getY() - vert;
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getUpperRight();
//
//        double newX = currentPoint.getX() + horiz / 2;
//        double newY = currentPoint.getY() - vert;
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getRight();
//
//        double newX = currentPoint.getX() + horiz;
//        double newY = currentPoint.getY();
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      for (int i = 0; i < timesActionRepeated; i++) {
//        currentCell = currentCell.getBottomRight();
//
//        double newX = currentPoint.getX() + horiz / 2;
//        double newY = currentPoint.getY() + vert;
//
//        currentPoint = new Point2D.Double(newX, newY);
//
//        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));
//
//        map.put(currentHexagon, currentCell);
//
//        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
//      }
//      currentCell = currentCell.getRight();
//
//      double newX = currentPoint.getX() + horiz;
//      double newY = currentPoint.getY();
//
//      currentPoint = new Point2D.Double(newX, newY);
//      timesActionRepeated++;
//    }

    for (Hexagon hexagon : map.keySet()) {
      drawHexagon(g2d, map.get(hexagon), hexagon, hexagon.getCenter());
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    Cell middleCell = underlyingBoard.get(model.getSideLength() - 1).get(model.getSideLength() - 1);

//    if (!map.isEmpty()) {
//      updateBoard(middleCell, g2d);
//    } else {
      drawBoard(middleCell, g2d);
//    }

    g2d.dispose();
  }

  static int getRowFromPoint(Point pt) {
    Cell clickedCell = getCellFromPoint(pt);
    Integer row = getCellInt(clickedCell, true);
    if (row != null) return row;
    try {
      return model.getRowFromCell(clickedCell);
    } catch (IllegalArgumentException e) {
      // can't throw apparently
      return -1;
    }
  }

  static int getColFromPoint(Point pt) {
    Cell clickedCell = getCellFromPoint(pt);
    Integer col = getCellInt(clickedCell, false);
    if (col != null) return col;
    try {
      return model.getColFromCell(clickedCell);
    } catch (IllegalArgumentException e) {
      // cant throw an exception apparently
      return -1;
    }
  }

  private static Integer getCellInt(Cell clickedCell, boolean isRow) {
    for (int row = 0; row < model.getSideLength() * 2 - 1; row++) {
      for (int col = 0; col < model.getSideLength() * 2 - 1; col++) {
        if (underlyingBoard.get(row).get(col) == null) {
          continue;
        }
        if (underlyingBoard.get(row).get(col) == clickedCell) {
          if (isRow) {
            return row;
          } else  {
            return col;
          }
        }
      }
    }
    return null;
  }

  // Helper method to get the hexagon at a specific point
  public Hexagon getHexagonAtPoint(Point point) {
    for (Hexagon hexagon : map.keySet()) {
      if (hexagon.getHexagon().contains(point)) {
        return hexagon;
      }
    }
    return null;
  }

  private static Cell getCellFromPoint(Point point) {
    Cell clickedCell = null;
    for (Hexagon hexagon : map.keySet()) {
      if (hexagon.getHexagon().contains(point)) {
        clickedCell = map.get(hexagon);
      }
    }
    return clickedCell;
  }

  public void drawBlueTile(Point point, Color color) {
    Hexagon clickedHexagon;
    boolean cyanAlreadyExists = false;
    Hexagon cyanAlreadyHexagon = null;
    boolean hexagonClicked = false;

    for (Hexagon hexagon : map.keySet()) {
      if (hexagon.getColor() == Color.CYAN) {
        cyanAlreadyExists = true;
        cyanAlreadyHexagon = hexagon;
      }
      if (hexagon.getHexagon().contains(point)) {
        if (hexagon.getColor() == Color.CYAN) {
          clickedHexagon = hexagon;
          clickedHexagon.setColor(Color.LIGHT_GRAY);
        }
        clickedHexagon = hexagon;
        clickedHexagon.setColor(color);
        hexagonClicked = true;
      }
    }
    if (!hexagonClicked) {
      for (Hexagon hexagon : map.keySet()) {
        if (hexagon.getColor() == Color.CYAN) {
          hexagon.setColor(Color.LIGHT_GRAY);
        }
      }
    }
    if (cyanAlreadyExists) {
      cyanAlreadyHexagon.setColor(Color.LIGHT_GRAY);
    }
    repaint();
  }

  private class MouseAdapter extends java.awt.event.MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
      Point point = mouseEvent.getPoint();

      int row = ReversiPanel.getRowFromPoint(point);
      int col = ReversiPanel.getColFromPoint(point);

      System.out.println(row + " " + col);

      drawBlueTile(point, Color.CYAN);
    }

  }

}
