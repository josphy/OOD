package concrete.data.chatroom;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.datatype.chatroom.ICRRejectionStatusType;

/**
 * visitor command for CRRejectionStatusData type data packet
 */
public class CRRejectionStatusCmd extends DataPacketCRAlgoCmd<ICRRejectionStatusType>{

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -7144544693151711678L;
	
	/**
	 * command to model adapter
	 */
	@SuppressWarnings("unused")
	private ICRCmd2ModelAdapter cmd2ModelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketCR<ICRRejectionStatusType> host, String... params) {
		System.out.println("received rejection failure");
		System.out.println(host.getData().getFailureInfo());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
}
