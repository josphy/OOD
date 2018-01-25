package model;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Timer;
import util.Dispatcher;
import util.Randomizer;

/**
 * This is a Ball Model class
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */
public class BallModel {

	/**
	 * _model2ViewAdpt is the instance of model to view adapter. This insures that the adapter is always valid.
	 */
	private IModel2ViewAdapter _model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;

	/**
	 * Constructor of BallModel.
	 * @param model2ViewAdpt An instance of model to view adaptor.
	 */
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdpt = model2ViewAdpt;
	}

	/**
	 * _timeSlice is the time interval for calling update method of model to view adapter.
	 */
	private int _timeSlice = 50; // update every 50 milliseconds

	/**
	 * _timer is the timer which calling update method of model to view adapter every _timeSlice.
	 */
	private Timer _timer = new Timer(_timeSlice, (e) -> _model2ViewAdpt.update());

	/**
	 * start method starts the timer.
	 */
	public void start() {
		_timer.start();
	}

	/**
	 * myDispatcher is the instance of the dispatcher.
	 */
	private Dispatcher myDispatcher = new Dispatcher();

	/**
	 * This is the method that is called by the view's adapter to the model, i.e. is called by IView2ModelAdapter.paint().
	 * This method will update the sprites's painted locations by painting all the sprites
	 * onto the given Graphics object.
	 * @param g The Graphics object from the view's paintComponent() call.
	 */
	public void update(Graphics g) {
		myDispatcher.notifyAll(g); // The Graphics object is being given to all the sprites (Observers)
	}

	/**
	* The following method returns an instance of an ABall, given a fully qualified class name (package.classname) of
	* a subclass of ABall.
	* The method assumes that there is only one constructor for the supplied class that has the same *number* of
	* input parameters as specified in the args array and that it conforms to a specific
	* signature, i.e. specific order and types of input parameters in the args array.
	* @param className A string that is the fully qualified class name of the desired object
	* @return An instance of the supplied class. 
	*/
	private ABall loadBall(String className) { // YOUR CODE MAY HAVE MORE/DIFFERENT INPUT PARAMETERS!

		try {
			//random velocity
			Rectangle velRec = new Rectangle(0, 0, 10, 10);
			//random location
			Rectangle locRec = new Rectangle(0, 0, ballPanel.getWidth(), ballPanel.getHeight());
			Object[] args = new Object[] { Randomizer.Singleton.randomLoc(locRec),
					Randomizer.Singleton.randomInt(10, 20), Randomizer.Singleton.randomVel(velRec),
					Randomizer.Singleton.randomColor(), ballPanel }; // YOUR CONSTRUCTOR MAY BE DIFFERENT!!   The supplied values here may be fields, input parameters, random values, etc.
			java.lang.reflect.Constructor<?> cs[] = Class.forName(className).getConstructors(); // get all the constructors
			java.lang.reflect.Constructor<?> c = null;
			for (int i = 0; i < cs.length; i++) { // find the first constructor with the right number of input parameters
				if (args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (ABall) c.newInstance(args); // Call the constructor.   Will throw a null ptr exception if no constructor with the right number of input parameters was found.
		} catch (Exception ex) {
			System.err.println("Class " + className + " failed to load. \nException = \n" + ex);
			ex.printStackTrace(); // print the stack trace to help in debugging.
			return null; // Is this really a useful thing to return here?  Is there something better that could be returned? 
		}

	}

	/**
	 * ballPanel is the ball panel to draw balls.
	 */
	private JPanel ballPanel;

	/**
	 * setPanel method sets the ball panel.
	 * @param ballPanel The panel to show the animation of balls.
	 */
	public void setPanel(JPanel ballPanel) {
		// TODO Auto-generated method stub
		this.ballPanel = ballPanel;
	}

	/**
	 * addBall method adds a ball to the panel.
	 * @param s The type of the ball.
	 */
	public void addBall(String s) {
		myDispatcher.addObserver(loadBall(s));
	}

	/**
	 *  clearBalls method clears all balls on the panel.
	 */
	public void clearBalls() {
		myDispatcher.deleteObservers();
	}

}
