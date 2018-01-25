package model.strategy;

import java.awt.Point;

import model.ball.Ball;
import util.Randomizer;

// TODO: Auto-generated Javadoc
/**
 * the curve moving strategy.
 */
public class CurveStrategy implements IUpdateStrategy {

	/**
	 * v1 is a random angle for new velocity calculation.
	 */
	private int v1 = Randomizer.Singleton.randomInt(10, 20);

	/**
	 * this defines the speed to changing velocity directions,
	 * which will determine the radius of the circular motion.
	 */
	private final double _theta = Math.PI / v1;
	
	/** stored cos(_theta). */
	private double cosA = Math.cos(_theta);
	
	/** stored sin(_theta). */
	private double sinA = Math.sin(_theta);

	/**
	 * update state the ball of context.
	 * @param context the ball to update strategy.
	 */
	@Override
	public void updateState(Ball context) {
		// TODO Auto-generated method stub
		int Vx = context.getVelocity().x;
		int Vy = context.getVelocity().y;
		Point vel = new Point();
		vel.x = (int) Math.round(Vx * cosA - Vy * sinA);
		vel.y = (int) Math.round(Vy * cosA + Vx * sinA);
		context.setVelocity(vel);
	}

}
