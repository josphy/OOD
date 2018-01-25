package view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

/**
 * This is a GUI to run  Ball World.
 * @param <TStrategyDropListItem> the objects that strategy drop list items
 *                                stand for.
 * @param <TPaintDropListItem> the objects that paint strategy drop list items
 *                             stand for.
 */
public class BallFrame<TStrategyDropListItem, TPaintDropListItem> extends JFrame {

  /**
   * serialVersionUID is the serial version UID.
   */
  private static final long serialVersionUID = -8987134431120800637L;

  /**
   * contentPane is the container panel.
   */
  private JPanel contentPane;

  /**
   * pnlControl is the a panel which is used to put control components.
   */
  private final JPanel pnlControl = new JPanel();

  /**
   * btnMakeBall is the button to create a ball with type in the left text
   * field.
   */
  private final JButton btnMakeBall = new JButton("Make Selected Shape");

  /**
   * btnClearAll is the button to remove all the balls on the ball panel.
   */
  private final JButton btnClearAll = new JButton("Clear All");

  /**
   * JTextField is the text field to type in different ball types.
   */
  private final JTextField txtStrategy = new JTextField();

  /**
   * strategy selection, switch and combine panel.
   */
  private final JPanel pnlStrategy = new JPanel();
  /**
   * the add strategy to list button.
   */
  private final JButton btnAdd = new JButton("Add to Lists");

  /** the make ball panel. */
  private final JPanel pnlMake = new JPanel();

  /** the combine drop list1. */
  private final JComboBox<TStrategyDropListItem> combo1 = new JComboBox<>();

  /** the combine drop list2. */
  private final JComboBox<TStrategyDropListItem> combo2 = new JComboBox<>();
  /**
   * the combine two strategies button.
   */
  private final JButton btnCombine = new JButton("Combine!");
  /**
   * the switch strategy panel.
   */
  private final JPanel pnlSwitch = new JPanel();
  /**
   * the make switcher ball button.
   */
  private final JButton btnMakeSwitcher = new JButton("Make Switcher");

  /**
   * the switch strategy button.
   */
  private final JButton btnSwitch = new JButton("Switch!");

  /**
   * Adapter back to the model for control tasks.
   */
  private IModelControlAdapter<TStrategyDropListItem,
                               TPaintDropListItem> modelControlAdpt;
  /**
  * Adapter back to the model for update tasks.
  */
  private IModelUpdateAdapter modelUpdateAdpt;

