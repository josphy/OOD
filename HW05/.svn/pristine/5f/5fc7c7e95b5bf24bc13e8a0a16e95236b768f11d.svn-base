package view;

import javax.swing.JPanel;

/**
 * Adapter that the view uses to communicate to the model for non-repetitive
 * control tasks such as manipulating strategies.
 *
 * @param <TStrategyDropListItem> the objects that strategy drop list items stand for.
 * @param <TPaintDropListItem> he objects that paint strategy drop list items stand for.
 */
public interface IModelControlAdapter<TStrategyDropListItem, TPaintDropListItem> {

  /**
   * Take the given short strategy name and return a corresponding something
   * to put onto both drop lists.
   *
   * @param classname  The shortened class name of the desired strategy
   * @return Something to put onto both the drop lists.
   */
  public TStrategyDropListItem addStrategyFac(String classname);

  /**
   * Take the given short shape name and return a corresponding something to
   * put onto drop list.
   *
   * @param classname  The shortened class name of the desired strategy
   * @return Something to put onto both the drop lists.
   */
  public TPaintDropListItem addPaintStrategyFac(String classname);

  /**
   * Make a ball with the selected short strategy name.
   *
   * @param strategySelectedItem  A shorten class name for the desired strategy
   * @param paintSelectedItem     A shorten class name for the desired paint strategy
   */
  public void makeBall(TStrategyDropListItem strategySelectedItem,
      TPaintDropListItem paintSelectedItem);

  /**
   * Return a new object to put on both lists, given two items from the lists.
   *
   * @param strategySelectedItem1   An object from one strategy drop list
   * @param strategySelectedItem2   An object from the other strategy drop list
   * @return An object to put back on both lists.
   */
  public TStrategyDropListItem combineStrategies(TStrategyDropListItem
      strategySelectedItem1, TStrategyDropListItem strategySelectedItem2);

  /**
   * make a switcher ball.
   *
   * @param paintSelectedItem A shorten class name for the desired paint strategy
   */
  public void makeSwitcherBall(TPaintDropListItem paintSelectedItem);

  /**
   * switch strategy.
   *
   * @param selectedItem switch strategy to selectedItem strategy.
   */
  public void switchStrategy(TStrategyDropListItem selectedItem);

  /**
   * clear all balls.
   */
  public void clearBalls();

  /**
   * set the panel to paint the ball in model.
   *
   * @param ballPanel the panel to paint the ball.
   */
  public void setPanel(JPanel ballPanel);

}
