package model.paint.paintstrategy;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import model.Ball;
import model.IPaintStrategy;

/**
 * The top-level affine transform paint strategy that provides services for its
 * subclasses, plus default behaviors and abstract behaviors.
 *
 * This class provides the basic affine transform services that its subclasses
 * will use to resize, translate and rotate their prototype images into their
 * proper current locations and orientations on the screen. This class is
 * designed to be the root class for all strategies that use affine transforms
 * to create their visual representations.
 *
 * One convenience service that APaintStrategy provides for all of its
 * subclasses is default behavior for some of its methods. The subclasses only
 * need to override those methods that it needs without having to explicitly
 * implement the behaviors of all possible methods.
 */
public abstract class APaintStrategy implements IPaintStrategy{
  /**
   * An AffineTransform object used to perform all affine transforms
   */
  protected AffineTransform at;

  /**
   * Takes an AffineTransform object and store the reference.
   *
   * @param at  an AffineTransform object used to perform all affine transforms
   */
  public APaintStrategy(AffineTransform at) {
    this.at = at;
  }

  /**
   * Paint on the given graphics context using the color, scale and direction
   * provided by the host.
   *
   * This Template Design Pattern method sets up AffineTransform object to
   * translate, scale and rotate based on the the ball's current position,
   * radius and velocity. It then delegates to two other methods to finish the
   * job, the paintCfg() method to finish any further refinements of the
   * affine transform and the paintXfrm() method to actually perform the
   * painting.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  public void paint(Graphics g, Ball host) {
    double scale = host.getRadius();
    at.setToTranslation(host.getLocation().x, host.getLocation().y);
    at.scale(scale, scale);
    at.rotate(host.getVelocity().x, host.getVelocity().y);
    g.setColor(host.getColor());
    paintCfg(g, host);
    paintXfrm(g, host, at);
  }

  /**
   * This method is used for doing additional configurations by a subclass.
   *
   * The paintCfg() method is set to be a concrete no-op that the subclasses may
   * or may not override. This method allows the subclass to inject additional
   * processing into the paint() method process before the final transformations
   * are performed. Since this method is "protected", it is only available for
   * use by the subclasses and not other types of objects.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   */
  protected void paintCfg(Graphics g, Ball host) {}

  /**
   * Paint the host onto the given Graphics context. This method performs the
   * actual painting.
   *
   * Notice that the translation, rotation and scaling have already been added
   * to the affine transform before it gets to paintXfrm(). This allows the same
   * affine transform to be shared amongst paint strategies, reducing the number
   * of times that it has to be calculated. When an affine transform instance is
   * being shared amongst strategies, then it is invariant across those
   * strategies. Thus, this method allows an invariant translation, rotation and
   * scaling to be performed that applies to all composed strategies.
   *
   * Since APaintStrategy doesn't know what sort of thing is being painted, it
   * has no idea how exactly to apply the affine transform, so the paintXfrm()
   * method must remain abstract, forcing the subclasses to implement it.
   *
   * @param g     a Graphics context that will be paint on
   * @param host  a host Ball that the required information will be pulled from
   * @param at    an AffineTransform object used to perform all affine transforms
   */
  public abstract void paintXfrm(Graphics g, Ball host, AffineTransform at);

  /**
   * By default, do nothing for initialization.
   *
   * @param host  a host Ball that the required information will be pulled from
   */
  @Override
  public void init(Ball host) {}

}
