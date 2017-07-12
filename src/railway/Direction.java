package railway;

/**
 * Created by abelsang on 2017-07-03.
 * The directions.
 */
public enum Direction {
  NORTH, SOUTH, EAST, WEST;

  /**
   * Get the opposite direction
   *
   * @return opposite direction.
   */
  public Direction opposite() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case WEST:
        return EAST;
    }
    return null;
  }

}
