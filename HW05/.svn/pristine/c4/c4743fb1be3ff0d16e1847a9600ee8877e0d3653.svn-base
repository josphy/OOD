package model;

import util.IDispatcher;

/**
 * Top-level abstraction of an update strategy.
 *
 * @param TDispMsg The type of message that the supplied IDispatcher can send
 */
public interface IUpdateStrategy<TDispMsg> {
  /**
   * Initialize the strategy. This method should be called every time the Ball
   * sets a new strategy.
   *
   * @param context  The ball using this strategy.
   */
  public void init(Ball context);

  /**
   * Abstract method to update the state of a ball.
   *
   * @param context     the ball to be updated strategy
   * @param dispatcher  the Dispatcher which sends the command that is calling
   *                    through to here.
   */
  public void updateState(Ball context, IDispatcher<TDispMsg> dispatcher);

  /**
   * A factory for a typed null strategy object.
   *
   * Usage: instantiate this factory class using the desired TDispMsg type and
   * then call its make() method to create the correctly typed null strategy
   * object.
   *
   * @param TDispMsg The type of message that the supplied IDispatcher can send
   */
  public static final class NullFactory<TDispMsg> implements
    IUpdateStrategyFac<TDispMsg> {
    public IUpdateStrategy<TDispMsg> make() {
      return new IUpdateStrategy<TDispMsg>() {
        @Override
        public void init(Ball context) {}
        @Override
        public void updateState(Ball context,IDispatcher<TDispMsg> dispatcher) {}
      };
    }
  }

}
