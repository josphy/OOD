package concrete;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import common.DataPacketUser;
import common.IChatRoom;
import common.IUser;
import common.IUserMessageType;
import common.datatype.user.IInvitationType;
import provided.datapacket.DataPacketAlgo;
import concrete.data.user.InvitationData;

/**
 * server implementation of IUser: auto connect back sends invitation to room
 */
public class Server implements IUser {

	/**
	 * unique id
	 */
	private UUID id;

	/**
	 * user name
	 */
	private String name;

	/**
	 * server stub
	 */
	private IUser serverStub;

	/**
	 * user available chat rooms
	 */
	private transient Map<UUID, IChatRoom> chatrooms;

	/**
	 * server lobby
	 */
	private transient Set<IChatRoom> lobby;

	/**
	 * game teams
	 */
	private transient Map<Integer, IChatRoom> teams;

	/**
	 * connected users
	 */
	private transient Map<UUID, IUser> connectedUsers;

	/**
	 * local visitor to process incoming data packets
	 */
	private transient DataPacketAlgo<String, String> myDataPacketAlgo;

	/**
	 * user to team map
	 */
	private transient Map<IUser, Integer> user2TeamMap = new HashMap<IUser, Integer>();

	/**
	 * constructor
	 * 
	 * @param id
	 *            unique id
	 * @param name
	 *            display name
	 */
	public Server(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.chatrooms = new HashMap<>();
		this.lobby = new HashSet<IChatRoom>();
		this.connectedUsers = new HashMap<>();
	}

	/**
	 * get user to team map
	 * @return current user to team map
	 */
	public Map<IUser, Integer> getUser2TeamMap() {
		return this.user2TeamMap;
	}

	/**
	 * set lobby of the server
	 * 
	 * @param lobby
	 *            lobby chat room
	 */
	public void setLobby(IChatRoom lobby) {
		this.lobby.add(lobby);
	}

	/**
	 * set this stub's RMI stub
	 * 
	 * @param stub
	 *            server stub
	 */
	public void setStub(IUser stub) {
		this.serverStub = stub;
	}

	/**
	 * set IChatRoom teams
	 * 
	 * @param teams
	 *            server initialized team chat rooms
	 */
	public void setTeams(Map<Integer, ChatRoom> teams) {
		this.teams = new HashMap<Integer, IChatRoom>();
		for (Integer index : teams.keySet()) {
			this.teams.put(index, teams.get(index));
		}
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
	 * get publicly available rooms: team rooms are not public
	 */
	public Collection<IChatRoom> getChatRooms() throws RemoteException {
		return lobby;
	}

	@Override
	/**
	 * allow remote connect - auto connect back
	 */
	public void connect(IUser userStub) throws RemoteException {
		this.connectedUsers.put(userStub.getUUID(), userStub);
		// invite to lobby
		userStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,
				new InvitationData((IChatRoom) lobby.toArray()[0]), serverStub));
		// auto team assignment
		int teamIndex = (int) Math.round(Math.random()) + 1;
		IChatRoom team = teams.get(teamIndex);
		user2TeamMap.put(userStub, teamIndex);
		userStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,
				new InvitationData((IChatRoom) team), serverStub));
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
