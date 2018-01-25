package provided.music;

/**
* The command used by APhraseVisitor that is associated with a host or hosts.
 * @author rixner * ----------------------------------------------
* Abstract command used by APhraseVisitor.  Each command is associated with a particular host(s). */
public interface IPhraseVisitorCmd {
	public Object apply(String id, IPhrase host, Object... params);
}
