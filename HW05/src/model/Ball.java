package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import util.IObserver;
import util.IDispatcher;

/**
 * This is a concrete ball class.
 */
public class Ball implements IObserver<IBallCmd> {
  /**
   * the position of the center point of the ball.
   */
  protected Point location;
  /**
   * radius is the radius of a ball.
   */
  protected int radius;
  /**
   * the velocity of a ball which include the x-axis velocity and y-axis
   * velocity
   */
  protected Point velocity;
  /**
   * color is the color of a ball.
   */
  protected Color color;
  /**
   * canvas is the panel for the ball word.
   */
  protected Component canvas;
  /**
   * the update strategy.
   */
  protected IUpdateStrategy<IBallCmd> updateStrategy;
  /**
   * the paint strategy.
   */
  protected IPaintStrategy paintStrategy;
  /**
   * the interact strategy
   */
  protected IInteractStrategy interactStrategy;

  /**
   * Constructor of ABall.
   *
   * @param loc               The center point's location of a ball
   * @param radius            The radius of a ball
   * @param vel               The velocity of a ball
   * @param color             The color of a ball
   * @param canvas            The panel for the ball word
   * @param updateStrategy    The ball's update strategy
   * @param paintStrategy     The ball's paint strategy
   */
  public Ball(Point loc, int radius, Point vel, Color color, Component canvas,
      IUpdateStrategy<IBallCmd> updateStrategy, IPaintStrategy paintStrategy) {
    setLocation(loc);
    setRadius(radius);
    setVelocity(vel);
    setColor(color);
    setCanvas(canvas);
    // Default interactStrategy is no-op, which can be changed in the init()
    // of the setUpdateStrategy().
    setInteractStrategy(IInteractStrategy.NULL_STRATEGY);
    setUpdateStrategy(updateStrategy);
    setPaintStrategy(paintStrategy);
  }

  /**
   * Update the ball's state using updateStrategy.
   *
   * @param dispatcher  the dispatcher to use if needed
   */
  public void updateState(IDispatcher<IBallCmd> dispatcher) {
    updateStrategy.updateState(this, dispatcher);
  }

  /**
   * move method moves ball's location under velocity.
   */
  public void move() {
    location.translate(velocity.x, velocity.y);
  }

  /**
   * This method sets the location and the velocity of the ball when it
   * touches the bounds of the ball panel.
   */
  public void bounce() {
    // check if hit left wall
    if (location.x - radius < 0) {
      velocity.x = -velocity.x;
      location.x = 2 * radius - location.x;
    }
    // check if hit top wall
    if (location.y - radius < 0) {
      location.y = 2 * radius - location.y;
      velocity.y = -velocity.y;
    }
    // check if hit right wall;
    if (canvas.getWidth() - location.x - radius < 0) {
      location.x = 2 * (canvas.getWidth() - radius) - location.x;
      velocity.x = -velocity.x;
    }
    // check if hit bottom wall;
    if (canvas.getHeight() - location.y - radius < 0) {
      location.y = 2 * (canvas.getHeight() - radius) - location.y;
      velocity.y = -velocity.y;
    }
  }

  /**
   * paint the ball with paint strategy
   *
   * @param g   the Graphics object to be painted on
   */
  public void paint(Graphics g) {
    paintStrategy.init(this);
    paintStrategy.paint(g, this);
  }

  /**
   * This method is called by the ballModel Dispatcher to notify all the balls
   * to perform the given command. The given command is executed.
   *
   * @param o     the Dispatcher that sent the update request
   * @param cmd   the IBallCmd that will be run
   */
  @Override
  public void update(IDispatcher<IBallCmd> o, IBallCmd cmd) {
    cmd.apply(this, o);
  }

  /**
   * Perform interaction with another Ball.
   *
   * @param another   the Ball to interact with
   * @param disp      The Dispatcher that is to be used if desired
   */
  public void interactWith(Ball another, IDispatcher<IBallCmd> disp) {
    interactStrategy.interact(this, another, disp);
  }

  /**
   * @return current ball paint strategy
   */
  public IPaintStrategy getPaintStrategy() {
    return this.paintStrategy;
  }

  /**
   * set ball paint strategy.
   *
   * @param paintStrategy new ball paint strategy.
   */
  public void setPaintStrategy(IPaintStrategy paintStrategy) {
    this.paintStrategy = paintStrategy;
    this.paintStrategy.init(this);
  }

  /**
   * @return current ball update strategy
   */
  public IUpdateStrategy<IBallCmd> getUpdateStrategy() {
    return this.updateStrategy;
  }

  /**
   * set ball update strategy.
   *
   * @param updateStrategy new ball update strategy.
   */
  public void setUpdateStrategy(IUpdateStrategy<IBallCmd> updateStrategy) {
    this.updateStrategy = updateStrategy;
    this.updateStrategy.init(this);
  }

  /**
   * @return the interactStrategy
   */
  public IInteractStrategy getInteractStrategy() {
    return interactStrategy;
  }

  /**
   * @param interactStrategy the interactStrategy to set
   */
  public void setInteractStrategy(IInteractStrategy interactStrategy) {
    this.interactStrategy = interactStrategy;
  }

  /**
   * @return current ball center location.
   */
  public Point getLocation() {
    return location;
  }

  /**
   * set ball center location.
   *
   * @param loc new ball center location.
   */
  public void setLocation(Point loc) {
    this.location = loc;
  }

  /**
   * @return current ball velocity.
   */
  public Point getVelocity() {
    return this.velocity;
  }

  /**
   * set ball velocity.
   *
   * @param vel new ball velocity.
   */
  public void setVelocity(Point vel) {
    this.velocity = vel;
  }

  /**
   * @return current ball radius.
   */
  public int getRadius() {
    return this.radius;
  }

  /**
   * set ball radius.
   *
   * @param radius new ball radius.
   */
  public void setRadius(int radius) {
    this.radius = radius;
  }

  /**
   * @return current ball color.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * set ball color.
   *
   * @param color new ball color.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * @return the canvas
   */
  public Component getCanvas() {
    return canvas;
  }

  /**
   * set working canvas.
   *
   * @param canvas desired working canvas.
   */
  public void setCanvas(Component canvas) {
    this.canvas = canvas;
  }

}
