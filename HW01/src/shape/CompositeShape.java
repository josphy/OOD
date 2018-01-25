package shape;

import java.util.Set;
import java.awt.Graphics;

/**
* this is a concrete sub-class of AShape contains two composite shape
 *  @author Yining Bao
 *  @author Haoshan Zou */
public class CompositeShape extends AShape {
	private Set<AShape> children = new HashSet<AShape>();

	/**
	* Constructor of composite shape
	 * 	 * @param shape1 first general shape
	 * 	 * @param shape2 second general shape */
	public CompositeShape(AShape shape1, AShape shape2) {
		children.add(shape1);
		children.add(shape2);
	}

	@Override
	public void paint(Graphics g) {
		for (AShape child : children) {
			child.paint(g);
		}

	}
}
