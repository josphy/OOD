package model.shape;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * Polygon factory that can make polygon objects
 */
public class PolygonFactory implements IShapeFactory {
	
	/**
	 * An empty polygon that could accept points to form the required polygon
	 */
	private Polygon newPolygon = new Polygon();

	/**
	 * The affine transform to use
	 */
	private AffineTransform at;
	
	/**
	 * Scale the polygon to a unit size
	 */
	private double scaleFactor;

	/**
	 * Constructor of polygon factory. Use Vararg to allow multiple input points
	 * Add the input points to the polygon one by one
	 * @param at The affine transform to use
	 * @param scaleFactor The scale factor
	 * @param points The points that forms the polygon
	 */
	public PolygonFactory(AffineTransform at, double scaleFactor, Point... points) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		for (Point point:points) {
			newPolygon.addPoint(point.x, point.y);
		}
	}

	/**
	 * Make the polygon object at the given location
	 * https://www.clear.rice.edu/comp310/f17/lectures/lec12/
	 */
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor);  // optional rotation can be added as well
		return at.createTransformedShape(newPolygon);
	}

}
