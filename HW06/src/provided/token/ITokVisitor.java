package provided.token;

/**
* Interface for a token visitor. */
public interface ITokVisitor {
	public Object caseAt(String id, Token host, Object... params);
}
