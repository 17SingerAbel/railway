package railway;
// Author: Paul Gries
// Written: 27 June 1997
// Last revised: 29 June 1997
// Refactor: Yeqi Sang, 10 July 2017

import java.awt.Color;
import java.awt.Frame;

/**
 * The simulation class that can keep track of all codes in package.
 */
public class TrainSimulation extends Frame {

  /**
   * The Tracks on which the Trains run.
   */
  private Track[] tracks = new Track[4];
  /**
   * The Trains running on the Tracks.
   */
  private Train[] trains = new Train[8];

  /**
   * A train simulation which builds track and trians.
   */
  public TrainSimulation() {
    // Track[0]
    tracks[0] = new Track();
    tracks[0].resize(540, 400);
    tracks[0].move(0, 0);
    tracks[0].setBackground(Color.white);
    tracks[0].show();

    trains[0] = new Train("Train 0");
    trains[0].addToTrain(new Car(Color.green));
    trains[0].addToTrain(new Car(Color.red));

    trains[1] = new Train("Train 1");
    trains[1].addToTrain(new Car(Color.green));
    trains[1].addToTrain(new Car(Color.red));

//    trains[2] = new Train("Train 2");
//    trains[2].addToTrain(new Car(Color.green));
//    trains[2].addToTrain(new Car(Color.red));

    trains[0].addToTrack(tracks[0], Direction.EAST, new GridLoc(2, 2));
    trains[0].setSpeed(620);
    trains[1].addToTrack(tracks[0], Direction.NORTH, new GridLoc(2, 4));
    trains[1].setSpeed(350);
//    trains[2].addToTrack(T.tracks[0], Direction.EAST, new GridLoc(2, 2));
//    trains[2].setSpeed(754);

//    trains[0].start();
//    trains[1].start();
//    trains[2].start();

    //Uncomment this to get more tracks!!!

    // railway.Track 2.
 /*     tracks[1] = new railway.Track();
        tracks[1].resize (400, 400);
        tracks[1].move(410, 0);
        tracks[1].setBackground(Color.white);
        tracks[1].show();

        trains[3] = new Train("Train 3");
        trains[3].addToTrain(new Car(Color.green));
        trains[4] = new Train("Train 4");
        trains[4].addToTrain(new Car(Color.green));

        trains[3].addToTrack(T.tracks[1], Direction.EAST, new GridLoc(0, 0));
        trains[3].setSpeed(500);
        trains[4].addToTrack(T.tracks[1], Direction.NORTH, new GridLoc(1, 2));
        trains[4].setSpeed(400);

        trains[3].start();
        trains[4].start();

        // railway.Track 3.
        tracks[2] = new Track();
        tracks[2].resize (400, 400);
        tracks[2].move(400, 400);
        tracks[2].setBackground(Color.white);
        tracks[2].show();

        trains[5] = new Train("Train 5");
        trains[5].addToTrain(new Car(Color.green));
        trains[6] = new Train("Train 6");
        trains[6].addToTrain(new Car(Color.green));

        trains[5].addToTrack(T.tracks[2], Direction.SOUTH, new GridLoc(2, 4));
        trains[5].setSpeed(500);
        trains[6].addToTrack(T.tracks[2], Direction.NORTH, new GridLoc(2, 0));
        trains[6].setSpeed(400);

        trains[5].start();
        trains[6].start();*/
  }

  /**
   * The main methods run the programme.
   * @param args Input.
   */
  public static void main(String[] args) {
    new TrainSimulation();

  }
}

