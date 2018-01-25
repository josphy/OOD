package model.paintStrategy;

import java.awt.geom.AffineTransform;

import model.paint.AnimePaintStrategy;

/**
 * AnimePaintStrategy, display Fish1PaintStrategy and Fish2PaintStrategy in turns, so it looks like swimming.
 */
public class SwimFishPaintStrategy extends AnimePaintStrategy{

	/**
	 * No-parameter constructor that uses a new affine transform
	 * Initialize with two different paint strategy
	 */
	public SwimFishPaintStrategy() {
		super(new AffineTransform(), new Fish1PaintStrategy(), new Fish2PaintStrategy());
	}
	
	/**
	 * Constructor that use a given affine transform
	 * Initialize with two different paint strategy
	 * @param at - The AffineTransform to use
	 */
	public SwimFishPaintStrategy(AffineTransform at) {
		super(at, new Fish1PaintStrategy(), new Fish2PaintStrategy());
	}

}
