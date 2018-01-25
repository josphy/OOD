package control;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.*;
import model.*;

/**
 * MVC Controller for the system
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */

public class Controller {

	/**
	 * model is the instance of BallModel.
	 */
	private BallModel model;

	/**
	 * model is the instance of BallFrame.
	 */
	private BallFrame view;

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
		view = new BallFrame(new IView2ModelAdapter() {

			/**
			 * paint method is the implementation of paint method in IView2ModelAdapter. It paints all the balls on the ball panel.
			 */
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				model.update(g);

			}

			/**
			 * addBall method is the implementation of addBall method in IView2ModelAdapter. It adds a ball whose type is s on the ball panel.
			 */
			@Override
			public void addBall(String s) {
				// TODO Auto-generated method stub
				model.addBall(s);
			}

			/**
			 * clearBalls method is the implementation of clearBalls method in IView2ModelAdapter. It removes all balls on the ball panel.
			 */
			@Override
			public void clearBalls() {
				// TODO Auto-generated method stub
				model.clearBalls();
			}

			/**
			 * setPanel method is the implementation of setPanel method in IView2ModelAdapter. It sets the ball panel.
			 */
			@Override
			public void setPanel(JPanel ballPanel) {
				// TODO Auto-generated method stub
				model.setPanel(ballPanel);
			}

		});

	}

	/**
	 * Start the system by starting model and view.
	 */
	public void start() {
		model.start();
		view.start(view);
	}

	/**
	 * Launch the application.
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
