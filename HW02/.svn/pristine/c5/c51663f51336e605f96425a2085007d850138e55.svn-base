package view;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This is an interface of view to model adapter
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */

public interface IView2ModelAdapter {

	/**
	 * paint method uses graphic g to paint balls.
	 * @param g The Graphics object to paint on.
	 */
	public void paint(Graphics g);

	/**
	 * addBall method adds a ball to the panel.
	 * @param s The type of the ball.
	 */
	public void addBall(String s);

	/**
	 *  clearBalls method clears all balls on the panel.
	 */
	public void clearBalls();

	/**
	 * setPanel method provides the ball panel to the model.
	 * @param ballPanel The panel to show the animation of balls.
	 */
	public void setPanel(JPanel ballPanel);

	/**
	 * No-op singleton implementation of IView2ModelAdapter 
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IView2ModelAdapter NULL_OBJECT = new IView2ModelAdapter() {
		public void paint(Graphics g) {
		}

		@Override
		public void addBall(String s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void clearBalls() {
			// TODO Auto-generated method stub

		}

		@Override
		public void setPanel(JPanel ballPanel) {
			// TODO Auto-generated method stub

		}
	};

}
