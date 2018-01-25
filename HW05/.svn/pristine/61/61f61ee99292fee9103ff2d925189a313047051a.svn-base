package model.paint.shapefactory;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * A concrete factory for creating a Polygon shape.
 *
 * This factory holds an internal unit shape that is transformed into its
 * output, as desired by a paint strategy for its own unit shape.
 */
public class PolygonFactory implements IShapeFactory {
  /**
   * An internal prototype polygon used to be transformed to be an output
   */
  private Polygon polygon = new Polygon();
  /**
   * An AffineTransform object used to perform transformation
   */
  private AffineTransform at;
  /**
   * Scale factor that scales the integer point defined polygon to a unit size,
   * which requires doubles. The value of scaleFactor is the ratio of the
   * desired unit size to the defined size of the prototype polygon.
   */
  private double scaleFactor = 1.0;

  /**
   * Construct a polygon based on AffineTransform object, scale factor, and
   * input Points.
   *
   * @param at            an AffineTransform object
   * @param scaleFactor   the ratio of the desired unit size to the defined size
   *                      of the prototype polygon.
   * @param points        composing Point objects of the polygon
   */
  public PolygonFactory(AffineTransform at, double scaleFactor, Point ... points) {
    this.at = at;
    this.scaleFactor = scaleFactor;
    for (Point p : points) {
      polygon.addPoint(p.x, p.y);
    }
  }

  @Override
  public Shape makeShape(double x, double y, double xScale, double yScale) {
    at.setToTranslation(x, y);
    // optional rotation can be added as well
    at.scale(xScale * scaleFactor, yScale * scaleFactor);
    return at.createTransformedShape(polygon);
  }

}
