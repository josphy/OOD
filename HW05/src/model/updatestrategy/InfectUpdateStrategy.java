package model.updatestrategy;

import java.awt.Color;

import model.Ball;
import model.IBallCmd;
import util.IDispatcher;
import util.Randomizer;

/**
 * When two balls overlap, make the other ball also an infect ball. Recover to a
 * straight ball after a period of time.
 *
 * Interaction Criteria: Overlapping Radii
 * Interaction Behavior: Set the other ball's updateStrategy to this strategy
 */
public class InfectUpdateStrategy extends AUpdateStrategy<IBallCmd> {
  /**
   * Tick counter that counts out the delay before another ball can be infected
   */
  private int count = 0;
  /**
   * Tick delay which increases at each infection to keep total infectious rate
   * from exponentially exploding. When count is greater than delay, set the
   * Ball's update strategy to StraightStrategy.
   */
  private int delay = 500;

  @Override
  public void updateState(Ball context, IDispatcher<IBallCmd> dispatcher) {
    context.setColor(Color.BLACK);
    dispatcher.dispatch(new IBallCmd() {
      @Override
      public void apply(Ball other, IDispatcher<IBallCmd> disp) {
        count++;
        if (count != 0 && context != other) {
          if ((context.getRadius() + other.getRadius()) >
              context.getLocation().distance(other.getLocation())) {
            other.setUpdateStrategy(new InfectUpdateStrategy());
          }
        }
        if (count > delay) {
          context.setColor(Randomizer.Singleton.randomColor());
          context.setUpdateStrategy(new StraightUpdateStrategy<IBallCmd>());
        }
      }
    });
  }

}
