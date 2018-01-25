package model.updatestrategy;

import model.Ball;
import util.IDispatcher;
import util.Randomizer;

/**
 * breathing strategy: ball sinusoidally changes radius.
 */
public class BreathingUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * maximum radius of a ball
   */
  private static int MAX_RADIUS = 20;
  /**
   * minimum radius of a ball
   */
  private static int MIN_RADIUS = 8;
  /**
   * the unit which the ball changes its radius
   */
  private int breathIntensity = 1;
  /**
   * the initial changing direction of the radius
   * 1 - increasing
   * 0 - decreasing
   */
  private int direction = Randomizer.Singleton.randomInt(0, 1);
  /**
   * update ball with breathing.
   *
   * @param context the context
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    int radius = context.getRadius();
    if (direction == 1) {
      radius += breathIntensity;
      context.setRadius(radius);
      if (radius >= MAX_RADIUS) {
        direction = 0;
      }
    } else {
      radius -= breathIntensity;
      context.setRadius(radius);
      if (radius <= MIN_RADIUS) {
        direction = 1;
      }
    }
  }

}
