package model.paint.paintstrategy.concrete;

import java.awt.geom.AffineTransform;

import model.paint.paintstrategy.AnimatePaintStrategy;

/**
 * Subclass of AnimatePaintStrategy that alternates between a Fish1PaintStrategy
 * and a Fish2PaintStrategy.
 */
public class SwimFishPaintStrategy extends AnimatePaintStrategy {
  /**
   * No-parameter constructor that instantiates an AffineTransform for internal
   * use.
   */
  public SwimFishPaintStrategy() {
    this(new AffineTransform());
  }

  /**
   * Constructor that takes an external AffineTransform for internal use.
   *
   * @param at      The AffineTransform to use for internal calculations
   */
  public SwimFishPaintStrategy(AffineTransform at) {
    super(at, new Fish1PaintStrategy(), new Fish2PaintStrategy());
  }
}
