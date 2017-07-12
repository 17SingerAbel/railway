package railway;

import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A switch rail has 3 ends with 2 result.
 */
public class SwitchRail extends Rail {

  /**
   * My line coordinates for drawing myself.
   */
  private double x1, y1, x2, y2, x3, y3;
  private Direction enterEnd;
  private Direction straightEnd;
  private Direction cornerEnd;
  /**
   * Info for my corner portion.
   */
  private int startAngle;
  /**
   * Whether I am aligned to go straight.
   */
  private boolean goingStraight;

  /**
   * A switch rail with 3 directions and location.
   *
   * @param end1 direction 1.
   * @param end2 direction 2.
   * @param end3 direction 3.
   * @param loc location.
   */
  public SwitchRail(Direction end1, Direction end2, Direction end3, GridLoc loc) {
    super(loc);
    color = Color.magenta;
    neighbourMap.put(end1, new EmptyRail());
    neighbourMap.put(end2, new EmptyRail());
    neighbourMap.put(end3, new EmptyRail());

    enterEnd = end1;
    straightEnd = end2;
    cornerEnd = end3;
  }

  /**
   * Set multipliers and start angle.
   *
   * @param a1 a1.
   * @param b1 b1.
   * @param a2 a2.
   * @param b2 b2.
   * @param a3 a3.
   * @param b3 b3.
   * @param angle startAngle.
   */
  void setAll(double a1, double b1, double a2, double b2, double a3, double b3, int angle) {
    x1 = a1;
    x2 = a2;
    x3 = a3;
    y1 = b1;
    y2 = b2;
    y3 = b3;
    startAngle = angle;

  }

  /**
   * Return true if dir is a valid direction for me.
   */
  @Override
  public boolean exitOK(Direction dir) {
    return dir.equals(enterEnd) || dir.equals(straightEnd) || dir.equals(cornerEnd);
  }

  /**
   * Return true if d is valid for this railway.Rail, return false and
   * print an error otherwise.
   *
   * @param dir direction.
   * @return the direction is valid or not.
   */
  @Override
  boolean validDir(Direction dir) {
    if (!exitOK(dir)) {
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * Given that dir is the Direction from which a Car entered,
   * report where the railway.Car will exit.
   *
   * @param dir The direction.
   * @return direction to exit.
   */
  @Override
  public Direction exit(Direction dir) {
    if (validDir(dir)) {
      if (goingStraight) {
        return dir.equals(enterEnd)
            ? straightEnd
            : enterEnd;
      } else {
        return dir.equals(enterEnd)
            ? cornerEnd
            : enterEnd;
      }
    }
    return null;
  }

  /**
   * d is the direction that I entered from,
   * and must be one of enterEnd, straightEnd and CornerEnd.
   *
   * @param dir The direction.
   * @return the rail at the other end.
   */
  @Override
  public Rail nextRail(Direction dir) {
    if (validDir(dir)) {
      if (goingStraight) {
        return dir.equals(enterEnd)
            ? neighbourMap.get(straightEnd)
            : neighbourMap.get(enterEnd);
      } else {
        return dir.equals(enterEnd)
            ? neighbourMap.get(cornerEnd)
            : neighbourMap.get(enterEnd);
      }
    }
    return null;
  }

  /**
   * Handle a mouse click.  This will toggle the direction of the switch.
   */
  @Override
  public boolean handleEvent(Event evt) {
    if (evt.id == Event.MOUSE_DOWN && !occupied()) {
      goingStraight = !goingStraight;
      repaint();
      return true;
    }
    // If we get this far, I couldn't handle the event
    return false;
  }

  /**
   * Return the startAngle.
   * @return startAngle.
   */
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
    Rectangle b = bounds();
    // Draw current direction of the switch darker.
    if (goingStraight) {
      graph.setColor(Color.lightGray);
      graph.drawArc((int) (x3 * b.width), (int) (y3 * b.height),
          b.width, b.height, startAngle, 90);
      graph.setColor(color);
      graph.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
          (int) (x2 * b.width), (int) (y2 * b.height));
    } else {
      graph.setColor(Color.lightGray);
      graph.drawLine((int) (x1 * b.width), (int) (y1 * b.height),
          (int) (x2 * b.width), (int) (y2 * b.height));
      graph.setColor(color);
      graph.drawArc((int) (x3 * b.width), (int) (y3 * b.height),
          b.width, b.height, startAngle, 90);
    }
  }

  /**
   * Return whether rail go straight or not.
   *
   * @return go straight or not.
   */
  public boolean getGoingStraight() {
    return goingStraight;
  }

  /**
   * Return the toString of the switch rail.
   * @return String.
   */
  @Override
  public String toString() {
    return "SwitchRail";
  }

}

