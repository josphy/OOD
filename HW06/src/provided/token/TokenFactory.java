package provided.token;

/**
* Concrete factory to create tokens */
public class TokenFactory implements ITokenFactory {
	public static final TokenFactory Singleton = new TokenFactory();

	private TokenFactory() {
	}

	public Token makeToken(final String name, String lexeme) {
		return new Token(name, lexeme);
	}

}
