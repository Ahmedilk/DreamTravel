package data;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class TripManager implements Serializable {

    private final ArrayList<Trip> tripList = new ArrayList();
    private int tripIDCounter = 1;

    private static TripManager tripManager;

    private TripManager() {
    }

    /**
     * Creates new TripManager if it doesnt already exist
     * saves tripManager
     *
     * @return
     */
    public static TripManager getTheTripManager() {
        if (tripManager == null) {
            tripManager = new TripManager();
            if (tripManager.load() == false) {
                System.out.println("No trip file exists, creating a new one...");
                tripManager.save();
            }
        }
        return tripManager;
    }

    private boolean load() {
        File tripFile = new File("saves/tripFile.conf");
        if (tripFile.canRead()) {
            try {
                FileInputStream fis = new FileInputStream(tripFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tripManager = (TripManager) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                String error = "Error while loading trip file!";
                System.out.println(error);
                JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    /**
     * Saves trips to List
     */
    public void save() {
        File tripFile = new File("saves/tripFile.conf");
        try {
            FileOutputStream fos = new FileOutputStream(tripFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tripManager);
            oos.close();
            fos.close();
            System.out.println("Successfully saved trips.");
        } catch (Exception e) {
            String error = "Error while saving trip file!";
            System.out.println(error);
            JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Adds a new trip to the list.
     *
     * @param newTrip
     */
    public void addTrip(Trip newTrip) {
        tripList.add(newTrip);
        save();
    }

    /**
     * Updates (replaces) an existing Trip in the list.
     *
     * @param newTrip
     * @throws java.lang.Exception
     */
    public void updateTrip(Trip newTrip) throws Exception {
        int pos = getIndexFromTripID(newTrip.gettripID());
        if (pos < 0) {
            throw new Exception("Trip missing: " + newTrip);
        }

        tripList.set(pos, newTrip);
        save();
    }

    /**
     * Deletes trip from the list if it exists. Resets IDCounter if possible.
     *
     * @param trip
     */
    public void deleteTrip(Trip trip) {
        int pos = tripList.indexOf(trip);
        if (pos < 0) {
            return;
        }
        tripList.remove(trip);

        if (tripList.isEmpty()) {
            tripIDCounter = 1;
        }
        save();
    }

    /**
     * 
     */
    public void deleteAllTrip() {
        tripList.clear();
        if (tripList.isEmpty()) {
            tripIDCounter = 1;
        }
        save();
    }

//      Creates five test trips
    public void testFiveTrips() {
        System.out.println("\r\nAdded 5 test trips!");
        tripList.add(new Trip(tripIDCounter++, "BW Trip", "culinary", 218.0, 7));
        tripList.add(new Trip(tripIDCounter++, "Berlin Trip", "culture", 378.0, 7));
        tripList.add(new Trip(tripIDCounter++, "Rhein Cruise", "nature", 789.0, 7));
        tripList.add(new Trip(tripIDCounter++, "Hamburg Harbor Adventure", "maritime", 93.0, 7));
        tripList.add(new Trip(tripIDCounter++, "Schwarzwald Getaway", "nature", 132.0, 14));
        save();
    }

    /**
     * Returns the index in the list of the trip with the specified tripID
     * Returns -1 if none with this ID exist.
     *
     * @param tripID
     */
    private int getIndexFromTripID(int tripID) {
        for (int location = 0; location < tripList.size(); location++) {
            if (tripList.get(location).gettripID() == tripID) {
                return location;
            }
        }
        return -1;
    }

    /**
     * 
     *
     * @param index
     * @return
     */
    public Trip getTrip(int index) {
        return tripList.get(index);
    }

    /**
     * returns tripList, because its private in this class
     *
     * @return
     */
    public ArrayList<Trip> getTripList() {
        return tripList;
    }

    /**
     * +1 for destinationID so every new trip has its own ID
     *
     * @return
     */
    public int getNextTripID() {
        return tripIDCounter++;
    }
}
