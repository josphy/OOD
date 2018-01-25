package rmiServer.model;

import java.rmi.RemoteException;

import provided.compute.ICompute;
import provided.compute.ITask;
import provided.compute.IRemoteTaskViewAdapter;
import provided.compute.ILocalTaskViewAdapter;

/**
 * Following defines the RMI server object that implements ICompute interface,
 * used in this RMI server system.
 * 
 * @author Xiaojun Wu
 * @author Haoshan Zou
 */
public class RMIComputeServer implements ICompute {
	/**
	 * The adapter that used to communicate local system(server) view.  
	 */
	private ILocalTaskViewAdapter localTestViewAdapter;

	/**
	 * The adapter that allows client to communicate with server view.
	 */
	private IRemoteTaskViewAdapter serverTVAStub;

	/**
	 * The model of this RMI server system.
	 */
	private IRMIServerEngineModel RMIServerEngine;

	/**
	 * Constructor.
	 * 
	 * @param localTestViewAdapter The adapter that used to communicate with local system(server) view.
	 * @param serverTVAStub The adapter that allows client to communicate with server view.
	 * @param RMIServerEngine The model of this RMI server system.
	 */
	public RMIComputeServer(ILocalTaskViewAdapter localTestViewAdapter, IRemoteTaskViewAdapter serverTVAStub,
			IRMIServerEngineModel RMIServerEngine) {
		this.localTestViewAdapter = localTestViewAdapter;
		this.serverTVAStub = serverTVAStub;
		this.RMIServerEngine = RMIServerEngine;
	}

	@Override
	/**
	 * execute task and return result
	 */
	public <T> T executeTask(ITask<T> t) throws RemoteException {
		T res;
		t.setTaskViewAdapter(localTestViewAdapter);
		localTestViewAdapter.append("[ComputeEngine.executeTask()] Executing task: " + t + "\n");
		res = t.execute();
		localTestViewAdapter.append("[ComputeEngine.executeTask()] Task result = " + res + "\n");
		return res;
	}

	@Override
	/**
	 * receive client view stub and return server view stub
	 */
	public IRemoteTaskViewAdapter setTextAdapter(IRemoteTaskViewAdapter clientTVAStub) throws RemoteException {
		RMIServerEngine.setClientTVAStub(clientTVAStub);
		clientTVAStub.append("Hi, I am Xiaojun's RMI server");
		return serverTVAStub;
	}
}
