/**
 * 
 */
package model.paint;

import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import model.ball.*;

/**
 * To keep an image upright with this class
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * Constructor, the same as the super class
	 * @param at
	 * @param filename
	 * @param fillFactor
	 */
	
	public UprightImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at, filename, fillFactor);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor, the same as the super class
	 * @param filename
	 * @param fillFactor
	 */
	
	public UprightImagePaintStrategy(String filename, double fillFactor) {
		super(filename, fillFactor);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calculate the angle to add a scaling correction
	 * First call super class's paintCfg to retain its functionality
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 */
	protected void paintCfg(Graphics g, Ball host) {
		super.paintCfg(g, host);
		if(Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x))> Math.PI/2.0) {
			getAffTrans().scale(1.0, -1.0);
		}        
	}

}
