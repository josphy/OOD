package control;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.*;
import model.*;
import model.strategy.*;
import model.paint.*;

/**
 * MVC Controller for the system.
 *
 * @version 1.0
 * @since 2017-09-17
 */
public class Controller {

	/**
	 * model is the instance of BallModel.
	 */
	private BallModel model;

	/**
	 * model is the instance of BallFrame.
	 */
	private BallFrame<IStrategyFac, IPaintStrategyFac> view;

	/**
	 * Controller constructor builds the system.
	 */
	public Controller() {
		
		/**
		 * Use model to view adapter to repaint the ball panel.
		 */
		model = new BallModel(new IModel2ViewAdapter() {

			/**
			 * update method is the implementation of update method in IModel2ViewAdapter interface. This method repaints the ball panel.
			 */
			@Override
			public void update() {
				// TODO Auto-generated method stub
				view.update();
			}
		});

		/**
		 * Use view to draw the ball on the view side.
		 */
		view = new BallFrame<IStrategyFac, IPaintStrategyFac>(new IModelControlAdapter<IStrategyFac, IPaintStrategyFac>() {

			@Override
			/**
			 * Returns an IStrategyFac that can instantiate the strategy specified
			 * by classname. Returns null if classname is null. The toString() of
			 * the returned factory is the classname.
			 * @param classname - Shortened name of desired strategy 
			 * @return A factory to make that strategy
			 */
			public IStrategyFac addStrategy(String classname) {
				return model.makeStrategyFac(classname);
			}

			@Override
			/**
			 * Add a ball to the system with strategy and paintStrategy as given by IStrategyFac and IPaintStrategyFac
			 * @param selectedItem - The fully qualified name of the desired strategy.
			 * @param selectedPaint - The fully qualified name of the desired paint strategy.
			 */
			public void makeBall(IStrategyFac selectedItem, IPaintStrategyFac selectedPaint) {
				if (null != selectedItem && null != selectedPaint)
					model.loadBall(selectedItem.make(), selectedPaint.make()); 	          	  
			}

			@Override
			/**
			 * Add a switcher ball to the system with a given paint strategy
			 * @param selectedPaint - The fully qualified name of the desired paint strategy.
			 */
			public void makeSwitcherBall(IPaintStrategyFac selectedPaint) {
				model.loadBall(model.getSwitcherStrategy(), selectedPaint.make()); 		          	  
			}

			@Override
			/**
			 * Returns an IStrategyFac that can instantiate a MultiStrategy with the
			 * two strategies made by the two given IStrategyFac objects. Returns
			 * null if either supplied factory is null. The toString() of the
			 * returned factory is the toString()'s of the two given factories,
			 * concatenated with "-".             * 
			 * @param selectedItem1 An IStrategyFac for a strategy
			 * @param selectedItem2 An IStrategyFac for a strategy
			 * @return An IStrategyFac for the composition of the two strategies
			 */
			public IStrategyFac combineStrategies(IStrategyFac selectedItem1, IStrategyFac selectedItem2) {
				return model.combineStrategyFacs(selectedItem1, selectedItem2);
			}

			@Override
			/**
			 * Clear all existing balls
			 */
			public void clearBalls() {
				model.clearBalls();
			}

			@Override
			/**
			 * Set drawing panel
			 * @param ballPanel - panel for drawing ball
			 */
			public void setPanel(JPanel ballPanel) {
				model.setPanel(ballPanel);
			}

			@Override
			/**
			 * Switch all switcher balls' strategy to a given strategy
			 * @param selectedItem - the strategy to switch to
			 */
			public void switchStrategy(IStrategyFac selectedItem) {
				model.switchSwitcherStrategy(selectedItem.make());
			}

			@Override
			/**
			 * Returns an IPaintStrategyFac that can instantiate the paint strategy specified
			 * by paintname. 
			 * @param paintname - Shortened name of desired paint strategy 
			 * @return A factory to make that paint strategy
			 */
			public IPaintStrategyFac addPntStrategy(String paintName) {
				return model.makePaintStrategy(paintName);
			}
			
		}, new IModelUpdateAdapter() {
			/**
			 * Pass the update request to the model.
			 * @param g The Graphics object the balls use to draw themselves.
			 */
			public void update(Graphics g) {
				model.update(g);
			}
		});

	}

	/**
	 * Start the system by starting model and view.
	 */
	public void start() {
		model.start();
		view.start();
	}

	/**
	 * Launch the application.
	 * @param args input parameter
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
