package control;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.*;
import model.*;

/**
 * MVC Controller for the system.
 */
public class Controller {
  /**
   * the model of the system
   */
  private BallModel model;
  /**
   * the view of the system
   */
  private BallFrame<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac> view;

  /**
   * Controller constructor builds the system.
   */
  public Controller() {
    // set the model
    model = new BallModel(new IViewUpdateAdapter() {
      @Override
      public void update() {
        view.update();   // invoke the update() of the view
      }
    });

    // set the view
    view =
      new BallFrame<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>(
          new IModelControlAdapter<IUpdateStrategyFac<IBallCmd>, IPaintStrategyFac>() {
        @Override
        public IUpdateStrategyFac<IBallCmd> addStrategyFac(String className) {
          return model.makeStrategyFac(className);
        }

        @Override
        public IPaintStrategyFac addPaintStrategyFac(String className) {
          return model.makePaintStrategyFac(className);
        }

        @Override
        public void makeBall(IUpdateStrategyFac<IBallCmd> strategyFac,
            IPaintStrategyFac paintStrategyFac) {
          if (strategyFac != null && paintStrategyFac != null) {
            model.loadBall(strategyFac, paintStrategyFac);
          }
        }

        @Override
        public void makeSwitcherBall(IPaintStrategyFac paintStrategyFac) {
          model.loadBall(new IUpdateStrategyFac<IBallCmd>() {
            @Override
            public IUpdateStrategy<IBallCmd> make() {
              return model.getSwitcherStrategy();
            }
          }, paintStrategyFac);
        }

        @Override
        public IUpdateStrategyFac<IBallCmd> combineStrategies(
            IUpdateStrategyFac<IBallCmd> selectedItem1,
            IUpdateStrategyFac<IBallCmd> selectedItem2) {
          return model.combineStrategyFacs(selectedItem1, selectedItem2);
        }

        @Override
        public void clearBalls() {
          model.clearBalls();
        }

        @Override
        public void setPanel(JPanel ballPanel) {
          model.setPanel(ballPanel);
        }

        @Override
        public void switchStrategy(IUpdateStrategyFac<IBallCmd> selectedItem) {
          model.switchSwitcherStrategy(selectedItem.make());
        }
      },

      new IModelUpdateAdapter() {
        @Override
        public void update(Graphics g) {
          model.update(g);
        }
      });
    }

  /**
   * Start the system by starting model and view.
   */
  public void start() {
    model.start();
    view.start(view);
  }

  /**
   * Launch the application.
   * @param args input parameters
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Controller controller = new Controller();
          controller.start();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

}
