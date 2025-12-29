package app.controller.Receptionist;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.Constants;
import java.util.Date;
import java.util.List;

/**
 * The type Register sns user arrival controller.
 */
public class RegisterSNSUserArrivalController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;


    /**
     * Instantiates a new Register sns user arrival controller.
     */
    public RegisterSNSUserArrivalController() {
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();
    }


    /**
     * Checks login .
     *
     * @return the boolean
     */
    public boolean checkLogin() {return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);}

    /**
     * Gets working vaccination center.
     *
     * @return the working vaccination center
     */
    public VaccinationCenter getWorkingVaccinationCenter () {
        try {
            return m_oPlatform.getRegisterEmployee().getWorkingVaccinationCenter(m_oApp.getCurrentUserSession().getUserId().getEmail());
        } catch (NullPointerException ex) {
            System.out.println("null vaccination center");
            return null;
        }

    }

    /**
     * Get list vaccination center list.
     *
     * @return the list
     */
    public List<VaccinationCenter> getListVaccinationCenter(){
        return this.m_oPlatform.getRegisterVaccinationCenter().getListVaccinationCenters();
    }


    /**
     * Check if exists sns user .
     *
     * @param snsUserNumber the sns user number
     * @return the boolean
     */
    public boolean checkIfExistsSNSUser(String snsUserNumber)
    {
        if (this.m_oPlatform.getRegisterSNSUser().getSNSUserBySNSUserNumber(snsUserNumber) == null)
            return false;
        else
            return true;
    }

    /**
     * Gets sns user.
     *
     * @param snsUserNumber the sns user number
     * @return the sns user
     */
    public SNSUser getSNSUser(String snsUserNumber)
    {
       return this.m_oPlatform.getRegisterSNSUser().getSNSUserBySNSUserNumber(snsUserNumber);
    }


    /**
     * Validate user arrival boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @param snsUserNumber     the sns user number
     * @return the boolean
     */
    public VaccineSchedule validateUserArrival(VaccinationCenter vaccinationCenter,String snsUserNumber){
        return vaccinationCenter.validateUserArrival(snsUserNumber);
    }


    /**
     * Register user arrival boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @param vaccineSchedule  the vaccineSchedule
     * @param dateOfArrival     the date of arrival
     * @return the boolean
     */
    public boolean registerUserArrival(VaccinationCenter vaccinationCenter, VaccineSchedule vaccineSchedule, Date dateOfArrival) {
        return vaccinationCenter.registerUserArrival(vaccineSchedule,dateOfArrival);
    }





}
