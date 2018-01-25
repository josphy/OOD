package provided.music;

/**
* Multiple Decorator ISeqList where the active decoree (the "target") is
 * toggled (cycled, actually) through all the available decorees.   
 * This is arguably a State Design Pattern where each decoree is 
 * considered to be another state of the system.
 * This is used to implement repeats where the first time through, the notes are linked
 * back to a repeat start (decoree #0) but the next time through, the next note is the one
 * after the repeat (decoree #1).
 * 
 * @author swong * ----------------------------------------------
* Decorated ISeqList that transparently cycles through multiple targets (decorees) */
public class ToggleDecoratorSeqList implements ISeqList {
	private ISeqList[] decorees = { MTSeqList.Singleton, MTSeqList.Singleton };
	private int targetIdx = 0;

	public ToggleDecoratorSeqList() {
	}

	/**
	* Used to explicitly set the target (active) decoree if needed.
	 * 	 * @param targetIdx  the index of the target decoree */
	public void setTargetToDecoree(int targetIdx) {
		this.targetIdx = targetIdx;
	}

	/**
	* Accessor for the current target (active) decoree
	 * 	 * @return  The current target (active) decoree */
	public ISeqList getTargetDecoree() {
		return decorees[targetIdx];
	}

	@Override
	public String toString() {
		return (String) this.execute(NESeqList.getToStringAlgo(), "{");
	}

	public ToggleDecoratorSeqList(ISeqList... decorees) {
		setDecorees(decorees);
		setTargetToDecoree(0);
	}

	public void setDecorees(ISeqList... decorees) {
		this.decorees = decorees;
	}

	/**
	* Setter methods used to set a particular decoree.   
	 * 	 * The specified index must already exist.
	 * 	 * @param idx The index of the decoree to replace
	 * 	 * @param decoree  The new decoree ISeqList. */
	public void setDecoree(int idx, ISeqList decoree) {
		this.decorees[idx] = decoree;
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		ISeqList target = decorees[targetIdx++];
		if (targetIdx >= decorees.length)
			targetIdx = 0; // This must be set before the delegate because it may be needed before the recursion returns.
		return target.execute(algo, params);
	}
}
