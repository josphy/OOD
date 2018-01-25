package game.data;

import java.rmi.RemoteException;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import game.view.GameView;
import provided.mixedData.MixedDataKey;

/**
 * visitor command for QueryStateData.class
 */
public class QueryStateCmd extends DataPacketCRAlgoCmd<QueryStateData> {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 8964339712375994457L;

	/**
	 * mixed data key to get game view
	 */
	private MixedDataKey<GameView> viewKey;

	/**
	 * adapter to chat room model
	 */
	private transient ICRCmd2ModelAdapter modelAdpt;

	/**
	 * constructor
	 * @param key game view key
	 */
	public QueryStateCmd(MixedDataKey<GameView> key) {
		this.viewKey = key;
	}

	@Override
	public String apply(Class<?> index, DataPacketCR<QueryStateData> host, String... params) {
		try {
			modelAdpt.appendMsg("please select state: " + host.getData().getState(),
					host.getSender().getUserStub().getName());
			GameView gameView = modelAdpt.get(viewKey);
			if (gameView != null)
				gameView.showStateName("please select " + host.getData().getState());
		} catch (RemoteException e) {
			System.err.println("exception proccessing query state data:\n" + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.modelAdpt = cmd2ModelAdpt;
	}

}
