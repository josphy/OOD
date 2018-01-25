package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
* this is a concrete sub-class of AShape whose paint() method draws a specific Circle
 *  @author Yining Bao
 *  @author Haoshan Zou */
public class Circle extends AShape {
	private int radius = 5;
	private Color myColor = Color.red;

	/**
	* Constructor of circle shape
	 * 	 * @param x the x coordinate of the upper left corner of the circle to be filled.
	 * 	 * @param y the y coordinate of the upper left corner of the circle to be filled.
	 * 	 * @param radius the radius of the circle
	 * 	 * @param myColor the color of the circle */
	public Circle(int x, int y, int radius, Color myColor) {
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.radius = radius;
		this.myColor = myColor;
	}

	/**
	* Overridden method to paint the Circle in the given graphics
	 * 	 *  @param g The Graphics object to paint on. */
	public void paint(Graphics g) {
		g.setColor(myColor);
		g.fillOval(x_coordinate, y_coordinate, radius * 2, radius * 2);
	}

}
