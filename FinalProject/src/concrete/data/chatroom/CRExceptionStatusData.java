package concrete.data.chatroom;

import common.DataPacketCR;
import common.datatype.chatroom.ICRExceptionStatusType;

/**
 * Data class for IExceptionStatusType data packet
 */
public class CRExceptionStatusData implements ICRExceptionStatusType {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 1238611075971361530L;
	
	/**
	 * hold original data
	 */
	DataPacketCR<?> originalData;
	
	/**
	 * hold failure info
	 */
	String failureInfo;

	/**
	 * constructor to set externally provided information
	 * @param originalData original data received
	 * @param failureInfo failure description
	 */
	public CRExceptionStatusData(DataPacketCR<?> originalData, String failureInfo) {
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
