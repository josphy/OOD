/**
 * 
 */
package model.paint;

import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Graphics;
import model.ball.*;
/**
 *	Allow users to set a fixed color to a paint strategy
 */
public class FixedColorPaintStrategy extends ADecoratorPaintStrategy {

	/**
	 * The fixed color to use
	 */
	private Color color;
	
	/**
	 * Constructor
	 * @param at Affine Transform
	 * @param originalStrategy The strategy to decorate
	 * @param color Color to use
	 */
	public FixedColorPaintStrategy(AffineTransform at, APaintStrategy originalStrategy, Color color) {
		super(at, originalStrategy);
		// TODO Auto-generated constructor stub
		this.color = color;
	}

	/**
	 * Constructor
	 * @param originalStrategy The strategy to decorate
	 * @param color Color to use
	 */
	public FixedColorPaintStrategy(APaintStrategy originalStrategy, Color color) {
		super(originalStrategy);
		// TODO Auto-generated constructor stub
		this.color = color;
	}
	
	/**
	 * Set the graphic color, and then paint normally
	 */
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		g.setColor(color);
		super.paintXfrm(g, host, at);
	}

}
