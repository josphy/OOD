package model.updatestrategy;

import model.Ball;
import model.IUpdateStrategy;
import util.IDispatcher;

/**
 * switcher strategy class: able to update strategy after ball generated.
 */
public class SwitcherUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * the strategy for a switcherStrategy, which can be later changed to
   * other strategies. the default is a straight strategy.
   */
  private IUpdateStrategy<TDispMsg> strategy = new StraightUpdateStrategy<TDispMsg>();

  /**
   * update state the ball of context.
   *
   * @param context the context
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    strategy.updateState(context, dispatcher);
  }

  /**
   * set a new strategy.
   *
   * @param newStrategy a new strategy.
   */
  public void setStrategy(IUpdateStrategy<TDispMsg> newStrategy) {
    strategy = newStrategy;
  }

}
