package data;

import GUI.GUIManager;
import GUI.UserInterface;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class DataManager {
    DestinationManager destinationManager;
    private TripManager tripManager;
    private GUIManager guiManager;
    private static UserInterface userInterface;
    
    private static DataManager dataManager;
    
    private DataManager(){
    }
    
    public static DataManager getTheDataManager() {
        if (dataManager == null) {
            dataManager = new DataManager();
            dataManager.destinationManager = DestinationManager.getTheDestinationManager();
            dataManager.tripManager = TripManager.getTheTripManager();
            dataManager.guiManager = GUIManager.getTheGUIManager();
            
            // Possibility to add 5 test destinations and/or trips
            // repeats EVERY TIME you start the program!
            // comment if unwanted
            int option = JOptionPane.showOptionDialog(userInterface,
                "Do you want to add testobjects?",
                "Testobjects option",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes","No"},
                "No");
            if(option == JOptionPane.YES_OPTION)
            dataManager.destinationManager.testFiveDestinations();
            dataManager.tripManager.testFiveTrips();
        }
        return dataManager;
    }
    
    /**
     * Displays the main menu, where the user can say what he would like to manage.
     */
    public void startProgram(){
        guiManager.startGui();
    }
}
