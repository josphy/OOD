package model;

import util.IDispatcher;

/**
 * An interface that represents commands sent through the dispatcher to process
 * the balls.
 */
@FunctionalInterface
public interface IBallCmd {
  /**
   * The method is run by the ball's update() method, which is called when the
   * ball is updated by the dispatcher.
   *
   * @param context the ball that is calling this method, the context under
   *                which the command is to be run
   * @param disp    the Dispatcher that sent the command out
   */
  public void apply(Ball context, IDispatcher<IBallCmd> disp);
}
