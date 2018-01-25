package game.data;

import java.rmi.RemoteException;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;

/**
 * visitor command for ChooseStateData.class
 */
public class ChooseStateCmd extends DataPacketCRAlgoCmd<ChooseStateData> {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -8360033921529626266L;

	/**
	 * adapter to chat room model
	 */
	private transient ICRCmd2ModelAdapter modelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketCR<ChooseStateData> host, String... params) {
		try {
			modelAdpt.appendMsg("has choosen the area of " + host.getData().getStateName(),
					host.getSender().getUserStub().getName());
			//model calculate score and send result

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

}
