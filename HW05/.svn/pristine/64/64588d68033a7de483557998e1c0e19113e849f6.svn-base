package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A subclass of ShapePaintStrategy which keeps the shape upright no matter
 * which way it is going.
 */
public class UprightShapePaintStrategy extends ShapePaintStrategy {
  /**
   * Construct an object with a new AffineTransform object and a given shape.
   * @param shape   an shape to be transformed
   */
  public UprightShapePaintStrategy(Shape shape) {
    super(shape);
  }

  /**
   * Construct an object with an existing AffineTransform object and a given
   * shape.
   *
   * @param at    an AffineTransform object used to perform all affine transforms
   * @param shape an shape to be transformed
   */
  public UprightShapePaintStrategy(AffineTransform at, Shape shape) {
    super(at, shape);
  }

  /**
   * Augment the paintCfg() to filp the Y-axis whenever the shape is traveling
   * to the left.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  protected void paintCfg(Graphics g, Ball host) {
    super.paintCfg(g, host);
    if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x))
        > Math.PI / 2.0) {
        at.scale(1.0, -1.0);
    }
  }

}
