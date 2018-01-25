package provided.music;

/**
* A non-empty ("cons") sequence list. * ----------------------------------------------
* A non-empty ISeqList */
public class NESeqList implements ISeqList {
	public static final String ID = "NESeqList";

	private IPhrase _first;
	private ISeqList _rest;
	private static IPhraseVisitor _toStringAlgo;

	/**
	* Accessor method for the first element
	 * 	 * @return the IPhrase that is the first element of the sequence list */
	public IPhrase getFirst() {
		return _first;
	}

	/**
	* Accessor method for the rest of the lsit
	 * 	 * @return the rest of the sequence list */
	public ISeqList getRest() {
		return _rest;
	}

	/**
	* Getter for the _toStringAlgo helper.  Only for use internally inside this package
	 * 	 * so ToggleDecoratorSeqList and FineToggleDecoratorSeqList can use the same visitor.
	 * 	 * @return the toStringAlgo helper visitor */
	/*package*/ static IPhraseVisitor getToStringAlgo() {
		return _toStringAlgo;
	}

	/**
	* Run a visitor over the list to convert it to a String.  
	 * 	 * Delegates to the rest of the list using the helper algo, with the accumulator 
	 * 	 * (params[0]) set to "{"+_first.toString().  
	 * 	 * @return "{a, b, c, d}" */
	public String toString() {
		return (String) _rest.execute(_toStringAlgo, "{" + _first.toString());
	}

	/**
	* Cons an IPhrase to a Sequence List.
	 * 	 * @param first The first element of the sequence
	 * 	 * @param rest The rest of the sequence */
	public NESeqList(IPhrase first, ISeqList rest) {
		this._rest = rest;
		this._first = first;
	}

	/**
	* Setter for the _toStringAlgo helper.  This non-typical method is provided solely 
	 * 	 * for the purpose of enabling the student to install their toStringAlgo into the 
	 * 	 * read-only provided code.
	 * 	 * @param stringAlgo The helper algo to use. */
	public static void setToStringAlgo(IPhraseVisitor stringAlgo) {
		_toStringAlgo = stringAlgo;
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}
}
