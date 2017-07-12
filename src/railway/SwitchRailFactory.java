package railway;

/**
 * Created by abelsang on 2017-07-05.
 * A factory create different switch rails in cases.
 */
public class SwitchRailFactory {

  /**
   * Create a switch rail in cases.
   *
   * @param e1 direction 1.
   * @param e2 direction 2.
   * @param e3 direction 3.
   * @param loc location.
   * @return The switch rail in specific direction.
   */
  public SwitchRail create(Direction e1, Direction e2, Direction e3, GridLoc loc) {
    SwitchRail rail = new SwitchRail(e1, e2, e3, loc);
    switch (e1) {
      case EAST:
        switch (e3) {
          case NORTH:
            setEWN(rail);
            break;
          case SOUTH:
            setEWS(rail);
            break;
        }
        break;
      case WEST:
        switch (e3) {
          case NORTH:
            setWEN(rail);
            break;
          case SOUTH:
            setWES(rail);
            break;
        }
        break;
      case NORTH:
        switch (e3) {
          case EAST:
            setNSE(rail);
            break;
          case WEST:
            setNSW(rail);
            break;
        }
        break;
      case SOUTH:
        switch (e3) {
          case EAST:
            setSNE(rail);
            break;
          case WEST:
            setSNW(rail);
            break;
        }
        break;
    }
    return rail;
  }

  /**
   * Set EWN Rail
   *
   * @param rail EWN Rail.
   */
  private void setEWN(SwitchRail rail) {
    rail.setAll(0.0, 0.5, 1.0, 0.5, 0.5, -0.5, 180);
  }

  /**
   * Set EWS Rail.
   *
   * @param rail EWS Rail.
   */
  private void setEWS(SwitchRail rail) {
    rail.setAll(0.0, 0.5, 1.0, 0.5, 0.5, 0.5, 90);

  }

  /**
   * Set SNE Rail.
   *
   * @param rail SNE Rail.
   */
  private void setSNE(SwitchRail rail) {
    rail.setAll(0.5, 0.0, 0.5, 1.0, 0.5, 0.5, 90);
  }

  /**
   * Set SNW Rail.
   *
   * @param rail SNW Rail.
   */
  private void setSNW(SwitchRail rail) {
    rail.setAll(0.5, 0.0, 0.5, 1.0, -0.5, 0.5, 0);

  }

  /**
   * Set NSE Rail.
   *
   * @param rail NSE Rail.
   */
  private void setNSE(SwitchRail rail) {
    rail.setAll(0.5, 0.0, 0.5, 1.0, 0.5, -0.5, 180);

  }

  /**
   * Set NSW Rail.
   *
   * @param rail NSW Rail.
   */
  private void setNSW(SwitchRail rail) {
    rail.setAll(0.5, 0.0, 0.5, 1.0, -0.5, -0.5, 270);
  }

  /**
   * Set WEN Rail.
   *
   * @param rail WEN Rail.
   */
  private void setWEN(SwitchRail rail) {
    rail.setAll(0.0, 0.5, 1.0, 0.5, -0.5, -0.5, 270);
  }

  /**
   * Set WES Rail.
   *
   * @param rail WES Rail.
   */
  private void setWES(SwitchRail rail) {
    rail.setAll(0.0, 0.5, 1.0, 0.5, -0.5, 0.5, 0);
  }

}
