package model.paint.paintstrategy.concrete;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import model.Ball;
import model.IPaintStrategy;

/**
 * Paint strategy that paints a filled circle with the Ball's radius.This
 * functionality is duplicated by the EllipsePaintStrategy.The class
 * demonstrates a direct implementation of IPaintStrategy.
 */
public class BallPaintStrategy implements IPaintStrategy {
  /**
   * The AffineTransformed used for internal calculations
   */
  private AffineTransform at;
  /**
   * A unit sized circle used as a prototype
   */
  private Ellipse2D.Double ball;

  /**
   * No parameter constructor that creates a 1 pixel radius ball at the origin.
   * Instantiates a new AffineTransform for internal use.
   */
  public BallPaintStrategy() {
    this(new AffineTransform(), 0, 0, 1, 1);
  }

  /**
   * Constructor that allows one to create the prototype ball of arbitrary
   * dimension and location. The AffineTransform is externally supplied.
   *
   * @param at      The AffineTransform to use for internal calculations
   * @param x       floating point x-coordinate of center of the circle
   * @param y       floating point y-coordinate of center of the circle
   * @param width   floating point x-radius of the circle
   * @param height  floating point y-radius of the circle
   */
  public BallPaintStrategy(AffineTransform at, double x, double y,
      double width, double height) {
    this.at = at;
    ball = new Ellipse2D.Double(x - width, y - height, 2 * width, 2 * height);
  }

  /**
   * Paint on the given graphics context using the color, scale and direction
   * provided by the host. This is done by setting up the AffineTransform to
   * scale then translate. Calls paintXfrm() to actually perform the painting
   * using the set up transform.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void paint(Graphics g, Ball host) {
    int radius = host.getRadius();
    at.setToTranslation(host.getLocation().x, host.getLocation().y);
    at.scale(radius, radius);
    g.setColor(host.getColor());
    paintXfm(g, host, at);
  }

  /**
   * Paint a transformed circle with the given AffineTransfor.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  public void paintXfm(Graphics g, Ball host, AffineTransform at) {
    ((Graphics2D) g).fill(at.createTransformedShape(ball));
  }

  /**
   * By default, do nothing for initialization.
   *
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {}

}
