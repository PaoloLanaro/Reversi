package cs3500.reversi.provider.view;


import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Font;
import java.awt.Point;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;


import cs3500.reversi.provider.model.GenericPlayer;
//import cs3500.reversi.provider.model.IHex;
import cs3500.reversi.provider.model.IHex;
import cs3500.reversi.provider.model.PlayStatus;
import cs3500.reversi.provider.model.PlayerAi;
import cs3500.reversi.provider.model.Position2D;
import cs3500.reversi.provider.model.ReadOnlyReversiModel;
import cs3500.reversi.provider.model.TileType;

/**
 * A JSimonPanel will draw all the colors, allow users to click on them,
 * and play the game.
 */
public class ReversiPanel extends JPanel {
  private final ReadOnlyReversiModel model;
  private HashMap<IHex, Polygon> polygonGrid;
  private static int HEXSIZE = 15;
  private Polygon activeHex;

  private IHex activeHexagon;

  private Polygon prevHex;

  private final List<ViewFeatures> featuresListeners;

  private final GenericPlayer playerName;

  private JLabel scoreText;


  /**
   * The constructor for reversi panel.
   * @param model the given read only model.
   */
  public ReversiPanel(ReadOnlyReversiModel model, GenericPlayer player) {
    this.model = Objects.requireNonNull(model);
    MouseEventsListener listener = new MouseEventsListener();
    KeyboardListener keys = new KeyboardListener();
    this.addKeyListener(keys);
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
    this.polygonGrid = new HashMap<>();
    this.activeHex = null;
    this.activeHexagon = null;
    this.prevHex = null;
    this.featuresListeners = new ArrayList<>();
    this.playerName = player;
    this.scoreText = new JLabel();
  }

  /**
   * Conceptually, we can choose a different coordinate system
   * and pretend that our panel is 40x40 "cells" big. You can choose
   * any dimension you want here, including the same as your physical
   * size (in which case each logical pixel will be the same size as a physical
   * pixel, but perhaps your calculations to position things might be trickier)
   * @return Our preferred *logical* size.
   */
  private Position2D getNextPoint(Position2D center, int size, int i) {
    double angle = (60 * i) - 30;
    double x = size * Math.cos(Math.toRadians(angle));
    double y = size * Math.sin(Math.toRadians(angle));
    return new Position2D(center.getX() +  x,
    (center.getY() + y));
  }

  /**
   * Creates a hexagon shape that is a polygon.
   * @param x1 the x cord center point.
   * @param y1 the y cord center point.
   * @return a polygon.
   */
  public static Polygon polyHex(int x1, int y1) {
    int h = HEXSIZE * 3;
    int r = h / 2;
    int s = (int) (h / 1.73205);
    int t = (int) (r / 1.73205);
    int y = y1 + HEXSIZE;
    int x = x1 + HEXSIZE;

    if (s == 0  || h == 0) {
      System.out.println("ERROR: size of hex has not been set");
      return new Polygon();
    }

    int[] cx;
    int[] cy;
    cx = new int[] {x, x + t, x + t + t, x + t + t, x + t, x};
    int b = (t - t / 4);
    cy = new int[] {y + t / 4 ,y + b, y + t / 4, y - t - t / 4, y - t - b, y - t - t / 4};

    return new Polygon(cx,cy,6);
  }

  /**
   * Draws a hexagonal shape.
   * @param g2d the graphics.
   * @param tile if there is a tile on this hex.
   * @param circleColor the color of the tile.
   * @param start the start position.
   * @param size the size of the hex.
   */
  private void drawHex(Graphics2D g2d,
                       boolean tile, Color circleColor, Position2D start , int size) {
    AffineTransform oldTransform = g2d.getTransform();
    g2d.translate(start.getX(), start.getY());
    Position2D first = getNextPoint(start,size, 1);
    g2d.setColor(Color.BLACK);

    for (int i = 2 ; i <= 7; i++) {
      Position2D next = getNextPoint(start,size, i);
      g2d.drawLine((int)first.getX(), (int)first.getY(), (int)next.getX(), (int)next.getY() );

      first =  getNextPoint(start,size, i);
    }
    g2d.setColor(Color.CYAN);
    g2d.setColor(Color.BLACK);
    if (tile) {
      Shape circle = new Ellipse2D.Double(
              start.getX() - size / 2.0,
              start.getY() - size / 2.0,
              2 * size / 2.0,
              2 * size / 2.0);
      g2d.draw(circle);
      g2d.setColor(circleColor);
      g2d.fill(circle);
    }
    g2d.setTransform(oldTransform);
  }

