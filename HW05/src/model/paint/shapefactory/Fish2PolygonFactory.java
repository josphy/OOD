package model.paint.shapefactory;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A subclass of PolygonFactory for creating Fish2, with closed-mouse and
 * a tail.
 */
public class Fish2PolygonFactory extends PolygonFactory {
  /**
   * A singleton object
   */
  public static final Fish2PolygonFactory Singleton = new Fish2PolygonFactory();

  /**
   * Construct a polygon of Fish2, which is done by invoking the constructor
   * in PolygonFactory with necessary arguments.
   */
  private Fish2PolygonFactory() {
    super(new AffineTransform(), 0.1, new Point(10, 0), new Point(6, 3),
        new Point(2, 1), new Point(0, 2), new Point(0, -2), new Point(2, -1),
        new Point(6, -3));
  }

}
