package data;

import java.io.Serializable;

/**
 *
 * @author ahmed
 */
public class Trip implements Serializable {

    private int tripID;
    private String tName;
    private String tType;
    private String tCost;
    private String tDuration;

    public Trip(int tripID, String tName, String tType, double tCost, int tDuration) {
        this.tripID = tripID;
        this.tName = tName;
        this.tType = tType;
        this.tCost = tCost + " €";
        this.tDuration = tDuration + " days";
    }

    public int gettripID() {
        return tripID;
    }

    public void settripID(int tripID) {
        this.tripID = tripID;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType;
    }

    public String gettCost() {
        return tCost;
    }

    public void settCost(double tCost) {
        this.tCost = tCost + " €";
    }

    public String gettDuration() {
        return tDuration;
    }

    public void settDuration(int tDuration) {
        this.tDuration = tDuration + " days";
    }

    @Override
    public String toString(){
        return "ID: "+tripID+" \nName: "+tName+" \nType: "+tType+" \nCost: "+tCost+" \nDuration: "+tDuration;
    }
    
}
