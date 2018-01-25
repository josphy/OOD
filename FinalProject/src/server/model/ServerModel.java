package server.model;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.IComponentFactory;
import common.IReceiver;
import common.IUser;
import common.IUserCmd2ModelAdapter;
import common.IUserMessageType;
import common.datatype.IRequestCmdType;
import common.datatype.chatroom.IAddReceiverType;
import common.datatype.chatroom.ICRExceptionStatusType;
import common.datatype.chatroom.ICRInstallCmdType;
import common.datatype.chatroom.ICRRejectionStatusType;
import common.datatype.chatroom.IRemoveReceiverType;
import common.datatype.user.IInvitationType;
import common.datatype.user.IQuitType;
import common.datatype.user.IUserExceptionStatusType;
import common.datatype.user.IUserInstallCmdType;
import common.datatype.user.IUserRejectionStatusType;
import concrete.ChatRoom;
import concrete.Receiver;
import concrete.Server;
import concrete.data.chatroom.AddReceiverData;
import concrete.data.chatroom.CRExceptionStatusCmd;
import concrete.data.chatroom.CRExceptionStatusData;
import concrete.data.chatroom.CRInstallCmdData;
import concrete.data.chatroom.CRRejectionStatusCmd;
import concrete.data.chatroom.CRRejectionStatusData;
import concrete.data.chatroom.ImageIconCmd;
import concrete.data.chatroom.ImageMessageData;
import concrete.data.chatroom.TextMessageCmd;
import concrete.data.chatroom.TextMessageData;
import concrete.data.user.UserExceptionStatusCmd;
import concrete.data.user.UserRejectionStatusCmd;
import game.data.ChooseStateData;
import game.data.GameFactory;
import game.data.GameOverCmd;
import game.data.GameOverData;
import game.data.GameStartCmd;
import game.data.GameStartData;
import game.data.QueryStateCmd;
import game.data.QueryStateData;
import game.view.GameView;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataDictionary;
import provided.mixedData.MixedDataKey;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import provided.util.IVoidLambda;

/**
 * Game server model 
 */

public class ServerModel {

	/**
	 * server model to view adapter
	 */
	private IServerModel2ViewAdapter viewAdpt;

	/**
	 * rmi utils for connection initialization
	 */
	private RMIUtils rmiUtils;

	/**
	 * RMI registry
	 */
	private Registry registry;

	/**
	 * IUser implementation Server
	 */
	private Server server;

	/**
	 * server stub
	 */
	private IUser serverStub;

	/**
	 * lobby receiver
	 */
	private Receiver receiver;

	/**
	 * lobby receiver stub
	 */
	private IReceiver receiverStub;

	/**
	 * The data packet sent to the user
	 */

	private DataPacketAlgo<String, String> dataPacketAlgoUser;

	/**
	 * data packet algo for lobby receiver
	 */
	private DataPacketAlgo<String, String> dataPacketAlgoCR;

	/**
	 * mixed data dict to provide cross application storage
	 */
	private MixedDataDictionary dict = new MixedDataDictionary();

	/**
	 * state query key
	 */
	private MixedDataKey<String> stateQueryKey = new MixedDataKey<String>(UUID.randomUUID(), "query state",
			String.class);

	/**
	 * game view key
	 */
	private MixedDataKey<GameView> viewKey = new MixedDataKey<GameView>(UUID.randomUUID(), "game view", GameView.class);

	/**
	 * lobby chat room
	 */
	private ChatRoom lobby;

	/**
	 * team chat rooms
	 */
	private Map<Integer, ChatRoom> teams = new HashMap<Integer, ChatRoom>(); // current implementation: two teams

	/**
	 * team score
	 */
	private Map<Integer, Integer> teamScore = new HashMap<Integer, Integer>();

	/**
	 * server receivers in teams
	 */
	private Map<Integer, Receiver> teamReceivers = new HashMap<Integer, Receiver>();

