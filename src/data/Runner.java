package data;

/**
 *
 * @author ahmed
 */
public class Runner {

    private DataManager dataManager;
    
    public Runner() {
        dataManager = DataManager.getTheDataManager();
    }
    
    /**
     * @param args the command line arguments
     */
//    starting the prgram
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.dataManager.startProgram();
    }
    
}
