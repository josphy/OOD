package shape;

import java.awt.Color;
import java.awt.Graphics;

/**
* this is a concrete sub-class of AShape whose paint() method draws a specific Arc
 *  @author Yining Bao
 *  @author Haoshan Zou */
public class Arc extends AShape {
	private int width = 10;
	private int height = 10;
	private int startAngle = 0;
	private int arcAngle = 45;
	/*package*/ Color myColor = Color.RED;

	/**
	* Constructor of arc shape
	 * 	 * @param x the x coordinate of the upper-left corner of the arc to be filled.
	 * 	 * @param y the y coordinate of the upper-left corner of the arc to be filled.
	 * 	 * @param width the width of the arc to be filled.
	 * 	 * @param height the height of the arc to be filled.
	 * 	 * @param startAngle  the beginning angle.
	 * 	 * @param arcAngle  the angular extent of the arc, relative to the start angle.
	 * 	 * @param myColor the color of the arc */
	public Arc(int x, int y, int width, int height, int startAngle, int arcAngle, Color myColor) {
		this.x_coordinate = x;
		this.y_coordinate = y;
		this.width = width;
		this.height = height;
		this.arcAngle = arcAngle;
		this.startAngle = startAngle;
		this.myColor = myColor;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(myColor);
		g.fillArc(this.x_coordinate, this.y_coordinate, this.width, this.height, this.startAngle, this.arcAngle);

	}
}
