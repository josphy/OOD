package shape;

import java.awt.Graphics;

/**
*  Abstract super class for all kinds of shape can be painted in Graphics
 *  @author Yining Bao
 *  @author Haoshan Zou */
public abstract class AShape {
	protected int x_coordinate = 0;
	protected int y_coordinate = 0;

	/**
	*  set the x coordinate of the shape
	 * 	 *  @param x x coordinate */
	public void setx(int x) {
		x_coordinate = x;
	}

	/**
	*  set the y coordinate of the shape
	 * 	 *  @param y y coordinate */
	public void sety(int y) {
		y_coordinate = y;
	}

	/**
	*  get the x coordinate of the shape
	 * 	 *  @return x coordinate of the shape */
	public int getx() {
		return x_coordinate;
	}

	/**
	*  get the y coordinate of the shape
	 * 	 *  @return y coordinate of the shape */
	public int gety() {
		return y_coordinate;
	}

	/**
	* abstract method to paint the shape in the given graphics
	 * 	 *  @param g The Graphics object to paint on. */
	public abstract void paint(Graphics g);

}
