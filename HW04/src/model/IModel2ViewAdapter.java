package model;

// TODO: Auto-generated Javadoc
/**
 * This is a model to view adapter.
 *
 * @author Qingyue Liu, Haoshan Zou
 * @version 1.0
 * @since 2017-09-07
 */

public interface IModel2ViewAdapter {
	/**
	 * The method that tells the view to update.
	 */
	public void update();

	/**
	 * No-op "null" adapter
	 * See the web page on the Null Object Design Pattern at http://cnx.org/content/m17227/latest/
	 */
	public static final IModel2ViewAdapter NULL_OBJECT = new IModel2ViewAdapter() {
		public void update() {
		}
	};

}
