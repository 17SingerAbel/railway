package railway;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The straight rail has two ends which must be opposite each other.
 */
public class StraightRail extends TwoEndRail {

  /**
   * The multipliers for the endpoints of the railway.StraightRail.
   */
  private double x1, y1, x2, y2;

  /**
   * A Straight Rail.
   *
   * @param end1 direction1.
   * @param end2 direction2.
   * @param loc location.
   */
  public StraightRail(Direction end1, Direction end2, GridLoc loc) {
    super(end1, end2, loc);
    color = Color.blue;
    if ((end1 == Direction.EAST) || (end1 == Direction.WEST)) {
      setEWLoc();
    } else if ((end1 == Direction.NORTH) || (end1 == Direction.SOUTH)) {
      setNSLoc();
    }
  }

  /**
   * Create East West Rail.
   */
  private void setEWLoc() {
    x1 = 0.0;
    y1 = 0.5;
    x2 = 1.0;
    y2 = 0.5;
  }

  /**
   * Build North South Rail.
   */
  private void setNSLoc() {
    x1 = 0.5;
    y1 = 0.0;
    x2 = 0.5;
    y2 = 1.0;
  }

  /**
   * Draw the graphic of corner rail and redraw itself.
   *
   * @param graph The graph.
   */
  @Override
  public void draw(Graphics graph) {
    super.draw(graph);
    graph.setColor(color);
    Rectangle b = getBounds();
    graph.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
        (int) (x2 * b.width), (int) (y2 * b.height));

  }

  /**
   * Return the toString of the straight rail.
   * @return String.
   */
  @Override
  public String toString() {
    return "StraightRail";
  }

}

