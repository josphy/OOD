package model.updatestrategy;

import model.Ball;
import model.IBallCmd;
import util.IDispatcher;
import util.Randomizer;

/**
 * When two balls overlap, both of which will flash while overlapping.
 *
 * Interaction Criteria: Overlapping Radii
 * Interaction Behavior: Flashing while overlapping
 */
public class FlashUpdateStrategy extends AUpdateStrategy<IBallCmd> {

  /**
  * update strategy that changes both balls' color if collides
  * @param context this ball
  * @param dispatcher current dispatcher
  */
  @Override
  public void updateState(final Ball context, IDispatcher<IBallCmd> dispatcher) {
    dispatcher.dispatch(new IBallCmd() {
      @Override
      public void apply(Ball another, IDispatcher<IBallCmd> disp) {
        if (context != another) {
          double distance = context.getLocation().distance(another.getLocation());
          if (distance < context.getRadius() + another.getRadius()) {
            another.setColor(Randomizer.Singleton.randomColor());
            context.setColor(Randomizer.Singleton.randomColor());
          }
        }
      }
    });
  }

}
