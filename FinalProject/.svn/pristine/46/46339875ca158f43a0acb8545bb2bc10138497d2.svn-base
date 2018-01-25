package concrete.data;

import common.datatype.IRequestCmdType;

/**
 * Data class for IRequestCmdType data packet
 */
public class RequestCmdData implements IRequestCmdType {
	
	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = -8785166559058272881L;

	/**
	 * command index for visitor
	 */
	Class<?> cmdId;

	/**
	 * constructor to install externally provided data
	 * @param id desired command id
	 */
	public RequestCmdData(Class<?> id) {
		this.cmdId = id;
	}

	@Override
	/**
	 * getter of the id
	 */
	public Class<?> getCmdId() {
		return cmdId;
	}

}
