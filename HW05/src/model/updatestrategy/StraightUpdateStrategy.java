package model.updatestrategy;

import model.Ball;
import util.IDispatcher;

/**
 * straight moving strategy.
 */
public class StraightUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * for straight moving ball, do nothing when update state.
   *
   * @param context the context
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
  }

}
