package model.paintStrategy;

import java.awt.Graphics;

import model.ball.Ball;
import model.paint.IPaintStrategy;

/**
 * Paint strategy that paints a ball directly, without affine transform.
 */
public class BallPaintStrategy implements IPaintStrategy {

	/**
	 * No parameter constructor for the class
	 */
	public BallPaintStrategy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do nothing for initialization.
	 * @param host - The ball to initialize.
	 */
	@Override
	public void init(Ball host) {
		// TODO Auto-generated method stub
	}

	/**
	 * Paints a ball with the host ball's information
	 * @param g - The graphics to draw upon.
	 * @param host - The host ball.
	 */
	@Override
	public void paint(Graphics g, Ball host) {
		g.setColor(host.getColor());
		g.fillOval(host.getLocation().x - host.getRadius(), host.getLocation().y - host.getRadius(), 2 * host.getRadius(), 2 * host.getRadius());
	}

}

