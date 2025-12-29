package app.controller.Nurse;

import app.controller.App;
import app.domain.model.AdverseReaction;
import app.domain.model.Platform.Platform;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineAdministration;
import app.domain.shared.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Record adverse reaction controller.
 */
public class RecordAdverseReactionController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;



    /**
     * Instantiates a new Record adverse reaction controller.
     */
    public RecordAdverseReactionController()
    {
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Check login.
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_NURSE);
    }

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
        try{
            return this.m_oPlatform.getRegisterSNSUser().getSNSUserBySNSUserNumber(snsUserNumber);
        }catch (NullPointerException ex)
        {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    /**
     * Gets vaccine administrations.
     *
     * @return the vaccine administrations
     */
    public List<String> getVaccineAdministrations(SNSUser user)
    {
        List<String> listVaccineAdministration = new ArrayList<>();
        for (VaccineAdministration vaccineAdministration : user.getVaccineAdministrations())
        {
            String administration = "Vaccine: " + vaccineAdministration.getVaccine().getName() + "Vaccination date: " + vaccineAdministration.getDate().toString() +" Lot number: " + vaccineAdministration.getLotNumber();
            listVaccineAdministration.add(administration);
        }

        return listVaccineAdministration;
    }

    /**
     * Record adverse reaction.
     *
     * @param adverseReaction       the adverse reaction
     * @param vaccineAdministration the vaccine administration
     * @return the boolean
     */
    public boolean recordAdverseReaction(SNSUser user, String adverseReaction, int vaccineAdministration)
    {
        VaccineAdministration va = user.getVaccineAdministrations().get(vaccineAdministration);

        AdverseReaction ar = new AdverseReaction(adverseReaction,va);
        user.registerAdverseReaction(ar);
        return true;
    }

}
