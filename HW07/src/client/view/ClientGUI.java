package client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ClientGUI<TaskItem> extends JFrame {

	private static final long serialVersionUID = -5936004102932928468L;
	private JPanel contentPane;
	private final JPanel pnlControl = new JPanel();
	private final JButton btnQuit = new JButton("Quit");
	private final JPanel pnlConnect = new JPanel();
	private final JTextField txtIp = new JTextField();
	private final JButton btnConnect = new JButton("Connect");
	private final JTextField txtMsg = new JTextField();
	private final JPanel pnlAdd = new JPanel();
	private final JTextField txtTask = new JTextField();
	private final JButton btnAdd = new JButton("Add to Lists");
	private final JPanel pnlRun = new JPanel();
	private final JButton btnRun = new JButton("Run Task");
	private final JComboBox<TaskItem> boxTask1 = new JComboBox<TaskItem>();
	private final JComboBox<TaskItem> boxTask2 = new JComboBox<TaskItem>();
	private final JButton btnCombine = new JButton("Combine Tasks");
	private final JPanel pnlParameter = new JPanel();
	private final JTextField txtParameter = new JTextField();
	private final JScrollPane pnlDisplay = new JScrollPane();
	private final JTextArea txtDisplay = new JTextArea();

	@SuppressWarnings("unchecked")
	/**
	 * adapter to client's model
	 */
	private IModelAdapter<TaskItem> _modelAdapter = (IModelAdapter<TaskItem>) IModelAdapter.NULL_OBJECT;

	/**
	 * start the view by making it visible
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Constructor of the class. Create the frame.
	 * @param externally provided adapter to the model
	 */
	public ClientGUI(IModelAdapter<TaskItem> adapter) {
		this._modelAdapter = adapter;
		initGUI();
	}

	/**
	 * initialize the view and its components
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 400);
		this.setTitle("RMI Client");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//control panel setting:
		contentPane.add(pnlControl, BorderLayout.NORTH);
		pnlControl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//quit
		pnlControl.add(btnQuit);
		btnQuit.setToolTipText("Shut down RMI system and quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_modelAdapter.quit();
			}
		});

		//connect
		pnlControl.add(pnlConnect);
		pnlConnect.setBorder(new TitledBorder("Remote Host:"));
		pnlConnect.setLayout(new GridLayout(0, 1, 0, 0));
		txtIp.setText("ip");
		txtIp.setToolTipText("IP address of remote compute engine");
		pnlConnect.add(txtIp);

		pnlConnect.add(btnConnect);
		btnConnect.setToolTipText("Click to connect to remote compute engine");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		//message
		pnlControl.add(txtMsg);
		txtMsg.setBorder(new TitledBorder("Send msg to remote host's view"));
		txtMsg.setText("Hello from client! Press Enter to send.");
		txtMsg.setToolTipText("Hit Enter to send the string to remote view");
		txtMsg.setColumns(20);
		txtMsg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg = txtMsg.getText();
					if (msg.length() == 0) {
						return;
					}
					_modelAdapter.sendMsg(msg);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtMsg.setText("");
				}
			}
		});

		//add
		pnlControl.add(pnlAdd);
		pnlAdd.setLayout(new GridLayout(0, 1, 0, 0));
		pnlAdd.add(txtTask);
		txtTask.setText("Task");
		txtTask.setToolTipText("Fully qualified classname of an ITask implementation with FACTORY field defined");

		pnlAdd.add(btnAdd);
		btnAdd.setToolTipText("Add the factory of the specified ITask to the drop lists");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTask(txtTask.getText());
			}
		});

		//run
		pnlControl.add(pnlRun);
		pnlRun.setLayout(new GridLayout(0, 1, 0, 0));
		pnlRun.add(btnRun);
		btnRun.setToolTipText("Instantiate selected task with given parameter and run it on remote server");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				append("[Task execution returned result] "
						+ _modelAdapter.runTask(boxTask1.getItemAt(boxTask1.getSelectedIndex()), txtParameter.getText())
						+ '\n');
			}
		});
		pnlRun.add(boxTask1);
		boxTask1.setToolTipText("Select desired task factory to run or combine");
		pnlRun.add(boxTask2);
		boxTask2.setToolTipText("Select desired task factory to combine");
		pnlRun.add(btnCombine);
		btnCombine.setToolTipText("Add a task factory for a composite of selected tasks");
		btnCombine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskItem o = _modelAdapter.combineTask(boxTask1.getItemAt(boxTask1.getSelectedIndex()),
						boxTask2.getItemAt(boxTask2.getSelectedIndex()));
				if (null == o)
					return; // just in case
				boxTask1.addItem(o);
				boxTask2.addItem(o);
			}
		});

		//parameter
		pnlControl.add(pnlParameter);
		pnlParameter.setBorder(new TitledBorder("Run Parameter:"));
		pnlParameter.add(txtParameter);
		txtParameter.setToolTipText("A parameter value used to instantiate the class");
		txtParameter.setText("5");
		txtParameter.setColumns(8);

		//display panel setting:
		contentPane.add(pnlDisplay, BorderLayout.CENTER);
		pnlDisplay.setViewportView(txtDisplay);
	}

	/**
	 * let client model connect to remote server
	 */
	private void connect() {
		append("Attempting to connect to " + txtIp.getText() + "...");
		append(_modelAdapter.connect(txtIp.getText()));
	}

	/**
	 * append given string to view's output display
	 * @param s given display content
	 */
	public void append(String s) {
		txtDisplay.append(s + '\n');
		txtDisplay.setCaretPosition(txtDisplay.getDocument().getLength());
	}

	/**
	 * setter of view's remote host text box content
	 * @param host desired host address
	 */
	public void setRemoteHost(String host) {
		txtIp.setText(host);
	}

	/**
	 * add task item into both drop lists
	 * @param taskname given task name
	 */
	public void addTask(String taskname) {
		TaskItem o = _modelAdapter.addTask(taskname);
		if (null == o)
			return; // just in case
		boxTask1.addItem(o);
		boxTask2.addItem(o);
	}

}
