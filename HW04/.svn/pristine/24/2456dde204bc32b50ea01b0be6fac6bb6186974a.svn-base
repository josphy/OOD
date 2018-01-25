package model.paint;
import model.ball.*;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

/**
 * The abstract paint strategy class
 * Enables painting and affine transforms
 */
public abstract class APaintStrategy implements IPaintStrategy {
	
	/**
	 * The affine transform object to use
	 */
	protected AffineTransform affTrans;
	
	/**
	 * Constructor.
	 * @param at The initial affine transform.
	 */
	public APaintStrategy(AffineTransform at) {
		affTrans = at;
	}
	
	/**
	 * Empty constructor.
	 * Start with a new affine transform.
	 */
	public APaintStrategy() {
		affTrans = new AffineTransform();
	}
	
	/**
	 * Initialize the transformation, if needed
	 */
	public void init(Ball host) {
	}
	
	/**
	 * Paint the affine transformed shape, according to its parameters
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param g The graphic for painting
	 * @param host Host ball object for all its position and velocity information
	 */
	public void paint(Graphics g, Ball host) {
		  double scale = host.getRadius();
		  affTrans.setToTranslation(host.getLocation().x, host.getLocation().y);
		  affTrans.scale(scale, scale);
		  affTrans.rotate(host.getVelocity().x, host.getVelocity().y);
		  g.setColor(host.getColor());    
		  paintCfg(g, host);
		  paintXfrm(g, host, affTrans);
		}
	
	/**
	 * For override. 
	 * This method allows the subclass to inject additional processing into the paint method process before the final transformations are performed.
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param g The graphic for painting
	 * @param host Host ball object for all its position and velocity information
	 */
	protected void paintCfg(Graphics g, Ball host) {
	}
	
	/**
	 * Use a given affine transform to perform the transformation
	 * @param g The graphic for painting
	 * @param host Host ball object for all its position and velocity information
	 * @param at Affine transform to use
	 */
	public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at);

	/**
	 * Getters of affine transform
	 * @return Current affine transform
	 */
	protected AffineTransform getAffTrans() {
		return affTrans;
	}
	
}
