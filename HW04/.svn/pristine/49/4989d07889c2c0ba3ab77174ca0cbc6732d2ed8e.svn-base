package model.strategy;

import model.ball.Ball;
import util.Randomizer;
import util.SineMaker;

// TODO: Auto-generated Javadoc
/**
 * breathing strategy: ball sinusoidally changes radius.
 */
public class BreathingStrategy implements IUpdateStrategy {

	/**
	 * v1 is a random value for sine function.
	 */
	private int v1 = Randomizer.Singleton.randomInt(1, 40);

	/**
	 * v2 is a random value for sine function.
	 */
	private int v2 = Randomizer.Singleton.randomInt(1, 40);

	/**
	 * max is the larger number between v1 and v2.
	 */
	private int max = v1 > v2 ? v1 : v2;

	/**
	 * min is the smaller number between v1 and v2.
	 */
	private int min = v1 > v2 ? v2 : v1;

	/**
	 * sine is an instance of SineMaker.
	 */
	private SineMaker sine = new SineMaker(min, max, 20);

	/**
	 * update ball with breathing.
	 *
	 * @param context the context
	 */
	@Override
	public void updateState(Ball context) {
		context.setRadius(sine.getIntVal());
	}

}
