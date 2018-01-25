package provided.music;

/**
* An empty sequence list. * ----------------------------------------------
* An empty ISeqList */
public class MTSeqList implements ISeqList {
	public static final String ID = "MTSeqList";

	public static final MTSeqList Singleton = new MTSeqList();

	private MTSeqList() {
	}

	public String toString() {
		return "{}";
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}
}
