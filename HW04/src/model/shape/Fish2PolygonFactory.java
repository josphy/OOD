package model.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A PolygonFactory that could generate a polygon that looks like a fish
 */
public class Fish2PolygonFactory extends PolygonFactory{

	/**
	 * Constructor, just use a bunch of points to construct a polygon
	 */
	public Fish2PolygonFactory() {
		
		super(new AffineTransform(), 0.2, new Point(9,0), new Point(7,2), new Point(3,4), new Point(0,0),
				new Point(0,10), new Point(3,6), new Point(7,8), new Point(9,10), new Point(12,8), 
				new Point(16,5), new Point(12,2));
	}

}
