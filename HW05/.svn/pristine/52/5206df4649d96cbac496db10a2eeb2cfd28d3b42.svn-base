package model.paint.paintstrategy.concrete;

import java.awt.Graphics;

import model.Ball;
import model.IPaintStrategy;

/**
 * Paint strategy that paints a filled square with the Ball's radius.
 *
 * The class demonstrates a direct implementation of IPaintStrategy. This
 * functionality is duplicated by the RectanglePaintStrategy.
 *
 * This strategy does not rotate the square as it bounces around, which simply
 * uses a built-in method of the Graphics object to paint a filled square
 * (rectangle) onto the screen. No affine transforms are involved.
 */
public class SquarePaintStrategy implements IPaintStrategy {
  /**
   * No parameter constructor for this class.
   */
  public SquarePaintStrategy() {}

  /**
   * Paints a square on the given graphics context using the color and radius
   * provided by the host.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void paint(Graphics g, Ball host) {
    int side = host.getRadius();  // the side of the square
    g.setColor(host.getColor());
    g.fillRect(host.getLocation().x - side, host.getLocation().y - side,
        2 * side, 2 * side);
  }

  /**
   * By default, do nothing for initialization.
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {}

}
