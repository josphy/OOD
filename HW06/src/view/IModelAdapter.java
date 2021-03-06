package view;

/** 
* View to model adapter
*/
public interface IModelAdapter<TInstrument> {
	/**
	 * load abc file using full path ("/songs/" + txtInput.getText() + ".abc")
	 * @param partial filename
	 */
	void Load(String filename);

	/**
	 * Parse
	 */
	void Parse();

	/**
	 * Load
	 * @param instrument
	 */
	void Play(TInstrument instrument);

	/**
	 * Stop
	 */
	void Stop();

	/**
	 * Null object
	 */
	public static final IModelAdapter<?> NULL_OBJECT = new IModelAdapter<Object>() {

		@Override
		public void Load(String filename) {
		}

		@Override
		public void Parse() {
		}

		@Override
		public void Play(Object instrument) {
		}

		@Override
		public void Stop() {
		}
	};
}
