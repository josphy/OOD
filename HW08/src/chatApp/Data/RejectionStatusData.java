package chatApp.Data;

import common.DataPacketChatRoom;
import common.IRejectionStatusType;

/**
 * Data class for IRejectionStatusType data packet
 */
public class RejectionStatusData implements IRejectionStatusType {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 7673668347122823839L;
	
	/**
	 * holds original data
	 */
	DataPacketChatRoom<?> originalData;
	
	/**
	 * failure description
	 */
	String failureInfo;

	/**
	 * constructor to install externally provided data
	 * @param originalData original data received
	 * @param failureInfo failure description generated
	 */
	public RejectionStatusData(DataPacketChatRoom<?> originalData, String failureInfo) {
		this.originalData = originalData;
		this.failureInfo = failureInfo;
	}

	@Override
	/**
	 * getter of original data
	 */
	public DataPacketChatRoom<?> getOriginalData() {
		return this.originalData;
	}

	@Override
	/**
	 * getter of failure description
	 */
	public String getFailureInfo() {
		return this.failureInfo;
	}

}
