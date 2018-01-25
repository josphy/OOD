package model.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A factory that can make Rectangle objects
 * @author Haoshan Zou, Qingsheng Li
 *
 */
public class RectangleShapeFactory implements IShapeFactory {
	
	/**
	 * Singleton, for other classes to make rectangle objects
	 */
	private static RectangleShapeFactory Singleton= new RectangleShapeFactory();


	/**
	 * Make a rectangle shape from the input parameters
	 * Note: First transform the center to top left corner
	 * @param xScale Half of the length in x-direction of rectangle
	 * @param yScale Half of the length in y-direction of rectangle
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Rectangle2D.Double(x - xScale, y - yScale, 2 * xScale, 2 * yScale);
	}

	/**
	 * Getter of singleton
	 * @return The singleton
	 */
	public static RectangleShapeFactory getFac() {
		return Singleton;
	}

}
