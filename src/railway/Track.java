package railway;

import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;

/**
 * A track is made up of rails.
 */
public class Track extends Frame {

  /**
   * The Panel on which the railway.Track appears.
   */
  private TrackPanel trackPanel;
  // ------------------------------------------------------------------
  // The following items are the Rails and Trains on the railway.Track.
  /**
   * The maximum number of trains I can hold.
   */
  private int MAX_TRAINS = 10;
  /**
   * The Trains running on me.
   */
  private Train[] trainList = new Train[MAX_TRAINS];
  /**
   * The number of trains on me .
   */
  private int numTrains = 0;
  /**
   * The grid of rails.
   */
  private Rail[][] rails;
  /**
   * Whether my Trains are running.  If true, no more Rails can be placed.
   */
  private boolean running = false;

  // ------------------------------------------------------------------
  /**
   * The following buttons define the interface for running and saving me.
   */
  private Button runStopButton, quitButton;
  /**
   * The following buttons are used to control the track.
   */
  private Button accelButton, decelButton, accelLotsButton, decelLotsButton;
  /**
   * A corner rail factory.
   */
  private CornerRailFactory cornerFactory;
  /**
   * A switch rail factory.
   */
  private SwitchRailFactory switchFactory;

  /**
   * A new Track.
   */
  public Track() {
    rails = new Rail[20][20];
    cornerFactory = new CornerRailFactory();
    switchFactory = new SwitchRailFactory();
    buildTrack();
    for (int row = 0; row < rails.length; row++) {
      for (int col = 0; col < rails[0].length; col++) {
        rails[row][col] = new EmptyRail();
      }
    }
    rails[0][0] = cornerFactory.create(Direction.SOUTH, Direction.EAST, new GridLoc(0, 0));

    rails[0][1] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(0, 1));
    connectRails(rails[0][0], rails[0][1], Direction.EAST);

