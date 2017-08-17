/**
 * Rider DTO.
 */
package net.luisalbertogh.cyclingstats.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author loga
 *
 */
@XmlRootElement
public final class Rider {
    private String name;
    private String birthDate;
    private String nationality;
    private String weight;
    private String height;
    private Integer odr;
    private Integer gc;
    private Integer tt;
    private Integer sprint;
    
    /**
     * Default constructor.
     */
    public Rider(){
        /* EMPTY */
    }
    
    /**
     * Constructor with args.
     * @param name
     * @param birthDate
     * @param nationality
     * @param weight
     * @param height
     * @param odr
     * @param gc
     * @param tt
     * @param sprint
     */
    public Rider(String name, String birthDate, String nationality, String weight, String height, Integer odr, Integer gc,
            Integer tt, Integer sprint) {
        this.name = name;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.weight = weight;
        this.height = height;
        this.odr = odr;
        this.gc = gc;
        this.tt = tt;
        this.sprint = sprint;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }
    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }
    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }
    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
    /**
     * @return the height
     */
    public String getHeight() {
        return height;
    }
    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }
    /**
     * @return the odr
     */
    public Integer getOdr() {
        return odr;
    }
    /**
     * @param odr the odr to set
     */
    public void setOdr(Integer odr) {
        this.odr = odr;
    }
    /**
     * @return the gc
     */
    public Integer getGc() {
        return gc;
    }
    /**
     * @param gc the gc to set
     */
    public void setGc(Integer gc) {
        this.gc = gc;
    }
    /**
     * @return the tt
     */
    public Integer getTt() {
        return tt;
    }
    /**
     * @param tt the tt to set
     */
    public void setTt(Integer tt) {
        this.tt = tt;
    }
    /**
     * @return the sprint
     */
    public Integer getSprint() {
        return sprint;
    }
    /**
     * @param sprint the sprint to set
     */
    public void setSprint(Integer sprint) {
        this.sprint = sprint;
    }
    
    @Override
    public String toString(){
        String str = "";
        str += "- Name: " + this.name + "\n";
        str += "- Birthdate: " + this.birthDate + "\n";
        str += "- Nationality: " + this.nationality + "\n";
        str += "- Weight: " + this.weight + "\n";
        str += "- Height: " + this.height + "\n";
        str += "- ODR: " + this.odr + "\n";
        str += "- GC: " + this.gc + "\n";
        str += "- TT: " + this.tt + "\n";
        str += "- Sprint: " + this.sprint + "\n";
        
        return str; 
    }
}
