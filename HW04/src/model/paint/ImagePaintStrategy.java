package model.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.MediaTracker;

import model.ball.Ball;

/**
 * Class for painting images
 */
public class ImagePaintStrategy extends APaintStrategy {

	/**
	 * The canvas panel for painting the image
	 */
	private ImageObserver imageObs;
	
	/**
	 * Image file to paint
	 */
	private Image image;
	
	/**
	 * Scale factor
	 */
	double scaleFactor = 1.0;
	/**
	 * Fill factor of the image
	 */
	double fillFactor = 1.0;
	
	/**
	 * Local affine transform to transform the image
	 */
	protected AffineTransform localAT = new AffineTransform();
	protected AffineTransform tempAT = new AffineTransform();
	
	/**
	 * Constructor, get the image by file name
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param at Affine transform to use
	 * @param filename Image file name
	 * @param fillFactor fill factor to paint the image
	 */
	public ImagePaintStrategy(AffineTransform at, String filename, double fillFactor) {
		super(at);
		this.fillFactor = fillFactor;
		try {
			image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));
		}
		catch (Exception e){
			System.err.println("ImagePaintStrategy: Error reading file: " + filename + "\n" + e);
		}
	}
	
	/**
	 * Constructor, use a new affine transform
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param filename
	 * @param fillFactor
	 */
	public ImagePaintStrategy(String filename, double fillFactor) {
		this(new AffineTransform(), filename, fillFactor);
	}

	/**
	 * Initialize the fill factor computation and the media tracker
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param host Ball object for canvas information
	 */
	public void init(Ball host) {
		imageObs = host.getCanvas();
		MediaTracker mt = new MediaTracker(host.getCanvas());
		mt.addImage(image, 1);
		try {
			mt.waitForAll();
		}
		catch(Exception e){
		  	System.out.println("ImagePaintStrategy.init(): Error waiting for image.  Exception = " + e);
		}
		scaleFactor = 2.0 / (fillFactor * (image.getWidth(imageObs) + image.getHeight(imageObs)) / 2.0);
	}
	
	/**
	 * Execute the affine transform and paint the image
	 * https://www.clear.rice.edu/comp504/f17/lectures/lec12/
	 * @param g Graphic for painting
	 * @param host Ball object for location and velocity information
	 * @param at Affine transform to use
	 */
	@Override
	public void paintXfrm(Graphics g, Ball host, AffineTransform at){
		localAT.setToScale(scaleFactor, scaleFactor);
		localAT.translate(-image.getWidth(imageObs)/2.0, -image.getHeight(imageObs)/2.0);
		localAT.preConcatenate(at);
		((Graphics2D)g).drawImage(image, localAT, imageObs); 
	}
}
