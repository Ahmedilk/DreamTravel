package GUI;

import data.Destination;
import data.DestinationManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmed
 */
public class TableDestination extends javax.swing.table.AbstractTableModel {

    public java.util.ArrayList<Destination> destinationList;
    private final String[] columns = {"DestinationID", "Name", "Type", "Cost"};

    
    /**
     * Constructs a new TableDestination object.
     * Initializes the destinationList with data from DestinationManager. 
     *
     */
    public TableDestination() {
        destinationList = DestinationManager.getTheDestinationManager().getDestinationList();
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
        if (destinationList == null) {
            return 0;
        }
        return destinationList.size();
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
        Destination destination = destinationList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return destination.getdestinationID();
            case 1:
                return destination.getdName();
            case 2:
                return destination.getdType();
            case 3:
                return destination.getdCost();
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
        Destination destination = destinationList.get(rowIndex);
        switch (columnIndex) {
            case 1:
                String dName = value.toString().trim();
                if (!isValidDName(dName)) {
                    JOptionPane.showMessageDialog(null, "Only letters are allowed, and two consecutive spaces are not allowed.\nAnd/Or incorrect characters", "Invalid Name!", JOptionPane.ERROR_MESSAGE);
                } else {
                    destination.setdName(value.toString().trim());
                }
                break;
            case 2:
                String dType = value.toString().trim();
                if (!isValidDType(dType)) {
                    JOptionPane.showMessageDialog(null, "Only letters are allowed, and two consecutive spaces are not allowed.\nAnd/Or incorrect characters", "Invalid Type!", JOptionPane.ERROR_MESSAGE);
                } else {
                    destination.setdType(value.toString().trim());
                }
                break;
            case 3:
                try {
                double dCost = Double.parseDouble(value.toString());
                if (dCost > 0 && dCost < 10000) // cost must be positive!
                {
                    destination.setdCost(dCost);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number under 10000!", "Invalid Cost!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The cost field should exclusively consist of numerical values!\nPlease correct", "Invalid Cost!", JOptionPane.ERROR_MESSAGE);
            }
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    private boolean isValidDName(String dName) {
        return dName.matches("[A-Za-z]+(\\s[A-Za-z]+)*") && dName.length() >= 2 && dName.length() <= 20;
    }

    private boolean isValidDType(String dType) {
        return dType.matches("[A-Za-z]+(\\s[A-Za-z]+)*") && dType.length() >= 2 && dType.length() <= 20;
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
