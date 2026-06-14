package data;

import java.io.Serializable;

/**
 *
 * @author ahmed
 */
public class Destination implements Serializable {

    private int destinationID;
    private String dName;
    private String dType;
    private String dCost;

    public Destination(int destinationID, String dName, String dType, double dCost) {
        this.destinationID = destinationID;
        this.dName = dName;
        this.dType = dType;
        this.dCost = dCost + " €";
    }

    public int getdestinationID() {
        return destinationID;
    }

    public String getdName() {
        return dName;
    }

    public String getdType() {
        return dType;
    }

    public String getdCost() {
        return dCost;
    }

    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public void setdType(String dType) {
        this.dType = dType;
    }

    public void setdCost(double dCost) {
        this.dCost = dCost + " €";
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "ID: "+destinationID+" \nName: "+dName+" \nType: "+dType+" \nCost: "+dCost;
    }
    
}