  /**
   * pnlBall is the ball panel which shows the animation of different type
   * of balls.
   */
  private final JPanel pnlBall = new JPanel() {

    /**
     * serialVersionUID is the serial version UID.
     */
    private static final long serialVersionUID = 4048578419360286049L;

    /**
    * Overridden paintComponent method to paint a shape in the panel.
    * @param g The Graphics object to paint on.
    **/
    public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Do everything normally done first
      modelUpdateAdpt.update(g); // call back to the model to paint the shapes
    }
  };
  private final JPanel pnlPaintStrategy = new JPanel();
  private final JComboBox<TPaintDropListItem> comboPaint = new JComboBox<>();
  private final JButton btnAddPaintStrategy = new JButton("Add to List");
  private final JTextField txtPaintStrategy = new JTextField();

  /**
   * Start method to start the GUI. This method is called by controller.
   * @param view The main frame of ball word GUI.
   */
  public void start(BallFrame<TStrategyDropListItem, TPaintDropListItem> view) {
    view.setVisible(true);
  }

  /**
   * Update function to updates the view by repainting the canvas.
   */
  public void update() {
    pnlBall.repaint();
  }

  /**
   * Constructor of BallFrame.
   * @param modelControlAdpt An instance of view to model control adaptor.
   * @param modelUpdateAdpt An instance of view to model update adaptor.
   */
  public BallFrame(IModelControlAdapter<TStrategyDropListItem,
    TPaintDropListItem> modelControlAdpt, IModelUpdateAdapter modelUpdateAdpt) {
    txtPaintStrategy.setToolTipText("Input a shape.");
    txtPaintStrategy.setText("Ball");
    txtPaintStrategy.setColumns(10);
    this.modelControlAdpt = modelControlAdpt;
    this.modelUpdateAdpt = modelUpdateAdpt;
    initGUI();
  }

  /**
   * initGUI initialize all the components on the frame.
   */
  private void initGUI() {
    //Frame settings
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 764, 527);

    //Container panel settings
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
    pnlControl.setBackground(new Color(221, 160, 221));

    //Control panel settings
    contentPane.add(pnlControl, BorderLayout.NORTH);
    pnlStrategy.setBorder(new TitledBorder(new EtchedBorder(
        EtchedBorder.LOWERED, null, null), "Update Strategy",
        TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

    //text input and add
    pnlControl.add(pnlStrategy);
    pnlStrategy.setLayout(new GridLayout(0, 1, 0, 0));

    txtStrategy.setColumns(5);
    txtStrategy.setText("Straight");
    txtStrategy.setToolTipText("Input an update strategy.");
    pnlStrategy.add(txtStrategy);

    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        TStrategyDropListItem o = modelControlAdpt.addStrategyFac(
            txtStrategy.getText());
        if (null == o)
          return; // just in case
        combo1.insertItemAt(o, 0);
        combo1.setSelectedIndex(0);
        combo2.insertItemAt(o, 0);
        combo2.setSelectedIndex(0);
      }
    });
    btnAdd.setToolTipText("Add the above strategy to both droplists in the "
        + "Make Shapes panel.");
    pnlStrategy.add(btnAdd);
    pnlMake.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
        null, null), "Make Shapes", TitledBorder.CENTER,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    //make ball and combine
    pnlControl.add(pnlMake);
    pnlMake.setLayout(new GridLayout(0, 1, 0, 0));

    btnMakeBall.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        modelControlAdpt.makeBall(combo1.getItemAt(combo1.getSelectedIndex()),
            comboPaint.getItemAt(comboPaint.getSelectedIndex()));
      }
    });
    pnlMake.add(btnMakeBall);
    btnMakeBall.setToolTipText(
        "<html>" + "Create a shape chosen from the droplist in the Shape panel" +
          "<br>" + "with the update strategy in the top droplist below." +
        "</html>");
    combo1.setToolTipText("Top droplist");

    pnlMake.add(combo1);
    combo2.setToolTipText("Bottom droplist");
    pnlMake.add(combo2);
    btnCombine.setToolTipText("Combine two strategies from two droplists above.");

    btnCombine.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        TStrategyDropListItem o = modelControlAdpt.combineStrategies(
            combo1.getItemAt(combo1.getSelectedIndex()),
            combo2.getItemAt(combo2.getSelectedIndex()));
        if (null == o)
          return; // just in case
        combo1.insertItemAt(o, 0);
        combo1.setSelectedIndex(0);
        combo2.insertItemAt(o, 0);
        combo2.setSelectedIndex(0);
      }
    });
    pnlMake.add(btnCombine);
    pnlSwitch.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
        null, null), "Switcher Controls", TitledBorder.CENTER,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    //make switcher and switch button
    pnlControl.add(pnlSwitch);
    pnlSwitch.setLayout(new GridLayout(2, 1, 0, 0));
    btnMakeSwitcher.setToolTipText(
        "<html>" + "Make a switcher whose shape is chosen from the droplist in "
                 + "the Shape panel" +
          "<br>" + "with the update strategy in the top droplist in the "
                 + "Make Shapes panel." +
        "</html>");
    btnMakeSwitcher.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modelControlAdpt.makeSwitcherBall(comboPaint.getItemAt(
            comboPaint.getSelectedIndex()));
      }
    });
    pnlSwitch.add(btnMakeSwitcher);
    btnSwitch.setToolTipText(
        "<html>" + "Change all switchers' update strategies to the one " +
           "<br>"+ "in the top droplist in the Make Shapes panel." +
        "</html>");
    btnSwitch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modelControlAdpt.switchStrategy(combo1.getItemAt(
            combo1.getSelectedIndex()));
      }
    });
    pnlSwitch.add(btnSwitch);

    //clear button
    btnClearAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        modelControlAdpt.clearBalls();
      }
    });
    pnlControl.add(btnClearAll);
    btnClearAll.setToolTipText("Remove all the shapes below.");
    pnlPaintStrategy.setBorder(new TitledBorder(new EtchedBorder(
        EtchedBorder.LOWERED, null, null), "Shape",
        TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
    pnlPaintStrategy.setToolTipText("");

    pnlControl.add(pnlPaintStrategy);
    pnlPaintStrategy.setLayout(new GridLayout(0, 1, 0, 0));

    pnlPaintStrategy.add(txtPaintStrategy);
    btnAddPaintStrategy.setToolTipText("Add the above shape in the droplist below.");
    pnlPaintStrategy.add(btnAddPaintStrategy);
    btnAddPaintStrategy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        TPaintDropListItem o = modelControlAdpt.addPaintStrategyFac(
            txtPaintStrategy.getText());
        if (null == o)
          return; // just in case
        comboPaint.insertItemAt(o, 0);
        comboPaint.setSelectedIndex(0);
      }
    });
    comboPaint.setToolTipText("Shape droplist");
    pnlPaintStrategy.add(comboPaint);

    //Ball panel settings
    pnlBall.setSize(600, 800);
    pnlBall.setBackground(Color.WHITE);
    contentPane.add(pnlBall, BorderLayout.CENTER);

    //Set the panel for ball world
    modelControlAdpt.setPanel(pnlBall);
  }

}
