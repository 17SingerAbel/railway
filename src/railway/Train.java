package railway;

/**
 * Trains have an Engine, followed by one or more Cars,
 * followed by a Caboose.Train has speed. Each turn, a railway.Train will move
 * one track piece in the current direction.
 */
public class Train extends Thread {

  /**
   * Track on which I am running.
   */
  private Track theTrack;
  /**
   * The number of cars in me.
   */
  private int numCars = 0;
  //private int totalWeight;    // The sum of the weights of my cars.
  /**
   * The amount of time between each of my turns.
   */
  private int delay;
  /** Whether I am moving forward. */
  private boolean forward = true;
  /**
   * The Car pulling the train.
   */
  private Car engine;
  /**
   * The Car at the end of the train.
   */
  private Car caboose;

  /**
   * A train with name.
   *
   * @param threadName name.
   */
  public Train(String threadName) {
    super(threadName);
  }

  /**
   * Add A Car T to the end of me.
   * @param T A car.
   */
  void addToTrain(Car T) {
    if (engine != null) {
      caboose.setNextCar(T);
    } else {
      engine = T;
    }
    caboose = T;
    numCars++;
  }

  /**
   * Set my delay between moves to d seconds.
   *
   * @param d some seconds.
   */
  void setSpeed(int d) {
    delay = d;
  }

  /**
   * Add me to Track T at location loc moving in direction dir.
   *
   * @param T Track
   * @param dir direction
   * @param loc location
   */
  void addToTrack(Track T, Direction dir, GridLoc loc) {
    theTrack = T;
    theTrack.addTrain(this);
    Car currCar = engine;
    while (currCar != null) {
      currCar.setDirection(dir);
      theTrack.addCar(loc, currCar);
      // Now figure out the dir for the next railway.Car,
      // and the next loc.
      switch (dir) {
        case NORTH:
          loc.delRow();
          break;
        case SOUTH:
          loc.addRow();
          break;
        case EAST:
          loc.addCol();
          break;
        case WEST:
          loc.delCol();
          break;
      }
      Direction nextDir = currCar.getRail().exit(dir);
      Rail nextRail = currCar.getRail().nextRail(nextDir);
      // Now I know the railway.Rail on which the next currCar will
      // be.  Find out how it got on to it.
      dir = nextRail.exit(dir.opposite());
      currCar = currCar.getNextCar();
    }
  }

  /**
   * Halve my delay.
   */
  void accelerateALot() {
    delay /= 2;
  }

  /**
   * Double my delay.
   */
  void decelerateALot() {
    delay *= 2;
  }

  /**
   * Speed up by a factor of 20ms.
   */
  void accelerate() {
    delay -= 20;
  }

  /**
   * Slow down by a factor of 20ms.
   */
  void decelerate() {
    delay += 20;
  }

  /**
   * Let the train run.
   */
  @Override
  public void run() {
    while (forward) {
      Direction dir = engine.getDirection();
      if (!engine.getRail().nextRail(dir).occupied()) {
        engine.move();
      }
      // Sleep for 1 second.
      try {
        sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

