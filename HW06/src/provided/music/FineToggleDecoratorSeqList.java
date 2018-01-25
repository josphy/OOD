package provided.music;

/**
* The class is used to implement "Da Capo al fine" or "D.C. al fine", 
 * which is to repeat from the beginning up to the "fine" marker.
 * 
 * this class assumes that only two target decorees are ever used.
 * 
 * The is ToggleDecoratorSeqList that can be signaled to switch to the 
 * another target.   Be default, the target decoree (#0) remains the same even 
 * when it is accessed.  But after setFine() is called, the target decoree
 * is switched to the #1 decoree (the "fine" target, usually an MTSeqList because
 * it is the end of the piece).   After the #1 decoree is used, the target
 * decoree is advanced to the next available decoree, which , for two decorees, 
 * would be back to the #0 decoree.  The class returns to default behavior then.
 * @author swong * ----------------------------------------------
* ToggleDecoratorSeqList with controllable cycling */
public class FineToggleDecoratorSeqList extends ToggleDecoratorSeqList {
	private boolean isFine = false;

	/**
	* Instantiates the class with two MTSeqList decorees. */
	public FineToggleDecoratorSeqList() {
		super();
	}

	/**
	* Sets the system to use the "fine" decorator at the next execution. */
	public void setFine() {
		this.isFine = true;
	}

	@Override
	public String toString() {
		return (String) this.execute(NESeqList.getToStringAlgo(), "{");
	}

	public FineToggleDecoratorSeqList(ISeqList... decorees) {
		super(decorees);
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		if (isFine) {
			isFine = false;
			this.setTargetToDecoree(1);
			return super.execute(algo, params);
		} else
			return getTargetDecoree().execute(algo, params);
	}
}
