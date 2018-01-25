package game.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import map.MapLayer;
import map.MapPanel;

/**
 * Game view
 */
public class GameView extends JFrame {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 5179876899323256723L;

	private JPanel contentPane;
	private final JPanel pnlDisplay = new JPanel();
	private final JPanel pnlControl = new JPanel();
	private final MapPanel map = new MapPanel();
	private final JButton btnSubmit = new JButton("Submit");
	private final JTextField textQuestion = new JTextField();

	/**
	 * adapter to game model
	 */
	private IGameView2ModelAdapter modelAdpt = null;

	/**
	 * start the game view
	 */
	public void start() {
		map.start();
		// setVisible(true);
	}

	/**
	 * Create the frame.
	 * 
	 * @param adapter
	 *            externally provided adapter to game model
	 */
	public GameView(IGameView2ModelAdapter adapter) {
		this.modelAdpt = adapter;
		initGUI();
	}

	/**
	 * init GUI layout
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(pnlDisplay, BorderLayout.CENTER);
		map.setPreferredSize(new java.awt.Dimension(600, 300));
		pnlDisplay.setLayout(new BorderLayout(0, 0));
		pnlDisplay.add(map);

		contentPane.add(pnlControl, BorderLayout.SOUTH);
		btnSubmit.setToolTipText("please choose one annotation and submit : )");
		pnlControl.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!modelAdpt.submitAnswer())
					alert("Submission failed. Please select exactly one state.");
			}
		});

		textQuestion.setColumns(20);
		textQuestion.setEditable(false);
		pnlControl.add(textQuestion);
	}

	/**
	 * get game view panel
	 * 
	 * @return content pane
	 */
	public Component getView() {
		return contentPane;
	}

	/**
	 * add layer to map panel
	 * 
	 * @param layer
	 *            map layer
	 */
	public void addMapLayer(MapLayer layer) {
		map.addLayer(layer);
	}

	/**
	 * Pop up an alert window on the screen to show some messages
	 * 
	 * @param msg
	 *            The message to be shown on the screen
	 */
	public void alert(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * set question box
	 * 
	 * @param name
	 *            desired display
	 */
	public void showStateName(String name) {
		textQuestion.setText(name);
	}

}
