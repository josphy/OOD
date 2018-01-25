package model.paintStrategy;

import java.awt.Graphics;

import model.ball.Ball;
import model.paint.IPaintStrategy;

/**
 * Paint strategy that paints a filled square with the Ball's radius. Does not utilize AffineTransform.
 * Copied from https://www.clear.rice.edu/comp310/f17/lectures/lec12/
 */
public class SquarePaintStrategy implements IPaintStrategy {

	/**
	 * No parameter constructor for the class
	 */
	public SquarePaintStrategy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * By default, do nothing for initialization.
	 * @param host - The ball to initialize.
	 */
	@Override
	public void init(Ball host) {
		// TODO Auto-generated method stub
	}

	/**
	 * Paints a square on the given graphics context using the color and radius provided by the host. 
	 * @param g - The graphics context to draw upon.
	 * @param host - The host ball.
	 */
	@Override
	public void paint(Graphics g, Ball host) {
		g.setColor(host.getColor());
		g.fillRect(host.getLocation().x - host.getRadius(), host.getLocation().y - host.getRadius(), 2*host.getRadius(), 2*host.getRadius());
	}

}
