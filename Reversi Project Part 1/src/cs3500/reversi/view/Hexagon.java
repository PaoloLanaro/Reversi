package cs3500.reversi.view;

import java.awt.geom.Point2D;
import java.awt.Polygon;

public class Hexagon {
  private final int radius;

  private final Point2D center;

  private final Polygon hexagon;

  public Hexagon(Point2D center, int radius) {
    this.center = center;
    this.radius = radius;
    this.hexagon = createHexagon();
  }

  private Polygon createHexagon() {
    Polygon polygon = new Polygon();

    for (int i = 0; i < 6; i++) {
      int currentX = (int) (center.getX() + radius
              * Math.cos(i * 2 * Math.PI / 6D + Math.PI / 6D));
      int currentY = (int) (center.getY() + radius
              * Math.sin(i * 2 * Math.PI / 6D + Math.PI / 6D));
      polygon.addPoint(currentX, currentY);
    }

    return polygon;
  }

  public Point2D getCenter() {
    return center;
  }

  public Polygon getHexagon() {
    return hexagon;
  }
}
