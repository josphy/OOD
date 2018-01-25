package model.updatestrategy;

import java.awt.Point;

import model.Ball;
import util.IDispatcher;
import util.Randomizer;

/**
 * the curve moving strategy.
 */
public class CurveUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * v1 is a random angle for new velocity calculation.
   */
  private int v1 = Randomizer.Singleton.randomInt(10, 20);
  /**
   * this defines the speed to changing velocity directions,
   * which will determine the radius of the circular motion.
   */
  private final double theta = Math.PI / v1;
  /**
   * stored cos(theta).
   */
  private double cosA = Math.cos(theta);
  /**
   * stored sin(theta).
   */
  private double sinA = Math.sin(theta);

  /**
   * update state the ball of context.
   *
   * @param context the ball to update strategy.
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    int Vx = context.getVelocity().x;
    int Vy = context.getVelocity().y;
    Point vel = new Point();
    vel.x = (int) Math.round(Vx * cosA - Vy * sinA);
    vel.y = (int) Math.round(Vy * cosA + Vx * sinA);
    context.setVelocity(vel);
  }

}
