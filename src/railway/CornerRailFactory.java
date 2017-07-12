package railway;

/**
 * Created by abelsang on 2017-07-05.
 * A factory create different corner rail in cases.
 */
public class CornerRailFactory {

  /**
   * Create a corner rail by 2 given direction and  the location.
   *
   * @param end1 direction 1.
   * @param end2 direction 2.
   * @param loc location.
   * @return a corner rail has the specific direction.
   */
  CornerRail create(Direction end1, Direction end2, GridLoc loc) {
    CornerRail rail = new CornerRail(end1, end2, loc);
    switch (end1) {
      case SOUTH:
        switch (end2) {
          case EAST:
            setSERail(rail);
            break;
          case WEST:
            setSWRail(rail);
            break;
        }
        break;
      case NORTH:
        switch (end2) {
          case EAST:
            setNERail(rail);
            break;
          case WEST:
            setNWRail(rail);
            break;
        }
        break;
    }
    return rail;
  }

  /**
   * Set rail to North East.
   *
   * @param rail the NE Rail.
   */
  private void setNERail(CornerRail rail) {
    rail.setAll(0.5, -0.5, 180);
  }

  /**
   * Set rail to North West.
   *
   * @param rail the NW Rail.
   */
  private void setNWRail(CornerRail rail) {
    rail.setAll(-0.5, -0.5, 270);
  }

  /**
   * Set rail to South East.
   *
   * @param rail the SE Rail.
   */
  private void setSERail(CornerRail rail) {
    rail.setAll(0.5, 0.5, 90);
  }

  /**
   * Set rail to South West.
   *
   * @param rail the SW Rail.
   */
  private void setSWRail(CornerRail rail) {
    rail.setAll(-0.5, 0.5, 0);
  }

}
