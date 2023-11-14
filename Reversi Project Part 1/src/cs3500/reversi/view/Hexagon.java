package cs3500.reversi.view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Polygon;
import java.util.Arrays;

/**
 * Represents a basic hexagon.
 * A 6 sided polygon with equal angles,
 * a radius (which represents the Hexagon's center point to a vertex),
 * a center point in a 2d plane,
 * and a color, which represents what color the hexagon is filled in as.
 */
public class Hexagon {

  private double radius;
  private Point2D center;
  private Polygon hexagon;
  private Color color;

  /**
   * Creates a Hexagon object.
   *
   * @param center the {@link Point2D} where this {@link Hexagon} should be centered on.
   * @param radius a double value representing the radius of the hexagon.
   *               The radius represents the Hexagon's center point to a vertex.
   * @param color  the {@link Color} which this {@link Hexagon} should be.
   */
  public Hexagon(Point2D center, double radius, Color color) {
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

  /**
   * Gets the center of a hexagon.
   *
   * @return a {@link Point2D} object which represents the center of this hexagon.
   */
  public Point2D getCenter() {
    return center;
  }

  /**
   * Gets the Polygon representation of this hexagon.
   *
   * @return a {@link Polygon} object which represents the hexagon.
   */
  public Polygon getHexagon() {
    return hexagon;
  }

  /**
   * Sets the color of the current hexagon.
   *
   * @param color the {@link Color} which the hexagon should be.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of the current hexagon.
   *
   * @return a {@link Color} object which represents the color of the hexagon.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Updates the center of the hexagon.
   * This will both set the center of the hexagon,
   * and fix the {@link Polygon} representation of the Hexagon.
   *
   * @param center the {@link Point2D} which represents the new center of the hexagon.
   */
  public void updateHexagon(Point2D center) {
    this.center = center;
    this.hexagon = createHexagon();
  }

  /**
   * Sets the radius of the current hexagon.
   *
   * @param radius a double value representing the radius of the hexagon.
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }
}
