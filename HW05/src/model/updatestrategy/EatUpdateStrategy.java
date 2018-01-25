package model.updatestrategy;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;

/**
 * Set up a MultiInteractStrategy which contains a "eat" interaction strategy
 * that eats another Ball when they interact and increases this Ball's area.
 *
 * This strategy must be combined with another strategy which has interaction
 * criteria detection mechanism，such as detecting overlapping.
 *
 * Interaction Behavior: Eat another ball
 */
public class EatUpdateStrategy implements IUpdateStrategy<IBallCmd> {
  /**
  * install eat interact strategy into the context ball
  * @param context this ball
  */
  @Override
  public void init(Ball context) {
    context.setInteractStrategy(new MultiInteractStrategy(
        context.getInteractStrategy(), new IInteractStrategy() {
      @Override
      public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
        double totalMass = context.getRadius() * context.getRadius() +
            target.getRadius() * target.getRadius();
        context.setRadius((int) Math.sqrt(totalMass));
        disp.deleteObserver(target);  // Delete the target.
      }
    }));
  }

  // Do nothing.
  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {}

}
