package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * Set up a MultiInteractStrategy which contains a "freeze" interaction strategy
 * that sets the other Ball's velocity to zero when they interact.
 *
 * This strategy must be combined with another strategy which has interaction
 * criteria detection mechanism，such as detecting overlapping.
 *
 * Interaction Behavior: Freeze another ball
 */
public class FreezeUpdateStrategy implements IUpdateStrategy<IBallCmd> {
  @Override
  public void init(Ball context) {
    context.setInteractStrategy(new MultiInteractStrategy(
        context.getInteractStrategy(), new IInteractStrategy(){
      @Override
      public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
        if (context != target) { target.setVelocity(new Point(0,0)); }
      }
    }));
  }

  // Do nothing.
  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {}

}