	/**
	 * server receiver stubs in teams
	 */
	private Map<Integer, IReceiver> teamReceiverStubs = new HashMap<Integer, IReceiver>();

	/**
	 * receiver algo for team chat rooms
	 */
	private Map<Integer, DataPacketAlgo<String, String>> teamAlgos = new HashMap<Integer, DataPacketAlgo<String, String>>();

	/**
	 * game client to team map
	 */
	private Map<IUser, Integer> user2TeamMap;

	/**
	 * game query states
	 */
	private List<String> states;

	/**
	 * Command used to output strings to one or more destinations. By default,
	 * output goes to standard error.
	 */
	private IVoidLambda<String> outputCmd = new IVoidLambda<String>() {
		public void apply(String... msgs) {
			for (String s : msgs) {
				viewAdpt.append(s + '\n');
			}
		}
	};

	/**
	 * constructor
	 * @param viewAdpt server view adapter
	 */
	public ServerModel(IServerModel2ViewAdapter viewAdpt) {
		this.viewAdpt = viewAdpt;
		this.rmiUtils = new RMIUtils(outputCmd);
		this.dataPacketAlgoUser = new DataPacketAlgo<String, String>(null);
		this.dataPacketAlgoCR = new DataPacketAlgo<String, String>(null);
		initDataPacketAlgoUser();
		initStates();
	}

	/**
	 * init command to chat room model adapter
	 */
	private ICRCmd2ModelAdapter cmd2ModelAdpt_CR = new ICRCmd2ModelAdapter() {

		@Override
		public String getName() {
			return "SERVER";
		}

		@Override
		public void appendMsg(String text, String name) {
			viewAdpt.append(name + ": " + text);
		}

		@Override
		public void buildScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub

		}

