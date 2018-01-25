package model.strategy;

import java.awt.Point;

import model.ball.Ball;
import util.Randomizer;

// TODO: Auto-generated Javadoc
/**
 * drunken strategy: balls change direction in every movement.
 */
public class DrunkenStrategy implements IUpdateStrategy {

	/**
	 * update state the ball of context.
	 * @param context the ball to update strategy.
	 */
	@Override
	public void updateState(Ball context) {
		// TODO Auto-generated method stub
		Point vel = new Point();
		vel.x = Randomizer.Singleton.randomInt(-20, 20);
		vel.y = Randomizer.Singleton.randomInt(-20, 20);
		context.setVelocity(vel);
	}

}