package chatApp.Data;

import java.awt.Color;
import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;

/**
 * command for ImageIcon.class visitor
 */
public class ImageIconCmd extends DataPacketAlgoCmd<ImageIcon>{

	/**
	 * auto generated UID
	 */
	private static final long serialVersionUID = 1586444981076490099L;
	
	/**
	 * command to model adapter
	 */
	private transient ICmd2ModelAdapter cmd2ModelAdpt;

	@Override
	/**
	 * constructor
	 */
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
		
	}

	@Override
	/**
	 * visitor command
	 */
	public String apply(Class<?> index, DataPacketChatRoom<ImageIcon> host, String... params) {
		
		cmd2ModelAdpt.buildScrollableComponent(new IComponentFactory() {

			@Override
			public Component makeComponent() {
				try {
					Component label = new JLabel(host.getSender().getUserStub().getName() + ": ");
					Component label2 = new JLabel(host.getData());
					JPanel panel = new JPanel();
					panel.add(label);
					panel.add(label2);
					panel.setBackground(Color.WHITE);
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					return panel;
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		}, "txtDialog");
		
		return null;
	}

}