		@Override
		public void buildNonScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub

		}

		@Override
		public <T> T put(MixedDataKey<T> key, T value) {
			return dict.put(key, value);
		}

		@Override
		public <T> T get(MixedDataKey<T> key) {
			return dict.get(key);
		}

		@Override
		public <T extends ICRMessageType> void sendTo(IReceiver target, Class<T> id, T data) {
			try {
				target.receive(new DataPacketCR<T>(id, data, receiverStub));
			} catch (RemoteException e) {
				System.err.println("exception sending message in CRCmd2ModelAdapter:/n" + e);
				e.printStackTrace();
			}
		}

		@Override
		public <T extends ICRMessageType> void sendToChatRoom(Class<T> id, T data) {
			lobby.sendPacket(new DataPacketCR<T>(id, data, receiverStub));
		}

	};

	/**
	 * init command to chat app model adapter
	 */
	private IUserCmd2ModelAdapter cmd2ModelAdpt_User = new IUserCmd2ModelAdapter() {

		@Override
		public String getName() {
			return "SERVER";
		}

		@Override
		public void appendMsg(String text, String name) {
			viewAdpt.append("USER " + name + ": " + text);
		}

		@Override
		public void buildScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub

		}

		@Override
		public void buildNonScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub

		}

		@Override
		public <T> T put(MixedDataKey<T> key, T value) {
			return dict.put(key, value);
		}

		@Override
		public <T> T get(MixedDataKey<T> key) {
			return dict.get(key);
		}

		@Override
		public <T extends IUserMessageType> void sendTo(IUser target, Class<T> id, T data) {
			try {
				target.receive(new DataPacketUser<T>(id, data, serverStub));
			} catch (RemoteException e) {
				System.err.println("exception sending message in userCmd2ModelAdapter:\n" + e);
				e.printStackTrace();
			}
		}

	};

	/**
	 * start model
	 */
	public void start() {
		try {
			rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_SERVER);
			registry = rmiUtils.getLocalRegistry();
		} catch (Exception e) {
			System.err.println("Exception while intializing RMI: \n" + e);
			e.printStackTrace();
		}

		try {
			server = new Server(UUID.randomUUID(), "GameServer");
			serverStub = (IUser) UnicastRemoteObject.exportObject(server, IRMI_Defs.CLASS_SERVER_PORT_SERVER + 1);
			server.setStub(serverStub);
			registry.rebind(IUser.BOUND_NAME, serverStub);
			viewAdpt.append("Game Server bound to \"" + IUser.BOUND_NAME + "\"\n");
		} catch (Exception e) {
			System.err.println("Server Exception: " + e);
			e.printStackTrace();
		}

		// create lobby
		lobby = new ChatRoom(UUID.randomUUID(), "LOBBY");
		viewAdpt.append("Server created lobby chat room.\n");
		server.addChatRoom(lobby);
		server.setLobby(lobby);

		// create receiver stub and join lobby
		receiver = new Receiver(UUID.randomUUID(), serverStub);
		viewAdpt.append("Make new Receiver for game server.\n");
		try {
			receiverStub = (IReceiver) UnicastRemoteObject.exportObject(receiver,
					IRMI_Defs.CLASS_SERVER_PORT_SERVER + 1);
			registry.rebind(lobby.getName(), receiverStub);
			lobby.addIReceiverStub(receiverStub);
			dataPacketAlgoCR = initDataPacketAlgoCR(dataPacketAlgoCR, lobby, receiverStub);
			receiver.setDataPacketAlgo(dataPacketAlgoCR);
		} catch (RemoteException e) {
			System.err.println("exception creating receiver stub for server");
			e.printStackTrace();
		}

		initTeams();
	}

	/**
	 * init team chat rooms
	 */
	public void initTeams() {
		teams.put(1, new ChatRoom(UUID.randomUUID(), "Team 1"));
		teams.put(2, new ChatRoom(UUID.randomUUID(), "Team 2"));
		teamScore.put(1, 0);
		teamScore.put(2, 0);

		// server join team
		viewAdpt.append("Server created team chat rooms.\n");
		for (Integer teamIndex : teams.keySet()) {
			ChatRoom team = teams.get(teamIndex);
			server.addChatRoom(team);
			// create receiver stub and join team
			Receiver newReceiver = new Receiver(UUID.randomUUID(), serverStub);
			viewAdpt.append("Make new Receiver for game server.\n");
			try {
				IReceiver stub = (IReceiver) UnicastRemoteObject.exportObject(newReceiver,
						IRMI_Defs.CLASS_SERVER_PORT_SERVER + 2 + teamIndex);
				registry.rebind(team.getName(), stub);
				team.addIReceiverStub(stub);
				DataPacketAlgo<String, String> algo = new DataPacketAlgo<String, String>(null);
				initDataPacketAlgoCR(algo, team, stub);
				installGameCmd();
				newReceiver.setDataPacketAlgo(algo);

				// store variables to avoid being garbage collected
				teamReceivers.put(teamIndex, newReceiver);
				teamReceiverStubs.put(teamIndex, stub);
				teamAlgos.put(teamIndex, algo);
			} catch (RemoteException e) {
				System.err.println("exception creating receiver stub for server");
				e.printStackTrace();
			}
		}

		server.setTeams(teams);
	}

	/**
	 * send game to lobby
	 */
	public void sendGame() {
		user2TeamMap = server.getUser2TeamMap();
		GameFactory fac = new GameFactory(receiverStub, viewKey);
		GameStartData msg = new GameStartData(fac);
		lobby.sendPacket(new DataPacketCR<GameStartData>(GameStartData.class, msg, receiverStub));
		viewAdpt.append("sent game to LOBBY\n");
	}

	/**
	 * send state query
	 */
	public void sendQuery() {
		if (states.size() == 0) {
			stopGame();
			return;
		}

		// pick random state
		int index = new Random().nextInt(states.size());
		String item = states.get(index);
		lobby.sendPacket(
				new DataPacketCR<QueryStateData>(QueryStateData.class, new QueryStateData(item), receiverStub));
		dict.put(stateQueryKey, item);
	}

	/**
	 * calculate score upon receiving client guess
	 * @param clientReceiver guess client
	 * @param answer state answer
	 * @return whether answer is correct
	 */
	public boolean calculateScore(IReceiver clientReceiver, String answer) {
		IUser client = null;
		try {
			client = clientReceiver.getUserStub();
			String query = dict.get(stateQueryKey);
			if (query.equals(answer)) {
				Integer teamIndex = user2TeamMap.get(client);
				teamScore.put(teamIndex, teamScore.get(teamIndex) + 1);
				lobby.sendPacket(new DataPacketCR<TextMessageData>(TextMessageData.class,
						new TextMessageData(client.getName() + " score 1 point for team " + teamIndex), receiverStub));

				// when one person answer correctly, immediately start next round
				states.remove(query);
				sendQuery();
				return true;
			} else {
				clientReceiver.receive(new DataPacketCR<TextMessageData>(TextMessageData.class,
						new TextMessageData("wrong guess"), receiverStub));
				return false;
			}
		} catch (RemoteException e) {
			System.err.println("error calculating score:\n" + e);
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * stop game: calculate score and decide winner
	 */
	public void stopGame() {
		dict.put(stateQueryKey, "empty");
		String result = "Game end!\n";
		for (int teamIndex : teamScore.keySet()) {
			result = result.concat("Team " + teamIndex + ": " + teamScore.get(teamIndex) + " point.\n");
		}
		viewAdpt.append(result);
		lobby.sendPacket(
				new DataPacketCR<TextMessageData>(TextMessageData.class, new TextMessageData(result), receiverStub));
		lobby.sendPacket(new DataPacketCR<GameOverData>(GameOverData.class, new GameOverData(), receiverStub));

		// announce winner
		int diff = teamScore.get(1) - teamScore.get(2);
		if (diff > 0) {
			result = "Winner: Team 1!";
			teams.get(1).sendPacket(new DataPacketCR<TextMessageData>(TextMessageData.class,
					new TextMessageData("Congratulations! You win!"), receiverStub));
			teams.get(2).sendPacket(new DataPacketCR<TextMessageData>(TextMessageData.class,
					new TextMessageData("Sorry! You lose!"), receiverStub));
		} else if (diff < 0) {
			result = "Winner: Team 2!";
			teams.get(1).sendPacket(new DataPacketCR<TextMessageData>(TextMessageData.class,
					new TextMessageData("Sorry! You lose!"), receiverStub));
			teams.get(2).sendPacket(new DataPacketCR<TextMessageData>(TextMessageData.class,
					new TextMessageData("Congratulations! You win!"), receiverStub));
		} else
			result = "Game tied!";
		lobby.sendPacket(
				new DataPacketCR<TextMessageData>(TextMessageData.class, new TextMessageData(result), receiverStub));

	}

	/**
	 * install game specific commands
	 */
	public void installGameCmd() {
		GameStartCmd gameStartCmd = new GameStartCmd();
		gameStartCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		dataPacketAlgoCR.setCmd(GameStartData.class, gameStartCmd);

		QueryStateCmd queryStateCmd = new QueryStateCmd(viewKey);
		queryStateCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		dataPacketAlgoCR.setCmd(QueryStateData.class, queryStateCmd);

		DataPacketCRAlgoCmd<ChooseStateData> chooseStateCmd = new DataPacketCRAlgoCmd<ChooseStateData>() {

			private static final long serialVersionUID = 3441054629534973162L;

			private transient ICRCmd2ModelAdapter modelAdpt;

			@Override
			public String apply(Class<?> index, DataPacketCR<ChooseStateData> host, String... params) {
				try {
					modelAdpt.appendMsg("has choosen the area of " + host.getData().getStateName(),
							host.getSender().getUserStub().getName());
					// model calculate score and send result
					calculateScore(host.getSender(), host.getData().getStateName());

				} catch (RemoteException e) {
					System.err.println("Exception processing choose state data");
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				this.modelAdpt = cmd2ModelAdpt;
			}
		};
		chooseStateCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		dataPacketAlgoCR.setCmd(ChooseStateData.class, chooseStateCmd);

		GameOverCmd gameOverCmd = new GameOverCmd(viewKey);
		gameOverCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		dataPacketAlgoCR.setCmd(GameOverData.class, gameOverCmd);
	}

	/**
	 * install regular chatroom receiver commands
	 * 
	 * @param algo
	 *            a datapacket algo for lobby or team receiver
	 * @param chatroom
	 *            target chatroom
	 * @param sender
	 *            corresponding receiver stub in that room
	 * @return a installed data packet algo
	 */
	public DataPacketAlgo<String, String> initDataPacketAlgoCR(DataPacketAlgo<String, String> algo, ChatRoom chatroom,
			IReceiver sender) {

		if (algo == null)
			algo = new DataPacketAlgo<String, String>(null);

		/**
		 * unknown message type goes to default command
		 */
		algo.setDefaultCmd(new DataPacketCRAlgoCmd<ICRMessageType>() {

			private static final long serialVersionUID = -7935877124304940055L;

			@Override
			public String apply(Class<?> index, DataPacketCR<ICRMessageType> host, String... params) {
				System.out.println("unknown chat room data packet type " + index.toString());

				// server does not deal with unknown message

				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}

		});

		algo.setCmd(IAddReceiverType.class, new DataPacketCRAlgoCmd<IAddReceiverType>() {

			private static final long serialVersionUID = 6966687911909827199L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IAddReceiverType> host, String... params) {
				try {
					// check receiver stub in the data packet
					IReceiver stub = host.getData().getReceiverStub();
					System.out.println("received add receiver message");
					// server does not check sender
					boolean isNew = chatroom.addIReceiverStub(stub);
					if (isNew) {
						cmd2ModelAdpt_CR.appendMsg(" has joined room: " + chatroom.getName(),
								stub.getUserStub().getName());
						chatroom.sendPacket(new DataPacketCR<IAddReceiverType>(IAddReceiverType.class,
								new AddReceiverData(stub), sender));
					}

				} catch (RemoteException e) {
					try {
						host.getSender().receive(new DataPacketCR<ICRRejectionStatusType>(ICRRejectionStatusType.class,
								new CRRejectionStatusData(host, e.getMessage()), sender));
					} catch (RemoteException e1) {
						System.err.println("Exception sending rejection: \n" + e1);
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(IRemoveReceiverType.class, new DataPacketCRAlgoCmd<IRemoveReceiverType>() {

			private static final long serialVersionUID = -7144544693151711678L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IRemoveReceiverType> host, String... params) {
				lobby.removeIReceiverStub(host.getData().getReceiverStub());
				try {
					cmd2ModelAdpt_CR.appendMsg(" has left the room!",
							host.getData().getReceiverStub().getUserStub().getName());
				} catch (RemoteException e) {
					System.err.println("Exception while removing receiver: \n" + e);
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(IRequestCmdType.class, new DataPacketCRAlgoCmd<IRequestCmdType>() {

			private static final long serialVersionUID = -7144544693151711678L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IRequestCmdType> host, String... params) {
				System.out.println("receive request for cmd");
				if (dataPacketAlgoCR.getCmd(host.getData().getCmdId()) == null) {
					try {
						host.getSender().receive(new DataPacketCR<ICRExceptionStatusType>(ICRExceptionStatusType.class,
								new CRExceptionStatusData(host, "no corresponding command"), sender));
					} catch (RemoteException e) {
						System.err.println("Exception sending exception failure: \n" + e);
						e.printStackTrace();
					}
					return null;
				}

				try {
					host.getSender()
							.receive(new DataPacketCR<ICRInstallCmdType>(ICRInstallCmdType.class,
									new CRInstallCmdData(
											(DataPacketCRAlgoCmd<?>) dataPacketAlgoCR.getCmd(host.getData().getCmdId()),
											host.getData().getCmdId()),
									sender));
				} catch (RemoteException e) {
					System.err.println("Exception installing requested command: \n" + e);
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		algo.setCmd(ICRInstallCmdType.class, new DataPacketCRAlgoCmd<ICRInstallCmdType>() {

			private static final long serialVersionUID = -7144544693151711678L;

			@Override
			public String apply(Class<?> index, DataPacketCR<ICRInstallCmdType> host, String... params) {
				System.out.println("receive cmd to install in chat room");

				// server does not install unknown command

				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		CRExceptionStatusCmd exceptionCmd = new CRExceptionStatusCmd();
		exceptionCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		algo.setCmd(ICRExceptionStatusType.class, exceptionCmd);

		CRRejectionStatusCmd rejectionCmd = new CRRejectionStatusCmd();
		exceptionCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		algo.setCmd(ICRRejectionStatusType.class, rejectionCmd);

		/**
		 * predefined commands for text and image
		 */
		TextMessageCmd stringCmd = new TextMessageCmd();
		stringCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		algo.setCmd(TextMessageData.class, stringCmd);

		ImageIconCmd imageIconCmd = new ImageIconCmd();
		imageIconCmd.setCmd2ModelAdpt(cmd2ModelAdpt_CR);
		algo.setCmd(ImageMessageData.class, imageIconCmd);

		return algo;
	}

	/**
	 * init user data packet algo
	 */
	public void initDataPacketAlgoUser() {
		/**
		 * unknown message type goes to default command
		 */
		dataPacketAlgoUser.setDefaultCmd(new DataPacketUserAlgoCmd<IUserMessageType>() {

			private static final long serialVersionUID = -7935877124304940055L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IUserMessageType> host, String... params) {
				// do not accept unknown data packet at user level
				System.out.println("ignored unknown user data packet type " + index.toString());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		dataPacketAlgoUser.setCmd(IUserInstallCmdType.class, new DataPacketUserAlgoCmd<IUserInstallCmdType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2584816089372155760L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IUserInstallCmdType> host, String... params) {
				System.out.println("ignored user install cmd message: " + host.getData().getCmdId().toString());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		dataPacketAlgoUser.setCmd(IInvitationType.class, new DataPacketUserAlgoCmd<IInvitationType>() {

			private static final long serialVersionUID = -1077314443648529256L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IInvitationType> host, String... params) {
				System.out.println("ignored invitation to room:" + host.getData().getChatRoom().getName());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}
		});

		dataPacketAlgoUser.setCmd(IQuitType.class, new DataPacketUserAlgoCmd<IQuitType>() {

			private static final long serialVersionUID = -5132083404368159574L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IQuitType> host, String... params) {
				server.removeUser(host.getData().getUserStub());
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
			}

		});

		UserExceptionStatusCmd exceptionCmd = new UserExceptionStatusCmd();
		exceptionCmd.setCmd2ModelAdpt(cmd2ModelAdpt_User);
		dataPacketAlgoUser.setCmd(IUserExceptionStatusType.class, exceptionCmd);

		UserRejectionStatusCmd rejectionCmd = new UserRejectionStatusCmd();
		exceptionCmd.setCmd2ModelAdpt(cmd2ModelAdpt_User);
		dataPacketAlgoUser.setCmd(IUserRejectionStatusType.class, rejectionCmd);

	}

	/**
	 * init state query: can choose between different difficulty
	 */
	public void initStates() {
		// easy
		//		states = new LinkedList<String>(Arrays.asList("California", "Texas", "Utah"));

		// medium
		states = new LinkedList<String>(Arrays.asList("California", "Texas", "Utah", "Nevada", "Arizona", "Washington",
				"Oregon", "Florida", "Illinois", "Colorado"));

		// hard
		//		states = new LinkedList<String>(Arrays.asList("California", "Texas", "Utah", "Nevada", "Arizona", "Washington",
		//				"Oregon", "Florida", "Illinois", "Colorado", "Oklahoma", "Pennsylvania", "South Dakota",
		//				"New Hampshire", "West Virginia", "New Jersey", "North Dakota"));

	}

}