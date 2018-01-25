/**
 * 
 */
package model.paintStrategy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.ball.Ball;
import model.paint.*;
import model.shape.*;
/**
 *	A strategy that can paint a moving ghost with a big red eye
 */
public class GhostPaintStrategy extends MultiPaintStrategy {

	/**
	 * Ghost in state 1
	 */
	ShapePaintStrategy ghost1 = new ShapePaintStrategy(new Ghost1Factory().makeShape(0, 0, 1, 1));
	/**
	 * Ghost in state 2
	 */
	ShapePaintStrategy ghost2 = new ShapePaintStrategy(new Ghost2Factory().makeShape(0, 0, 1, 1));
	/**
	 * Ghost switches between state 1 and state 2
	 */
	AnimePaintStrategy ghost3 = new AnimePaintStrategy(ghost1, ghost2);
	
	/**
	 * Constructor. Combine two strategies into one.
	 */
	public GhostPaintStrategy() {
		super(new AffineTransform());
		this.addNew(ghost3);
		this.addNew(new FixedColorPaintStrategy(new EllipsePaintStrategy(1.6,1.5,0.8,0.8), Color.red));
	}
	
	/**
	 * The same as paintCfg in uprightShapePaintStrategy, keep the ghost upright
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
