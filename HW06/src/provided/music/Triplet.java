package provided.music;

/**
* A musical triplet.  The three notes should be played with 2/3 of their
 * indicated duration. * ----------------------------------------------
* A set of 3 notes played in sequence in a special manner. */
public class Triplet extends NoteCollection {
	public static final String ID = "Triplet";

	public String toString() {
		String result = "Triplet(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}

	public Triplet(Note n1, Note n2, Note n3) {
		super(n1, n2, n3);
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}
}
