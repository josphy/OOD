package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

import util.Randomizer;

/**
 * This is a BreathingBall which is a concrete of the abstract ball class. Balls change color while moving.
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */
public class ColorChangingBall extends ABall {

	/**
	 * Constructor of ColorChangingBall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball
	 * @param color The color of a ball
	 * @param canvas The panel for the ball word
	 */
	public ColorChangingBall(Point loc, int radius, Point vel, Color color, JPanel canvas) {
		super(loc, radius, vel, color, canvas);
		// TODO Auto-generated constructor stub
	}

	/**
	 * updateFormate is override function to change a random color to the ball.
	 */
	@Override
	void updateFormate() {
		this.color = Randomizer.Singleton.randomColor();
	}

}
