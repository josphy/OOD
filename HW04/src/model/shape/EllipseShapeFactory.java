package model.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Ellipse factory that can make ellipse objects
 */
public class EllipseShapeFactory implements IShapeFactory {
	
	/**
	 * Singleton, for other classes to create ellipse object
	 */
	private static EllipseShapeFactory Singleton = new EllipseShapeFactory();

	/**
	 * Make an ellipse from the given parameters
	 * @param x - center of the ellipse
	 * @param y - center of the ellipse
	 * @param xRad - The x-radius of the ellipse
	 * @param yRad - The y-radius of the ellipse
	 * @return An concrete ellipse object
	 */
	@Override
	public Shape makeShape(double x, double y, double xRad, double yRad) {
		// TODO Auto-generated method stub
		return new Ellipse2D.Double(x - xRad, y - yRad, xRad, yRad);
	}

	/**
	 * Get EllipseShapeFactory singleton
	 * @return the singleton instance of EllipseShapeFactory
	 */
	public static EllipseShapeFactory getFac() {
		return Singleton;
	}


}
