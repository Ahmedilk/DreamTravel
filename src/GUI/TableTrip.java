package GUI;

import data.Trip;
import data.TripManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class TableTrip extends javax.swing.table.AbstractTableModel {

    private final java.util.ArrayList<Trip> tripList;
    private final String[] columns = {"TripID", "Name", "Type", "Cost", "Duration"};
    

    /**
     * Constructs a new TableTrip object.
     * Initializes the tripList with data from TripManager. 
     *
     */
    public TableTrip() {
        tripList = TripManager.getTheTripManager().getTripList();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * 
     *
     * @return the number of rows in the table.
     */
    @Override
    public int getRowCount() {
        if (tripList == null) {
            return 0;
        }
        return tripList.size();
    }
    
    /**
     *  Returns the value at the specified row and column index.
     *
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     * @return the value at the specified row and column index
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trip trip = tripList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return trip.gettripID();
            case 1:
                return trip.gettName();
            case 2:
                return trip.gettType();
            case 3:
                return trip.gettCost();
            case 4:
                return trip.gettDuration();
            default:
                return "Error";
        }
    }

    /**
     * Sets the value at the specified row and column index.
     * Handles input validation for name, type, cost, and duration columns.
     *
     * @param value       the new value to be set
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Trip trip = tripList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String tName = value.toString().trim();
                if (!isValidTName(tName)) {
                    JOptionPane.showMessageDialog(null, "Only letters are allowed, and two consecutive spaces are not allowed.\nAnd/Or incorrect characters", "Invalid Name!", JOptionPane.ERROR_MESSAGE);
                } else {
                    trip.settName(value.toString().trim());
                }
                break;
            case 2:
                String tType = value.toString().trim();
                if (!isValidTType(tType)) {
                    JOptionPane.showMessageDialog(null, "Only letters are allowed, and two consecutive spaces are not allowed.\nAnd/Or incorrect characters", "Invalid Type!", JOptionPane.ERROR_MESSAGE);
                } else {
                    trip.settType(value.toString().trim());
                }
                break;
            case 3:
                try {
                double tCost = Double.parseDouble(value.toString());
                if (tCost > 0 && tCost < 10000) // cost must be positive!
                {
                    trip.settCost(tCost);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number under 10000!", "Invalid Cost!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The cost field should exclusively consist of numerical values!\nPlease correct", "Invalid Cost!", JOptionPane.ERROR_MESSAGE);
            }
            break;
            case 4:
                try {
                int tDuration = Integer.parseInt(value.toString());
                if (tDuration == 7 || tDuration == 14) {
                    trip.settDuration(tDuration);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter 7 or 14!", "Invalid Duration!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The duration field should exclusively consist of numerical values!\nPlease correct", "Invalid Duration!", JOptionPane.ERROR_MESSAGE);
            }
            break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    private boolean isValidTName(String tName) {
        return tName.matches("[A-Za-z]+(\\s[A-Za-z]+)*") && tName.length() >= 2 && tName.length() <= 20;
    }

    private boolean isValidTType(String tType) {
        return tType.matches("[A-Za-z]+(\\s[A-Za-z]+)*") && tType.length() >= 2 && tType.length() <= 20;
    }

    /**
     * Returns whether the specified cell is editable.
     * All cells except the first column (TripID) are editable.
     *
     * @param rowIndex    the index of the row
     * @param columnIndex the index of the column
     * @return true if the cell is editable, false otherwise
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    /**
     * Returns the name of the specified column.
     *
     * @param columnIndex the index of the column
     * @return the column name
     */
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /**
     * Returns whether the specified cell is editable.All cells except the first column (TripID) are editable.
     *
     * @param columnIndex the index of the column
     * @return true if the cell is editable, false otherwise
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    /**
     * Notifies the table taht the data has been updated and needs to be refreshed
     */
    public void updateData() {
        fireTableDataChanged();
    }

}
