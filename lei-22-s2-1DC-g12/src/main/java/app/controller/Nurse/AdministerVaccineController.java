package app.controller.Nurse;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.AdministrationProcess;
import app.domain.model.Vaccine.Vaccine;
import app.domain.shared.Constants;

import java.util.Calendar;
import java.util.List;

/**
 * The type Administer vaccine controller.
 */
public class AdministerVaccineController {


    /**
     * The App.
     */
    App app;
    /**
     * The Vaccination center.
     */
    VaccinationCenter vaccinationCenter;

    /**
     * Instantiates a new Administer vaccine controller.
     */
    public AdministerVaccineController(){
        app = App.getInstance();
    }

    /**
     * Validate user session boolean.
     *
     * @return the boolean
     */
    public boolean validateUserSession(){
        return app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_NURSE);
    }

    /**
     * Get list of waiting room users .
     *
     * @return the list
     */
    public List<SNSUser> getListOfWaitingRoomUsers(){
        String email = app.getCurrentUserSession().getUserId().getEmail();
        this.vaccinationCenter = this.app.getCompany().getPlatform().getRegisterEmployee().getWorkingVaccinationCenter(email);
        return this.vaccinationCenter.getWaitingRoomSNSUsers();
    }

    /**
     * Get available vaccines list.
     *
     * @param snsUser the sns user
     * @return the list
     */
    public List<Vaccine> getAvaliableVaccines(SNSUser snsUser){
        VaccineSchedule vs = vaccinationCenter.getVaccineScheduleFromUser(snsUser);
        return vaccinationCenter.getAvaliableVaccines(vs.getVaccineType());
    }

    /**
     * Get vaccine administration process .
     *
     * @param vaccine the vaccine
     * @return the administration process
     */
    public AdministrationProcess getVaccineAdministrationProcess(Vaccine vaccine){
        return vaccine.getListAdministrationProcess().get(0);
    }

    /**
     * Get adverse reactions list.
     *
     * @param snsUser the sns user
     * @return the list
     */
    public List<AdverseReaction> getAdverReactions(SNSUser snsUser){
        return snsUser.getAdverseReactions();
    }

    /**
     * Get dosages .
     *
     * @param vaccine the vaccine
     * @return the double
     */
    public Double getDosages(Vaccine vaccine){
        return vaccine.getListAdministrationProcess().get(0).getReferenceDose();
    }

    /**
     * Create vaccine administration .
     *
     * @param snsUser   the sns user
     * @param vaccine   the vaccine
     * @param lotNumber the lot number
     * @return the vaccine administration
     */
    public VaccineAdministration createVaccineAdministration(SNSUser snsUser, Vaccine vaccine, String lotNumber){
        VaccineSchedule vs = vaccinationCenter.getVaccineScheduleFromUser(snsUser);
        return  this.vaccinationCenter.createVaccineAdministration(snsUser,vs.getVaccineType(),vaccine,lotNumber);
    }

    /**
     * Register vaccine administration .
     *
     * @param vaccineAdministration the vaccine administration
     * @return the boolean
     */
    public boolean registerVaccineAdministration(VaccineAdministration vaccineAdministration){
        SNSUser user = vaccineAdministration.getSnsUser();
        user.registerVaccineAdministration(vaccineAdministration);
        SMSNotifier sms = App.getInstance().getCompany().getPlatform().getSmsNotifier();
        Calendar cal = Calendar.getInstance();
        cal.setTime(vaccineAdministration.getDate());
        cal.add(Calendar.MINUTE,this.app.getCompany().getPlatform().getRegisterVaccinationCenter().getRecoveryTime());
        boolean success = sms.addRecoveryRoom(vaccineAdministration.getSnsUser(),cal.getTime());
        return this.vaccinationCenter.registerVaccineAdministration(vaccineAdministration) == success;
    }


}
