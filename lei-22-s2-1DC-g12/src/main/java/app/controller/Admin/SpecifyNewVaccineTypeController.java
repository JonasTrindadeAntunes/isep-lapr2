package app.controller.Admin;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Platform.Platform;
import app.domain.model.Vaccine.VaccineType;

import java.util.Arrays;
import java.util.List;

/**
 * The type Specify new vaccine type controller.
 */
public class SpecifyNewVaccineTypeController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Company.
     */
    private Company m_oCompany;
    /**
     * The Platform
     */
    private Platform m_oPlatform;
    /**
     * The VaccineType
     */
    private VaccineType m_oVaccineType;


    /**
     * Instantiates a new Specify new vaccine type controller.
     */
    public SpecifyNewVaccineTypeController()
    {
        this.m_oApp = App.getInstance();
        this.m_oCompany = m_oApp.getCompany();
        this.m_oPlatform = m_oCompany.getPlatform();

    }

    /**
     * New vaccine type boolean.
     *
     * @param code          the code
     * @param designation   the designation
     * @param typeOfVaccine the type of vaccine
     * @return the boolean
     */
    public boolean newVaccineType(String code,String designation,String typeOfVaccine)
    {
        try
        {
            this.m_oVaccineType = this.m_oPlatform.getRegisterVaccineType().newVaccineType(code,designation,typeOfVaccine);
            return this.m_oPlatform.getRegisterVaccineType().validateVaccineType(m_oVaccineType);

        }
        catch(RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            this.m_oVaccineType = null;
            return false;
        }

    }

    /**
     * Register vaccine type boolean.
     *
     * @return the boolean
     */
    public boolean registerVaccineType(){return this.m_oPlatform.getRegisterVaccineType().registerVaccineType(m_oVaccineType);}

    /**
     * Get vaccine type string .
     *
     * @return the string
     */
    public String getVaccineTypeString(){return m_oVaccineType.toString(); }


    /**
     * Get list vaccine type .
     *
     * @return the list
     */
    public List<VaccineType> getListVaccineType(){return this.m_oPlatform.getRegisterVaccineType().getListVaccineType();}


    /**
     * Gets types of vaccine.
     *
     * @return the types of vaccine
     */
    public List<VaccineType.TypesOfVaccine> getTypesOfVaccine()
    {
        VaccineType.TypesOfVaccine[] array = VaccineType.TypesOfVaccine.values();
        return Arrays.asList(array);
    }

}
