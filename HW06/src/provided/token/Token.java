package provided.token;

/**
* Represents a token in the language. */
public class Token {
	/*package*/ String _name;
	private String _lexeme;

	/**
	* Token constructor.
	 * 	 *
	 * 	 * @param name The name associated with this token.  This is the id value when it executes its visitors.
	 * 	 * @param lexeme _lexeme this token is representing. */
	public Token(String name, String lexeme) {
		_name = name;
		_lexeme = lexeme;
	}

	/**
	* Accessor for the name of this token
	 * 	 * @return The name of this token */
	public String getName() {
		return _name;
	}

	/**
	* Accessor for the lexeme of this token
	 * 	 * @return The lexeme of this token */
	public String getLexeme() {
		return _lexeme;
	}

	/**
	* Return a string representation of the token: "[name] lexeme"
	 * 	 *
	 * 	 * @return string representation */
	public String toString() {
		return "[" + _name + "] " + _lexeme;
	}

	public Object execute(ITokVisitor algo, Object... params) {
		return algo.caseAt(_name, this, params);
	}
}
