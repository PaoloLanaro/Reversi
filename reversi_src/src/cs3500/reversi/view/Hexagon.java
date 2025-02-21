package cs3500.reversi.view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Polygon;
import java.util.Objects;

/**
 * Represents a basic hexagon.
 * A six-sided polygon with equal angles,
 * a radius (representing the Hexagon's center point to a vertex),
 * a center point in a 2D plane,
 * and a color that determines the hexagon's fill color.
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

  @Override
  public String toString() {
    return "Center Position: " + center;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (!(other instanceof Hexagon otherObj)) {
      return false;
    }
    return Objects.equals(otherObj.center, this.center)
            && Objects.equals(otherObj.hexagon, this.hexagon)
            && Objects.equals(otherObj.color, this.color)
            && Objects.equals(otherObj.radius, this.radius);
  }

  @Override
  public int hashCode() {
    return this.center.hashCode()
            + this.hexagon.npoints
            + (int) this.radius
            + this.color.hashCode();
  }
}
