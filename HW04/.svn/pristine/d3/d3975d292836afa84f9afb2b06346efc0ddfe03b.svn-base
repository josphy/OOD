package model.strategy;

import model.ball.Ball;

// TODO: Auto-generated Javadoc
/**
 * multiple strategies: enables dynamically combined strategies.
 */
public class MultiStrategy implements IUpdateStrategy {

	/** strategy 1. */
	private IUpdateStrategy _s1;
	
	/** strategy 2. */
	private IUpdateStrategy _s2;

	/**
	 * constructor.
	 * @param s1 strategy1
	 * @param s2 strategy2
	 */
	public MultiStrategy(IUpdateStrategy s1, IUpdateStrategy s2) {
		_s1 = s1;
		_s2 = s2;
	}

	/**
	 * update state the ball of context.
	 *
	 * @param context the context
	 */
	@Override
	public void updateState(Ball context) {
		_s1.updateState(context);
		_s2.updateState(context);
	}

}
