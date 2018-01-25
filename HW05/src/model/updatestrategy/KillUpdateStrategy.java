package model.updatestrategy;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * Set up a MultiInteractStrategy which contains a "kill" interaction strategy
 * that kills another Ball when they interact.
 *
 * This strategy must be combined with another strategy which has interaction
 * criteria detection mechanism，such as detecting overlapping.
 *
 * Interaction Behavior: Kill another ball
 */
public class KillUpdateStrategy implements IUpdateStrategy<IBallCmd> {
  @Override
  public void init(Ball context) {
    context.setInteractStrategy(new MultiInteractStrategy(
        context.getInteractStrategy(), new IInteractStrategy(){
      @Override
      public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
        disp.deleteObserver(target);
      }
    }));
  }

  // Do nothing.
  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {}

}
