package chatApp.controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.Collection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import chatApp.model.ChatAppModel;
import chatApp.model.IMainViewAdapter;
import chatApp.model.IMain2MiniAdapter;
import chatApp.view.IMainView2ModelAdapter;
import chatApp.view.MainView;
import chatRoom.controller.MiniController;
import chatRoom.model.ChatRoomModel;
import chatRoom.model.IMini2MainAdapter;
import common.IChatRoom;
import common.IReceiver;
import common.IUser;
import concrete.Receiver;
import provided.mixedData.MixedDataDictionary;

/**
 * Controller for the overall Chat App
 */
public class MainController {

	/**
	 * main chat app GUI
	 */
	private MainView view;

	/**
	 * main chat app model
	 */
	private ChatAppModel model;

	/**
	 * main controller constructor
	 */
	private MainController() {

		model = new ChatAppModel(new IMainViewAdapter() {

			@Override
			public void append(String s) {
				view.append(s);

			}

			@Override
			public void setLocalIP(String localAddress) {
				view.setLocalIP(localAddress);

			}

			@Override
			public void addUser(IUser remoteUserStub) {
				view.addUser(remoteUserStub);

			}

			@Override
			public void addChatroom(IChatRoom chatroom) {
				view.addRoom(chatroom);

			}

			@Override
			public IMain2MiniAdapter makeMini(IChatRoom chatroom, IReceiver receiverStub, Receiver receiver,
					MixedDataDictionary dict) {
				MiniController miniController = new MiniController(chatroom, receiverStub, receiver, dict,
						new IMini2MainAdapter() {

							@Override
							public void leaveRoom(IChatRoom chatroom) {
								model.deleteRoom(chatroom);

							}

							@Override
							public Container buildScrollablePnl(String label) {
								return view.getScrollablePnl(label);
							}

							@Override
							public Container buildUnscrollablePnl(String label) {
								return view.getUnscrollablePnl(label);
							}

						});

				IMain2MiniAdapter miniMVCAdpt = new IMain2MiniAdapter() {

					@Override
					public ChatRoomModel getMiniModel() {
						return miniController.getMiniModel();
					}

					@Override
					public void quitRoom() {
						miniController.leaveRoom();
					}

					@Override
					public JPanel getMiniView() {
						return miniController.getMiniGUI();
					}

				};

				//create new tab
				view.getTabPnl().add(chatroom.getName(), miniController.getMiniGUI());

				miniController.start();

				return miniMVCAdpt;
			}

			@Override
			public void setConnectedUser(Collection<IUser> connectedUsers) {
				view.setUserBox(connectedUsers);
			}

			@Override
			public JTabbedPane getTabPnl() {
				return view.getTabPnl();
			}

			@Override
			public Container buildScrollablePanel(String label) {
				return view.getScrollablePnl(label);
			}

			@Override
			public Container buildUnscrollablePanel(String label) {
				return view.getUnscrollablePnl(label);
			}

			@Override
			public void setAvailRoom(Collection<IChatRoom> chatRooms) {
				view.setAvailRoom(chatRooms);

			}

		});

		view = new MainView(new IMainView2ModelAdapter() {

			@Override
			public void login(int port, String username) {
				model.login(port, username);

			}

			@Override
			public void createChatRoom(String name) {
				model.createRoom(name);

			}

			@Override
			public void connect(String remoteHost) {
				model.connectTo(remoteHost);

			}

			@Override
			public void requestRoom(Object selectedUser) {
				model.requestRoom((IUser) selectedUser);

			}

			@Override
			public void sendRoom(Object selectedUser) {
				//				model.invite((IUser) selectedUser);

			}

			@Override
			public void join(Object selectedRoom) {
				model.joinRoom((IChatRoom) selectedRoom);

			}

			@Override
			public void quit() {
				model.quit();

			}

		});

	}

	/**
	 * start main model and view
	 */
	public void start() {
		model.start();
		view.start();
	}

	/**
	 * Launch the application.
	 * @param args Arguments given by the system or command line.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Java specs say that the system must be constructed on the GUI event thread.
			public void run() {
				try {
					MainController controller = new MainController(); // instantiate the system
					controller.start(); // start the system
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
