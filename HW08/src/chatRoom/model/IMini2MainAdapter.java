package chatRoom.model;

import common.IChatRoom;

/**
 * mini model to main model adapter
 */
public interface IMini2MainAdapter {

	/**
	 * leave current room
	 * @param chatroom current room
	 */
	public void leaveRoom(IChatRoom chatroom);

}