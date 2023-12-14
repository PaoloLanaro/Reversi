package cs3500.reversi.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.RowCol;

/**
 * This class is a JPanel extension for representing {@link cs3500.reversi.model.SquareReversi}
 * games.
 * It will create a square grid, that looks like any other generic Reversi game.
 */
public class SquareReversiPanel extends JPanel {

  private final double CIRCLE_RATIO = 7 / 10.0;

  private int circleDiameter;
  private int circleOffset;

  private final ReadOnlyReversi model;
  private int squareSideLength;
  private final List<List<Rectangle>> squareList;

  protected ViewFeatures featureListener;

  Rectangle activeRectangle;

  /**
   * Constructs a {@link SquareReversiPanel} object.
   * The model should be assumed to be a Square-style game.
   *
   * @param model the {@link ReadOnlyReversi} to display.
   */
  public SquareReversiPanel(ReadOnlyReversi model) {
    this.model = model;
    this.setMinimumSize(new Dimension(800, 900));
    this.setBackground(Color.DARK_GRAY);
    this.addMouseListener(new MouseAdapter());
    this.addKeyListener(new KeyboardAdapter());
    this.setFocusable(true);
    this.squareList = new ArrayList<>();
  }

  public void addFeaturesListener(ViewFeatures featuresListener) {
    this.featureListener = featuresListener;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();

    int pixelShortSide = Math.min(getWidth(), getHeight());

    squareSideLength = pixelShortSide / model.getSideLength();

    circleDiameter = (int) (squareSideLength * CIRCLE_RATIO);
    circleOffset = (squareSideLength - circleDiameter) / 2;

    if (squareList.isEmpty()) {
      createBoard(g2d);
    } else {
      updateBoard(g2d);
    }

    g2d.dispose();
  }

  private void createBoard(Graphics2D g2d) {
    int boardSize = model.getSideLength();

    for (int row = 0; row < boardSize; row++) {
      squareList.add(new ArrayList<>());
      for (int col = 0; col < boardSize; col++) {
        int xPos = col * squareSideLength;
        int yPos = row * squareSideLength;

        Rectangle rectangle = new Rectangle(xPos, yPos, squareSideLength, squareSideLength);
        squareList.get(row).add(rectangle);
      }
    }
    update(g2d);
  }

  private void setHighlightedHex(int row, int col) {
    activeRectangle = squareList.get(row).get(col);
  }

  private RowCol getHighlightedHex() {
    int boardSize = model.getSideLength();
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (squareList.get(row).get(col) == activeRectangle) {
          return new RowCol(row, col);
        }
      }
    }
    return null;
  }

  private void updateBoard(Graphics2D g2d) {
    int boardSize = model.getSideLength();
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        int xPos = col * squareSideLength;
        int yPos = row * squareSideLength;

        Rectangle currRectangle = squareList.get(row).get(col);
        currRectangle.x = xPos;
        currRectangle.y = yPos;
        currRectangle.width = squareSideLength;
        currRectangle.height = squareSideLength;

        if (currRectangle == activeRectangle) {
          g2d.setColor(Color.CYAN);
          g2d.fill(currRectangle);
        } else {
          g2d.setColor(Color.LIGHT_GRAY);
          g2d.fill(currRectangle);
        }
        g2d.setColor(Color.BLACK);
        g2d.draw(currRectangle);

        if (chooseFillColor(row, col, g2d)) {
          g2d.fillOval(xPos + circleOffset, yPos + circleOffset, circleDiameter, circleDiameter);
        }

      }
    }
  }

  private Boolean chooseFillColor(int row, int col, Graphics2D g2d) {
    DiscColor color = model.getCellAt(row, col).getColor();
    if (color == DiscColor.BLACK) {
      g2d.setColor(Color.BLACK);
      return true;
    } else if (color == DiscColor.WHITE) {
      g2d.setColor(Color.WHITE);
      return true;
    }
    return false;
  }

  /**
   * An inner class for keeping track of mouse events on the panel.
   */
  public class MouseAdapter extends java.awt.event.MouseAdapter {
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
      int sideLength = model.getSideLength();
      for (int row = 0; row < sideLength; row++) {
        for (int col = 0; col < sideLength; col++) {

          if (squareList.get(row).get(col).contains(mouseEvent.getPoint())) {
            setHighlightedHex(row, col);
            repaint();
            System.out.println("(" + row + ", " + col + ")");
          }
        }
      }
    }
  }

  /**
   * An inner class meant to keep track of key events on the panel.
   */
  public class KeyboardAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
      switch (event.getKeyCode()) {
        case KeyEvent.VK_ENTER -> {
          RowCol coordinate = getHighlightedHex();
          if (coordinate == null) {
            featureListener.pushError("There is no currently highlighted cell in the game.");
          } else {
            featureListener.makeMove(getHighlightedHex());
          }
          repaint();
        }
        case KeyEvent.VK_BACK_SPACE -> featureListener.passTurn();
        default -> System.out.println("Not a valid key");
      }
    }
  }
}
