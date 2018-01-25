package chatApp.view;

/**
 * main view to main model adapter
 */
public interface IMainView2ModelAdapter {

	/**
	 * login
	 * @param port
	 * @param username
	 */
	public void login(int port, String username);

	/**
	 * create chat room
	 * @param port
	 * @param name
	 */
	public void createChatRoom(int port, String name);

	/**
	 * connect to remote host
	 * @param remoteHost
	 */
	public void connect(String remoteHost);

	/**
	 * request room list from remote user
	 * @param selectedUser
	 */
	public void requestRoom(Object selectedUser);

	/**
	 * send room to remote user
	 * @param selectedUser
	 */
	public void sendRoom(Object selectedUser);

	/**
	 * join room
	 * @param port
	 * @param selectedRoom
	 */
	public void join(int port, Object selectedRoom);

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
		public void createChatRoom(int port, String name) {
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
		public void join(int port, Object selectedRoom) {
			// TODO Auto-generated method stub

		}

		@Override
		public void quit() {
			// TODO Auto-generated method stub

		}

	};

}
