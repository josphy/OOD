package rmiServer.controller;

import java.awt.EventQueue;

import rmiServer.view.*;
import rmiServer.model.*;
import provided.compute.ILocalTaskViewAdapter;

/**
 * Following defines controller of RMI server system.
 * 
 * @author Xiaojun Wu
 * @author Haoshan Zou
 */
public class RMIServerController {
	/**
	 * The RML server model of this RML server system.
	 */
	private IRMIServerEngineModel RMIServerEngineModel;

	/**
	 * The main frame of this RMI server system.
	 */
	private RMIServerFrame RMIServerMainFrame;

	/**
	 * Constructor.
	 */
	public RMIServerController() {
		RMIServerEngineModel = new RMIServerModel(new ILocalTaskViewAdapter() {
			@Override
			public void append(String s) {
				RMIServerMainFrame.addText(s);
			}
		});

		RMIServerMainFrame = new RMIServerFrame(new IRMIServerView2ModelAdapter() {
			@Override
			public void msgToClientView(String msg) {
				RMIServerEngineModel.sendMsgToClient(msg);
			}

			@Override
			public void quit() {
				RMIServerEngineModel.stop();
			}
		});
	}

	/**
	 * This method starts the RMI server application.
	 */
	public void start() {
		RMIServerMainFrame.start();
		RMIServerEngineModel.start();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RMIServerController controller = new RMIServerController();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
