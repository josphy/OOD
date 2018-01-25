package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A concrete paint strategy that cycles through a sequence of paint strategies,
 * painting one per paint update.
 *
 * Note: this strategy cannot be directly instantiated by the model system
 * because it lacks a no-parameter constructor.
 */
public class AnimatePaintStrategy extends APaintStrategy {
  /**
   * An array of APaintStrategies to cycle through
   */
  private APaintStrategy [] strategyArr;
  /**
   * The counter that keeps track of which paint strategy to use next
   */
  private int count;

  /**
   * Construct an object with a new AffineTransform object and a given
   * APaintStrategy array.
   * @param strategies  an array of APaintStrategies
   */
  public AnimatePaintStrategy(APaintStrategy ... strategies) {
    this(new AffineTransform(), strategies);
  }

  /**
   * Construct an object with a given AffineTransform object and a given
   * APaintStrategy array.
   * @param at          an AffineTransform object
   * @param strategies  an array of APaintStrategies
   */
  public AnimatePaintStrategy(AffineTransform at, APaintStrategy ... strategies) {
    super(at);
    strategyArr = strategies;
    count = 0;
  }

  /**
   * Paints the currently indexed paint strategy on the given Graphics context
   * using the supplied AffineTransform.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    if (count >= strategyArr.length) { count = 0; } // reverse back to 0
    strategyArr[count++].paintXfrm(g, host, at);
  }

}
