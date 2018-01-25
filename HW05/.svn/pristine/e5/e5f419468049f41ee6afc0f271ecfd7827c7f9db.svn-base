package model.paint.shapefactory;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A concrete factory for creating an Ellipse shape, which is an
 * Ellipse2D.Double object.
 */
public class EllipseShapeFactory implements IShapeFactory {
  /**
   * A singleton object
   */
  public static final EllipseShapeFactory Singleton = new EllipseShapeFactory();

  /**
   * Private constructor to prevent being newed.
   */
  private EllipseShapeFactory() {}

  /**
   * Create an Ellipse centered at (x, y), with specified x and y scale factors.
   *
   * @param x       floating point x-coordinate of center of the Ellipse
   * @param y       floating point y-coordinate of center of the Ellipse
   * @param xScale  floating point x-radius of the Ellipse
   * @param yScale  floating point x-radius of the Ellipse
   * @return        an Ellipse object
   */
  @Override
  public Shape makeShape(double x, double y, double xScale, double yScale) {
    return new Ellipse2D.Double(x - xScale / 2, y - yScale / 2, xScale, yScale);
  }
}
