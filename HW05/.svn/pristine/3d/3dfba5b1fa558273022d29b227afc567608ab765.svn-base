package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import util.IDispatcher;
import util.Randomizer;

/**
 * wander strategy: ball randomly go forward or backward.
 */
public class WanderUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * update state the ball of context.
   *
   * @param context the context
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    Point vel = context.getVelocity();
    if (vel.x > 0) {
      vel.x = Randomizer.Singleton.randomInt(1, 40);
    } else {
      vel.x = -1 * Randomizer.Singleton.randomInt(1, 40);
    }
    context.setVelocity(vel);
  }

}
