package concrete.data.chatroom;

import java.rmi.RemoteException;

import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.DataPacketCR;
import concrete.data.chatroom.TextMessageData;

/**
 * command for TextMessageData.class visitor
 */
public class TextMessageCmd extends DataPacketCRAlgoCmd<TextMessageData>{
	
	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -9071343446076818679L;
	
	/**
	 * command to model adapter
	 */
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;

	@Override
	/**
	 * visitor
	 */
	public String apply(Class<?> index, DataPacketCR<TextMessageData> host, String... params) {
		
		try {
			cmd2ModelAdpt.appendMsg(host.getData().getText(), host.getSender().getUserStub().getName());
		} catch (RemoteException e) {
			System.err.println("error displaying text message");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	/**
	 * adapter setter
	 */
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
		
	}

}
