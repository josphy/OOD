package rmiServer.model;

import provided.compute.IRemoteTaskViewAdapter;

/**
 * Following defines the interface that provides the operations that a RMI server model should have.
 * 
 * @author Xiaojun Wu
 * @author Haoshan Zou
 */
public interface IRMIServerEngineModel {
	/**
	 * Start the RMI Server (ICompute engine): <br/>
	 * 1. (Do this FIRST!) Use the provided IRMIUtils to start the RMI system, using port = IRMI_Defs.CLASS_SERVER_PORT_SERVER.  
	 * This will also automatically start the the security manager and the class file server for remote dynamic
	 * class loading.<br/>
	 * 2. Instantiate the ICompute server, saving a reference to it in the model somewhere. <br/>
	 * 3. Create a stub of the ICompute server. <br/>
	 * 4. Use the IRMIUtils to get the LOCAL Registry.  Save the reference somewhere for use later. <br/>
	 * 5. Bind the ICompute engine stub to the local Registry using the name and port defined by ICompute 
	 * locating the local Registry and binding a STUB of an ICompute engine instance to the that registry.  
	 */
	void start();

	/**
	 * Stops the engine model by unbinding the ICompute engine from the Registry 
	 * and stopping class file server.   This MUST be called before exiting the system! 
	 * Procedure: <br/>
	 * 1. First, unbind the ICompute server stub from the local Registry. <br/>
	 * 2. Use the IRMIUtils to stop the RMI system.
	 */
	void stop();

	/**
	 * Send string message to connected remote client using the IRemoteTaskViewAdapter STUB 
	 * received from the client..   The message should also be echoed to the local 
	 * engine server's user interface.
	 * @param text  Message to send
	 */
	void sendMsgToClient(String text);

	/**
	 * This methods that set the adapter that the server model uses to communicate with the client view.
	 * to the input new clientToViewAdapter.
	 * 
	 * @param newClientTVAStub The new adapter that RMI server model uses to communicate with the client view.
	 */
	void setClientTVAStub(IRemoteTaskViewAdapter newClientTVAStub);
}
