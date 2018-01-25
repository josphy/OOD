package server.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * server view
 */
public class ServerView extends JFrame {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 2169819873400934393L;

	private JPanel contentPane;
	private final JPanel pnlControl = new JPanel();
	private final JPanel pnlDisplay = new JPanel();
	private final JButton btnStart = new JButton("Send Map");
	private final JButton btnTerminateGame = new JButton("Terminate Game");
	private final JButton btnStartGame = new JButton("Start Game");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textInfo = new JTextArea();

	/**
	 * server view to model adapter
	 */
	private IServerView2ModelAdapter modelAdpt;

	/**
	 * start server view
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Create the frame.
	 * @param modelAdpt server view to model adapter
	 */
	public ServerView(IServerView2ModelAdapter modelAdpt) {
		this.modelAdpt = modelAdpt;
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

		contentPane.add(pnlControl, BorderLayout.NORTH);
		pnlControl.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.startGame();
			}
		});

		pnlControl.add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.sendQuery();
			}
		});

		pnlControl.add(btnTerminateGame);
		btnTerminateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.stopGame();
			}
		});

		contentPane.add(pnlDisplay, BorderLayout.CENTER);
		pnlDisplay.setLayout(new BorderLayout(0, 0));

		pnlDisplay.add(scrollPane);
		textInfo.setEditable(false);

		scrollPane.setViewportView(textInfo);
	}

	/**
	 * append text to info panel
	 * @param s text
	 */
	public void append(String s) {
		textInfo.append(s + '\n');
		textInfo.setCaretPosition(textInfo.getText().length());
	}

}
