package view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is a GUI to run  Ball World
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */
public class BallFrame extends JFrame {

	/**
	 * serialVersionUID is the serial version UID.
	 */
	private static final long serialVersionUID = -8987134431120800637L;

	/**
	 * contentPane is the container panel.
	 */
	private JPanel contentPane;

	/**
	 * pnlControl is the a panel which is used to put control components.
	 */
	private final JPanel pnlControl = new JPanel();

	/**
	 * btnMakeBall is the button to create a ball with type in the left text field.
	 */
	private final JButton btnMakeBall = new JButton("Make Ball");

	/**
	 * btnClearAll is the button to remove all the balls on the ball panel.
	 */
	private final JButton btnClearAll = new JButton("Clear All");

	/**
	 * JTextField is the text field to type in different ball types.
	 */
	private final JTextField tfBallType = new JTextField();

	/**
	 * IView2ModelAdapter is an instance of view to model adapter. The model adapter is initialized to a no-op to insure that system always has well-defined behavior.
	 */
	private IView2ModelAdapter _view2ModelAdpt = IView2ModelAdapter.NULL_OBJECT;

	/**
	 * pnlBall is the ball panel which shows the animation of different type of balls.
	 */
	private final JPanel pnlBall = new JPanel() {

		/**
		 * serialVersionUID is the serial version UID.
		 */
		private static final long serialVersionUID = 4048578419360286049L;

		/**
		* Overridden paintComponent method to paint a shape in the panel.
		* @param g The Graphics object to paint on.
		**/
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // Do everything normally done first, e.g. clear the screen.
			_view2ModelAdpt.paint(g); // call back to the model to paint the sprites

		}
	};

	/**
	 * Start method to start the GUI. This method is called by controller.
	 * @param view The main frame of ball word GUI.
	 */
	public void start(BallFrame view) {
		view.setVisible(true);
	}

	/**
	 * Update function to updates the view by repainting the canvas.
	 */
	public void update() {
		pnlBall.repaint();
	}

	/**
	 * Constructor of BallFrame.
	 * @param _view2ModelAdpt An instance of view to model adaptor.
	 */
	public BallFrame(IView2ModelAdapter _view2ModelAdpt) {
		this._view2ModelAdpt = _view2ModelAdpt;
		tfBallType.setColumns(20);
		tfBallType.setText("model.StraightBall");
		initGUI();
	}

	/**
	 * initGUI initialize all the components on the frame.
	 */
	private void initGUI() {
		//Frame settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 427);

		//Container panel settings
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//Control panel settings
		contentPane.add(pnlControl, BorderLayout.NORTH);

		pnlControl.add(tfBallType);
		tfBallType.setToolTipText("Enter the type of the ball whith the format model.ballType");

		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_view2ModelAdpt.addBall(tfBallType.getText());
			}
		});
		btnMakeBall.setToolTipText("Create a ball with the type in the text field");

		pnlControl.add(btnMakeBall);
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_view2ModelAdpt.clearBalls();
			}
		});
		btnClearAll.setToolTipText("Remove all the balls in the ball panel");

		pnlControl.add(btnClearAll);

		//Ball panel settings
		pnlBall.setSize(600, 800);
		contentPane.add(pnlBall, BorderLayout.CENTER);

		//Set the panel for ball word
		_view2ModelAdpt.setPanel(pnlBall);

	}

}
