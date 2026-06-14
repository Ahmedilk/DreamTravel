package data;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import GUI.IFrameDestination;
import GUI.UserInterface;

/**
 *
 * @author ahmed
 */
public class DestinationManager implements Serializable {

    private final ArrayList<Destination> destinationList = new ArrayList();
    private int destinationIDCounter = 1;

    private static DestinationManager destinationManager;

    public DestinationManager() {
    }

    public static DestinationManager getTheDestinationManager() {
        if (destinationManager == null) {
            destinationManager = new DestinationManager();
            if (destinationManager.load() == false) {
                System.out.println("No destination file exists, creating a new one...");
                destinationManager.save();
            }
        }
        return destinationManager;
    }

    private boolean load() {
        File destinationFile = new File("saves/destinationFile.conf");
        if (destinationFile.canRead()) {
            try {
                FileInputStream fis = new FileInputStream(destinationFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                destinationManager = (DestinationManager) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                String error = "Error while loading destination file!";
                System.out.println(error);
                JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    public void save() {
        File destinationFile = new File("saves/destinationFile.conf");
        try {
            FileOutputStream fos = new FileOutputStream(destinationFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(destinationManager);
            oos.close();
            fos.close();
            System.out.println("Successfully saved destinations.");
        } catch (Exception e) {
            String error = "Error while saving destination file!";
            System.out.println(error);
            JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Adds a new destination to the list.
     *
     * @param newDestination
     */
    public void addDestination(Destination newDestination) {
        destinationList.add(newDestination);
        save();
    }

    /**
     * Updates (replaces) an existing Destination in the list.
     *
     * @param newDestination
     * @throws java.lang.Exception
     */
    public void updateDestination(Destination newDestination) throws Exception {
        int pos = getIndexFromDestinationID(newDestination.getdestinationID());
        if (pos < 0) {
            throw new Exception("Destination missing: " + newDestination);
        }

        destinationList.set(pos, newDestination);
        save();
    }

    /**
     * Deletes destination from the list if it exists. Resets IDCounter if
     * possible.
     *
     * @param destination
     */
    public void deleteDestination(Destination destination) {
        int pos = destinationList.indexOf(destination);
        if (pos < 0) {
            return;
        }
        destinationList.remove(destination);

        if (destinationList.isEmpty()) {
            destinationIDCounter = 1;
        }
        save();
    }

    public void deleteAllDestination() {
            destinationList.clear();
            if (destinationList.isEmpty()) {
                destinationIDCounter = 1;
            }
            save();
    }

//      Creates five test destinations
    public void testFiveDestinations() {
        System.out.println("\r\nAdded 5 test destinations!");
        destinationList.add(new Destination(destinationIDCounter++, "Mummelsee", "nature", 18.0));
        destinationList.add(new Destination(destinationIDCounter++, "Schwarzwald", "nature", 38.0));
        destinationList.add(new Destination(destinationIDCounter++, "Brenners Parkhotel", "culture", 89.0));
        destinationList.add(new Destination(destinationIDCounter++, "Berlin", "urban", 188.0));
        destinationList.add(new Destination(destinationIDCounter++, "Rheinfall", "nature", 62.0));
        save();
    }

    /**
     * Returns the index in the list of the destination with the specified
     * destinationID, -1 if none with this ID exist.
     *
     * @param destinationID
     */
    
    private int getIndexFromDestinationID(int destinationID) {
        for (int location = 0; location < destinationList.size(); location++) {
            if (destinationList.get(location).getdestinationID() == destinationID) {
                return location;
            }
        }
        return -1;
    }

    public Destination getDestination(int index) {
        return destinationList.get(index);
    }

    public ArrayList<Destination> getDestinationList() {
        return destinationList;
    }

    public int getNextDestinationID() {
        return destinationIDCounter++;
    }
}
