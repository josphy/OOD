package chatRoom.model;

import java.rmi.RemoteException;

import javax.swing.ImageIcon;

import chatApp.Data.RemoveReceiverData;
import common.DataPacketChatRoom;
import common.IRemoveReceiverType;
import common.IChatRoom;
import common.IReceiver;

/**
 * chat room model (mini model)
 */
public class ChatRoomModel {

	/**
	 * adater to mini view
	 */
	public IMiniViewAdapter viewAdpt = IMiniViewAdapter.NULL_OBJECT; //local view adapter

	/**
	 * corresponding chatroom
	 */
	private IChatRoom chatroom; //API - to send

	/**
	 * current user's receiver stub for this room
	 */
	private IReceiver myReceiver; //API - to receive

	/**
	 * adapter to main model
	 */
	private IMini2MainAdapter mainAdpt;

	/**
	 * constructor
	 * @param chatroom
	 * @param receiverStub
	 * @param viewAdpt 
	 * @param mainAdpt
	 */
	public ChatRoomModel(IChatRoom chatroom, IReceiver receiverStub, IMiniViewAdapter viewAdpt,
			IMini2MainAdapter mainAdpt) {
		this.chatroom = chatroom;
		this.viewAdpt = viewAdpt;
		this.myReceiver = receiverStub;
		this.mainAdpt = mainAdpt;
	}

	/**
	 * start mini model
	 * @throws RemoteException
	 */
	public void start() throws RemoteException {
	}

	/**
	 * current user quit the room
	 */
	public void leaveRoom() {
		chatroom.removeIReceiverStubLocally(myReceiver);
		chatroom.sendPacket(new DataPacketChatRoom<IRemoveReceiverType>(IRemoveReceiverType.class,
				new RemoveReceiverData(myReceiver), myReceiver)); //IAddReceiverType
		refreshUserList();
		viewAdpt.append("You have left this room!");
		mainAdpt.leaveRoom(chatroom);
		//		System.exit(0);	//shut down the GUI
	}

	/**
	 * send text to the room
	 * @param text
	 */
	public void sendText(String text) {
		chatroom.sendPacket(new DataPacketChatRoom<String>(String.class, text, myReceiver));
	}

	/**
	 * send image to the room
	 * @param image
	 */
	public void sendImg(ImageIcon image) {
		chatroom.sendPacket(new DataPacketChatRoom<ImageIcon>(ImageIcon.class, image, myReceiver));
	}

	/**
	 * get current chat room 
	 * @return
	 */
	public IChatRoom getChatRoom() {
		return this.chatroom;
	}

	/**
	 * refresh user list in view
	 */
	public void refreshUserList() {
		viewAdpt.setUserList(chatroom.getIReceiverStubs());
	}

}
