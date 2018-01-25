/**
 * 
 */
package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import model.ball.Ball;

/**
 * Combine multiple paint strategies into one paint strategy
 */
public class MultiPaintStrategy extends APaintStrategy {

	/**
	 * All available paint strategies.
	 * Use an ArrayList to hold them so that we can easily add new strategies
	 */
	private ArrayList<APaintStrategy> pstrats = new ArrayList<APaintStrategy>();
	
	/**
	 * Constructor, combine several input strategies into one
	 * @param at Affine Transform to use
	 * @param inputStrats Vararg for arbitrary number of input strategies
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... inputStrats) {
		super(at);
		for (APaintStrategy inputStrat: inputStrats) {
			pstrats.add(inputStrat);
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor, combine several input strategies into one
	 * @param inputStrats Vararg for arbitrary number of input strategies
	 */
	public MultiPaintStrategy(APaintStrategy... inputStrats) {
		super(new AffineTransform());
		for (APaintStrategy inputStrat: inputStrats) {
			pstrats.add(inputStrat);
		}
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initialize all strategies in the multi-strategy class
	 * @param host Host ball object
	 */
	public void init(Ball host) {
		for (APaintStrategy child: pstrats) {
			child.init(host);
		}
	}
	
	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.ball.Ball, java.awt.geom.AffineTransform)
	 */
	/**
	 * Paint all children's paint strategy one by one
	 * @param g Graphics to paint
	 * @param host Host ball object for velocity and location information
	 * @param at Affine Transform to use
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		for (APaintStrategy child: pstrats) {
			child.paintXfrm(g, host, at);
		}
	}

	/**
	 * Add a new strategy to the multi-strategy class
	 * @param newStrat The strategy to add
	 */
	public void addNew(APaintStrategy newStrat) {
		this.pstrats.add(newStrat);
	}
}
