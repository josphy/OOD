package provided.token;

/**
* Abstract factory to produce tokens */
public interface ITokenFactory {
	public Token makeToken(String name, String lexeme);

}
