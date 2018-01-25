package chatApp.Data;

import common.IAddReceiverType;
import common.IReceiver;

/**
 * Data class for IAddReceiverType data packet
 */
public class AddReceiverData implements IAddReceiverType {
	
	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 8131659606791620349L;
	
	/**
	 * hold receiver stub
	 */
	private IReceiver receiverStub;

	/**
	 * Constructor to install externally provided IReceiver stub
	 * @param stub my receiver stub to add to remote user
	 */
	public AddReceiverData(IReceiver stub) {
		this.receiverStub = stub;
	}

	@Override
	/**
	 * getter of the stub
	 */
	public IReceiver getReceiverStub() {
		return receiverStub;
	}

}