    rails[0][2] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.SOUTH, new GridLoc(0, 2));
    connectRails(rails[0][1], rails[0][2], Direction.EAST);
    connectRails(rails[0][2], rails[0][3], Direction.EAST);

    rails[0][3] = new StraightRail(Direction.WEST, Direction.EAST, new GridLoc(0, 3));
    connectRails(rails[0][2], rails[0][3], Direction.EAST);

    rails[0][4] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.SOUTH, new GridLoc(0, 4));
    connectRails(rails[0][3], rails[0][4], Direction.EAST);

    rails[1][4] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(1, 4));
    connectRails(rails[0][4], rails[1][4], Direction.SOUTH);

    rails[0][5] = cornerFactory.create(Direction.SOUTH, Direction.WEST, new GridLoc(0, 5));
    connectRails(rails[0][4], rails[0][5], Direction.EAST);

    rails[1][2] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(1, 2));
    connectRails(rails[0][2], rails[1][2], Direction.SOUTH);

    rails[2][2] = new CrossRail(new GridLoc(2, 2));
    connectRails(rails[1][2], rails[2][2], Direction.SOUTH);

    rails[2][3] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(2, 3));
    connectRails(rails[2][2], rails[2][3], Direction.EAST);

    rails[2][4] = new CrossRail(new GridLoc(2, 4));
    connectRails(rails[1][4], rails[2][4], Direction.SOUTH);
    connectRails(rails[2][3], rails[2][4], Direction.EAST);

    rails[2][5] = new CrossRail(new GridLoc(2, 5));
    connectRails(rails[2][4], rails[2][5], Direction.EAST);

    rails[2][6] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.SOUTH, new GridLoc(2, 6));
    connectRails(rails[2][5], rails[2][6], Direction.EAST);

    rails[3][6] = cornerFactory.create(Direction.NORTH, Direction.WEST, new GridLoc(3, 6));
    connectRails(rails[2][6], rails[3][6], Direction.SOUTH);

    rails[3][5] = cornerFactory.create(Direction.NORTH, Direction.EAST, new GridLoc(3, 5));
    connectRails(rails[3][6], rails[3][5], Direction.WEST);
    connectRails(rails[2][5], rails[3][5], Direction.SOUTH);

    rails[1][5] = switchFactory
        .create(Direction.NORTH, Direction.SOUTH, Direction.EAST, new GridLoc(1, 5));
    connectRails(rails[0][5], rails[1][5], Direction.SOUTH);
    connectRails(rails[2][5], rails[1][5], Direction.NORTH);

    rails[1][6] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(1, 6));
    connectRails(rails[1][5], rails[1][6], Direction.EAST);

    rails[1][7] = cornerFactory.create(Direction.SOUTH, Direction.WEST, new GridLoc(1, 7));
    connectRails(rails[1][6], rails[1][7], Direction.EAST);

    rails[2][7] = switchFactory
        .create(Direction.SOUTH, Direction.NORTH, Direction.WEST, new GridLoc(2, 7));
    connectRails(rails[1][7], rails[2][7], Direction.SOUTH);
    connectRails(rails[2][6], rails[2][7], Direction.EAST);

    rails[3][7] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(3, 7));
    connectRails(rails[2][7], rails[3][7], Direction.SOUTH);

    rails[4][7] = cornerFactory.create(Direction.NORTH, Direction.WEST, new GridLoc(4, 7));
    connectRails(rails[3][7], rails[4][7], Direction.SOUTH);

    rails[4][6] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(4, 6));
    connectRails(rails[4][7], rails[4][6], Direction.WEST);

    rails[4][5] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.SOUTH, new GridLoc(4, 5));
    connectRails(rails[4][6], rails[4][5], Direction.WEST);

    rails[4][4] = cornerFactory.create(Direction.NORTH, Direction.EAST, new GridLoc(4, 4));
    connectRails(rails[4][5], rails[4][4], Direction.WEST);

    rails[3][4] = switchFactory
        .create(Direction.SOUTH, Direction.NORTH, Direction.WEST, new GridLoc(3, 4));
    connectRails(rails[2][4], rails[3][4], Direction.SOUTH);
    connectRails(rails[4][4], rails[3][4], Direction.NORTH);

    rails[3][3] = switchFactory
        .create(Direction.EAST, Direction.WEST, Direction.SOUTH, new GridLoc(3, 3));
    connectRails(rails[3][4], rails[3][3], Direction.WEST);

    rails[4][3] = switchFactory
        .create(Direction.SOUTH, Direction.NORTH, Direction.WEST, new GridLoc(4, 3));
    connectRails(rails[4][3], rails[3][3], Direction.NORTH);

    rails[5][1] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(5, 1));
    connectRails(rails[5][0], rails[5][1], Direction.EAST);

    rails[5][2] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(5, 2));
    connectRails(rails[5][1], rails[5][2], Direction.EAST);

    rails[5][3] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.NORTH, new GridLoc(5, 3));
    connectRails(rails[5][3], rails[4][3], Direction.NORTH);
    connectRails(rails[5][2], rails[5][3], Direction.EAST);

    rails[5][4] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(5, 4));
    connectRails(rails[5][4], rails[5][3], Direction.WEST);

    rails[5][5] = cornerFactory.create(Direction.NORTH, Direction.WEST, new GridLoc(5, 5));
    connectRails(rails[5][5], rails[5][4], Direction.WEST);
    connectRails(rails[5][5], rails[4][5], Direction.NORTH);

    rails[3][2] = new CrossRail(new GridLoc(3, 2));
    connectRails(rails[3][3], rails[3][2], Direction.WEST);
    connectRails(rails[2][2], rails[3][2], Direction.SOUTH);

    rails[4][2] = switchFactory
        .create(Direction.WEST, Direction.EAST, Direction.NORTH, new GridLoc(4, 2));
    connectRails(rails[3][2], rails[4][2], Direction.SOUTH);
    connectRails(rails[4][3], rails[4][2], Direction.WEST);

    rails[4][1] = cornerFactory.create(Direction.NORTH, Direction.EAST, new GridLoc(4, 1));
    connectRails(rails[4][2], rails[4][1], Direction.WEST);

    rails[3][1] = cornerFactory.create(Direction.SOUTH, Direction.EAST, new GridLoc(3, 1));
    connectRails(rails[3][2], rails[3][1], Direction.WEST);
    connectRails(rails[3][1], rails[4][1], Direction.SOUTH);

    rails[2][1] = new StraightRail(Direction.EAST, Direction.WEST, new GridLoc(2, 1));
    connectRails(rails[2][2], rails[2][1], Direction.WEST);

    rails[2][0] = switchFactory
        .create(Direction.NORTH, Direction.SOUTH, Direction.EAST, new GridLoc(2, 0));
    connectRails(rails[2][1], rails[2][0], Direction.WEST);
    connectRails(rails[1][0], rails[2][0], Direction.SOUTH);

    rails[1][0] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(1, 0));
    connectRails(rails[2][0], rails[1][0], Direction.NORTH);
    connectRails(rails[1][0], rails[0][0], Direction.NORTH);

    rails[3][0] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(3, 0));
    connectRails(rails[2][0], rails[3][0], Direction.SOUTH);

    rails[4][0] = new StraightRail(Direction.NORTH, Direction.SOUTH, new GridLoc(4, 0));
    connectRails(rails[3][0], rails[4][0], Direction.SOUTH);

    rails[5][0] = cornerFactory.create(Direction.NORTH, Direction.EAST, new GridLoc(5, 0));
    connectRails(rails[4][0], rails[5][0], Direction.SOUTH);
    connectRails(rails[5][1], rails[5][0], Direction.WEST);

    trackPanel.addToPanel(rails);
  }

  /**
   * Add the buttons for creating Rails.
   */
  private void buildTrack() {
    trackPanel = new TrackPanel();
    add("Center", trackPanel);

    runStopButton = new Button("Run");
    quitButton = new Button("Quit");
    accelButton = new Button("Accelerate");
    decelButton = new Button("Decelerate");
    accelLotsButton = new Button("Accelerate A Lot");
    decelLotsButton = new Button("Decelerate A Lot");

    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(0, 1));
    p2.add(runStopButton);
    p2.add(accelLotsButton);
    p2.add(decelLotsButton);
    p2.add(accelButton);
    p2.add(decelButton);
    p2.add(quitButton);
    add("East", p2);

    pack();
  }

  /**
   * Read Rail-placing commands from the user.
   *
   * @param evt event
   * @return true or false
   */
  @Override
  public boolean handleEvent(Event evt) {
    Object target = evt.target;
    if (evt.id == Event.ACTION_EVENT) {
      if (target instanceof Button) {
        if ("Run".equals(evt.arg)) {
          running = true;
          for (int i = 0; i < numTrains; i++) {
            trainList[i].start();
          }
          ((Button) target).setLabel("Suspend");
        } else if ("Suspend".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].suspend();
          }
          running = false;
          ((Button) target).setLabel("Resume");
        } else if ("Resume".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].resume();
          }
          running = false;
          ((Button) target).setLabel("Suspend");
        } else if ("Accelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerate();
          }
        } else if ("Decelerate".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerate();
          }
        } else if ("Accelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].accelerateALot();
          }
        } else if ("Decelerate A Lot".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].decelerateALot();
          }
        } else if ("Quit".equals(evt.arg)) {
          for (int i = 0; i < numTrains; i++) {
            trainList[i].stop();
          }
          running = false;
          System.exit(0);
        }
        return true;
      }
    }
    // If we get this far, I couldn't handle the event
    return false;
  }

  /**
   * Connect rails rail1 and rail2; rail2 is in direction dir from rail1.
   *
   * @param rail1 rail 1
   * @param rail2 rail 2
   * @param dir direction
   */
  private void connectRails(Rail rail1, Rail rail2, Direction dir) {
    rail1.register(rail2, dir);
    rail2.register(rail1, dir.opposite());
  }

  // ------------------------------------------------------------------
  // Paint the display.

  /**
   * Add e to the rail at location loc.
   *
   * @param loc location
   * @param car Car
   */
  void addCar(GridLoc loc, Car car) {
    rails[loc.getRow()][loc.getCol()].enter(car);
    car.setRail(rails[loc.getRow()][loc.getCol()]);
  }

  // update
  // ------------------------------------------------------------------

  /**
   * Repaint the track.
   *
   * @param graph The graph.
   */
  @Override
  public void paint(Graphics graph) {
    update(graph);
  }

  /**
   * Update the display; tell all my Tracks to update themselves.
   *
   * @param graph The graph.
   */
  @Override
  public void update(Graphics graph) {
    trackPanel.repaint();
  }

  /**
   * Add T to myself.
   *
   * @param train Train
   */
  void addTrain(Train train) {
    trainList[numTrains] = train;
    numTrains++;
  }

}
