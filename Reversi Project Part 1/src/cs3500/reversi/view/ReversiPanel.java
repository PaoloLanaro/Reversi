package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.security.KeyPair;
import java.util.ArrayList;
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
  private final List<Hexagon> hexagonList;
  private final List<Cell> cellList;

  private static List<List<Cell>> underlyingBoard;


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
    this.addKeyListener(new KeyboardAdapter());
    this.setFocusable(true);
    this.hexagonList = new ArrayList<>();
    this.cellList = new ArrayList<>();
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
    hexagonList.add(middleHexagon);
    cellList.add(middleCell);
//    map.put(middleHexagon, middleCell);

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

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();

        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();

        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = new Hexagon(currentPoint, HEXAGON_RADIUS, convertColor(currentCell));

        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
//        map.put(currentHexagon, currentCell);

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
    int listCounter = 0;
    // set the current "onion layer" to the first layer
    int timesActionRepeated = 1;
    // get initial, MIDDLE, (x, y) --- BASED OFF OF THE CURRENT PANELS WIDTH AND HEIGHT
    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);

    // get "middle (onion layer 0) hexagon" and draws it
    Hexagon middleHexagon = hexagonList.get(listCounter);
    middleHexagon.updateHexagon(currentPoint);
    drawHexagon(g2d, middleCell, middleHexagon, middleHexagon.getCenter());

    listCounter++;

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

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();

        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();

        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();

        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();

        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;

        currentPoint = new Point2D.Double(newX, newY);

        Hexagon currentHexagon = hexagonList.get(listCounter);
        currentHexagon.updateHexagon(currentPoint);

        listCounter++;

        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      currentCell = currentCell.getRight();

      double newX = currentPoint.getX() + horiz;
      double newY = currentPoint.getY();

      currentPoint = new Point2D.Double(newX, newY);
      timesActionRepeated++;
    }


  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    Cell middleCell = underlyingBoard.get(model.getSideLength() - 1).get(model.getSideLength() - 1);

    if (!hexagonList.isEmpty()) {
      updateBoard(middleCell, g2d);
    } else {
      drawBoard(middleCell, g2d);
    }

    g2d.dispose();
  }

  private int getRowFromPoint(Point pt) {
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

  private int getColFromPoint(Point pt) {
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
          } else {
            return col;
          }
        }
      }
    }
    return null;
  }

  private Cell getCellFromPoint(Point point) {
    Cell clickedCell = null;

    for (int hexagon = 0; hexagon < hexagonList.size(); hexagon++) {
      if (hexagonList.get(hexagon).getHexagon().contains(point)) {
        clickedCell = cellList.get(hexagon);
      }
    }
    return clickedCell;
  }

  public void drawBlueTile(Point point, Color color) {
    Hexagon clickedHexagon;
    boolean cyanAlreadyExists = false;
    Hexagon cyanAlreadyHexagon = null;
    boolean hexagonClicked = false;

    for (Hexagon hexagon : hexagonList) {
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
      for (Hexagon hexagon : hexagonList) {
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

      int row = getRowFromPoint(point);
      int col = getColFromPoint(point);

      System.out.println(row + " " + col);

      drawBlueTile(point, Color.CYAN);
    }

  }

  private class KeyboardAdapter extends java.awt.event.KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          System.out.println("Make move");
          repaint();
          break;
        case KeyEvent.VK_BACK_SPACE:
          System.out.println("Pass turn");
        case KeyEvent.VK_U:
          System.out.println("Upper left");
          repaint();
          break;
        case KeyEvent.VK_I:
          System.out.println("Upper right");
          repaint();
          break;
        case KeyEvent.VK_H:
          System.out.println("Left");
          repaint();
          break;
        case KeyEvent.VK_K:
          System.out.println("Right");
          repaint();
          break;
        case KeyEvent.VK_N:
          System.out.println("Bottom left");
          repaint();
          break;
        case KeyEvent.VK_M:
          System.out.println("Bottom right");
          repaint();
          break;
      }
    }
  }

}
