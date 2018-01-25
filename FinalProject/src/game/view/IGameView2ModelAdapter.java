package game.view;

/**
 * Game view's adapter to game model
 */
public interface IGameView2ModelAdapter {

	/**
	 * submit answer to server
	 * @return if submit success, return true
	 */
	public boolean submitAnswer();

}
