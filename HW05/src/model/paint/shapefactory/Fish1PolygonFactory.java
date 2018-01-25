package model.paint.shapefactory;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * A subclass of PolygonFactory for creating Fish1, with open-mouse and a
 * tail.
 */
public class Fish1PolygonFactory extends PolygonFactory {
  /**
   * A singleton object
   */
  public static final Fish1PolygonFactory Singleton = new Fish1PolygonFactory();

  /**
   * Construct a polygon of Fish1, which is done by invoking the constructor
   * in PolygonFactory with necessary arguments.
   */
  private Fish1PolygonFactory() {
    super(new AffineTransform(), 0.1, new Point(9, 1), new Point(6, 3),
        new Point(2, 1), new Point(0, 2), new Point(0, -2), new Point(2, -1),
        new Point(6, -3), new Point(9, -1), new Point(7, 0));
  }

}
