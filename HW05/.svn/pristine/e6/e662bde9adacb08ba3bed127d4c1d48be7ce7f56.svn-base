package model.paint.paintstrategy.concrete;

import model.paint.paintstrategy.UprightImagePaintStrategy;

/**
 * Paint strategy that uses either an animated Mario or Sonic image.
 */
public class MarioSonicPaintStrategy extends UprightImagePaintStrategy {
  /**
   * The file path of images
   */
  private static final String FILE_PATH = "../../image/";
  /**
   * No-parameter constructor that instantiates the AffineTransform used
   * internally and draw an animated Mario or Sonic with a 50% probability.
   * The fill factor is around 0.7.
   */
  public MarioSonicPaintStrategy() {
    super(Math.random() <= 0.5 ? FILE_PATH + "Mario_animate.gif" :
      FILE_PATH + "Sonic_animate.gif", 0.7);
  }

}
