package client.model;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import client.model.task.MultiTaskFactory;
import provided.client.model.taskUtils.ITaskFactory;
import provided.client.model.taskUtils.SingletonTaskFactoryLoader;
import provided.compute.ICompute;
import provided.compute.IRemoteTaskViewAdapter;
import provided.compute.ITask;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * Model of the client MVC
 * 
 * @author Haoshan Zou, Xiaojun Wu
 */
public class ClientModel {

	/**
	 * adapter to client view
	 */
	private ILocalTaskViewAdapter _viewAdapter = ILocalTaskViewAdapter.NULL_OBJECT;

	/**
	 * stub to server view
	 */
	private IRemoteTaskViewAdapter serverViewStub;

	/**
	 * view adapter for server to connect to client view
	 */
	private IRemoteTaskViewAdapter clientView = new IRemoteTaskViewAdapter() {
		@Override
		public void append(String s) throws RemoteException {
			_viewAdapter.append("[SERVER]" + s);
		}
	};

	/**
	 * stub provided for server to connect to clientView
	 */
	private IRemoteTaskViewAdapter clientViewStub = null;

	/**
	 * holds registry stub
	 */
	private Registry registry;

	/**
	 * A reference to the proxy stub of the remote ICompute object
	 */
	private ICompute compStub;

	/**
	 * Constructor of the model class
	 * 
	 * @param adapter
	 *            externally provided local view adapter
	 */
	public ClientModel(ILocalTaskViewAdapter adapter) {
		this._viewAdapter = adapter;
		this.rmiUtils = new RMIUtils((s) -> {
			this._viewAdapter.append(s);
		});
	}

	/**
	 * RMI utilities for starting RMI and for getting the Registry
	 */
	private IRMIUtils rmiUtils;

	/**
	 * Starts the client model by initializing the RMI system and 
	 * creating a remote view adapter stub for use by an engine server. 
	 */
	public void start() {
		try {
			rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);
			_viewAdapter.setRemoteHost(rmiUtils.getLocalAddress());
			_viewAdapter.append("[ClientModel.start()] Ready...\n");

		} catch (SocketException | UnknownHostException e) {
			System.err.println("Exception while intializing RMI: \n" + e);
			e.printStackTrace();
			System.exit(-1);
		}

		try {
			clientViewStub = (IRemoteTaskViewAdapter) UnicastRemoteObject.exportObject(clientView,
					IRemoteTaskViewAdapter.BOUND_PORT_CLIENT);
		} catch (RemoteException e) {
			System.err.println("Exception while creating remote view adapter stub: \n" + e);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Stops the client model by using the IRMIUtils to stop the RMI system. This
	 * automatically stops class file server. This MUST be called before exiting the
	 * system!
	 */
	public void stop() {
		rmiUtils.stopRMI();
		System.exit(0);
	}

	/**
	 * Connects to the given remote host's Registry and retrieves the stub to the
	 * ICompute object bound to the ICompute.BOUND_NAME name in the remote Registry
	 * on port IRMI_Defs.REGISTRY_PORT.
	 * @param remoteHost
	 *            The IP address or host name of the remote server.
	 * @return A status string on the connection.
	 */
	public String connectTo(String remoteHost) {
		_viewAdapter.append("Locating registry at " + remoteHost + "...");
		registry = rmiUtils.getRemoteRegistry(remoteHost);
		_viewAdapter.append("[ClientModel.connectTo()] Found registry: " + registry);

		try {
			compStub = (ICompute) registry.lookup(ICompute.BOUND_NAME);
		} catch (RemoteException | NotBoundException e) {
			System.err.println("Exception while retrieving ICompute stub: \n" + e);
			e.printStackTrace();
			System.exit(-1);
		}
		_viewAdapter.append("[ClientModel.connectTo()] Found remote Compute object: " + compStub + "\n");

		try {
			serverViewStub = compStub.setTextAdapter(clientViewStub);
			serverViewStub.append("Hello from hz56's client!\n");
		} catch (RemoteException e) {
			System.err.println("Exception while setting up remote view connection: \n" + e);
			e.printStackTrace();
			System.exit(-1);
		}
		_viewAdapter.append("Got text adapter: " + serverViewStub + "\n");

		return "Connection to " + remoteHost + " established!\n";
	}

	/**
	 * Sends a string message to the connected compute engine using the
	 * IRemoteTaskViewAdapter STUB received from the engine server. This message
	 * should also be echoed to the local user interface.
	 * 
	 * @param text
	 *            The message to be sent
	 */
	public void sendMsgToComputeEngine(String text) {
		try {
			serverViewStub.append(text);
		} catch (RemoteException e) {
			System.out.println("Server Exception: " + e);
			e.printStackTrace();
			System.exit(-1);
		}
		_viewAdapter
				.append("[ClientModel.sendMsgToComputeEngine] Sending msg to connected remote host = \"" + text + "\"");
	}

	/**
	 * Runs the given ITask on the remote engine server, returning the String
	 * formatted result, which is produced using the given ITask's
	 * ITaskResultFormatter object.
	 * 
	 * @param task
	 *            The task to run on the remote engine server.
	 * @param <T>
	 *            The return type of the give ITask
	 * @return A string representation of the task results, using the task's
	 *         formatter object.
	 */
	public <T> String runTask(ITask<T> task) {
		T result = null;
		try {
			result = compStub.executeTask(task);
		} catch (RemoteException e) {
			System.err.println("Task execute exception: \n" + e);
			e.printStackTrace();
		}
		return task.getFormatter().format(result);
	}

	/**
	 * make a ITaskFactory item for the drop list by user input
	 * @param taskname user input task full name
	 * @return ITaskFactory item for the GUI drop list
	 */
	public ITaskFactory<?> makeITaskFac(String taskname) {
		return SingletonTaskFactoryLoader.SINGLETON.load(taskname);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * make a combined ITaskFactory item for the drop list by combining selected tasks
	 * @param selectedTask1 task from drop list 1
	 * @param selectedTask2 task from drop list 2
	 * @return combined MultiTaskFactory item
	 */
	public ITaskFactory<?> combineTaskFacs(ITaskFactory<?> selectedTask1, ITaskFactory<?> selectedTask2) {
		return new MultiTaskFactory(selectedTask1, selectedTask2);
	}

}
