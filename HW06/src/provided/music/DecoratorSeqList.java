package provided.music;

/**
* An ISeqList that transparently routes the visitor execution to its decoree.  
 * This class is primarily used to provide a placeholder when a the music should flow 
 * to a specific point in the sequence but that point has not yet been calculated, as occurs
 * for instance, during the processing of repeats.    When that target point is finally calculated, 
 * the decoree can be set and the decorated target will behave identically to the target.
 * This decorator can also be used to dynamically change the flow of the music by changing the target.  
 * @author swong * ----------------------------------------------
* Decorated ISeqList used as a transparent proxy placeholder. */
public class DecoratorSeqList implements ISeqList {
	private ISeqList decoree = MTSeqList.Singleton;

	public DecoratorSeqList() {
	}

	/**
	* Constructor used to set the decoree to a known value at construction time.
	 * 	 * @param decoree The decoree phrase sequence */
	public DecoratorSeqList(ISeqList decoree) {
		setDecoree(decoree);
	}

	/**
	* Setter methods used to set the decoree.
	 * 	 * @param decoree  The new decoree ISeqList. */
	public void setDecoree(ISeqList decoree) {
		this.decoree = decoree;
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return decoree.execute(algo, params);
	}
}
