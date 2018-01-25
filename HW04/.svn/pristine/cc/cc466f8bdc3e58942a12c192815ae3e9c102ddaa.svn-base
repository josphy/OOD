package model.paintStrategy;

import model.paint.ImagePaintStrategy;

/**
 * Paint strategy that upon instantiation, randomly picks one image from an array of images to use.
 */
public class PlanetImagePaintStrategy extends ImagePaintStrategy{
	
	/**
	 * Image array holding planet image file paths
	 */
	private static String[] IMAGES = {"../images/Venus.png", "../images/Earth.png", "../images/Mars.png",
			"../images/Jupiter.png", "../images/Saturn.png"};

	/**
	 * Randomly pick one of the image files to use when being constructed.
	 */
	public PlanetImagePaintStrategy() {
		super(IMAGES[(int) Math.floor(Math.random()*5)], 1.0);
	}

}
