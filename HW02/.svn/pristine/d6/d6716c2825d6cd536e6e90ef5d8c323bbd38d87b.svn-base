package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

import util.Randomizer;

/**
 * This is a GostBall which is a concrete of the abstract ball class. Balls change to random velocity after touching the wall.
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */

public class WanderBall extends ABall {
	/**
	 * Constructor of WanderBall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball.
	 * @param color The color of a ball.
	 * @param canvas The panel for the ball word.
	 */
	public WanderBall(Point loc, int radius, Point vel, Color color, JPanel canvas) {
		super(loc, radius, vel, color, canvas);
		// TODO Auto-generated constructor stub
	}

	/**
	 * updateFormate is override function to change a random velocity to the ball.
	 */
	@Override
	void updateFormate() {
		if (loc.x + radius >= canvas.getWidth() || loc.x - radius <= 0) {
			if (vel.x > 0) {
				vel.x = Randomizer.Singleton.randomInt(1, 40);
			} else {
				vel.x = -1 * Randomizer.Singleton.randomInt(1, 40);
			}
		}

		if (loc.y + radius >= canvas.getHeight() || loc.y - radius <= 0) {
			if (vel.x > 0) {
				vel.x = Randomizer.Singleton.randomInt(1, 40);
			} else {
				vel.x = -1 * Randomizer.Singleton.randomInt(1, 40);
			}
		}

	}

}
