package railway;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

/**
 * The Rail class.  A Rail object is a piece of track.  It knows
 * whether there is a Train on it or not, and Trains can enter()
 * and leave().  Given an entrance, a Rail can report where the exit()
 * is.
 */
public abstract class Rail extends Canvas {

  /**
   * The color of the rail.
   */
  protected Color color;
  /**
   * The location of the rail on the grid.
   */
  // modifier is default because it will be used in the constructor in subclass.
  GridLoc location;
  /**
   * The map to store ends of rail and its neighbours.
   */
  Map<Direction, Rail> neighbourMap;
  /**
   * Whether the rail has train or nor.
   */
  private boolean haveATrain;
  /**
   * The current car on the rail.
   */
  private Car currentCar;
  /**
   * The start angle.
   */
  private int startAngle;

  /**
   * The rail.
   */
  public Rail() {
    super();
  }

  /**
   * The rail with location.
   *
   * @param loc location.
   */
  public Rail(GridLoc loc) {
    location = loc;
    haveATrain = false;
    startAngle = 0;
    location = loc;
    neighbourMap = new HashMap<>();
  }

  /**
   * Return true iff I have a Car.
   *
   * @return Whether there is a car on itself.
   */
  boolean occupied() {
    return haveATrain;
  }

  /**
   * Enter the railway and Register that a Train is on the rail.
   * Return true if successful,
   *
   * @param newCar The coming car.
   * @return whether there is a train on the rail.
   */
  boolean enter(Car newCar) {
    haveATrain = true;
    currentCar = newCar;
    return true;
  }

  /**
   * Leave the railway and register that a rain is no longer on the rail.
   */
  void leave() {
    haveATrain = false;
    repaint();
  }

  /**
   * Paint the graph and update my display..
   *
   * @param graph Graph.
   */
  public void paint(Graphics graph) {
    draw(graph);
  }

  /**
   * Return true if dir is a valid direction for me.
   */
  public boolean exitOK(Direction dir) {
    return neighbourMap.containsKey(dir);
  }

  /**
   * Return true if d is valid for this rail, return false and
   * print an error otherwise.
   *
   * @param dir direction.
   * @return the direction is valid or not.
   */
  boolean validDir(Direction dir) {
    if (!exitOK(dir)) {
      Exception e = new Exception();
      e.printStackTrace(System.out);
      return false;
    }
    return true;
  }

  /**
   * Register that rail is in Direction d.
   *
   * @param rail rail.
   * @param dir direction.
   */
  public void register(Rail rail, Direction dir) {
    if (validDir(dir)) {
      neighbourMap.put(dir, rail);
    }
  }

  /**
   * Given that dir is the Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param dir The direction.
   */
  abstract public Direction exit(Direction dir);

  /**
   * Given that d is the Direction from which a Car entered,
   * report which Rail is next.
   *
   * @param dir direction.
   * @return The next Rail.
   */
  public Rail nextRail(Direction dir) {
    return neighbourMap.get(exit(dir));
  }

  /**
   * Get the start angle.
   *
   * @return startAngle.
   */
  int getStartAngle() {
    return startAngle;
  }

  /**
   * Draw the graphic of the rail and redraw itself.
   *
   * @param graph Graph.
   */
  // Redraw myself.
  public void draw(Graphics graph) {
    Rectangle b = getBounds();
    graph.setColor(Color.white);
    graph.fillRect(0, 0, b.width - 1, b.height - 1);
    graph.setColor(Color.lightGray);
    graph.drawRect(0, 0, b.width - 1, b.height - 1);
    if (haveATrain) {
      currentCar.draw(graph);
    }
  }

  /**
   * Return myself as a string.
   *
   * @return The string.
   */
  @Override
  public String toString() {
    return "Rail";
  }

}

