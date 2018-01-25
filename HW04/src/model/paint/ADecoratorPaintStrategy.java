/**
 * 
 */
package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;

/**
 * This decorator class allows its subclasses to add extra features to the decorees
 * Simply delegate to the decorees
 */
public abstract class ADecoratorPaintStrategy extends APaintStrategy {

	/**
	 * The strategy to deal with
	 */
	private APaintStrategy originalStrategy;
	/**
	 * Constructor, build a decorator from another paint strategy
	 * @param at The affine transform object
	 * @param originalStrategy The strategy to decorate
	 */
	public ADecoratorPaintStrategy(AffineTransform at, APaintStrategy originalStrategy) {
		super(at);
		// TODO Auto-generated constructor stub
		this.originalStrategy = originalStrategy;
	}

	/**
	 * Constructor, build a decorator from another paint strategy
	 * @param originalStrategy The strategy to decorate
	 */
	public ADecoratorPaintStrategy(APaintStrategy originalStrategy) {
		// TODO Auto-generated constructor stub
		super(new AffineTransform());
		this.originalStrategy = originalStrategy;
	}	
	
	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.ball.Ball, java.awt.geom.AffineTransform)
	 */

	@Override
	/**
	 * Simply delegate to the decoree
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		originalStrategy.paintXfrm(g, host, at);
	}
	
	/**
	 * Simply delegate to the decoree
	 */
	public void init(Ball host) {
		originalStrategy.init(host);
	}
	
	/**
	 * Simply delegate to the decoree
	 */
	public void paint(Graphics g, Ball host) {
		originalStrategy.paint(g, host);
	}

}
