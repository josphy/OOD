package concrete.data.chatroom;

import common.DataPacketCR;
import common.datatype.chatroom.ICRRejectionStatusType;

/**
 * Data class for IRejectionStatusType data packet
 */
public class CRRejectionStatusData implements ICRRejectionStatusType {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 7673668347122823839L;
	
	/**
	 * holds original data
	 */
	DataPacketCR<?> originalData;
	
	/**
	 * failure description
	 */
	String failureInfo;

	/**
	 * constructor to install externally provided data
	 * @param originalData original data received
	 * @param failureInfo failure description generated
	 */
	public CRRejectionStatusData(DataPacketCR<?> originalData, String failureInfo) {
		this.originalData = originalData;
		this.failureInfo = failureInfo;
	}

	@Override
	/**
	 * getter of original data
	 */
	public DataPacketCR<?> getOriginalData() {
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
