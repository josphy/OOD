package model.ball;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import model.strategy.IUpdateStrategy;

// TODO: Auto-generated Javadoc
/**
 * This is a concrete ball class.
 *
 * @author Haoshan Zou, Li Yang
 * @version 1.0
 * @since 2017-09-17
 */

public class Ball implements Observer {
	/**
	 * loc is the center point of a ball.
	 */
	protected Point loc;

	/**
	 * radius is the radius of a ball.
	 */
	protected int radius;

	/**
	 * vel is the velocity of a ball which include the x-axis velocity and y-axis velocity.
	 */
	protected Point vel;

	/**
	 * color is the color of a ball.
	 */
	protected Color color;

	/**
	 * canvas is the panel for the ball word.
	 */
	protected Component canvas;

	/**
	 * the update strategy.
	 */
	protected IUpdateStrategy strategy;

	/**
	 * Constructor of ABall.
	 * @param loc The center point of a ball.
	 * @param radius The radius of a ball.
	 * @param vel The velocity of a ball
	 * @param color The color of a ball
	 * @param canvas The panel for the ball word
	 * @param strategy the ball update strategy.
	 */
	public Ball(Point loc, int radius, Point vel, Color color, Component canvas, IUpdateStrategy strategy) {
		this.loc = loc;
		this.radius = radius;
		this.vel = vel;
		this.color = color;
		this.canvas = canvas;
		this.strategy = strategy;
	}

	/**
	 * paint to paint a ball in the panel.
	 * @param g The Graphics object to paint on.
	 */
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(loc.x - radius, loc.y - radius, 2 * radius, 2 * radius);

	}

	/**
	 * Bound method sets the location and the velocity of the ball when it tough the bound of the ball panel.
	 */
	private void bounce() {

		// check if hit left wall
		if (loc.x - radius < 0) {
			vel.x = -vel.x;
			loc.x = 2 * radius - loc.x;
		}
		// check if hit top wall
		if (loc.y - radius < 0) {
			loc.y = 2 * radius - loc.y;
			vel.y = -vel.y;
		}
		// check if hit right wall;
		if (canvas.getWidth() - loc.x - radius < 0) {
			loc.x = 2 * (canvas.getWidth() - radius) - loc.x;
			vel.x = -vel.x;
		}
		// check if hit bottom wall;
		if (canvas.getHeight() - loc.y - radius < 0) {
			loc.y = 2 * (canvas.getHeight() - radius) - loc.y;
			vel.y = -vel.y;
		}
	}

	/**
	 * move method moves ball's location under velocity.
	 */
	private void move() {
		loc.translate(vel.x, vel.y);
	}

	/**
	 * update method overrides of update() of Observer.
	 * @param o the observable
	 * @param g the object to paint on
	 */
	public void update(Observable o, Object g) {
		strategy.updateState(this);
		move();
		bounce();
		paint((Graphics) g);
	}

	/**
	 * get ball update strategy.
	 * @return current ball update strategy
	 */
	public IUpdateStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * set ball update strategy.
	 * @param strategy new ball update strategy.
	 */
	public void setStrategy(IUpdateStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * get ball center location.
	 * @return current ball center location.
	 */
	public Point getLocation() {
		return loc;
	}

	/**
	 * set ball center location.
	 * @param loc new ball center location.
	 */
	public void setLocation(Point loc) {
		this.loc = loc;
	}

	/**
	 * get ball velocity.
	 * @return current ball velocity.
	 */
	public Point getVelocity() {
		return this.vel;
	}

	/**
	 * set ball velocity.
	 * @param vel new ball velocity.
	 */
	public void setVelocity(Point vel) {
		this.vel = vel;
	}

	/**
	 * get ball radius.
	 * @return current ball radius.
	 */
	public int getRadius() {
		return this.radius;
	}

	/**
	 * set ball radius.
	 * @param radius new ball radius.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * get ball color.
	 *
	 * @return current ball color.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * set ball color.
	 * @param color new ball color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
