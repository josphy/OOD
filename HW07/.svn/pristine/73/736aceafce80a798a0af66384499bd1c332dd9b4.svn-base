package client.model.task;

import java.rmi.RemoteException;
import java.util.ArrayList;

import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

/**
 * MultiTask: enable dynamically combined task
 */
public class MultiTask<Q, R> implements ITask<ArrayList<Object>> {

	/**
	 * a nice serial version UID
	 */
	private static final long serialVersionUID = -2062204862714215781L;

	/**
	 * ITask component 1
	 */
	private ITask<Q> task1;

	/** 
	 * ITask component 2
	 */
	private ITask<R> task2;

	/**
	 * Adapter to the local view.
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * constructor to save tasks
	 * @param task1 selected task 1
	 * @param task2 selected task 2
	 */
	public MultiTask(ITask<Q> task1, ITask<R> task2) {
		this.task1 = task1;
		this.task2 = task2;
	}

	@Override
	/**
	 * Sequentially execute task1 and task2, return formatted task result. 
	 * Display status in server's view.
	 */
	public ArrayList<Object> execute() throws RemoteException {
		taskView.append("MultiTask executing: \n");
		ArrayList<Object> output = new ArrayList<Object>();
		taskView.append("\n(Executing Task #1)");
		output.add((Object) this.task1.execute());
		taskView.append("\n(Executing Task #2)");
		output.add((Object) this.task2.execute());
		return output;
	}

	@Override
	/**
	 * Sets the task view adapter to a new value.  Used by the server to attach
	 * the task to its view.
	 */
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
		task1.setTaskViewAdapter(viewAdapter);
		task2.setTaskViewAdapter(viewAdapter);
	}

	@Override
	/**
	 * Return a a formatter that makes a string of the form:  
	 * "MultiTask results : " 
	 */
	public ITaskResultFormatter<ArrayList<Object>> getFormatter() {
		return new ITaskResultFormatter<ArrayList<Object>>() {
			@SuppressWarnings("unchecked")
			public String format(ArrayList<Object> result) {
				String output = "";
				output += "MultiTask Results:\n";
				output += "(1) " + task1.getFormatter().format((Q) result.get(0)) + '\n';
				output += "(2) " + task2.getFormatter().format((R) result.get(1)) + '\n';
				return output;
			}
		};
	}

}
