package model.paint.paintstrategy.concrete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;
import model.paint.paintstrategy.FixedColorDecoratorPaintStrategy;
import model.paint.paintstrategy.MultiPaintStrategy;

/**
 * Subclass of MultiPaintStrategy that uses a SwimFishPaintStrategy and an
 * EllipsePaintStrategy to paint a swimming fish shape that has an eye.
 */
public class NiceFishPaintStrategy extends MultiPaintStrategy {
  /**
   * No-parameter constructor that instantiates the AffineTransform for
   * internal use.
   */
  public NiceFishPaintStrategy() {
    this(new AffineTransform());
  }

  /**
   * Constructor that uses an externally supplied AFfineTransform for
   * internal use.
   *
   * @param at      The AffineTransform to use for internal calculations
   */
  public NiceFishPaintStrategy(AffineTransform at) {
    super(at, new SwimFishPaintStrategy(),
        // Assume fish has a black eye.
        new FixedColorDecoratorPaintStrategy(Color.BLACK,
            new EllipsePaintStrategy(at, 0.75, -0.1, 0.2, 0.2)));
  }

  /**
   * Augment the paintCfg() to filp the Y-axis whenever the shape is traveling
   * to the left.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  protected void paintCfg(Graphics g, Ball host) {
    super.paintCfg(g, host);
    if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x))
        > Math.PI / 2.0) {
        at.scale(1.0, -1.0);
    }
  }

}
