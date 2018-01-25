package provided.util;

/**
* Represents an instrument as a name and an integer value
 * @author swong
 *  * */
public class ABCInstrument {
	private String name;

	public String getName() {
		return this.name;
	}

	private int value;

	public int getValue() {
		return this.value;
	}

	public ABCInstrument(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String toString() {
		return name + ": " + value;
	}

}
