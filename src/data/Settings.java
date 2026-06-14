package data;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class Settings implements Serializable {

    private String programName = "Dreamland Travel";
    private String programVersion = "1.2";
    private String licenseeName = "Boozz Rizan and Ahmed Ali Enterprise";

    private static Settings settings;

    private Settings() {
        // private so no one can create another Settings instance from outside of this class
    }

    public static Settings getTheSettings() {
        if (settings == null) {
            settings = new Settings();
            if (settings.load() == false) {
                System.out.println("No config file exists, creating a new one...");
                settings.save();
            }
        }
        return settings;
    }

    private boolean load() {
        File configFile = new File("saves/config.conf");
        if (configFile.canRead()) { //test if it can read the file
            try {
                FileInputStream fis = new FileInputStream(configFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                settings = (Settings) ois.readObject();
                ois.close();
                fis.close();
            } catch (Exception e) {
                String error = "Error while loading config file!";
                System.out.println(error);
                JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    public void save() {
        File configFile = new File("saves/config.conf");
        try {
            FileOutputStream fos = new FileOutputStream(configFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.close();
            fos.close();
            System.out.println("Successfully saved settings.");
        } catch (Exception e) {
            String error = "Error while saving config file!";
            System.out.println(error);
            JOptionPane.showMessageDialog(null, error, "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramVersion() {
        return programVersion;
    }

    public void setProgramVersion(String programVersion) {
        this.programVersion = programVersion;
    }

    public String getLicenseeName() {
        return licenseeName;
    }

    public void setLicenseeName(String licenseeName) {
        this.licenseeName = licenseeName;
    }
}
