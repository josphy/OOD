package model.paint.paintstrategy.concrete;

import java.util.Random;

import model.paint.paintstrategy.ImagePaintStrategy;

/**
 * Paint strategy that paints a random planet.
 */
public class PlanetPaintStrategy extends ImagePaintStrategy {
  /**
   * The file path of images
   */
  private static final String FILE_PATH = "../../image/";
  /**
   * The array of names of the planets to be painted
   */
  private static String[] planet = new String[] {
      FILE_PATH + "Mars.png", FILE_PATH + "Earth.png",
      FILE_PATH + "Jupiter.png", FILE_PATH + "Venus.png",
      FILE_PATH + "Saturn.png"};

  /**
   * No parameter constructor that creates a planet image with a fill factor
   * to be 1.0.
   *
   * An AffineTranform for internal use is instantiated.
   */
  public PlanetPaintStrategy() {
    this(planet[new Random().nextInt(planet.length)], 1.0);
  }

  /**
   * Constructor with a specified filename and a fill factor.
   *
   * @param filename    the file name of the image
   * @param fillFactor  The ratio of the bounding circle to the extent of the
   * entire image
   */
  public PlanetPaintStrategy(String filename, double fillFactor) {
    super(filename, fillFactor);
  }

}
