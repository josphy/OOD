package model;

/**
 * This interface is used for the fast, repetitive updating communications
 * to the view, such as those needed during a timer tick.
 */
public interface IViewUpdateAdapter {
  /**
   * Update the view, in response to a timer tick event.
   */
  public void update();

  /**
   * A no-op implementation of this interface.
   */
  public static final IViewUpdateAdapter NULL_OBJECT = new IViewUpdateAdapter() {
    public void update() {}
  };

}
