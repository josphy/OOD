package provided.music;

/**
* Class that represents a tuplet, a series of notes played in a fraction of their normal total time.
 * The exact fraction depends on the meter and the number of notes in the tuplet.
 * A triplet is a specific type of tuplet.
 * @author swong * ----------------------------------------------
* A arbitrary set of notes played in sequence in a special manner. */
public class Tuplet extends NoteCollection {
	public static final String ID = "Tuplet";

	public String toString() {
		String result = "Tuplet(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}

	public Tuplet(Note... notes) {
		super(notes);
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}
}
