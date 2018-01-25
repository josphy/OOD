package model.paint.paintstrategy.concrete;

import java.awt.geom.AffineTransform;

import model.paint.paintstrategy.ShapePaintStrategy;
import model.paint.shapefactory.Fish1PolygonFactory;

/**
 * Paint strategy to paint an fish1 shape.
 */
public class Fish1PaintStrategy extends ShapePaintStrategy {
  /**
   * No parameter constructor that creates a prototype Fish1 with an average
   * radius of 1. An AffineTranform for internal use is instantiated.
   */
  public Fish1PaintStrategy() {
    this(new AffineTransform(), 0, 0, 1.5, 1.5);
  }

  /**
   * Constructor that allows the specification of the location, x-radius and
   * y-radius of the prototype ellipse. The AffineTransform to use is given.
   * @param at      The AffineTransform to use for internal calculations
   * @param x       floating point x-coordinate of center of the Fish1
   * @param y       floating point y-coordinate of center of the Fish1
   * @param width   floating point x-radius of the the Fish1
   * @param height  floating point y-radius of the the Fish1
   */
  public Fish1PaintStrategy(AffineTransform at, double x, double y,
      double width, double height) {
    super(at, Fish1PolygonFactory.Singleton.makeShape(x, y, width, height));
  }

}
