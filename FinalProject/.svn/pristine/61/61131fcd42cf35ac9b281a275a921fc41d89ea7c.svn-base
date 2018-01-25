package concrete.data.chatroom;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.datatype.chatroom.ICRExceptionStatusType;

/**
 * visitor command for CRExceptionStatusData type data packet
 */
public class CRExceptionStatusCmd extends DataPacketCRAlgoCmd<ICRExceptionStatusType>{

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -7144544693151711678L;
	
	/**
	 * chat room command to model adapter
	 */
	@SuppressWarnings("unused")
	private transient ICRCmd2ModelAdapter cmd2ModelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketCR<ICRExceptionStatusType> host, String... params) {
		System.out.println("received exception failure");
		System.out.println(host.getData().getFailureInfo());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
