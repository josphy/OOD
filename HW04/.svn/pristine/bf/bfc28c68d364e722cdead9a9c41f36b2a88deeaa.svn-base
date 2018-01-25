package model.paintStrategy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;
import model.paint.FixedColorPaintStrategy;
import model.paint.MultiPaintStrategy;

/**
 * Combine two strategies: SwimFish and FixedColor, to generate a nicer fish
 */
public class NiceFishPaintStrategy extends MultiPaintStrategy{

	/**
	 * No-parameter constructor, initialize a fixed color paint strategy
	 */
	public NiceFishPaintStrategy() {
		super(new AffineTransform());
		this.addNew(new SwimFishPaintStrategy());
		this.addNew(new FixedColorPaintStrategy(new EllipsePaintStrategy(2.2,1.0,0.4,0.4), Color.red));
	}
	
	/**
	 * Initialize a fixed color paint strategy to paint the eyes on the fish
	 * @param at - The AffineTransform to use.
	 */
	public NiceFishPaintStrategy(AffineTransform at) {
		super(at);
		this.addNew(new SwimFishPaintStrategy());
		this.addNew(new FixedColorPaintStrategy(new EllipsePaintStrategy(2.2,1.0,0.4,0.4), Color.red));
	}

	/**
	 * The same as paintCfg in uprightShapePaintStrategy, keep the fish upright
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 */
	@Override
	protected void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if(Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x))> Math.PI/2.0) {
			getAffTrans().scale(1.0, -1.0);
		}        		
	}
}
