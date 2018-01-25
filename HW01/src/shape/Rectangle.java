package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
* this is a concrete sub-class of AShape whose paint() method draws a specific Rectangle
 *  @author Yining Bao
 *  @author Haoshan Zou */
public class Rectangle extends AShape {
	private int width = 10;
	private int height = 10;
	private Color myColor = Color.RED;

	/**
	* Constructor of rectangle shape
	 * 	 * @param x the x coordinate of the rectangle to be filled.
	 * 	 * @param y the y coordinate of the rectangle to be filled.
	 * 	 * @param width the width of the rectangle to be filled.
	 * 	 * @param height the height of the rectangle to be filled
	 * 	 * @param myColor the color of the rectangle */
	public Rectangle(int x, int y, int width, int height, Color myColor) {
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.width = width;
		this.height = height;
		this.myColor = myColor;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(myColor);
		g.fillRect(this.x_coordinate, this.y_coordinate, this.width, this.height);

	}
}
