package model;

import java.awt.Graphics;

/**
 * The top-level interface that fundamentally defines what a paint strategy can
 * do. This is the view of the paint strategy from the perspective of the ball.
 */
public interface IPaintStrategy {
  /**
   * Paint the host onto the given Graphics context.
   *
   * The exact nature of the manipulations required to get the ball's visual
   * representation onto the screen is up to the implementing class and may or
   * may not involve affine transforms.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  public void paint(Graphics g, Ball host);

  /**
   * Initialize the strategy and host ball.
   *
   * This method must be run whenever the ball gets a new strategy, such as in a
   * setPaintStrategy() method or even in the constructor of the ball. The
   * safest way to do this is to have the constructor set the paint strategy
   * field by calling the setPaintStrategy() method thus keeping the code to
   * initialize the strategy only in a single location.
   *
   * @param host  a host Ball that the required information will be pulled from
   */
  public void init(Ball host);

  /**
   * A no-op implementation of this interface.
   */
  public static final IPaintStrategy NULL_OBJECT = new IPaintStrategy() {
    @Override
    public void paint(Graphics g, Ball host) {}
    @Override
    public void init(Ball host) {}
  };

}
