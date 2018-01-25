/**
 * 
 */
package model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import model.ball.Ball;

/**
 *	The paint strategy for painting animations, use a different paint strategy each time
 */
public class AnimePaintStrategy extends APaintStrategy {

	/**
	 * The array list to store possible paint strategies
	 */
	private ArrayList<APaintStrategy> pstrats = new ArrayList<APaintStrategy>();
	/**
	 * A counter, to count which strategy to use
	 */
	private int counter = 0;	
	
	/**
	 * Constructor. Using Vararg for all strategies to use
	 * @param at Affine Transform to use
	 * @param inputStrats All strategies to use
	 */
	public AnimePaintStrategy(AffineTransform at, APaintStrategy... inputStrats) {
		super(at);
		// TODO Auto-generated constructor stub
		for (APaintStrategy inputStrat: inputStrats) {
			pstrats.add(inputStrat);
		}
	}

	/**
	 * Constructor. Using Vararg for all strategies to use
	 * @param inputStrats All strategies to use
	 */
	public AnimePaintStrategy(APaintStrategy... inputStrats) {
		// TODO Auto-generated constructor stub
		super(new AffineTransform());
		for (APaintStrategy inputStrat: inputStrats) {
			pstrats.add(inputStrat);
		}
	}

	/* (non-Javadoc)
	 * @see model.paint.APaintStrategy#paintXfrm(java.awt.Graphics, model.ball.Ball, java.awt.geom.AffineTransform)
	 */
	/**
	 * Each 10 time steps change to a different strategy, and then update the counter
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at) {
		// TODO Auto-generated method stub
		
		if(counter / 10 >= pstrats.size()) {
			counter -= pstrats.size() * 10;
		}
		pstrats.get(counter / 10).paintXfrm(g, host, at);
		counter++;

	}

}