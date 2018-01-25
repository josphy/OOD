package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * An abstract class that provides default behavior for its subclasses that will
 * decorate another APaintStrategy to add functionality to that strategy.
 *
 * All this class's methods do are to simply delegate to the decoree. A subclass
 * should override one or more methods, adding additional processing either
 * before or after delegating to the decoree.
 *
 * Note: this strategy cannot be directly instantiated by the model system
 * because it lacks a no-parameter constructor.
 */
public abstract class ADecoratorPaintStrategy extends APaintStrategy {
  /**
   * A decoree APaintStrategy whose behavior will be augmented.
   */
  private APaintStrategy decoree;

  /**
   * Construct an object with a new AffineTransform object and a given decoree.
   * @param decoree   a APaintStrategy to be decorated
   */
  public ADecoratorPaintStrategy(APaintStrategy decoree) {
    this(new AffineTransform(), decoree);
  }

  /**
   * Construct an object with a given AffineTransform object and a given
   * decoree.
   * @param at        an AffineTransform object
   * @param decoree   a APaintStrategy to be decorated
   */
  public ADecoratorPaintStrategy(AffineTransform at, APaintStrategy decoree) {
    super(at);
    this.decoree = decoree;
  }

  /**
   * Delegate to the decoree's paint() method.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void paint(Graphics g, Ball host) {
    decoree.paint(g, host);
  }

  /**
   * Delegate to the decoree's paintXfrm() method.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    decoree.paintXfrm(g, host, at);
  }

  /**
   * Delegate to the decoree's init() method.
   *
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {
    decoree.init(host);
  }

}
