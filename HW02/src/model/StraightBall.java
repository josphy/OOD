package model;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * This is a StraightBall which is a concrete of the abstract ball class. Balls goes in a straight line.
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */
public class StraightBall extends ABall {

	/**
	 * Constructor of StraightBall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball.
	 * @param color The color of a ball.
	 * @param canvas The panel for the ball word.
	 */
	public StraightBall(Point loc, int radius, Point vel, Color color, JPanel canvas) {
		super(loc, radius, vel, color, canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	void updateFormate() {
		// TODO Auto-generated method stub

	}

}