  /**
   * Draws a horizontal row of hexes.
   * @param g2d the graphics.
   * @param firstPoint the first point to start at.
   * @param size the size of the row.
   * @param index the index of the row.
   */
  private void drawHorzontalRow(Graphics2D g2d, Position2D firstPoint, int size, int index) {
    List<IHex> gridRow = this.model.getGridCopy().get(index);
    AffineTransform oldTransform = g2d.getTransform();
    Position2D temp = new Position2D(firstPoint.getX(), firstPoint.getY());
    for (int i = 0; i < gridRow.size(); i++) {
      IHex h = gridRow.get(i);
      double horz = Math.sqrt(3) * size;
      Color c = Color.CYAN;
      if (h.getTileColor() == TileType.BLACK) {
        c = Color.BLACK;
      }
      if (h.getTileColor() == TileType.WHITE) {
        c = Color.PINK;
      }
      this.drawHex(g2d,!h.colorNone(), c, temp, 15);
      Polygon hex = polyHex((int)((temp.getX() - HEXSIZE) * 2 + HEXSIZE / 4),
              (int)((temp.getY() * 2) - HEXSIZE / 2 - HEXSIZE / 4));
      polygonGrid.put(h, hex);
      temp = new Position2D(temp.getX() + horz / 2, temp.getY());
      if (activeHex == null && prevHex != null) {
        prevHex.reset();
      }
      else if (polyEquals(prevHex, activeHex)) {
        g2d.setColor(Color.lightGray);
        prevHex.reset();
      }
      else if (polyEquals(activeHex, hex)) {
//        System.out.println("IHex QRS Coordinates: " + h.toStringQRS());
        g2d.setColor(Color.CYAN);
        g2d.fillPolygon(hex);
      }
      g2d.setTransform(oldTransform);
    }

  }

  /**
   * Draws the entire grid of hexagonal shapes.
   * @param g2d is the graphics.
   * @param firstPoint is the first point.
   * @param size the size.
   */
  private void drawGrid(Graphics2D g2d, Position2D firstPoint, int size) {
    AffineTransform oldTransform = g2d.getTransform();
    int maxRow = this.model.getMaxRowLength();
    int topHalf = (maxRow - 1) / 2;
    Position2D temp = new Position2D(firstPoint.getX(), firstPoint.getY());
    double vert;
    double horz = Math.sqrt(3) * size;
    for (int i = 0; i < topHalf + 1; i++) {
      this.drawHorzontalRow(g2d, temp,size, i);
      vert = (3 * size) / 2.0;
      temp = new Position2D(temp.getX() - (horz / 4) , temp.getY() - vert / 2);
    }
    temp = new Position2D(temp.getX() + (horz / 2) , temp.getY());
    for (int i = topHalf + 1; i < maxRow; i++) {
      this.drawHorzontalRow(g2d, temp,size, i);
      vert = (3 * size) / 2.0;
      temp = new Position2D(temp.getX() + (horz / 4) , temp.getY() - vert / 2);
    }
    g2d.setTransform(oldTransform);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.transform(transformLogicalToPhysical());
    int modifyBasedOffRow = (model.getMaxRowLength() - 1) / 2;
    double calcWidth = getWidth() / 2 - ( (HEXSIZE * modifyBasedOffRow));
    double calcHeight = getHeight() / 2 + ( (HEXSIZE * modifyBasedOffRow) + HEXSIZE * 1.5);
    drawGrid(g2d, new Position2D(calcWidth / 2, calcHeight / 2),15);
    AffineTransform oldTransform = g2d.getTransform();
    g2d.setTransform(oldTransform);
//    this.aiMakesMove();
//    this.setPanelBackground();
    this.repaint();
  }

