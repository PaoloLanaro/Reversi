package cs3500.reversi.view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Polygon;
import java.util.Arrays;

public class Hexagon {
  private final int radius;

  private Point2D center;

  private Polygon hexagon;
  private Color color;

  public Hexagon(Point2D center, int radius, Color color) {
    this.center = center;
    this.radius = radius;
    this.hexagon = createHexagon();
    this.color = color;
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

  @Override
  public int hashCode() {
    int xPointAverage = Arrays.stream(hexagon.xpoints).sum() / hexagon.npoints;
    int yPointAverage = Arrays.stream(hexagon.ypoints).sum() / hexagon.npoints;
    return (int) center.getY() * 3 * 23 + (int) center.getY() * 5 * 91 *
            hexagon.npoints * xPointAverage * yPointAverage;
  }

  public Point2D getCenter() {
    return center;
  }

  public Polygon getHexagon() {
    return hexagon;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public void updateHexagon(Point2D center) {
    this.center = center;
    this.hexagon = createHexagon();
  }
}
