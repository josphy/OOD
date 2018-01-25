package view;

import javax.swing.JFrame;
import shape.AShape;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
*  MainFrame class is the main frame of the application which implements the user interface.
 *  @author Yining Bao
 *  @author Haoshan Zou */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -3256914973903308813L;

	private AShape myShape = new Circle(150, 100, 25, Color.MAGENTA);
	private AShape compoShape = new CompositeShape(new Circle(200, 100, 0, Color.BLUE),
			new Arc(250, 100, 0, 0, 0, 45, Color.CYAN));
	private JPanel contentPane;
	private final JPanel pnlCenter = new JPanel() {
		/**
		 * the serial Version UID of center panel
		 */
		private static final long serialVersionUID = -872444218515942499L;

		/**
		* Overridden paintComponent method to paint a shape in the panel.
		* @param g The Graphics object to paint on.
		**/
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // Do everything normally done first, e.g. clear the screen.
			g.setColor(Color.RED); // Set the color to use when drawing
			g.fillOval(75, 100, 20, 40); // paint a filled 20x40 red ellipse whose upper left corner is at (75, 100)
			myShape.paint(g);
			compoShape.paint(g);
		}
	};
	private final JPanel pnlNorth = new JPanel();
	private final JLabel lblInfo = new JLabel("info");
	private final JButton btnControl = new JButton("click");
	private final JTextField textField = new JTextField();
	private final JPanel pnlSouth = new JPanel();
	private final JButton btnCircle = new JButton("Circle");
	private final JButton btnRectangle = new JButton("Rectangle");
	private final JButton btnArc = new JButton("Arc");
	private final JButton btnCompositeshape = new JButton("CompositeShape");

	/**
	* Launch the application.
	 * 	 * @param args default arguments of main method */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	*  set frame visible */
	public void start() {
		setVisible(true);
	}

	/**
	* Create the frame. */
	public MainFrame() {
		textField.setToolTipText("for to user type");
		textField.setColumns(10);
		initGUI();
	}

	/**
	*  initiate the GUI */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		pnlCenter.setBackground(Color.PINK);

		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlNorth.setBackground(Color.ORANGE);

		contentPane.add(pnlNorth, BorderLayout.NORTH);
		lblInfo.setToolTipText("show what's has been typed");

		pnlNorth.add(lblInfo);
		btnControl.setToolTipText("set the label's text to whatever text has been typed into the text field");
		btnControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInfo.setText(textField.getText());

			}
		});

		pnlNorth.add(btnControl);

		pnlNorth.add(textField);
		pnlSouth.setBackground(Color.YELLOW);

		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		btnCircle.setToolTipText("show a circle and other regular shape disappear");

		pnlSouth.add(btnCircle);
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myShape = new Circle(150, 100, 25, Color.MAGENTA);
				pnlCenter.repaint();

			}
		});
		btnRectangle.setToolTipText("show an arc and other regular shape disappear");
		pnlSouth.add(btnRectangle);
		btnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myShape = new Rectangle(150, 100, 50, 50, Color.GREEN);
				pnlCenter.repaint();

			}
		});
		pnlSouth.add(btnArc);
		btnArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myShape = new Arc(150, 100, 50, 50, 0, 45, Color.ORANGE);
				pnlCenter.repaint();

			}
		});
		btnCompositeshape.setToolTipText("show a pre-determined composite shape");
		pnlSouth.add(btnCompositeshape);
		btnCompositeshape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compoShape = new CompositeShape(new Circle(310, 100, 25, Color.BLUE),
						new Arc(330, 100, 60, 60, 0, 45, Color.CYAN));
				pnlCenter.repaint();

			}
		});
	}

}
