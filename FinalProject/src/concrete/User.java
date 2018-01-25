package concrete;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import common.DataPacketUser;
import common.IChatRoom;
import common.IUser;
import common.IUserMessageType;
import provided.datapacket.DataPacketAlgo;

/**
 * User local implementation of network API IUser
 */
public class User implements IUser {

	/**
	 * unique id
	 */
	private UUID id;

	/**
	 * user name
	 */
	private String name;

	/**
	 * user available chat rooms
	 */
	private transient Map<UUID, IChatRoom> chatrooms;

	/**
	 * connected users
	 */
	private transient Map<UUID, IUser> connectedUsers;

	/**
	 * local visitor to process incoming data packets
	 */
	private transient DataPacketAlgo<String, String> myDataPacketAlgo;

	/**
	 * constructor
	 * 
	 * @param id
	 *            unique id
	 * @param name
	 *            display name
	 */
	public User(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.chatrooms = new HashMap<>();
		this.connectedUsers = new HashMap<>();
	}

	/**
	 * set externally provided visitor algo
	 * 
	 * @param myDataPacketAlgo
	 *            provided in main model
	 */
	public void setDataPacketAlgo(DataPacketAlgo<String, String> myDataPacketAlgo) {
		this.myDataPacketAlgo = myDataPacketAlgo;
	}

	/**
	 * add available chat room
	 * 
	 * @param chatroom
	 *            chat room to add to
	 * @return whether the room is added
	 */
	public boolean addChatRoom(IChatRoom chatroom) {
		boolean isNew = !this.chatrooms.containsKey(chatroom.getUUID());
		this.chatrooms.put(chatroom.getUUID(), chatroom);
		System.out.println("adding room " + chatroom.getUUID().toString() + " " + chatroom.getName());
		return isNew;
	}

	@Override
	/**
	 * get name
	 */
	public String getName() throws RemoteException {
		return this.name;
	}

	@Override
	/**
	 * get id
	 */
	public UUID getUUID() throws RemoteException {
		return this.id;
	}

	@Override
	/**
	 * get available rooms
	 */
	public Collection<IChatRoom> getChatRooms() throws RemoteException {
		return new HashSet<IChatRoom>(chatrooms.values());
	}

	@Override
	/**
	 * allow remote connect - auto connect back
	 */
	public void connect(IUser userStub) throws RemoteException {
		this.connectedUsers.put(userStub.getUUID(), userStub);
	}

	@Override
	public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
		data.execute(myDataPacketAlgo);
	}

	/**
	 * get connected users
	 * 
	 * @return connected user
	 */
	public Collection<IUser> getConnectedUsers() {
		return new HashSet<IUser>(connectedUsers.values());
	}

	public void removeUser(IUser stub) {
		try {
			connectedUsers.remove(stub.getUUID());
		} catch (RemoteException e) {
			System.err.println("exception removing stub from connected user list:/n" + e);
			e.printStackTrace();
		}
	}

	/**
	 * display user name
	 */
	public String toString() {
		return this.name;
	}

}