package app.domain.model.Vaccine;

import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Vaccine.
 */
public class Vaccine implements Serializable  {

    private List<AdministrationProcess> listAdministrationProcess;
    private String id;
    private String name;


    /**
     * Instantiates a new Vaccine.
     *
     * @param id   the id
     * @param name the name
     */
    public Vaccine(String id, String name) {
        if((id == null) || (id.isEmpty()) || (name == null) || (name.isEmpty()))
            throw new IllegalArgumentException(MessageBundle.getString("noargumentcanbenull"));

        this.id = id;
        this.name = name;
        this.listAdministrationProcess = new ArrayList<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() { return id; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the hashcode value of an object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }
    /**
     *  Compares the current object with the other object passed as paremeter
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

        Vaccine vac = (Vaccine) o;
        return this.id.equalsIgnoreCase(vac.id);
    }


    /**
     * Sets list administration process.
     *
     * @param listAdministrationProcess the list administration process
     */
    public void setListAdministrationProcess(List<AdministrationProcess> listAdministrationProcess) {
        this.listAdministrationProcess = listAdministrationProcess;
    }


    /**
     *
     * returns the list of administrationProcess
     */
    public List<AdministrationProcess> getListAdministrationProcess(){return listAdministrationProcess;}


    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return MessageBundle.getString("vaccine") + ":\n" + MessageBundle.getString("id") + ": " + id + "\n" + MessageBundle.getString("name") + ": " + name + "\n" + MessageBundle.getString("administrationprocesses") + ": \n" + listAdministrationProcess;
    }

    /**
     * New administration process.
     *
     * @param ageGroups             the age groups
     * @param referenceDose         the reference dose
     * @param dosesToBeAdministered the doses to be administered
     * @param timeInterval          the time interval
     * @return the administration process
     */
    public AdministrationProcess newAdministrationProcess(List<Integer[]> ageGroups, Double referenceDose, int dosesToBeAdministered, List<Integer[]> timeInterval) {
        return new AdministrationProcess(ageGroups, referenceDose, dosesToBeAdministered, timeInterval);
    }

    /**
     * Validate administration process boolean.
     *
     * @param administrationProcess the administration process
     * @return the boolean
     */
    public boolean validateAdministrationProcess(AdministrationProcess administrationProcess) {
        for(AdministrationProcess adminProc : listAdministrationProcess)
        {
            if(adminProc == administrationProcess){
                return false;
            }
        }
        return true;
    }

    /**
     * Add administration process boolean.
     *
     * @param administrationProcess the administration process
     * @return the boolean
     */
    public boolean addAdministrationProcess(AdministrationProcess administrationProcess) {
        return listAdministrationProcess.add(administrationProcess);
    }
}
