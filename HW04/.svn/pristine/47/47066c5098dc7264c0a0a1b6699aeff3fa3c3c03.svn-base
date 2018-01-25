package model.paint;
import model.ball.*;
import java.awt.Graphics;

/**
 * Paint Strategy interface
 */
public interface IPaintStrategy {
	/**
	 * Initialize the strategy and the host ball, and other necessary computation
	 */
	public void init(Ball host);
	
	/**
	 * The actual paint operation
	 * @param g Paint the host ball to graphic g
	 * @param host Host ball for location, velocity, canvas etc.
	 */
	public void paint(Graphics g, Ball host);
	
	/**
	 * No-op singleton
	 */
	public static final IPaintStrategy NULL_OBJECT = new IPaintStrategy() {
		public void init(Ball host) {
		}
		public void paint(Graphics g, Ball host) {
		}
	};
	
}
