package server.view;

/**
 * server view to model adapter
 */
public interface IServerView2ModelAdapter {

	/**
	 * start game
	 */
	public void startGame();

	/**
	 * terminate game
	 */
	public void stopGame();

	/**
	 * send query
	 */
	public void sendQuery();

}
