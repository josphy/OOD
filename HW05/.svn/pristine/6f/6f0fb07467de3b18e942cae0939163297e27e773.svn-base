package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import model.Ball;

/**
 * A concrete paint strategy that paints an image from a file, scaled to the
 * host Ball's radius.
 *
 * Note: this strategy cannot be directly instantiated by the model system
 * because it lacks a no-parameter constructor.
 */
public class ImagePaintStrategy extends APaintStrategy {
  /**
   * An ImageObserber object for image operations, handling synchronization
   * issues.
   */
  private ImageObserver imgObs;
  /**
   * An Image to be painted
   */
  private Image image;
  /**
   * The ratio of the effective circle of the image to the extent of the
   * entire image. This is determined by human judgment roughly.
   */
  private double fillFactor = 1.0;
  /**
   * The ratio of the unit radius circle to the effective radius size of the
   * image. This is the inverse of the fill factor times half the average of
   * the width and height of the image.
   *
   *                      fillFactor * (image.width + image.height) / 2
   * effective radius = -------------------------------------------------
   *                                          2
   *
   * effective radius * scaleFactor = unit size (1)
   *
   * scaleFactor = 1 / effective radius
   */
  private double scaleFactor = 1.0;
  /**
   * A local AffineTransform object to scale and offset to create a unit-sized
   * image
   */
  protected AffineTransform localAT = new AffineTransform();

  /**
   * Construct an object with a given AffineTransform object, an image file
   * name and a fill factor.
   *
   * @param at          an AffineTransform object
   * @param filename    filename is the file plus its path relative to the
   *                    location of the class that is doing the loading,
   *                    i.e. the subclass of ImagePaintStrategy
   * @param fillFactor  a fill factor
   */
  public ImagePaintStrategy(AffineTransform at, String filename,
      double fillFactor) {
    super(at);
    this.fillFactor = fillFactor;
    try {
      // only the resources are allocated
      image = Toolkit.getDefaultToolkit().getImage(
          this.getClass().getResource(filename));
    } catch (Exception e) {
      System.err.println("ImagePaintStrategy: Error reading file: " + filename +
          "\n" + e);
    }
  }

  /**
   * Construct an object with an image file name
   * and a fill factor.
   * @param filename    filename is the file plus its path relative to the
   *                    location of the class that is doing the loading,
   *                    i.e. the subclass of ImagePaintStrategy
   * @param fillFactor  a fill factor
   */
  public ImagePaintStrategy(String filename, double fillFactor) {
    this(new AffineTransform(), filename, fillFactor);
  }

  /**
   * Perform affine transform based on the internal AffineTransform object
   * first, then based on the given AffineTransform object.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  @Override
  public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
    localAT.setToScale(scaleFactor, scaleFactor);
    // translate to the center the image
    localAT.translate(-image.getWidth(imgObs) / 2.0,
                      -image.getHeight(imgObs) / 2.0);
    // perform localAT first, then at
    localAT.preConcatenate(at);
    ((Graphics2D) g).drawImage(image, localAT, imgObs);
  }

  /**
   * Initialize a strategy for the image based on the host's information.
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {
    imgObs = host.getCanvas();
    // Wait for the image to load which takes a long time relative to other
    // operations
    MediaTracker mt = new MediaTracker(host.getCanvas());
    mt.addImage(image, 1);
    try {
      mt.waitForAll();
    } catch (Exception e) {
      System.out.println("ImagePaintStrategy.init(): Error waiting for image."
          + "Exception = " + e);
    }
    scaleFactor = 2.0 / (fillFactor * (image.getWidth(imgObs) +
        image.getHeight(imgObs)) / 2.0);
  }

}
