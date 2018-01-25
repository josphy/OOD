package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import util.IDispatcher;
import util.Randomizer;

/**
 * drunken strategy: balls change direction in every movement.
 */
public class DrunkenUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * update state the ball of context.
   *
   * @param context the ball to update strategy.
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    Point vel = new Point();
    vel.x = Randomizer.Singleton.randomInt(-20, 20);
    vel.y = Randomizer.Singleton.randomInt(-20, 20);
    context.setVelocity(vel);
  }

}
