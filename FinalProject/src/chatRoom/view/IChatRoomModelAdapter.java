package chatRoom.view;

import javax.swing.ImageIcon;

/**
 * mini view to mini model adapter
 */
public interface IChatRoomModelAdapter {

	/**
	 * send text message
	 * @param text user input text message
	 */
	public void sendMsg(String text);

	/**
	 * send image
	 * @param image selected image
	 */
	public void sendImg(ImageIcon image);

	/**
	 * quit current room
	 */
	public void quit();

	/**
	 * invite user
	 * @param ip remote user ip
	 */
	public void invite(String ip);

	/**
	 * null object
	 */
	public static IChatRoomModelAdapter NULL_OBJECT = new IChatRoomModelAdapter() {

		@Override
		public void sendMsg(String text) {
			// TODO Auto-generated method stub

		}

		@Override
		public void sendImg(ImageIcon image) {
			// TODO Auto-generated method stub

		}

		@Override
		public void quit() {
			// TODO Auto-generated method stub

		}

		@Override
		public void invite(String ip) {
			// TODO Auto-generated method stub

		}

	};

}
