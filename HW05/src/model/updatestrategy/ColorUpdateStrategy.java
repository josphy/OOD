package model.updatestrategy;

import model.Ball;
import util.IDispatcher;
import util.Randomizer;

/**
 * color strategy: ball changing color every movement.
 */
public class ColorUpdateStrategy<TDispMsg> extends AUpdateStrategy<TDispMsg> {
  /**
   * update ball with random changing color.
   *
   * @param context the context
   */
  @Override
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher) {
    context.setColor(Randomizer.Singleton.randomColor());
  }

}
