package app.controller.Admin;

import app.controller.App;
import app.domain.model.Vaccine.AdministrationProcess;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Specify new vaccine controller.
 */
public class SpecifyNewVaccineController {

    /**
     * The App.
     */
    private App app;
    /**
     * The Vaccine.
     */
    private Vaccine vaccine;
    /**
     * The List of AdministrationProcess.
     */
    private List<AdministrationProcess> list;
    /**
     * The VaccineType.
     */
    private VaccineType vaccineType;


    /**
     * Instantiates a new Specify new vaccine controller.
     */
    public SpecifyNewVaccineController() {
        this.app = App.getInstance();
        list = new ArrayList<>();
    }

    /**
     * New vaccine boolean.
     *
     * @param id   the id
     * @param name the name
     * @return the boolean
     */
    public boolean newVaccine(String id, String name) {
        try {
            this.vaccine = VaccineType.newVaccine(id, name);
            return this.vaccineType.validateVaccine(vaccine);
        }
        catch(RuntimeException ex) {
            System.out.println(ex.getMessage());
            this.vaccine = null;
            return false;
        }

    }

    /**
     * New administration process boolean.
     *
     * @param ageGroups             the age groups
     * @param dosesToBeAdministered the doses to be administered
     * @param dosage                the dosage
     * @param listTimeInterval      the list time interval
     * @return the boolean
     */
    public boolean newAdministrationProcess(List<Integer[]> ageGroups, int dosesToBeAdministered, double dosage, List<Integer[]> listTimeInterval){
        AdministrationProcess administrationProcess = this.vaccine.newAdministrationProcess(ageGroups,dosage,dosesToBeAdministered,listTimeInterval);
        if(vaccine.validateAdministrationProcess(administrationProcess))
            return vaccine.addAdministrationProcess(administrationProcess);

        return false;
    }

    /**
     * Get vaccine info string.
     *
     * @return the string
     */
    public String getVaccineInfo(){
        return this.vaccine.toString();
    }

    /**
     * Add vaccine boolean.
     *
     * @return the boolean
     */
    public boolean addVaccine(){
        this.vaccineType.validateVaccine(this.vaccine);
        return this.vaccineType.addVaccine(vaccine);
    }

    /**
     * Get vaccine type list.
     *
     * @return the list
     */
    public List<VaccineType> getVaccineType(){
        return this.app.getCompany().getPlatform().getRegisterVaccineType().getListVaccineType();
    }

    /**
     * Sets vaccine type.
     *
     * @param type the type
     * @return the vaccine type
     */
    public boolean setVaccineType(Object type) {
        if(type.getClass() == VaccineType.class){
            this.vaccineType = (VaccineType)  type;
            return true;
        }else{
            return false;
        }
    }
}
