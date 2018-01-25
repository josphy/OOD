package game.data;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import game.view.GameView;
import provided.mixedData.MixedDataKey;

/**
 * visitor command for GameOverData.class
 */
public class GameOverCmd extends DataPacketCRAlgoCmd<GameOverData> {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 3871415137296763040L;

	/**
	 * key to retrieve game view from client mixed data dict
	 */
	private MixedDataKey<GameView> viewKey;

	/**
	 * adapter to chat room model
	 */
	private transient ICRCmd2ModelAdapter modelAdpt;

	/**
	 * constructor
	 * @param key game view mixed data key
	 */
	public GameOverCmd(MixedDataKey<GameView> key) {
		this.viewKey = key;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<GameOverData> host, String... params) {
		GameView gameView = modelAdpt.get(viewKey);
		if (gameView != null) {
			gameView.alert("Game end! See LOBBY for result!");
			gameView.showStateName("Game end!");
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.modelAdpt = cmd2ModelAdpt;
	}

}
