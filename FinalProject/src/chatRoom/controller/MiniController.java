package chatRoom.controller;

import java.awt.Container;
import java.rmi.RemoteException;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import chatRoom.model.ChatRoomModel;
import chatRoom.model.IMiniViewAdapter;
import chatRoom.model.IMini2MainAdapter;
import chatRoom.view.IChatRoomModelAdapter;
import chatRoom.view.MiniView;
import common.IChatRoom;
import common.IReceiver;
import concrete.Receiver;
import provided.mixedData.MixedDataDictionary;

/**
 * mini controller (chat room)
 */
public class MiniController {

	/**
	 * mini model
	 */
	private ChatRoomModel model;

	/**
	 * mini view
	 */
	private MiniView view;

	/**
	 * constructor
	 * @param chatroom chatroom object for this mini MVC
	 * @param receiverStub user's receiver stub in this chat room
	 * @param receiver receiver instance
	 * @param dict shared mixed data dict in one application
	 * @param mainAdpt mini to main adapter
	 */
	public MiniController(IChatRoom chatroom, IReceiver receiverStub, Receiver receiver, MixedDataDictionary dict,
			IMini2MainAdapter mainAdpt) {

		model = new ChatRoomModel(chatroom, receiverStub, receiver, dict, new IMiniViewAdapter() {

			@Override
			public void append(String s) {
				view.appendMsg(s);

			}

			@Override
			public void setUserList(Collection<IReceiver> receiverStubs) {
				view.setUserList(receiverStubs);
			}

			@Override
			public Container getPnl(String label) {
				return view.getPnl(label);
			}

		}, mainAdpt);

		view = new MiniView(new IChatRoomModelAdapter() {

			@Override
			public void sendMsg(String text) {
				model.sendText(text);

			}

			@Override
			public void sendImg(ImageIcon image) {
				model.sendImg(image);

			}

			@Override
			public void quit() {
				model.leaveRoom();

			}

			@Override
			public void invite(String ip) {
			}

		});

	}

	/**
	 * get mini GUI
	 * @return content panel
	 */
	public JPanel getMiniGUI() {
		return view.getMiniGUI();
	}

	/**
	 * start mini model
	 */
	public void start() {
		try {
			model.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		view.start();

	}

	/**
	 * get mini model
	 * @return model
	 */
	public ChatRoomModel getMiniModel() {
		return model;
	}

	/**
	 * leave current room
	 */
	public void leaveRoom() {
		model.leaveRoom();

	}

}
