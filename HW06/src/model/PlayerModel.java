package model;

import model.visitor.*;
import provided.abcParser.*;
import provided.music.*;
import provided.player.*;
import provided.util.*;

/** 
* Player Model class
*/
public class PlayerModel {
	/**
	 * host - IPhrase object
	 */
	private IPhrase host;

	/**
	 * instantiates model to view adapter
	 */
	private IViewAdapter adp = IViewAdapter.NULL_OBJECT;

	/**
	 * holds a sequence player to play music
	 */
	private SequencePlayer sp;

	/**
	 * Constructor
	 * @param viewAdp externally provided adapter
	 */
	public PlayerModel(IViewAdapter viewAdp) {
		adp = viewAdp;
	}

	/**
	 * set the text area and parse the content inside
	 * ABC.getFileContent - take out information in file which filename correspond to
	 * setTxtArea - actually setting content into view's text panel
	 * @param filename: partial name of the song
	 */
	public void LoadFile(String filename) {
		adp.setTxtArea(ABCUtil.Singleton.getFileContents(filename));
		host = new ABCParser(filename).parse();
	}

	/**
	 * Getter method of host
	 * @return host
	 */
	public IPhrase getHost() {
		return host;
	}

	/**
	 * Play method provided by professor
	 * @param instrument target instrument to play
	 */
	public void Play(ABCInstrument instrument) {
		sp = new SequencePlayer(16, instrument.getValue());
		sp.setTempo(120);
		host.execute(new PlayVisitor(), sp, 0);
		sp.play(ISequencePlayerStatus.NULL);
	}

	/**
	 * The parse string method
	 * @return String The result of parsing
	 */
	public String parseToString() {
		NESeqList.setToStringAlgo(new ToStringVisitor());//toStringAlgo is static,it's in type of IPhraseVisitor, It's set in this line
		return host.toString();
	}

	/**
	 * start
	 */
	public void start() {
	}

	/**
	 * stop
	 */
	public void stop() {
		sp.stop();
	}
}
