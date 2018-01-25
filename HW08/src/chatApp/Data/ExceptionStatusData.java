package chatApp.Data;

import common.DataPacketChatRoom;
import common.IExceptionStatusType;

/**
 * Data class for IExceptionStatusType data packet
 */
public class ExceptionStatusData implements IExceptionStatusType {

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 1238611075971361530L;
	
	/**
	 * hold original data
	 */
	DataPacketChatRoom<?> originalData;
	
	/**
	 * hold failure info
	 */
	String failureInfo;

	/**
	 * constructor to set externally provided information
	 * @param originalData original data received
	 * @param failureInfo failure description
	 */
	public ExceptionStatusData(DataPacketChatRoom<?> originalData, String failureInfo) {
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