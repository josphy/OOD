package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

import util.Randomizer;
import util.SineMaker;

/**
 * This is a BreathingBall which is a concrete of the abstract ball class. Balls change radius while moving.
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-17
 */

public class BreathingBall extends ABall {

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
	 * Constructor of BreathingBall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball.
	 * @param color The color of a ball.
	 * @param canvas The panel for the ball word.
	 */
	public BreathingBall(Point loc, int radius, Point vel, Color color, JPanel canvas) {
		super(loc, radius, vel, color, canvas);
		// TODO Auto-generated constructor stub
	}

	/**
	 * updateFormate is override function to change a radius according to sine function to the ball.
	 */
	@Override
	void updateFormate() {
		// TODO Auto-generated method stub
		this.radius = sine.getIntVal();
	}

}
