package concrete;

import java.rmi.RemoteException;
import java.util.UUID;

import common.ICRMessageType;
import common.DataPacketCR;
import common.IReceiver;
import common.IUser;
import provided.datapacket.DataPacketAlgo;

/**
 * Receiver local implementation for network API IReceiver
 */
public class Receiver implements IReceiver {

	/**
	 * unique id
	 */
	private UUID id;

	/**
	 * corresponding userStub
	 */
	private IUser userStub;

	/**
	 * local visitor to process incoming data packets
	 */
	private transient DataPacketAlgo<String, String> myDataPacketAlgo;

	/**
	 * constructor
	 * 
	 * @param id
	 *            unique id
	 * @param userStub
	 *            corresponding user stub
	 */
	public Receiver(UUID id, IUser userStub) {
		this.id = id;
		this.userStub = userStub;
	}

	/**
	 * set externally provided visitor algo
	 * @param algo provided in chat room model
	 */
	public void setDataPacketAlgo(DataPacketAlgo<String, String> algo) {
		this.myDataPacketAlgo = algo;
	}

	@Override
	/**
	 * receive data packet and execute
	 */
	public <T extends ICRMessageType> void receive(DataPacketCR<T> data) throws RemoteException {
		data.execute(myDataPacketAlgo);
	}

	@Override
	/**
	 * get user stub
	 */
	public IUser getUserStub() throws RemoteException {
		return userStub;
	}

	@Override
	/**
	 * get unique id
	 */
	public UUID getUUID() throws RemoteException {
		return id;
	}

}
