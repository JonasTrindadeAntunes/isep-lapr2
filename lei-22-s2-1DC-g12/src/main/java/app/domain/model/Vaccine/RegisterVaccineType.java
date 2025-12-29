package app.domain.model.Vaccine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Register vaccine type.
 */
public class RegisterVaccineType implements Serializable {

    private List<VaccineType> listVaccineTypes = new ArrayList<>();

    /**
     * New vaccine type vaccine type.
     *
     * @param code          the code
     * @param designation   the designation
     * @param typeOfVaccine the type of vaccine
     * @return the vaccine type
     */
    public VaccineType newVaccineType(String code, String designation,String typeOfVaccine)
    {
        return new VaccineType(code,designation,typeOfVaccine);
    }

    /**
     * Register vaccine type boolean.
     *
     * @param vac the vac
     * @return the boolean
     */
    public boolean registerVaccineType(VaccineType vac)
    {
        if(this.validateVaccineType(vac))
            return addVaccineType(vac);
        else
            return false;

    }

    private boolean addVaccineType(VaccineType vac){return this.listVaccineTypes.add(vac);}

    /**
     * Validate vaccine type boolean.
     *
     * @param vac the vaccine
     * @return the boolean
     */
    public boolean validateVaccineType(VaccineType vac)
    {
        for(VaccineType vacs : listVaccineTypes)
        {
            if(vacs.equals(vac)){
                return false;
            }
        }
        return true;
    }

    /**
     * Gets list vaccine type.
     *
     * @return the list vaccine type
     */
    public List<VaccineType> getListVaccineType()
    {
        return listVaccineTypes;
    }


}
