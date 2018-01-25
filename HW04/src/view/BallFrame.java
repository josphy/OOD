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
import java.awt.Color;

import javax.swing.JComboBox;
import java.awt.GridLayout;

/**
 * This is a GUI to run  Ball World.
 * @version 1.0
 * @param <TDropListItem> the objects that drop list items stand for.
 * @since 2017-09-23
 */
public class BallFrame<TDropListItem, TPaintListItem> extends JFrame {

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
	private final JButton btnMakeBall = new JButton("Make Selected Ball");

	/**
	 * btnClearAll is the button to remove all the balls on the ball panel.
	 */
	private final JButton btnClearAll = new JButton("Clear All");

	/**
	 * JTextField is the text field to type in different ball types.
	 */
	private final JTextField txtStrategy = new JTextField();

	/**
	 * strategy selection, switch and combine panel.
	 */
	private final JPanel pnlStrategy = new JPanel();
	/**
	 * the add strategy to list button.
	 */
	private final JButton btnAdd = new JButton("Add to lists");
	
	/** the make ball panel. */
	private final JPanel pnlMake = new JPanel();
	
	/** the combine drop list1. */
	private final JComboBox<TDropListItem> combo1 = new JComboBox<TDropListItem>();
	
	/** the combine drop list2. */
	private final JComboBox<TDropListItem> combo2 = new JComboBox<TDropListItem>();
	/**
	 * the combine two strategies button.
	 */
	private final JButton btnCombine = new JButton("Combine!");
	/**
	 * the switch strategy panel.
	 */
	private final JPanel pnlSwitch = new JPanel();
	/**
	 * the make switcher ball button.
	 */
	private final JButton btnMakeSwitcher = new JButton("Make Switcher");

	/**
	 * the switch strategy button.
	 */
	private final JButton btnSwitch = new JButton("Switch!");

