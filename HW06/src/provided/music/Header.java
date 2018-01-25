package provided.music;

/**
* An abc Header.  Headers are identified by a one character "id" and
 * can have an arbitrary string value. <br/>
 * Note that this class represents many different *types* of headers, 
 * each with its own id value. * ----------------------------------------------
* A single Header class represents multiple, distinct header hosts. */
public class Header implements IPhrase {
	private String _id;
	private String _value;

	/**
	* Create a header out of an id and value
	 * 	 * 
	 * 	 * @param id    - one character corresponding to the abc header name
	 * 	 * @param value - arbitrary string value of the header */
	public Header(String id, String value) {
		_id = id;
		_value = value;
	}

	/**
	* Accessor for the id.
	 * 	 * @return the _id */
	public String getID() {
		return _id;
	}

	/**
	* Accessor for the text value of this header.
	 * 	 * @return the _value */
	public String getValue() {
		return _value;
	}

	/**
	* String representation of this header: "id: text" */
	public String toString() {
		return _id + ":" + _value;
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(_id, this, params);
	}
}
