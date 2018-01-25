package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import model.IBallCmd;
import model.IInteractStrategy;
import model.IUpdateStrategy;
import model.MultiInteractStrategy;
import util.IDispatcher;
import util.Randomizer;

/**
 * Set up a MultiInteractStrategy which contains an escape interaction strategy
 * that teleport to another location when two balls interact.
 *
 * This strategy must be combined with another strategy which has interaction
 * criteria detection mechanism，such as detecting overlapping.
 *
 * Interaction Behavior: Escape to a random location
 */
public class EscapeUpdateStrategy implements IUpdateStrategy<IBallCmd> {
  @Override
  public void init(Ball context) {
    context.setInteractStrategy(new MultiInteractStrategy(
        context.getInteractStrategy(), new IInteractStrategy() {
      @Override
      public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
        if (context != target) {
          Point loc = new Point();
          loc.x = Randomizer.Singleton.randomInt(target.getRadius(),
              target.getCanvas().getWidth() - target.getRadius());
          loc.y = Randomizer.Singleton.randomInt(target.getRadius(),
              target.getCanvas().getHeight() - target.getRadius());
          context.setLocation(loc);
        }
      }
    }));

  }

  // Do nothing.
  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {}

}
