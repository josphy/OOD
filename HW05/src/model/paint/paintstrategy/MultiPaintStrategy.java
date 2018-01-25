package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * A composite design pattern extension of APaintStrategy that paints a set of
 * paint strategies.
 *
 * Note: this strategy cannot be directly instantiated by the model system
 * because it lacks a no-parameter constructor.
 */
public abstract class MultiPaintStrategy extends APaintStrategy {
  /**
   * An array of APaintStrategies to be painted
   */
  private APaintStrategy [] strategyArr;

  /**
   * Construct an object with a new AffineTransform object and a given
   * APaintStrategy array.
   *
   * @param strategies  an array of APaintStrategies
   */
  public MultiPaintStrategy(APaintStrategy ... strategies) {
    this(new AffineTransform(), strategies);
  }

  /**
   * Construct an object with a given AffineTransform object and a given
   * APaintStrategy array.
   *
   * @param at          an AffineTransform object
   * @param strategies  an array of APaintStrategies
   */
  public MultiPaintStrategy(AffineTransform at, APaintStrategy ... strategies) {
    super(at);
    strategyArr = strategies;
  }

  /**
   * Delegate to each APaintStrategy's init() method.
   *
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {
    for (APaintStrategy strategy: strategyArr) {
      strategy.init(host);
    }
  }

  /**
   * Delegate to each APaintStrategies paintXfrm() method.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    for (APaintStrategy strategy: strategyArr) {
      strategy.paintXfrm(g, host, at);
    }
  }

}
