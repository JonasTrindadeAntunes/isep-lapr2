package app.controller.Admin;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.util.List;

/**
 * The type Register vaccination center controller.
 */
public class RegisterVaccinationCenterController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;
    /**
     * The Company
     */
    private Company m_oCompany;
    /**
     * The VaccinationCenter
     */
    private VaccinationCenter m_oVaccinationCenter;


    /**
     * Instantiates a new Register vaccination center controller.
     */
    public RegisterVaccinationCenterController() {
        this.m_oApp = App.getInstance();
        this.m_oCompany = m_oApp.getCompany();
        this.m_oPlatform = m_oCompany.getPlatform();

    }


    /**
     * New vaccination center boolean.
     *
     * @param name                    the name
     * @param address                 the address
     * @param phoneNumber             the phone number
     * @param emailAddress            the email address
     * @param faxNumber               the fax number
     * @param websiteAddress          the website address
     * @param openingHours            the opening hours
     * @param openingMinutes          the opening minutes
     * @param closingHours            the closing hours
     * @param closingMinutes          the closing minutes
     * @param slotDuration            the slot duration
     * @param maxNumberVaccines       the max number vaccines
     * @param typeOfVaccinationCenter the type of vaccination center
     * @return the boolean
     */
    public boolean newVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Integer openingHours,Integer openingMinutes, Integer closingHours, Integer closingMinutes,Integer slotDuration, Integer maxNumberVaccines,int typeOfVaccinationCenter)
    {
        try
        {
            this.m_oVaccinationCenter = this.m_oPlatform.getRegisterVaccinationCenter().newVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,openingMinutes,closingHours,closingMinutes,slotDuration,maxNumberVaccines,typeOfVaccinationCenter);
            return this.m_oPlatform.getRegisterVaccinationCenter().validateVaccinationCenter(m_oVaccinationCenter);

        }
        catch(RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            this.m_oVaccinationCenter = null;
            return false;
        }
    }

    /**
     * Register vaccination center boolean.
     *
     * @return the boolean
     */
    public boolean registerVaccinationCenter(){return this.m_oPlatform.getRegisterVaccinationCenter().registerVaccinationCenter(m_oVaccinationCenter);}

    /**
     * Get types of vaccination center list.
     *
     * @return the list
     */
    public List<String> getTypesOfVaccinationCenter(){return this.m_oPlatform.getRegisterVaccinationCenter().getTypesOfVaccinationCenter();}

    /**
     * Get vaccination center string .
     *
     * @return the string
     */
    public String getVaccinationCenterString(){return this.m_oVaccinationCenter.toString();}
}
