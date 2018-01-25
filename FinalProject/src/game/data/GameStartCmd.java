package game.data;

import java.rmi.RemoteException;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;

/**
 * Visitor command to for GameStartData.class
 */
public class GameStartCmd extends DataPacketCRAlgoCmd<GameStartData> {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 8250064191783015619L;

	/**
	 * adapter to chat room model
	 */
	private transient ICRCmd2ModelAdapter modelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketCR<GameStartData> host, String... params) {

		try {
			modelAdpt.appendMsg("has sent you a game!\n", host.getSender().getUserStub().getName());
		} catch (RemoteException e) {
			System.err.println("Exception getting sender's name");
			e.printStackTrace();
		}
		host.getData().setCmd2ModelAdapter(modelAdpt);
		modelAdpt.buildNonScrollableComponent(host.getData().getFactory(), "GUESS STATE");
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.modelAdpt = cmd2ModelAdpt;
	}

}
