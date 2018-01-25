package provided.music;

/**
* Command-based implementation of IPhraseVisitor that uses IPhraseVisitorCmds stored in a hash table.
 * @author swong
 *  * */
public abstract class APhraseVisitor implements IPhraseVisitor {
	private IPhraseVisitorCmd defaultCmd;
	private Map<String, IPhraseVisitorCmd> cmds = new Hashtable<String, IPhraseVisitorCmd>();

	/**
	* Constructor that installs a default cmd that throws an IllegalArgumentException on any unknown host. * ----------------------------------------------
	* Abstract extended visitor implementation that uses a dictionary of commands to associate commands with host IDs. */
	public APhraseVisitor() {
		defaultCmd = new IPhraseVisitorCmd() {
			public Object apply(String idx, IPhrase host, Object... inps) {
				throw new IllegalArgumentException("APhraseVisitor: Unknown index encountered: " + idx);
			}
		};
	}

	/**
	* Constructor that sets the default cmd to the supplied cmd.
	 * 	 * @param defaultCmd   the default cmd to use. */
	public APhraseVisitor(IPhraseVisitorCmd defaultCmd) {
		this.defaultCmd = defaultCmd;
	}

	/**
	* Add the given command to the dictionary, associated with the given id value.
	 * 	 * @param id The id of the host that will use this command
	 * 	 * @param cmd The command that will be run when the host calls for it. */
	public void addCmd(String id, IPhraseVisitorCmd cmd) {
		cmds.put(id, cmd);
	}

	public Object caseAt(String id, IPhrase host, Object... params) {
		return cmds.getOrDefault(id, defaultCmd).apply(id, host, params);
	}
}
