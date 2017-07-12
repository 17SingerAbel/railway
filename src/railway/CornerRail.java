package railway;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The cornerRail which has two ends but not opposite to each other.
 */
public class CornerRail extends TwoEndRail {

  /**
   * The multipliers for the width and height.
   */
  private double x1, y1;
  /**
   * The start angle of the rail.
   */
  private int startAngle;

  /**
   * A corner rail.
   *
   * @param end1 direction 1.
   * @param end2 direction 2.
   * @param loc location.
   */
  public CornerRail(Direction end1, Direction end2, GridLoc loc) {
    super(end1, end2, loc);
  }

  /**
   * Set multipliers for width and height and the start angle for different cases.
   *
   * @param x multiplier for width.
   * @param y multiplier for height.
   * @param angle start angle.
   */
  void setAll(double x, double y, int angle) {
    x1 = x;
    y1 = y;
    startAngle = angle;
  }

  /**
   * Return start angle.
   *
   * @return start angle.
   */
  // Without this override, may return the instance variable in Rail.java
  // but not in CornerRail.java
  @Override
  int getStartAngle() {
    return startAngle;
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
    graph.drawArc((int) (x1 * b.width), (int) (y1 * b.height), b.width, b.height, startAngle, 90);
  }

  /**
   * Return the toString of the corner rail.
   * @return String.
   */
  @Override
  public String toString() {
    return "CornerRail";
  }

}

