package model.strategy;

import java.awt.Point;

import model.ball.Ball;
import util.Randomizer;

// TODO: Auto-generated Javadoc
/**
 * wander strategy: ball randomly go forward or backward.
 */
public class WanderStrategy implements IUpdateStrategy {

	/**
	 * update state the ball of context.
	 *
	 * @param context the context
	 */
	@Override
	public void updateState(Ball context) {
		Point vel = context.getVelocity();

		if (vel.x > 0) {
			vel.x = Randomizer.Singleton.randomInt(1, 40);
		} else {
			vel.x = -1 * Randomizer.Singleton.randomInt(1, 40);
		}

		context.setVelocity(vel);
	}

}