  /**
   * Ensures that it is the ai's turn. Then allows for it
   * to move using its strategy. Otherwise, it will pass.
   */
//  private void aiMakesMove() {
//    if (playerName.isAI() && model.getCurrentPlayer().getTileColor() == playerName.getTileColor()) {
//      for (ViewFeatures feat : featuresListeners) {
//        try {
//          PlayerAi temp = (PlayerAi)playerName;
//          Optional<IHex> h = temp.findBestMove();
//          if (h.isEmpty()) {
//            feat.playerMakesMove(null, true);
//          } else {
//            feat.playerMakesMove(h.get(), false);
//          }
//        }
//        catch (IllegalStateException | IOException is) {
//          feat.playerMakesMove(null, true);
//        }
//      }
//    }
//  }

  /**
   * Sets the background of the panel to a corresponding
   * color once the genericPlayer makes a move.
   */
//  private void setPanelBackground() {
//    if (this.playerName.getPlayerStatus() == PlayStatus.PASS) {
//      this.setBackground(Color.green);
//      scoreText.setText("Score: "  + Integer.toString(playerName.getScore()));
//      scoreText.setFont(new Font("Verdana",1,13));
//      this.add(scoreText);
//
//    }
//    else if (this.playerName.getPlayerStatus() == PlayStatus.FORCEDPASS) {
//      this.setBackground(Color.blue);
//      scoreText.setText("Score: "  + Integer.toString(playerName.getScore()));
//      scoreText.setFont(new Font("Verdana",1,13));
//      this.add(scoreText);
//
//    }
//    else if (this.playerName.getPlayerStatus() == PlayStatus.MOVE) {
//      this.setBackground(Color.MAGENTA);
//      scoreText.setText("Score: "  + Integer.toString(playerName.getScore()));
//      scoreText.setFont(new Font("Verdana",1,13));
//      this.add(scoreText);
//
//    }
//    else {
//      this.setBackground(Color.WHITE);
//    }
//  }


  /**
   * The transformations for the mouse movements.
   * Computes the transformation that converts board coordinates
   * (with (0,0) in center, width and height our logical size)
   * into screen coordinates (with (0,0) in upper-left,
   * width and height in pixels).
   * This is the inverse of {@link ReversiPanel#transformPhysicalToLogical()}.
   * @return The necessary transformation
   */
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform ret = new AffineTransform();

