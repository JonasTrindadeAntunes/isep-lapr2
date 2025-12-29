package app.domain.model.Vaccine;

import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.List;

/**
 * The type Administration process.
 */
public class AdministrationProcess implements Serializable  {
    private List<Integer[]> ageGroups;
    private Double referenceDose;
    private Integer dosesToBeAdministered;
    private List<Integer[]> timeInterval;

    /**
     * Instantiates a new Administration process.
     *
     * @param ageGroups             the age groups
     * @param referenceDose         the reference dose
     * @param dosesToBeAdministered the doses to be administered
     * @param timeInterval          the time interval
     */
    public AdministrationProcess(List<Integer[]> ageGroups,Double referenceDose,int dosesToBeAdministered,List<Integer[]> timeInterval){
        this.ageGroups = ageGroups;
        this.referenceDose = referenceDose;
        this.dosesToBeAdministered = dosesToBeAdministered;
        this.timeInterval = timeInterval;
    }

    /**
     * Instantiates a new Administration process.
     *
     * @param ageGroups             the age groups
     * @param referenceDose         the reference dose
     * @param dosesToBeAdministered the doses to be administered
     */
    public AdministrationProcess(List<Integer[]> ageGroups,Double referenceDose,int dosesToBeAdministered){
        if(dosesToBeAdministered >1){
            throw new IllegalArgumentException("Time interval must be defined!");
        }
        this.ageGroups = ageGroups;
        this.referenceDose = referenceDose;
        this.dosesToBeAdministered = dosesToBeAdministered;
    }

    /**
    * Returns the String of this current object
     */
    @Override
    public String toString() {
        String str1 = "";
        str1 += MessageBundle.getString("agegroups") + ": ";

        for(Integer[] age : ageGroups){
            str1 += (age[0] + "-" + age[1] + ", \n");
        }
        str1 += (MessageBundle.getString("referencedose") + ": " + referenceDose + ",\n" + MessageBundle.getString("dosestobeadministered") + ": " + dosesToBeAdministered + ",\n" + MessageBundle.getString("timeinterval") + ":");
        if(timeInterval != null)
            for(Integer[] time : timeInterval){
                str1 += ("Dose: " + time[0] + ", " + MessageBundle.getString("timeinterval") + ": " + time[1] + " " + MessageBundle.getString("days") + "\n");
            }
        return str1;
    }

    public Double getReferenceDose() {
        return referenceDose;
    }

    public Integer getDosesToBeAdministered() {
        return dosesToBeAdministered;
    }
}
