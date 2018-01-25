package chatApp.model;

import java.util.Collection;

import javax.swing.JTabbedPane;

import common.IChatRoom;
import common.IReceiver;
import common.IUser;

/**
 * main model to main view adapter
 */
public interface IMainViewAdapter {

	/**
	 * append string to info panel
	 * @param s string to display
	 */
	public void append(String s);

	/**
	 * set ip display
	 * @param localAddress ip retrieved after rmi starts
	 */
	public void setLocalIP(String localAddress);

	/**
	 * add user to conneted user drop list
	 * @param remoteUserStub user to add
	 */
	public void addUser(IUser remoteUserStub);

	/**
	 * add room to available chatroom drop list
	 * @param chatroom room to add
	 */
	public void addChatroom(IChatRoom chatroom);

	/**
	 * make mini MVC
	 * @param chatroom selected chatroom to join
	 * @param receiverStub user's receiver stub for this room
	 * @return adapter to access this room MVC
	 */
	public IMain2MiniAdapter makeMini(IChatRoom chatroom, IReceiver receiverStub);

	/**
	 * reset connected user drop list
	 * @param connectedUsers user's connected user list
	 */
	public void setConnectedUser(Collection<IUser> connectedUsers);

	/**
	 * get view's tabbed pnl for new room MVC set up
	 * @return tabbed panel
	 */
	public JTabbedPane getTabPnl();

}
