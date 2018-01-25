package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import model.ball.Ball;
import java.awt.Shape;

/**
 * Paint a shape object, created by shape factories
 *
 */
public class ShapePaintStrategy extends APaintStrategy {

	/**
	 * The shape object to paint
	 */
	private Shape shape;
	
	/**
	 * Constructor, initialize the shape object to use
	 * @param at Affine Transform for the shape painting
	 * @param shape The shape to paint
	 */
	public ShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at);
		this.shape = shape;
	}

	/**
	 * Constructor, initialize the shape object to use
	 * @param shape The shape to paint
	 */
	public ShapePaintStrategy(Shape shape) {
		super(new AffineTransform());
		this.shape = shape;
	}
	
	@Override
	/**
	 * Paint the affine transformed shape
	 * https://www.clear.rice.edu/comp310/f17/labs/lab04/
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		((Graphics2D)g) .fill(at.createTransformedShape(shape));
	}

}
