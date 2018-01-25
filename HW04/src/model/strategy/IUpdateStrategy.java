package model.strategy;

import model.ball.Ball;

// TODO: Auto-generated Javadoc
/**
 * update strategy interface.
 *
 */
public interface IUpdateStrategy {

	/**
	 * update state the ball of context.
	 * @param context the ball to update strategy.
	 */
	public abstract void updateState(Ball context);

}
