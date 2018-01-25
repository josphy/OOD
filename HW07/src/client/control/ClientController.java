package client.control;

import java.awt.EventQueue;

import client.model.ClientModel;
import client.model.ILocalTaskViewAdapter;
import client.view.ClientGUI;
import client.view.IModelAdapter;
import provided.client.model.taskUtils.ITaskFactory;

/**
 * Controller of client side MVC model
 * @author Haoshan Zou, Xiaojun Wu
 */
public class ClientController {

	/**
	 * view of client MVC
	 */
	private ClientGUI<ITaskFactory<?>> view;

	/**
	 * model of client MVC
	 */
	private ClientModel model;

	/**
	 * Constructor of the controller class. Instantiates and connects the model and
	 * the view.
	 */
	public ClientController() {

		view = new ClientGUI<ITaskFactory<?>>(new IModelAdapter<ITaskFactory<?>>() {

			@Override
			public ITaskFactory<?> addTask(String taskname) {
				return model.makeITaskFac(taskname);
			}

			@Override
			public String runTask(ITaskFactory<?> selectedTask, String param) {
				return model.runTask(selectedTask.make(param));
			}

			@Override
			public ITaskFactory<?> combineTask(ITaskFactory<?> selectedTask1, ITaskFactory<?> selectedTask2) {
				return model.combineTaskFacs(selectedTask1, selectedTask2);

			}

			@Override
			public String connect(String ip) {
				return model.connectTo(ip);
			}

			@Override
			public void sendMsg(String text) {
				model.sendMsgToComputeEngine(text);
			}

			@Override
			public void quit() {
				model.stop();
			}
		});

		model = new ClientModel(new ILocalTaskViewAdapter() {

			@Override
			public void append(String s) {
				view.append(s);
			}

			@Override
			public void setRemoteHost(String host) {
				view.setRemoteHost(host);
			}

		});
	}

	/**
	 * Starts the view then the model.
	 */
	public void start() {
		view.append("[ClientController.start()] " + "user.dir = " + System.getProperty("user.dir"));
		view.start();
		model.start();
		view.addTask("provided.client.model.task.Pi2");
		view.addTask("provided.client.model.task.GetInfo");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientController controller = new ClientController();
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
