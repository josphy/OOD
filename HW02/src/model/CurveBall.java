package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

import util.Randomizer;

/**
 * This is a GostBall which is a concrete of the abstract ball class. Balls move follows a curve.
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */

public class CurveBall extends ABall {
	/**
	 * v1 is a random angle for new velocity calculation.
	 */
	private int v1 = Randomizer.Singleton.randomInt(10, 20);

	/**
	 * Constructor of CurveBall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball.
	 * @param color The color of a ball.
	 * @param canvas The panel for the ball word.
	 */
	public CurveBall(Point loc, int radius, Point vel, Color color, JPanel canvas) {
		super(loc, radius, vel, color, canvas);
		// TODO Auto-generated constructor stub
	}

	/**
	 * updateFormate is override function to change a random velocity to the ball.
	 */
	@Override
	void updateFormate() {
		int Vx = vel.x;
		int Vy = vel.y;
		vel.x = (int) Math.round((Math.cos(Math.PI / v1) * Vx - Math.sin(Math.PI / v1) * Vy));
		vel.y = (int) Math.round(Math.cos(Math.PI / v1) * Vy + Math.sin(Math.PI / v1) * Vx);
	}

}
