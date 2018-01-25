package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.Ball;

/**
 * Concrete class that defines invariant painting behaviors to paint Shape
 * objects for all its subclasses.
 *
 * This class abstracts out the shape aspects into an java.awt.Shape object
 * transformed by an affine transform. It does this by leveraging off of the
 * affine transform services provided by APaintStrategy, thus needing only to
 * provide storage for an arbitrary shape and to implement the paintXfrm()
 * method which uses the existing affine transform to create the shape image
 * at the desired size, rotation and location.
 *
 * Note: this strategy cannot be directly instantiated by the model system
 * because it lacks a no-parameter constructor.
 */
public class ShapePaintStrategy extends APaintStrategy {
  /**
   * The shape to be painted
   */
  private Shape shape;

  /**
   * Construct an object with a new AffineTransform object and a given shape.
   *
   * @param shape   an shape to be transformed
   */
  public ShapePaintStrategy(Shape shape) {
    this(new AffineTransform(), shape);
  }

  /**
   * Construct an object with an existing AffineTransform object and a given
   * shape.
   *
   * @param at    an AffineTransform object used to perform all affine transforms
   * @param shape an shape to be transformed
   */
  public ShapePaintStrategy(AffineTransform at, Shape shape) {
    super(at);
    this.shape = shape;
  }

  /**
   * Paint the shape on the given Graphics context using the supplied
   * AffineTransform object. This method is called by the inherited paint()
   * method.
   *
   * This method utilizes the fact that the Graphics object used by the current
   * Java GUI system is in fact a Graphics2D subclass, which provides the useful
   * fill method for drawing arbitrary filled Shape objects, e.g. Rectangles.
   * All any subclass of ShapePaintStrategy needs to do is to supply a
   * unit-sized Shape to paint.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    ((Graphics2D) g).fill(at.createTransformedShape(shape));
  }

}
