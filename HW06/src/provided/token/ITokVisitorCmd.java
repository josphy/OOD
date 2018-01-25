package provided.token;

/**
* A command for use in ATokVisitors */
public interface ITokVisitorCmd {
	public Object apply(String id, Token host, Object... params);
}
