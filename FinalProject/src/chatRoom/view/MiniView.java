package chatRoom.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.rmi.RemoteException;
import java.util.Collection;

import common.IReceiver;

/**
 * Chat Room View
 */
public class MiniView extends JFrame {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -8418461545782780230L;

	/**
	 * content panel
	 */
	private JPanel contentPane;
	private final JPanel pnlDisplay = new JPanel();
	private final JSplitPane pnlSplit = new JSplitPane();
	private final JScrollPane pnlDialog = new JScrollPane();
	private final JScrollPane pnlUser = new JScrollPane();
	private final JTextArea txtUser = new JTextArea();
	private final JPanel txtDialog = new JPanel();

	private final JPanel pnlAction = new JPanel();
	private final JPanel pnlInput = new JPanel();
	private final JTextField txtInput = new JTextField();
	private final JButton btnSend = new JButton("Send");
	private final JButton btnSendImg = new JButton("Send Image");
	private final JButton btnQuitRoom = new JButton("Quit Room");

	/**
	 * mini model adapter
	 */
	private IChatRoomModelAdapter modelAdpt = IChatRoomModelAdapter.NULL_OBJECT;

	/**
	 * Constructor to create chat room view
	 * 
	 * @param modelAdp
	 *            externally-provided adapter to chat room model
	 */
	public MiniView(IChatRoomModelAdapter modelAdp) {
		initGUI();
		this.modelAdpt = modelAdp;
	}

	/**
	 * set GUI layout
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		this.setTitle("Chat Room"); // pass chat room name in parameter
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(pnlDisplay, BorderLayout.CENTER);
		pnlDisplay.setLayout(new BorderLayout(0, 0));

		pnlDisplay.add(pnlSplit);
		pnlSplit.setLeftComponent(pnlDialog);
		pnlSplit.setRightComponent(pnlUser);

		pnlDialog.setBorder(new TitledBorder("Dialog:"));
		pnlDialog.setToolTipText("display chat messages");
		txtDialog.setBackground(Color.WHITE);

		pnlDialog.setViewportView(txtDialog);
		txtDialog.setLayout(new BoxLayout(txtDialog, BoxLayout.Y_AXIS));
		pnlDialog.setPreferredSize(new Dimension(500, 400));

		pnlUser.setBorder(new TitledBorder("Connected Users:"));
		pnlUser.setViewportView(txtUser);
		txtUser.setToolTipText("display list of users connected to current chat room");
		txtUser.setColumns(10);
		txtUser.setEditable(false);

		contentPane.add(pnlAction, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) pnlAction.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		pnlAction.add(pnlInput);
		pnlInput.setLayout(new GridLayout(0, 1, 0, 0));
		pnlInput.add(txtInput);
		txtInput.setToolTipText("input text message here");
		txtInput.setColumns(10);
		pnlInput.add(btnSend);
		btnSend.setToolTipText("click to send text");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.sendMsg(txtInput.getText());
				txtInput.setText("");
			}
		});

		pnlAction.add(btnSendImg);
		btnSendImg.setToolTipText("send an image");
		btnSendImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(null);
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
				File f = jfc.getSelectedFile();
				try {
					Image bi = ImageIO.read(f);
					ImageIcon image = new ImageIcon(bi.getScaledInstance(200, -1, 0));

					modelAdpt.sendImg(image);
				} catch (IOException e1) {
					System.err.println("exception reading image:");
					e1.printStackTrace();
				}
			}
		});

		pnlAction.add(btnQuitRoom);
		btnQuitRoom.setToolTipText("quit the room");
		btnQuitRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelAdpt.quit();
			}
		});

	}

	/**
	 * start mini view
	 */
	public void start() {
		contentPane.repaint();
		// setVisible(true);
	}

	/**
	 * add message to dialog
	 * 
	 * @param s
	 *            message to display
	 */
	public void appendMsg(String s) {
		txtDialog.add(new JLabel(s + '\n'));
		txtDialog.validate();
		txtDialog.repaint();
	}

	/**
	 * set user list
	 * 
	 * @param stubs
	 *            list of user stubs
	 */
	public void setUserList(Collection<IReceiver> stubs) {
		txtUser.setText("");
		try {
			for (IReceiver stub : stubs)
				txtUser.append(stub.getUserStub().getName() + '\n');
		} catch (RemoteException e) {
			System.err.println("error displaying room user list");
			e.printStackTrace();
		}
		txtUser.repaint();
	}

	/**
	 * get dialog panel
	 * 
	 * @return dialog panel
	 */
	public Container getDialogPnl() {
		return txtDialog;
	}

	/**
	 * get mini content panel
	 * 
	 * @return content panel
	 */
	public JPanel getMiniGUI() {
		return contentPane;
	}

	/**
	 * get panel by label
	 * 
	 * @param label
	 *            panel label
	 * @return by default return dialog panel
	 */
	public Container getPnl(String label) {
		// possible panel selection implementation by label
		return txtDialog;
	}
}
