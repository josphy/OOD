package model.strategy;

import model.ball.Ball;

// TODO: Auto-generated Javadoc
/**
 * switcher strategy class: able to update strategy after ball generated.
 */
public class SwitcherStrategy implements IUpdateStrategy {

	/**
	 * the strategy for a switcherStrategy, which can be later changed tpo other strategies.
	 * the default is a straight strategy.
	 */
	private IUpdateStrategy _strategy = new StraightStrategy();

	/**
	 * update state the ball of context.
	 *
	 * @param context the context
	 */
	@Override
	public void updateState(Ball context) {
		_strategy.updateState(context);
	}

	/**
	 * set a new strategy.
	 * @param newStrategy a new strategy.
	 */
	public void setStrategy(IUpdateStrategy newStrategy) {
		_strategy = newStrategy;
	}

}
