package provided.music;

/**
* Interface for a Phrase visitor. * ----------------------------------------------
* Abstract extended visitor.
 * Supplies case behavior for every host. */
public interface IPhraseVisitor {
	public Object caseAt(String id, IPhrase host, Object... params);
}
