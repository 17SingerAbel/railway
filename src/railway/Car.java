package railway;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A car. A car object is a car in a train.
 */
public class Car {

  /**
   * The current rail where tha car in .
   */
  private Rail currentRail;
  /**
   * The color of the car.
   */
  private Color color;
  /**
   * The next car followed.
   */
  private Car nextCar;
  /**
   * The direction of the current rail.
   */
  private Direction dir;
  /**
   * The shape of the car.
   */
  private Rectangle board;
  /**
   * The width of the car.
   */
  private double width;
  /**
   * The height of the car.
   */
  private double height;
  /**
   * The square root of Hypotenuse.
   */
  private int sqrtOfHypotenuse;

  /**
   * A car can has different color.
   *
   * @param color Color.
   */
  public Car(Color color) {
    this.color = color;
  }

  /**
   * Get the current direction.
   *
   * @return The current direction.
   */
  public Direction getDirection() {
    return dir;
  }

  /**
   * Set car moving in direction dir.
   *
   * @param dir The direction.
   */
  public void setDirection(Direction dir) {
    this.dir = dir;
  }

  /**
   * Return current rail.
   *
   * @return rail.
   */
  public Rail getRail() {
    return currentRail;
  }

  /**
   * Place this Car on Rail r.
   *
   * @param rail The rail.
   */
  public void setRail(Rail rail) {
    currentRail = rail;
  }

  /**
   * Return next car.
   *
   * @return nextCar.
   */
  Car getNextCar() {
    return nextCar;
  }

  /**
   * Set the next Car.
   *
   * @param car the next car.
   */
  void setNextCar(Car car) {
    nextCar = car;
  }

  /**
   * Move forward one by one.
   */
  // Move forward one TrackPiece; Tell
  // all of the cars I am pulling to move as well.
  void move() {
    Direction nextDir = currentRail.exit(dir).opposite();
    Rail nextRail = currentRail.nextRail(dir);
    dir = nextDir;
    if (nextRail.enter(this)) {
      currentRail.leave();
      currentRail = nextRail;
      // We have to call this here rather than within currentRail.enter()
      // because otherwise the wrong railway.Rail is used...
      currentRail.repaint();
      if (nextCar != null) {
        nextCar.move();
      }
    }
  }

  /**
   * Switch rail go straightly.
   *
   * @return Whether the car go straight in switch rail.
   */
  private boolean switchStraight() {
    return currentRail instanceof SwitchRail && ((SwitchRail) currentRail).getGoingStraight();
  }

  /**
   * Switch rail go turns.
   *
   * @return Whether the car go turns in switch rail.
   */
  private boolean switchCorner() {
    return currentRail instanceof SwitchRail && !((SwitchRail) currentRail).getGoingStraight();
  }

  /**
   * A helper function to draw the car in corner.
   *
   * @param xPoints x-coordinate points.
   * @param yPoints y-coordinate points.
   */
  private void drawCorner(int[] xPoints, int[] yPoints) {
    if (currentRail.getStartAngle() == 180) {
      makeCornerPolygon(xPoints, yPoints, -sqrtOfHypotenuse,
          sqrtOfHypotenuse, (int) width, (int) (width / 2), (int) (height / 2), 0);
    } else if (currentRail.getStartAngle() == 270) {
      makeCornerPolygon(xPoints, yPoints, sqrtOfHypotenuse,
          sqrtOfHypotenuse, (int) (width / 2), 0, 0, (int) (height / 2));
    } else if (currentRail.getStartAngle() == 90) {
      makeCornerPolygon(xPoints, yPoints, -sqrtOfHypotenuse,
          -sqrtOfHypotenuse, (int) (width / 2), (int) width, (int) height, (int) (height / 2));
    } else if (currentRail.getStartAngle() == 0) {
      makeCornerPolygon(xPoints, yPoints, sqrtOfHypotenuse,
          -sqrtOfHypotenuse, 0, (int) (width / 2), (int) (height / 2), (int) height);
    }

  }

  /**
   * Draw the graphic of the car.
   *
   * @param graph The graphic.
   */
  // Redraw myself.
  void draw(Graphics graph) {
    board = currentRail.getBounds();
    width = board.width;
    height = board.height;
    sqrtOfHypotenuse = (int) Math.sqrt((width * width) / 4 + (height * height) / 4);
    int[] xPoints = new int[5];
    int[] yPoints = new int[5];
    if (currentRail instanceof CornerRail || switchCorner()) {
      drawCorner(xPoints, yPoints);
    } else if (currentRail instanceof StraightRail || currentRail instanceof CrossRail
        || switchStraight()) {
      if (dir.equals(Direction.NORTH) || dir.equals(Direction.SOUTH)) {
        makeStraightPolygon(xPoints, yPoints);
      } else {
        makeStraightPolygon(yPoints, xPoints);
      }
    }
    graph.setColor(color);
    graph.drawPolygon(xPoints, yPoints, 5);

  }

  /**
   * Create corner polygon.
   *
   * @param xPoints x-coordinates points.
   * @param yPoints y-coordinates points.
   * @param xSideOffset positive or minus of square root of Hypotenuse.
   * @param ySideOffset positive or minus of square root of Hypotenuse.
   * @param x0Mod x0 mod.
   * @param x1Mod x1 mod.
   * @param y0Mod y0 mod.
   * @param y1Mod y1 mod.
   */
  // The points, in order, are the back right of the car, the front right of the car,
  // the front left of the car, and the back left of the car.
  private void makeCornerPolygon(int[] xPoints, int[] yPoints,
      int xSideOffset, int ySideOffset, int x0Mod, int x1Mod, int y0Mod, int y1Mod) {
    xPoints[0] = x0Mod;
    xPoints[1] = x1Mod;
    xPoints[2] = xPoints[1] + xSideOffset / 2;
    xPoints[3] = xPoints[0] + xSideOffset / 2;
    xPoints[4] = xPoints[0];

    yPoints[0] = y0Mod;
    yPoints[1] = y1Mod;
    yPoints[2] = yPoints[1] + ySideOffset / 2;
    yPoints[3] = yPoints[0] + ySideOffset / 2;
    yPoints[4] = yPoints[0];
  }

  /**
   * Create straight polygon.
   *
   * @param aPoints x-coordinates points.
   * @param bPoints y-coordinates points.
   */
  private void makeStraightPolygon(int[] aPoints, int[] bPoints) {
    int width = board.width;
    int height = board.height;
    aPoints[0] = width / 4;
    aPoints[1] = 3 * width / 4;
    aPoints[2] = 3 * width / 4;
    aPoints[3] = width / 4;
    aPoints[4] = aPoints[0];

    bPoints[0] = height / 8;
    bPoints[1] = height / 8;
    bPoints[2] = 7 * height / 8;
    bPoints[3] = 7 * height / 8;
    bPoints[4] = bPoints[0];
  }

  @Override
  public String toString() {
    return "Car";
  }

}

