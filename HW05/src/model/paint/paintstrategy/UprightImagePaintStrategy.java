package model.paint.paintstrategy;

import java.awt.Graphics;

import model.Ball;

/**
 * A subclass of ImagePaintStrategy which keeps the image upright no matter
 * which way it is going.
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {
  /**
   * Create an object with a given file name and a fill factor.
   * @param filename    the image file name
   * @param fillFactor  a fill factor
   */
  public UprightImagePaintStrategy(String filename, double fillFactor) {
    super(filename, fillFactor);
  }

  /**
   * Augment the paintCfg() to filp the Y-axis whenever the image is traveling
   * to the left.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  protected void paintCfg(Graphics g, Ball host) {
    super.paintCfg(g, host);
    if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x))
        > Math.PI / 2.0) {
        at.scale(1.0, -1.0);
    }
  }

}
