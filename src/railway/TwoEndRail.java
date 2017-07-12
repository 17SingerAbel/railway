package railway;

import java.awt.Color;

/**
 * A two end rail has two ends which may or may be not opposite each other.
 */
public abstract class TwoEndRail extends Rail {

  /**
   * A two end rail with 2 directions and location.
   *
   * @param end1 direction 1.
   * @param end2 direction 2.
   * @param loc location.
   */
  public TwoEndRail(Direction end1, Direction end2, GridLoc loc) {
    super(loc);
    color = Color.black;
    neighbourMap.put(end1, new EmptyRail());
    neighbourMap.put(end2, new EmptyRail());
  }

  /**
   * Given dir is the direction where car entered. Report where to exit.
   *
   * @param dir The current direction.
   * @return direction to exit.
   */
  @Override
  public Direction exit(Direction dir) {
    if (validDir(dir)) {
      for (Direction end : neighbourMap.keySet()) {
        if (!end.equals(dir)) {
          return end;
        }
      }
    }
    return null;
  }

  /**
   * Return the toString of the two-end rail.
   * @return String.
   */
  @Override
  public String toString() {
    return "TwoEndRail";
  }

}

