package client.model.task;

import java.rmi.RemoteException;

import provided.client.model.taskUtils.ITaskFactory;
import provided.compute.ILocalTaskViewAdapter;
import provided.compute.ITask;
import provided.compute.ITaskResultFormatter;

/**
 * Task to generate Fibonacci sequence to a given number of digits
 * @author Haoshan Zou, Xiaojun Wu
 *
 */
public class Fibo implements ITask<String> {

	/**
	 * SerialversionUID for well-defined serialization.
	 */
	private static final long serialVersionUID = -7662490749049293871L;

	/**
	 * Adapter to the local view.
	 */
	private transient ILocalTaskViewAdapter taskView = ILocalTaskViewAdapter.DEFAULT_ADAPTER;

	/**
	 * number of Fibonacci digits to display
	 */
	private final int digits;

	/**
	 * Construct the task.
	 * @param digits the number of digits to generate to
	 */
	public Fibo(int digits) {
		this.digits = digits;
		taskView.append("Fibonacci constructing...");
	}

	@Override
	/**
	 * generate sequence.
	 * @return String of output sequence
	 */
	public String execute() throws RemoteException {
		taskView.append("Executing Fibo task with " + digits + " digits.");
		return computeFibo(digits);
	}

	/**
	 * compute fibonacci sequence
	 * @param digits length of desired sequence
	 * @return string output of generated sequence
	 */
	private String computeFibo(int digits) {
		StringBuilder fiboSequence = new StringBuilder();
		fiboSequence.append("1");
		int length = 1;
		int pre = 1;
		int now = 1;
		int sum = 0;
		for (length = 1; length < digits; length++) {
			fiboSequence.append(", ");
			fiboSequence.append(now);
			sum = pre + now;
			pre = now;
			now = sum;
		}
		return fiboSequence.toString();
	}

	@Override
	/**
	 * Sets the task view adapter to a new value.  Used by the server to attach
	 * the task to its view.
	 */
	public void setTaskViewAdapter(ILocalTaskViewAdapter viewAdapter) {
		taskView = viewAdapter;
	}

	@Override
	/**
	 * Return a a formatter that makes a string of the form:  
	 * "Fibonacci Sequence : [result] ([digits] places)" 
	 */
	public ITaskResultFormatter<String> getFormatter() {
		return new ITaskResultFormatter<String>() {

			public String format(String result) {
				return "Fibonacci Sequence : " + result + " (" + digits + " places)";
			}
		};
	}

	/**
	 * An ITaskFactory for this task
	 */
	public static final ITaskFactory<String> FACTORY = new ITaskFactory<String>() {
		public ITask<String> make(String param) {
			return new Fibo(Integer.parseInt(param));
		}

		@Override
		public String toString() {
			return Fibo.class.getName();
		}
	};

}
