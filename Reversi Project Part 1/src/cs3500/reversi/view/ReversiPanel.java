package cs3500.reversi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JPanel;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.Cell;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * The JPanel which represents the actual Reversi board. This class
 * creates the full board and allows for mouse and keyboard input to indicate
 * "moves".
 */
public class ReversiPanel extends JPanel {

  private final ReadOnlyReversi model;
  private double hexagonRadius;
  private final List<Hexagon> hexagonList;
  private final List<Cell> cellList;
  private List<List<Cell>> underlyingBoard;

  protected ViewFeatures featureListener;

  /**
   * Constructs the {@link ReversiPanel}.
   *
   * @param model the {@link ReadOnlyReversi} model to represent in the JPanel
   */
  public ReversiPanel(ReadOnlyReversi model) {
    this.model = model;
    this.setMinimumSize(new Dimension(800, 800));
    this.setBackground(Color.DARK_GRAY);
    underlyingBoard = model.getBoard();
    this.addMouseListener(new MouseAdapter());
    this.addKeyListener(new KeyboardAdapter());
    this.setFocusable(true);
    this.hexagonList = new ArrayList<>();
    this.cellList = new ArrayList<>();
    //basic 27
    this.hexagonRadius = 27;
  }

  // draws full board (onion wrapper)
  private void drawBoard(Cell middleCell, Graphics2D g2d) {
    // set the current "onion layer" to the first layer
    int timesActionRepeated = 1;
    // get initial, MIDDLE, (x, y) --- BASED OFF OF THE CURRENT PANELS WIDTH AND HEIGHT
    Point2D currentPoint = new Point2D.Double((double) getWidth() / 2, (double) getHeight() / 2);

    // creates "middle (onion layer 0) hexagon" and draws it
    Hexagon middleHexagon = new Hexagon(currentPoint, hexagonRadius, Color.LIGHT_GRAY);
    drawHexagon(g2d, middleCell, middleHexagon, middleHexagon.getCenter());
    hexagonList.add(middleHexagon);
    cellList.add(middleCell);
    //    map.put(middleHexagon, middleCell);

    // update the currentCell to the first new "onion" layer
    Cell currentCell = middleCell.getRight();

    // sets math for offsets from one cell to another
    double vert = hexagonRadius * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * hexagonRadius;

    double initLayerX = currentPoint.getX() + horiz;
    double initLayerY = currentPoint.getY();

    currentPoint = new Point2D.Double(initLayerX, initLayerY);

    theOnionLoop(g2d, timesActionRepeated, currentCell, currentPoint, horiz, vert);
  }

  private void theOnionLoop(Graphics2D g2d, int timesActionRepeated, Cell currentCell,
                            Point2D currentPoint, double horiz, double vert) {
    while (timesActionRepeated < model.getSideLength()) {
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomLeft();
        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() + vert;
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getLeft();
        double newX = currentPoint.getX() - horiz;
        double newY = currentPoint.getY();
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperLeft();
        double newX = currentPoint.getX() - horiz / 2;
        double newY = currentPoint.getY() - vert;
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getUpperRight();
        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() - vert;
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getRight();
        double newX = currentPoint.getX() + horiz;
        double newY = currentPoint.getY();
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
        drawHexagon(g2d, currentCell, currentHexagon, currentHexagon.getCenter());
      }
      for (int i = 0; i < timesActionRepeated; i++) {
        currentCell = currentCell.getBottomRight();
        double newX = currentPoint.getX() + horiz / 2;
        double newY = currentPoint.getY() + vert;
        currentPoint = new Point2D.Double(newX, newY);
        Hexagon currentHexagon = new Hexagon(currentPoint, hexagonRadius,
                convertColor(currentCell));
        hexagonList.add(currentHexagon);
        cellList.add(currentCell);
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
      g2d.setColor(Color.CYAN);
      g2d.fill(hexagon.getHexagon());
    }
    if (cell.getColor() != DiscColor.EMPTY) {
      Color color = cell.getColor() == DiscColor.WHITE ? Color.WHITE :
              cell.getColor() == DiscColor.BLACK ? Color.BLACK : Color.LIGHT_GRAY;
      g2d.setColor(color);
      int circleX = (int) centerPoint.getX() - (int) Math.ceil(hexagonRadius / (double) 2);
      int circleY = (int) centerPoint.getY() - (int) Math.ceil(hexagonRadius / (double) 2);

      Shape circle = new Ellipse2D.Double(circleX, circleY, hexagonRadius, hexagonRadius);

      g2d.fill(circle);
    }

    g2d.setColor(Color.BLACK);
    g2d.draw(hexagon.getHexagon());
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
    double vert = hexagonRadius * (double) 3 / (double) 2;
    double horiz = Math.sqrt(3) * hexagonRadius;

    double initLayerX = currentPoint.getX() + horiz;
    double initLayerY = currentPoint.getY();

    currentPoint = new Point2D.Double(initLayerX, initLayerY);

    updateOnionLayers(g2d, timesActionRepeated, currentCell,
            currentPoint, horiz, vert, listCounter);

  }

