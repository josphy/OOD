package concrete.data.user;

import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;
import common.IUserCmd2ModelAdapter;
import common.datatype.user.IUserRejectionStatusType;

/**
 * command for IUserRejectionStatusType data packet
 */
public class UserRejectionStatusCmd extends DataPacketUserAlgoCmd<IUserRejectionStatusType>{
	
	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -8940242654722813892L;
	
	/**
	 * user command to model adapter
	 */
	@SuppressWarnings("unused")
	private transient IUserCmd2ModelAdapter cmd2ModelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketUser<IUserRejectionStatusType> host, String... params) {
		System.out.println("received rejection failure");
		System.out.println(host.getData().getFailureInfo());
		return null;
	}


	@Override
	public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;		
	}

}