    ret.scale(1, -1);
    ret.translate(0,-getHeight());
    return ret;
  }

  /**
   * The transformations for the panel.
   * Computes the transformation that converts screen coordinates
   * (with (0,0) in upper-left, width and height in pixels)
   * into board coordinates (with (0,0) in center, width and height
   * our logical size).
   * This is the inverse of {@link ReversiPanel#transformLogicalToPhysical()}.
   * @return The necessary transformation pointer.
   */
  private AffineTransform transformPhysicalToLogical() {
    AffineTransform ret = new AffineTransform();
    ret.scale(1, -1);
    ret.translate(0,-getHeight());
    return ret;
  }

  /**
   * Repaints the panel.
   */
  public void advance() {
    this.repaint();
  }

  /**
   * Makes a pop up when there is an
   * error in the game.
   * @param msg is the error message to display.
   */
  public void error(String msg) {
    JOptionPane.showMessageDialog(null, msg,
            "Message", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * A screen pops up when the game is over that
   * displays which player has won/ or if there
   * is a tie.
   */
//  public void endGamePopUp() {
//    GenericPlayer winner = this.model.determineWinner();
//    String msg = "";
//    if (winner == null) {
//      msg = "game over: you tied";
//    }
//    else {
//      msg = winner.getPlayerName() + " WON!\n"
//              + "Winning Score: " + winner.getScore();
//    }
//    JOptionPane.showMessageDialog(null, msg, playerName.getPlayerName(),
//            JOptionPane.INFORMATION_MESSAGE);
//  }

  /**
   * The mouse event listener handles mouse moves.
   */
  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //event that should occur if a mouse release should impact the board
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      Point physicalP = e.getPoint();
      Point2D logicalP = transformPhysicalToLogical().transform(physicalP, null);
      boolean pressedInBounds = false;
      for (IHex p : polygonGrid.keySet()) {
        Polygon h = polygonGrid.get(p);
        if (p.getQ() == 1 && p.getR() == 2) {
//          System.out.println("here");
        }
//        if (h.contains(logicalP)) {
//          activeHex = h;
//          activeHexagon = p;
//          pressedInBounds = true;
//        }
        int xMod = h.xpoints[0] + HEXSIZE ;
        int yMod = h.ypoints[0] - HEXSIZE / 2;

        if (((logicalP.getX() <= xMod + HEXSIZE )
                &&
                (logicalP.getX() >= xMod - HEXSIZE))
                && ((logicalP.getY() <= yMod + HEXSIZE )
                &&
                (logicalP.getY() >= yMod - HEXSIZE))) {
          if (activeHex != null) {
            prevHex = polyHex(activeHex.xpoints[0] - HEXSIZE,
                    activeHex.ypoints[0] - HEXSIZE - 3);
          }
          pressedInBounds = true;
          activeHex = h;
          activeHexagon = p;
        }
      }
      System.out.println(activeHexagon.getQ() + "," + activeHexagon.getR()); // todo
      if (!pressedInBounds) {
        activeHex = null;
        activeHexagon = null;
      }
      ReversiPanel.this.repaint();
    }
  }

  /**
   * Used only for testing. Checks if the hex clicked matches
   * the qrs of the hex.
   * @param clicked is the point clicked.
   * @return a string of the clicked hex's qrs.
   */
  public String convertMouseToHexQRS(Point clicked) {
    Point2D logicalP = transformPhysicalToLogical().transform(clicked, null);
    boolean pressedInBounds = false;
    for (IHex p : polygonGrid.keySet()) {
      Polygon h = polygonGrid.get(p);
      int xMod = h.xpoints[0] + HEXSIZE ;
      int yMod = h.ypoints[0] - HEXSIZE / 2;

      if (((logicalP.getX() <= xMod + HEXSIZE )
              &&
              (logicalP.getX() >= xMod - HEXSIZE))
              && ((logicalP.getY() <= yMod + HEXSIZE )
              &&
              (logicalP.getY() >= yMod - HEXSIZE))) {
        if (activeHex != null) {
          prevHex = polyHex(activeHex.xpoints[0] - HEXSIZE,
                  activeHex.ypoints[0] - HEXSIZE - 3);
        }
        pressedInBounds = true;
        activeHex = h;
        activeHexagon = p;
      }
    }
    if (!pressedInBounds) {
      activeHex = null;
      activeHexagon = null;
    }
    if (activeHexagon == null) {
      return "";
    }
    return activeHexagon.toStringQRS();
  }


  /**
   * Checks if two polygons are the same. They are the
   * same if they have the same x,y position.
   * @param gon a polygon.
   * @param other another polygon.
   * @return true if they are the same.
   */
  private boolean polyEquals(Polygon gon, Polygon other) {
    if (other == null || gon == null) {
      return false;
    }
    return (gon.xpoints[0] == other.xpoints[0]
            &&
    gon.ypoints[0] == other.ypoints[0]);
  }

  /**
   * Take in the given feature and adds it to
   * a list of the current view features.
   * @param features is the given view feature.
   */
  public void addFeaturesListener(ViewFeatures features) {
    this.featuresListeners.add(Objects.requireNonNull(features));
  }

  /**
   * Gets the current players name.
   * @return a string of the player name.
   */
  public String getPlayerName() {
    return this.playerName.getPlayerName();
  }

  /**
   * Handles keyboard presses.
   */
  private class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
      //method to be carried out if a specific types k causes  change in the board
    }

    @Override
    public void keyPressed(KeyEvent e) {
//      System.out.println(featuresListeners);
      if (model.gameOver()) {
//        endGamePopUp();
        for (ViewFeatures feat : featuresListeners) {
          feat.quit();
        }
      }
//      if (!playerName.isAI()) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
          for (ViewFeatures feat : featuresListeners) {
            feat.playerMakesMove(null, true);
          }
          System.out.println("You passed");
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
          for (ViewFeatures feat : featuresListeners) {
            feat.playerMakesMove(activeHexagon, false);
          }
          activeHex.reset();
          System.out.println("You made a move");
        }
//      }
      //String score = Integer.toString(playerName.getScore());

    }

    @Override
    public void keyReleased(KeyEvent e) {
      //method to be carried out if releasing a specific key should cause a reuslt

    }

  }
}