  private void updateOnionLayers(Graphics2D g2d, int timesActionRepeated, Cell currentCell,
                                 Point2D currentPoint, double horiz, double vert, int listCounter) {
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

    int pixelShortSide = Math.min(getWidth(), getHeight());

    int hexagonAllowedSpace = pixelShortSide / underlyingBoard.size();

    hexagonRadius = hexagonAllowedSpace / Math.sqrt(3);

    for (Hexagon hexagon : hexagonList) {
      hexagon.setRadius(hexagonRadius);
    }

    underlyingBoard = model.getBoard();
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
    if (row != null) {
      return row;
    }
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
    if (col != null) {
      return col;
    }
    try {
      return model.getColFromCell(clickedCell);
    } catch (IllegalArgumentException e) {
      // cant throw an exception apparently
      return -1;
    }
  }

  private Integer getCellInt(Cell clickedCell, boolean isRow) {
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
      if (clickedCell != null) {
        break;
      }
      if (hexagonList.get(hexagon).getHexagon().contains(point)) {
        clickedCell = cellList.get(hexagon);
      }
    }
    return clickedCell;
  }

  private void drawBlueTile(Point point, Color color) {
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

  protected RowCol getHighlightedHex() {
    for (int hex = 0; hex < hexagonList.size(); hex++) {
      Hexagon hexagon = hexagonList.get(hex);
      if (hexagon.getColor() == Color.CYAN) {
        Point2D center = hexagon.getCenter();
        int row = getRowFromPoint(new Point((int) center.getX(), (int) center.getY()));
        int col = getColFromPoint(new Point((int) center.getX(), (int) center.getY()));
        return new RowCol(row, col);
      }
    }
    return null;
  }

  protected void addFeaturesListener(ViewFeatures featureListener) {
    this.featureListener = featureListener;
  }

  private class MouseAdapter extends java.awt.event.MouseAdapter {

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
      Point point = mouseEvent.getPoint();

      int row = getRowFromPoint(point);
      int col = getColFromPoint(point);

      if (row != -1 && col != -1) {
        boolean alreadyFilled =
                underlyingBoard.get(row).get(col).getColor() == DiscColor.BLACK ||
                        (underlyingBoard.get(row).get(col).getColor() == DiscColor.WHITE);

        if (alreadyFilled) {
          System.out.println("Board already filled at: (" + row + ", " + col + ")");
        } else {
          System.out.println("(" + row + ", " + col + ")");
        }
      } else {
        System.out.println("Not a valid cell");
      }

      drawBlueTile(point, Color.CYAN);
    }

  }

  private class KeyboardAdapter extends java.awt.event.KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
        case KeyEvent.VK_ENTER:
          RowCol highlightedHex = getHighlightedHex();
          if (highlightedHex == null) {
            featureListener.pushError("There is no currently highlighted cell in the game.");
          } else {
            featureListener.makeMove(getHighlightedHex());
          }
          repaint();
          break;
        case KeyEvent.VK_BACK_SPACE:
          featureListener.passTurn();
          break;
        default:
          System.out.println("Not a valid key");
          break;
      }
    }
  }
}