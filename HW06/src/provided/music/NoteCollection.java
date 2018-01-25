package provided.music;

/**
* A collection of multiple notes * ----------------------------------------------
* A collection of Notes */
public abstract class NoteCollection implements INote {
	protected Note[] notes;

	/**
	* Accessor for the array of notes in this collection
	 * 	 * @return the array of notes */
	public Note[] getNotes() {
		return notes;
	}

	public NoteCollection(Note... notes) {
		this.notes = notes;
	}
}
