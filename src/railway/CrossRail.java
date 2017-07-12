package railway;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The cross rail has 4 ends.
 */
public class CrossRail extends Rail {

  /**
   * The line coordinates for drawing itself.
   */
  //since those variables never changed, can be set to static final.
  private static final double X1 = 0.0;
  private static final double Y1 = 0.5;
  private static final double X2 = 1.0;
  private static final double Y2 = 0.5;
  private static final double X3 = 0.5;
  private static final double Y3 = 0.0;
  private static final double X4 = 0.5;
  private static final double Y4 = 1.0;

  /**
   * The cross rail with location.
   *
   * @param loc location.
   */
  public CrossRail(GridLoc loc) {
    super(loc);
    for (Direction d : Direction.values()) {
      neighbourMap.put(d, new EmptyRail());
    }
  }

  /**
   * Exit the rail and report the exit direction.
   *
   * @param dir The current direction.
   * @return The exit direction.
   */
  // Given that d is the railway.Direction from which a Car entered,
  // report where the Car will exit.
  @Override
  public Direction exit(Direction dir) {
    return dir.opposite();
  }

  // Redraw myself.

  /**
   * Draw the graphic of cross rail and redraw itself.
   *
   * @param graph The graph.
   */
  @Override
  public void draw(Graphics graph) {
    super.draw(graph);
    graph.setColor(color);
    Rectangle b = getBounds();
    graph.drawLine((int) (X1 * b.width), (int) (Y1 * b.height),
        (int) (X2 * b.width), (int) (Y2 * b.height));
    graph.drawLine((int) (X3 * b.width), (int) (Y3 * b.height),
        (int) (X4 * b.width), (int) (Y4 * b.height));
  }

  /**
   * Return the toString of the cross rail.
   * @return String.
   */
  @Override
  public String toString() {
    return "CrossRail";
  }
}

