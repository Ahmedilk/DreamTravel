package GUI;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import javax.swing.JOptionPane;
import data.DataManager;
import data.Destination;
import data.DestinationManager;
import data.Settings;
import java.util.ArrayList;

/**
 *
 * @author ahmed
 */
public class GUIManager {

    private UserInterface userInterface;
    private DataManager dataManager;
    private IFrameDestination iFrameDestination;
    private IFrameTrip iFrameTrip;
    private IFrameSettings iFrameSettings;
    private DialogAbout dialogAbout;

    private static GUIManager guiManager;

    private GUIManager() {
        dataManager = DataManager.getTheDataManager();
    }

    public static GUIManager getTheGUIManager() {
        if (guiManager == null) {
            guiManager = new GUIManager();
        }
        return guiManager;
    }

    public void startGui() {
        userInterface = new UserInterface();
        Settings settings = Settings.getTheSettings();
        userInterface.setTitle(settings.getProgramName() + " " + settings.getProgramVersion());

        userInterface.getMenuItemSettings().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemSettingsActionPerformed();
            }
        });

        userInterface.getMenuItemExitProgramm().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemExitProgramActionPerformed();
            }
        });
        userInterface.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                MenuItemExitProgramActionPerformed();
            }
        });

        userInterface.getMenuItemDestinationManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemDestinationManagerActionPerformed();
            }
        });
        userInterface.getMenuItemTripManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemTripManagerActionPerformed();
            }
        });
        userInterface.getMenuItemEmployeeManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemEmployeeManagerActionPerformed();
            }
        });
        userInterface.getMenuItemBusManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemBusManagerActionPerformed();
            }
        });
        userInterface.getMenuItemServiceManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemServiceManagerActionPerformed();
            }
        });
        userInterface.getMenuItemHotelManager().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemHotelManagerActionPerformed();
            }
        });
        userInterface.getMenuItemAbout().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MenuItemAboutActionPerformed();
            }
        });

        userInterface.setVisible(true);
    }

    private void MenuItemSettingsActionPerformed() {
        if (iFrameSettings == null) {
            iFrameSettings = new IFrameSettings(userInterface);
            userInterface.getDesktopPane().add(iFrameSettings);
            try {
                iFrameSettings.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        }
        iFrameSettings.setVisible(true);
        if(iFrameTrip != null){
            iFrameTrip.setVisible(false);
        }
        if(iFrameDestination != null){
            iFrameDestination.setVisible(false);
        }
    }

    private void MenuItemExitProgramActionPerformed() {
        int option = JOptionPane.showOptionDialog(userInterface,
                "Are you sure you want to exit?\nAll data are already saved!",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes","No"},
                "No");
        if (option == JOptionPane.YES_OPTION) {
            userInterface.dispose();
            System.exit(0);
        }
    }

    private void MenuItemDestinationManagerActionPerformed() {
        if (iFrameDestination == null) {
            iFrameDestination = new IFrameDestination(userInterface);
            userInterface.getDesktopPane().add(iFrameDestination);
            try {
                iFrameDestination.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        }
        iFrameDestination.setVisible(true);
        if(iFrameTrip != null){
            iFrameTrip.setVisible(false);
        }
        if(iFrameSettings != null){
            iFrameSettings.setVisible(false);
        }
    }

    private void MenuItemTripManagerActionPerformed() {
        if (iFrameTrip == null) {
            iFrameTrip = new IFrameTrip(userInterface);
            userInterface.getDesktopPane().add(iFrameTrip);
            try {
                iFrameTrip.setMaximum(true);
            } catch (PropertyVetoException ex) {
            }
        }
        iFrameTrip.setVisible(true);
        if(iFrameDestination != null){
            iFrameDestination.setVisible(false);
        }
        if(iFrameSettings != null){
            iFrameSettings.setVisible(false);
        }
        
    }

    private void MenuItemEmployeeManagerActionPerformed() {
        messageDialogSorry();
    }

    private void MenuItemBusManagerActionPerformed() {
        messageDialogSorry();
    }

    private void MenuItemHotelManagerActionPerformed() {
        messageDialogSorry();
    }

    private void MenuItemServiceManagerActionPerformed() {
        messageDialogSorry();
    }

    private void MenuItemAboutActionPerformed() {
        if (dialogAbout == null) {
            dialogAbout = new DialogAbout();
            dialogAbout.getcloseAbout().addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogAbout.setVisible(false);
                }
            });
        }
        dialogAbout.updateLabels();
        dialogAbout.setLocationRelativeTo(userInterface);
        dialogAbout.setVisible(true);
    }

    private void messageDialogSorry() {
        JOptionPane.showMessageDialog(userInterface,
                "Not yet implemented!",
                "",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
