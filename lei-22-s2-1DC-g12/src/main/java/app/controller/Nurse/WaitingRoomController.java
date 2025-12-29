package app.controller.Nurse;

import app.controller.App;
import app.domain.model.Platform.Platform;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.Constants;

import java.util.List;

/**
 * The type Waiting room controller.
 */
public class WaitingRoomController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;

    /**
     * Instantiates a new Waiting room controller.
     */
    public WaitingRoomController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Check login boolean.
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
     * Get list users in waiting room .
     *
     * @param vaccinationCenter the vaccination center
     * @return the list
     */
    public List<SNSUser> getListUsersInWaitingRoom(VaccinationCenter vaccinationCenter){
        return vaccinationCenter.getWaitingRoomSNSUsers();
    }





}
