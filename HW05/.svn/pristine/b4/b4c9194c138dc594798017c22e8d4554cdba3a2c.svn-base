package model.paint.paintstrategy.concrete;

import java.awt.geom.AffineTransform;

import model.paint.paintstrategy.ShapePaintStrategy;
import model.paint.shapefactory.Fish2PolygonFactory;

/**
 * Paint strategy to paint an fish2 shape.
 */
public class Fish2PaintStrategy extends ShapePaintStrategy {
  /**
   * No parameter constructor that creates a prototype Fish2 with an average
   * radius of 1. An AffineTranform for internal use is instantiated.
   */
  public Fish2PaintStrategy() {
    this(new AffineTransform(), 0, 0, 1.5, 1.5);
  }

  /**
   * Constructor that allows the specification of the location, x-radius and
   * y-radius of the prototype ellipse. The AffineTransform to use is given.
   * @param at      The AffineTransform to use for internal calculations
   * @param x       floating point x-coordinate of center of the Fish2
   * @param y       floating point y-coordinate of center of the Fish2
   * @param width   floating point x-radius of the the Fish2
   * @param height  floating point y-radius of the the Fish2
   */
  public Fish2PaintStrategy(AffineTransform at, double x, double y,
      double width, double height) {
    super(at, Fish2PolygonFactory.Singleton.makeShape(x, y, width, height));
  }

}
