package chatApp.view;

/**
 * main view to main model adapter
 */
public interface IMainView2ModelAdapter {

	/**
	 * login
	 * @param port self defined port
	 * @param username user defined display name
	 */
	public void login(int port, String username);

	/**
	 * create chat room
	 * @param name chat room name
	 */
	public void createChatRoom(String name);

	/**
	 * connect to remote host
	 * @param remoteHost host ip
	 */
	public void connect(String remoteHost);

	/**
	 * request room list from remote user
	 * @param selectedUser selected user in drop list
	 */
	public void requestRoom(Object selectedUser);

	/**
	 * send room to remote user
	 * @param selectedUser selected user from drop list
	 */
	public void sendRoom(Object selectedUser);

	/**
	 * join room
	 * @param selectedRoom chat room name
	 */
	public void join(Object selectedRoom);

	/**
	 * quit the app
	 */
	public void quit();

	/**
	 * null object
	 */
	public static IMainView2ModelAdapter NULL_OBJECT = new IMainView2ModelAdapter() {

		@Override
		public void login(int port, String username) {
			// TODO Auto-generated method stub

		}

		@Override
		public void createChatRoom(String name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void connect(String remoteHost) {
			// TODO Auto-generated method stub

		}

		@Override
		public void requestRoom(Object selectedUser) {
			// TODO Auto-generated method stub

		}

		@Override
		public void sendRoom(Object selectedUser) {
			// TODO Auto-generated method stub

		}

		@Override
		public void join(Object selectedRoom) {
			// TODO Auto-generated method stub

		}

		@Override
		public void quit() {
			// TODO Auto-generated method stub

		}

	};

}
