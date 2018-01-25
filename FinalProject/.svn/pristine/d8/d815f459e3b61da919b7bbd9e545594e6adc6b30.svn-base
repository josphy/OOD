package concrete.data.user;

import common.IChatRoom;
import common.datatype.user.IInvitationType;

/**
 * Data class for IInvitationType data packet
 */
public class InvitationData implements IInvitationType {
	
	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 6230736382704846133L;
	
	/**
	 * chat room to be invited to
	 */
	private IChatRoom chatroom;
	
	/**
	 * constructor to set chat room
	 * @param chatroom invited to this room
	 */
	public InvitationData(IChatRoom chatroom) {
		this.chatroom = chatroom;
	}

	@Override
	public IChatRoom getChatRoom() {
		return this.chatroom;
	}

}
