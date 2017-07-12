package railway;

/**
 * The empty rail from rails.
 */
public class EmptyRail extends Rail {

  /**
   * Return true if d is a valid direction for me.
   *
   * @param dir The direction.
   * @return false
   */
  @Override
  public boolean exitOK(Direction dir) {
    return false;
  }

  /**
   * Register that Rail r is in Direction d.
   *
   * @param dir The direction.
   */
  @Override
  public void register(Rail rail, Direction dir) {
  }

  /**
   * Given that d is the railway.Direction from which a Car entered,
   * report where the Car will exit.
   *
   * @param dir The direction.
   * @return Nothing
   */
  @Override
  public Direction exit(Direction dir) {
    return null;
  }

  /**
   * Given that d is the railway.Direction from which a Car entered,
   * report which Rail is next.
   *
   * @return Nothing
   */
  @Override
  public Rail nextRail(Direction dir) {
    return null;
  }

}

