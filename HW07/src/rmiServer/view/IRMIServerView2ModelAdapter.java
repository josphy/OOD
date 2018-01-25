package rmiServer.view;

/**
 * Following defines the adapter that the RMI server view uses to communicate with RMI server model.
 * 
 * @author Xiaojun Wu
 * @author Haoshan Zou
 */
public interface IRMIServerView2ModelAdapter {
	/**
	 * This method quits the RMI server application.
	 */
	public void quit();

	/**
	 * This method sends the msg to the client view.
	 * 
	 * @param msg The message that is sent to client view.
	 */
	public void msgToClientView(String msg);

	/**
	 * No-op singleton implementation of IRMIServerView2ModelAdapter.
	 */
	public static final IRMIServerView2ModelAdapter NULL_OBJECT = new IRMIServerView2ModelAdapter() {
		@Override
		public void quit() {
			// No-op
		}

		@Override
		public void msgToClientView(String msg) {
			// No-op
		}
	};
}
