/**
 * 
 */
package model.shape;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A polygon factory that can make a ghost in pacman
 */
public class Ghost1Factory extends PolygonFactory {

	/**
	 * Constructor. Build up the ghost shape with the parameters
	 */
	public Ghost1Factory() {
		super(new AffineTransform(), 0.1, new Point(0,8), new Point(8, 0), new Point(16,0), new Point(24,8), new Point(24,36),
				new Point(16, 30), new Point(8, 30), new Point(0, 36));
	}

}
