package concrete.data.user;

import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;
import common.IUserCmd2ModelAdapter;
import common.datatype.user.IUserExceptionStatusType;

/**
 * visitor command for UserExceptionStatusData.class data packet
 */
public class UserExceptionStatusCmd extends DataPacketUserAlgoCmd<IUserExceptionStatusType>{

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 7170748228579470681L;
	
	/**
	 * user command to model adapter
	 */
	@SuppressWarnings("unused")
	private transient IUserCmd2ModelAdapter cmd2ModelAdpt;

	@Override
	public String apply(Class<?> index, DataPacketUser<IUserExceptionStatusType> host, String... params) {
		System.out.println("received exception failure");
		System.out.println(host.getData().getFailureInfo());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;	
	}

}
