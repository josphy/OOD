package model.paint.paintstrategy.concrete;

import java.awt.geom.AffineTransform;

import model.paint.paintstrategy.ShapePaintStrategy;
import model.paint.shapefactory.RectangleShapeFactory;

/**
 * Concrete paint strategy that paints a rectangular shape scaled to the
 * size of the ball.
 */
public class RectanglePaintStrategy extends ShapePaintStrategy {
  /**
   * No-parameter constructor that instantiates the AffineTransform for
   * internal use plus defines a prototype Rectangle of a width = 4/3 and
   * height = 2/3 which is an average radius of 1.
   */
  public RectanglePaintStrategy() {
    this(new AffineTransform(), 0, 0, 2.0/3.0, 1.0/3.0);
  }

  /**
   * Constructor that allows the specification of the location, x-radius and
   * y-radius of the prototype Rectangle. The AffineTransform to use is given.
   *
   * @param at      The AffineTransform to use for internal calculations
   * @param x       floating point x-coordinate of center of the rectangle
   * @param y       floating point y-coordinate of center of the rectangle
   * @param width   floating point half-width of the rectangle
   * @param height  floating point half-height of the rectangle
   */
  public RectanglePaintStrategy(AffineTransform at, double x, double y,
      double width, double height) {
    super(at, RectangleShapeFactory.singleton.makeShape(x, y, width, height));
  }

}
