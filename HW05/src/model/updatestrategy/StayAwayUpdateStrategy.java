package model.updatestrategy;

import model.Ball;
import model.IBallCmd;
import util.IDispatcher;

/**
 * Detect if two balls are less than 20 distance away.
 *
 * Interaction Criteria: Two balls are less than 20 distance away
 */
public class StayAwayUpdateStrategy extends AUpdateStrategy<IBallCmd> {
  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
    dispatcher.dispatch(new IBallCmd() {
      @Override
      public void apply(Ball another, IDispatcher<IBallCmd> disp) {
        if (context != another) {  // Make sure the another is not itself.
          double distance = context.getLocation().distance(another.getLocation());
          if (context.getRadius() + another.getRadius() > distance - 20) {
            context.interactWith(another, dispatcher);
            another.interactWith(context, dispatcher);
          }
        }
      }
    });
  }
}
