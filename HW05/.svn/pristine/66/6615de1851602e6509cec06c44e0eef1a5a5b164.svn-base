package model.updatestrategy;

import java.awt.Color;

import model.Ball;
import model.IBallCmd;
import util.IDispatcher;

/**
 * Detects whether two balls have similar colors and overlap.
 *
 * Interaction Criteria: Overlapping Radii and Similar Colors
 */
public class SimilarColorUpdateStrategy extends AUpdateStrategy<IBallCmd> {
  /**
   * The maximum distance for two colors to be determined as similar.
   */
  private static double MAX_DISTANCE = 100;

  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
    dispatcher.dispatch(new IBallCmd() {
      @Override
      public void apply(Ball another, IDispatcher<IBallCmd> disp) {
        if (context != another) {  // Make sure the another is not itself.
          double distance = context.getLocation().distance(another.getLocation());
          if (context.getRadius() + another.getRadius() > distance &&
              similarColor(context, another)) {
            context.interactWith(another, dispatcher);
            another.interactWith(context, dispatcher);
          }
        }
      }
    });
  }

  /**
   * Determine whether two balls have similar colors.
   *
   * @param b1  a Ball
   * @param b2  a Ball
   * @return    true if the two balls' colors are similar, false otherwise
   */
  private boolean similarColor(Ball b1, Ball b2) {
    Color c1 = b1.getColor(), c2 = b2.getColor();
    double distance = Math.sqrt(Math.pow(c1.getRed() - c2.getRed(), 2) +
        Math.pow(c1.getGreen() - c2.getGreen(), 2) +
        Math.pow(c1.getBlue()- c2.getBlue(), 2));
    if (distance <= MAX_DISTANCE) { return true; }
    else { return false; }
  }

}
