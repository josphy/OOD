package model.paintStrategy;

import java.awt.geom.AffineTransform;

import model.paint.ShapePaintStrategy;
import model.shape.RectangleShapeFactory;

/**
 * Paint a rectangle made by the rectangle factory
 */
public class RectanglePaintStrategy extends ShapePaintStrategy{

	/**
	 * Initialize a Rectangle of a width = 4 and height = 3 
	 */
	public RectanglePaintStrategy() {
		this(new AffineTransform(), 0, 0, 2.0, 1.5);
	}
	
	/**
	 * Construct a paint strategy from the parameters of the rectangle
	 * @param at - The AffineTransform to use
	 * @param x - The x-coordinate of the center of rectangle. The transform is done in the factory class.
	 * @param y - The y-coordinate of the center of rectangle
	 * @param halfWidth - The half-width of the width
	 * @param halfHeight - The half length of the height
	 */
	public RectanglePaintStrategy(AffineTransform at, double x, double y, double halfWidth, double halfHeight) {
		super(at, RectangleShapeFactory.getFac().makeShape(x, y, halfWidth, halfHeight));
	}

}
