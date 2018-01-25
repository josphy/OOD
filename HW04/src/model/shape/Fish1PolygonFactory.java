package model.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A PolygonFactory that could generate a polygon that looks like a fish
 */
public class Fish1PolygonFactory extends PolygonFactory{

	/**
	 * Constructor, just use a bunch of points to construct a polygon
	 */
	public Fish1PolygonFactory() {
		super(new AffineTransform(), 0.2, new Point(10,0), new Point(16,4), new Point(14,5),
				new Point(16,6), new Point(10,10), new Point(6,7), new Point(0,10), new Point(0,0), 
				new Point(6,3));
	}

}
