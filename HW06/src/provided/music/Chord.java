package provided.music;

/**
* A collection of notes that make up a Chord.  All of the notes should
 * be played simultaneously.   The last note defines the length of all the notes. * ----------------------------------------------
* A collection of Notes all played at once */
public class Chord extends NoteCollection {
	public static final String ID = "Chord";

	public String toString() {
		String result = "Chord(" + notes[0];
		for (int i = 1; i < notes.length; i++) {
			result += ", " + notes[i];
		}
		return result + ")";
	}

	public Chord(Note... notes) {
		super(notes);
	}

	@Override
	public Object execute(IPhraseVisitor algo, Object... params) {
		return algo.caseAt(ID, this, params);
	}
}
