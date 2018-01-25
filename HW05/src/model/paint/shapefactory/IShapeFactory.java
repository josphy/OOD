package model.paint.shapefactory;

import java.awt.Shape;

/**
 * An abstract factory that creates a Shape for use as prototype shapes in
 * IPaintStrategies.
 */
public interface IShapeFactory {
  /**
   * Create a Shape object centered at (x, y), with specified x and y scale
   * factor.
   * @param x       floating point x-coordinate of center of the shape
   * @param y       floating point y-coordinate of center of the shape
   * @param xScale  floating point x-scale of the shape
   * @param yScale  floating point x-scale of the shape
   * @return        a Shape object
   */
  public Shape makeShape(double x, double y, double xScale, double yScale);

}