	/**
	 * Adapter back to the model for control tasks.
	 */
	private IModelControlAdapter<TDropListItem, TPaintListItem> _modelControlAdpt;
	/**
	* Adapter back to the model for update tasks.
	*/
	private IModelUpdateAdapter _modelUpdateAdpt;

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
			_modelUpdateAdpt.update(g); // call back to the model to paint the sprites
		}
	};
	
	/**
	 * Panel for buttons to manage new paint strategies
	 */
	private final JPanel pnlPaintStrategy = new JPanel();
	/**
	 * Button to add a new paint strategy to the drop list
	 */
	private final JButton btnAddNewStrategy = new JButton("Add New");
	
	/**
	 * Text field to input name of paint strategies
	 */
	private final JTextField txtPaintStrategy = new JTextField();
	
	/**
	 * Drop list for all added paint strategies
	 */
	private final JComboBox<TPaintListItem> pntStrategyBox = new JComboBox<TPaintListItem>();

	/**
	 * Start method to start the GUI. This method is called by controller.
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Update function to updates the view by repainting the canvas.
	 */
	public void update() {
		pnlBall.repaint();
	}

	/**
	 * Constructor of BallFrame.
	 * @param modelControlAdpt An instance of view to model control adaptor.
	 * @param modelUpdateAdpt An instance of view to model update adaptor.
	 */
	public BallFrame(IModelControlAdapter<TDropListItem, TPaintListItem> modelControlAdpt, IModelUpdateAdapter modelUpdateAdpt) {
		txtPaintStrategy.setText("Ball");
		txtPaintStrategy.setColumns(10);
		this._modelControlAdpt = modelControlAdpt;
		this._modelUpdateAdpt = modelUpdateAdpt;
		initGUI();
	}

	/**
	 * initGUI initialize all the components on the frame.
	 */
	private void initGUI() {
		//Frame settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 427);

		//Container panel settings
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlControl.setBackground(new Color(152, 251, 152));

		//Control panel settings
		contentPane.add(pnlControl, BorderLayout.NORTH);

		//text input and add
		pnlControl.add(pnlStrategy);
		pnlStrategy.setLayout(new GridLayout(0, 1, 0, 0));

		txtStrategy.setColumns(5);
		txtStrategy.setText("Straight");
		txtStrategy.setToolTipText("Name of strategy, XXX, from ballworld.XXXStrategy.");
		pnlStrategy.add(txtStrategy);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.addStrategy(txtStrategy.getText());
				if (null == o)
					return; // just in case
				combo1.insertItemAt(o, 0);
				combo2.insertItemAt(o, 0);
			}
		});
		btnAdd.setToolTipText("Add strategy to both droplists. The rest of classname is added automatically.");
		pnlStrategy.add(btnAdd);

		//make ball and combine
		pnlControl.add(pnlMake);
		pnlMake.setLayout(new GridLayout(0, 1, 0, 0));

		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_modelControlAdpt.makeBall(combo1.getItemAt(combo1.getSelectedIndex()), pntStrategyBox.getItemAt(pntStrategyBox.getSelectedIndex()));
			}
		});
		pnlMake.add(btnMakeBall);
		btnMakeBall.setToolTipText("Create a ball with the type in the text field");
		combo1.setToolTipText("The strategy that can be used to make a ball");

		pnlMake.add(combo1);
		combo2.setToolTipText("The strategy for combination");
		pnlMake.add(combo2);

		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TDropListItem o = _modelControlAdpt.combineStrategies(combo1.getItemAt(combo1.getSelectedIndex()),
						combo2.getItemAt(combo2.getSelectedIndex()));
				if (null == o)
					return; // just in case

				combo1.insertItemAt(o, 0);
				combo2.insertItemAt(o, 0);
			}
		});
		btnCombine.setToolTipText("Combine the selected strategies from both droplists into a single strategy that is added to both droplists");
		pnlMake.add(btnCombine);

		//make switcher and switch button
		pnlControl.add(pnlSwitch);
		pnlSwitch.setLayout(new GridLayout(2, 1, 0, 0));
		btnMakeSwitcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.makeSwitcherBall(pntStrategyBox.getItemAt(pntStrategyBox.getSelectedIndex()));
			}
		});
		btnMakeSwitcher.setToolTipText("Instantiate a ball that can switch strategies");
		pnlSwitch.add(btnMakeSwitcher);
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.switchStrategy(combo1.getItemAt(combo1.getSelectedIndex()));
			}
		});
		btnSwitch.setToolTipText("Switch the strategy on all switcher balls to the currently selected strategy in the upper droplist");
		pnlSwitch.add(btnSwitch);

		//clear button
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelControlAdpt.clearBalls();
			}
		});
		pnlControl.add(btnClearAll);
		btnClearAll.setToolTipText("Remove all the balls in the ball panel");
		
		//paint strategy panel
		pnlControl.add(pnlPaintStrategy);
		pnlPaintStrategy.setLayout(new GridLayout(3, 1, 0, 0));
		
		txtPaintStrategy.setToolTipText("Name of paint strategy");
		pnlPaintStrategy.add(txtPaintStrategy);
		btnAddNewStrategy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TPaintListItem o = _modelControlAdpt.addPntStrategy(txtPaintStrategy.getText());
				pntStrategyBox.insertItemAt(o, 0);
			}
		});
		btnAddNewStrategy.setToolTipText("Add paint strategy into the droplist below");
		pnlPaintStrategy.add(btnAddNewStrategy);
		pntStrategyBox.setToolTipText("Current paint strategy");
		
		pnlPaintStrategy.add(pntStrategyBox);
		
		//Ball panel settings
		pnlBall.setSize(600, 800);
		pnlBall.setBackground(new Color(224, 255, 255));
		contentPane.add(pnlBall, BorderLayout.CENTER);

		//Set the panel for ball world
		_modelControlAdpt.setPanel(pnlBall);

	}
}
