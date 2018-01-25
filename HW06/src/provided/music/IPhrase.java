package provided.music;

/**
* Top level of a piece of abc music.  The generalized notion of a playable piece of music. * ----------------------------------------------
* Abstract Host
 * Each concrete host calls its associated case on the visitor. */
public interface IPhrase {
	public Object execute(IPhraseVisitor algo, Object... params);
}
