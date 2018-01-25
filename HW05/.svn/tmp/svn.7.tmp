package model;

import util.IDispatcher;

/**
 * Multiple interact strategies.
 */
public class MultiInteractStrategy implements IInteractStrategy {
  /**
   * IInteractStrategy s1
   */
  private IInteractStrategy s1;
  /**
   * IInteractStrategy s2
   */
  private IInteractStrategy s2;

  /**
   * Construct a MultiInteractStrategy object.
   * @param s1  an IInteractStrategy
   * @param s2  an IInteractStrategy
   */
  public MultiInteractStrategy(IInteractStrategy s1, IInteractStrategy s2) {
    this.s1 = s1;
    this.s2 = s2;
  }

  @Override
  public void interact(Ball context, Ball target, IDispatcher<IBallCmd> disp) {
    s1.interact(context, target, disp);
    s2.interact(context, target, disp);
  }

}
