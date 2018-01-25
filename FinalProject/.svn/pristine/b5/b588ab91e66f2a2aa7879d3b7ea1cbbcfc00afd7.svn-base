package chatRoom.model;

import java.awt.Container;
import java.util.Collection;

import common.IReceiver;

/**
 * mini model to mini view adapter
 */
public interface IMiniViewAdapter {

	/**
	 * append string
	 * @param s message to display
	 */
	public void append(String s);

	/**
	 * get panel by label
	 * @param label panel label
	 * @return panel correponding panel
	 */
	public Container getPnl(String label);

	/**
	 * set display user list
	 * @param receiverStubs list of receiver stubs in this chat room
	 */
	public void setUserList(Collection<IReceiver> receiverStubs);

	/**
	 * null object
	 */
	public static IMiniViewAdapter NULL_OBJECT = new IMiniViewAdapter() {

		@Override
		public void append(String s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setUserList(Collection<IReceiver> iReceiverStubs) {
			// TODO Auto-generated method stub

		}

		@Override
		public Container getPnl(String label) {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
