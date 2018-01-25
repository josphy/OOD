package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import model.IBallCmd;
import util.IDispatcher;

/**
 * Spawn new balls when the ball contacts another one.
 *
 * Interaction Criteria: Overlapping Radii
 * Interaction Behavior: Create a new ball
 */
public class SpawnUpdateStrategy extends AUpdateStrategy<IBallCmd> {
  /**
   * Tick counter that counts out the delay before another ball can be spawned.
   */
  private int count = 0;
  /**
   * Tick delay which increases at each spawn to keep total spawn rate from
   * exponentially exploding.
   */
  private int delay = 100;

  /**
   * update method called by timer
   * @param context this ball
   * @param dispatcher current dispatcher
   */
  @Override
  public void updateState(final Ball context, IDispatcher<IBallCmd> dispatcher) {
    if (count++ > delay) {
      dispatcher.dispatch(new IBallCmd() {
        @Override
        public void apply(Ball another, IDispatcher<IBallCmd> disp) {
          if (count != 0 && context != another) {
            if (context.getRadius() + another.getRadius() >
                context.getLocation().distance(another.getLocation())) {
              disp.addObserver(new Ball(new Point(context.getLocation()),
                                        context.getRadius(),
                                        new Point(-context.getVelocity().x + 1,
                                                  -context.getVelocity().y + 1),
                                        context.getColor(),
                                        context.getCanvas(),
                                        new SpawnUpdateStrategy(),
                                        context.getPaintStrategy()
                                       )
                              );
              count = 0;
              delay *= 5;
            }
          }
        }
      });
    }

  }

}
