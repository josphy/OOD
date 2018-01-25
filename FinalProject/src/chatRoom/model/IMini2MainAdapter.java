package chatRoom.model;

import java.awt.Container;

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

	/**
	 * build a scrollable panel in tabbed panel
	 * @param label tab label
	 * @return panel in the new tab
	 */
	public Container buildScrollablePnl(String label);

	/**
	 * build a unscrollable panel in tabbed panel
	 * @param label tab label
	 * @return panel in the new tab
	 */
	public Container buildUnscrollablePnl(String label);

}
