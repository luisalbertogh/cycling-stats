/**
 * Riders list form
 */
package net.luisalbertogh.cyclingstats.controllers;

/**
 * @author loga
 *
 */
public class RidersList {
    /** The riders list */
    private String ridersNames;
    /** Riders classification */
    private String classif;

    /**
     * Default constructor.
     */
    public RidersList(){
        /* Empty */
    }

    /**
     * Constructor with arguments.
     * @param ridersList
     */
    public RidersList(String ridersList, String classif) {
        this.ridersNames = ridersList;
        this.classif = classif;
    }
    
    /**
     * @return the classif
     */
    public String getClassif() {
        return classif;
    }

    /**
     * @param classif the classif to set
     */
    public void setClassif(String classif) {
        this.classif = classif;
    }

    /**
     * @return the ridersList
     */
    public String getRidersNames() {
        return ridersNames;
    }

    /**
     * @param ridersList the ridersList to set
     */
    public void setRidersNames(String ridersList) {
        this.ridersNames = ridersList;
    }
}
