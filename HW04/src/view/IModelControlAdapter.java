package view;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Adapter that the view uses to communicate to the model for non-repetitive control tasks such as manipulating strategies.
 * @author swong
 * @param <TDropListItem> The type of objects put into the view's drop lists.
 *
 */
public interface IModelControlAdapter<TDropListItem, TPaintListItem> {

	/**
	 * Take the given short strategy name and return a corresponding something to put onto both drop lists.
	 * @param classname  The shortened class name of the desired strategy
	 * @return Something to put onto both the drop lists.
	 */
	public TDropListItem addStrategy(String classname);

	/**
	 * Make a ball with the selected short strategy name.
	 * @param selectedItem  A shorten class name for the desired strategy
	 * @param selectedPaint A class name for desired paint strategy
	 */
	public void makeBall(TDropListItem selectedItem, TPaintListItem selectedPaint);

	/**
	 * Return a new object to put on both lists, given two items from the lists.
	 * @param selectedItem1  An object from one drop list
	 * @param selectedItem2 An object from the other drop list
	 * @return An object to put back on both lists.
	 */
	public TDropListItem combineStrategies(TDropListItem selectedItem1, TDropListItem selectedItem2);

	/**
	 * make a switcher ball.
	 * @param selectedPaint The paint strategy to use
	 */
	public void makeSwitcherBall(TPaintListItem selectedPaint);

	/**
	 * switch strategy.
	 * @param selectedItem switch strategy to selectedItem strategy.
	 */
	public void switchStrategy(TDropListItem selectedItem);

	/**
	 * clear all balls.
	 */
	public void clearBalls();

	/**
	 * set the panel to paint the ball in model.
	 * @param ballPanel the panel to paint the ball.
	 */
	public void setPanel(JPanel ballPanel);
	
	/**
	 * tell the model to add a new paint strategy factory
	 * @param pntName the name of the paint strategy class
	 * @return a paint strategy generic
	 */
	public TPaintListItem addPntStrategy(String pntName);

}
