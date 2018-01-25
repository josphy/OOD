package view;

import java.awt.Graphics;

/**
 * model update adapter.
 */
public interface IModelUpdateAdapter {
  /**
   * model update: the dispatcher in model update all.
   *
   * @param g the Graphics object to paint balls on.
   */
  public void update(Graphics g);
}
