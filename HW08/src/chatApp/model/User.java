package chatApp.model;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import common.IChatRoom;
import common.IUser;

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
	 * constructor
	 * @param id
	 * @param name
	 */
	public User(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.chatrooms = new HashMap<>();
		this.connectedUsers = new HashMap<>();

	}

	/**
	 * add available chat room
	 * @param chatroom
	 * @return whether the room is added
	 */
	public boolean addChatRoom(IChatRoom chatroom) {
		this.chatrooms.put(chatroom.getUUID(), chatroom);
		System.out.println("adding room " + chatroom.getUUID().toString() + " " + chatroom.getName());
		return this.chatrooms.containsKey(chatroom.getUUID());
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

	/**
	 * get connected users
	 * @return connected user
	 */
	public Collection<IUser> getConnectedUsers() {
		return new HashSet<IUser>(connectedUsers.values());
	}

	/**
	 * display user name
	 */
	public String toString() {
		return this.name;
	}

}
