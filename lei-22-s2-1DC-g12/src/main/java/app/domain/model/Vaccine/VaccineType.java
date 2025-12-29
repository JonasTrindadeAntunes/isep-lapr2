package app.domain.model.Vaccine;

import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Vaccine type.
 */
public class VaccineType implements Serializable {

    /**
     * Code of the vaccine type
     */
    private String code;
    /**
     * Designation of the vaccine type
     */
    private String designation;
    /**
     * Type of vaccine
     */
    private String typeOfVaccine;
    /**
     * List of the vaccines per vaccine type
     */
    private List<Vaccine> vaccineList;


    /**
     * The enum Types of vaccine.
     */
    public enum TypesOfVaccine{

        /**
         * The Lav.
         */
        Lav ("Live-attenuated vaccines"),
        /**
         * The Iv.
         */
        Iv ( "Inactivated vaccines"),
        /**
         * The Sv.
         */
        Sv ("Subunit vaccines "),
        /**
         * The Tv.
         */
        Tv ("Toxoid vaccines"),
        /**
         * The Vvv.
         */
        Vvv ("Viral vector vaccines"),
        /**
         * The Mrna.
         */
        Mrna ("Messenger RNA (mRNA) vaccines");

        /**
         * label for the type of vaccine
         */
        private final String label;


        private TypesOfVaccine(String label){this.label = label;}


        /**
         * Returns the string of the type of vaccine
         */
        public String toString(){return this.label;}


    }


    /**
     * Instantiates a new Vaccine type.
     *
     * @param code        the code
     * @param designation the designation
     */
    public VaccineType(String code, String designation,  String typeOfVaccine) {
        if( (code.length() < 4) || (code.length() > 8) || (designation.isEmpty()) || (designation.length() > 40)
            || (typeOfVaccine.isEmpty()) || (typeOfVaccine == null))
            throw new IllegalArgumentException(MessageBundle.getString("vaccinetypeexception"));
        this.code = code;
        this.designation = designation;
        this.typeOfVaccine = typeOfVaccine;
        this.vaccineList = new ArrayList<>();
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets designation.
     *
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }


    /**
     * Returns the hashcode value of an object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.code);
        return hash;
    }

    /**
     * Compares the current object with the other object passed as parameter
     */
    @Override

    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        VaccineType vt = (VaccineType) o;
        if(this.code.compareToIgnoreCase(vt.code) == 0)
        {
            System.out.println(MessageBundle.getString("codeexists"));
            return true;
        }

        return false;
    }

    /**
     * Add vaccine boolean.
     *
     * @param vaccine the vaccine
     * @return the boolean
     */
    public boolean addVaccine(Vaccine vaccine){
        return this.vaccineList.add(vaccine);
    }

    /**
     * Validate vaccine boolean.
     *
     * @param vaccine the vaccine
     * @return the boolean
     */
    public boolean validateVaccine(Vaccine vaccine){
        return this.getVaccine(vaccine.getId()) == null;
    }

    /**
     * New vaccine vaccine.
     *
     * @param id   the id
     * @param name the name
     * @return the vaccine
     */
    public static Vaccine newVaccine(String id, String name) {
        return new Vaccine(id,name);
    }

    /**
     * Get vaccine vaccine.
     *
     * @param id the id
     * @return the vaccine
     */
    public Vaccine getVaccine(String id){
        for (Vaccine v : this.vaccineList){
            if(v.getId().contains(id)){
                return v;
            }

        }

        return null;
    }

    public List<Vaccine> getVaccineList()
    {
        return this.vaccineList;
    }


    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return MessageBundle.getString("vaccinetype") + ":\n" + MessageBundle.getString("code") + ": " + code + "\n" + MessageBundle.getString("designation") + ": " + designation + "\n" + MessageBundle.getString("typeofvaccine") + ": " + typeOfVaccine + "\n";
    }
}
