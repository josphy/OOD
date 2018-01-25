package model.paint.shapefactory;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A concrete factory for creating a Rectangle shape, which is a
 * Rectangle2D.double object.
 */
public class RectangleShapeFactory implements IShapeFactory {
  /**
   * A singleton object
   */
  public static final RectangleShapeFactory singleton = new RectangleShapeFactory();

  /**
   * Private constructor to prevent being newed.
   */
  public RectangleShapeFactory() {}

  /**
   * Create a Rectangle centered at (x, y), with specified x and y scale
   * factor.
   * @param x       floating point x-coordinate of center of the Rectangle
   * @param y       floating point y-coordinate of center of the Rectangle
   * @param xScale  floating point x-radius of the Rectangle
   * @param yScale  floating point x-radius of the Rectangle
   * @return        a Rectangle object
   */
  @Override
  public Shape makeShape(double x, double y, double xScale, double yScale) {
    return new Rectangle2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
  }

}
