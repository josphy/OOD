package model.paint.paintstrategy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * Decorator paint strategy that decorates another IPaintStrategy and causes
 * the painted color to always be a specified, fixed color.
 *
 * It is useful when you want a shape that is a specific color and not the color
 * of the host Ball, for example, the black colored eye on a fish.
 * Note that this class only works in situations where paintXfrm() is called,
 * because if paint() is called, the call is delegated to the decoree, which
 * will then delegate to its own paintXfrm() method, not this decorator's
 * method. Thus, this class is best used when being composed with other paint
 * strategies inside a MultiPaintStrategy.
 */
public class FixedColorDecoratorPaintStrategy extends ADecoratorPaintStrategy{
  /**
   * The color to be painted
   */
  private Color color;

  /**
   * Constructor that takes the fixed color and the decoree strategy.
   *
   * @param color     the fixed color to be painted
   * @param decoree   the decoree strategy whose color is overridden
   */
  public FixedColorDecoratorPaintStrategy(Color color, APaintStrategy decoree) {
    super(decoree);
    this.color = color;
  }

  /**
   * Set the color of the Graphics and delegate to the decoree's paintXfrm().
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    g.setColor(color);
    super.paintXfrm(g, host, at);
  }

}
