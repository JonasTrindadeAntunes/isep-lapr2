package app.controller.Receptionist;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.Slot;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineType;

import java.util.Date;
import java.util.List;

/**
 * The type Register vaccine schedule controller.
 */
public class RegisterVaccineScheduleController {

    /**
     * The App.
     */
    private App m_oApp;

    /**
     * The Vaccine Schedule
     */
    private VaccinationCenter m_oVaccinationCenter;

    /**
     * The Platform
     */
    private Platform m_oPlatform;

    /**
     * Instantiates a new Register vaccine schedule controller.
     */
    public RegisterVaccineScheduleController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();
    }

    /**
     * Checks login .
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return m_oApp.getCurrentUserSession().isLoggedInWithRole("Receptionist");
    }


    /**
     * Gets working vaccination center.
     *
     * @return the working vaccination center
     */
    public boolean getWorkingVaccinationCenter () {
        try {
            this.m_oVaccinationCenter = m_oPlatform.getRegisterEmployee().getWorkingVaccinationCenter(m_oApp.getCurrentUserSession().getUserId().getEmail());
            if(m_oVaccinationCenter == null)
                return false;
            else
                return true;
        } catch (NullPointerException ex) {
            System.out.println("null vaccination center");
            return false;
        }

    }

    /**
     * Check if exists sns user boolean.
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
     * Gets all available slots in vaccination center.
     *
     * @param dateDay the date day
     * @return the all available slots in vaccination center
     */
    public List<Slot> getAllAvailableSlotsInVaccinationCenter(Object dateDay)
    {
        Date date = (Date) dateDay;
        return this.m_oVaccinationCenter.getAllAvailableSlotsInVaccinationCenter(date);
    }


    /**
     * Create schedule vaccine schedule.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param dateDay     the date day
     * @param slotChoice  the slot choice
     * @return the vaccine schedule
     */
    public VaccineSchedule createSchedule(SNSUser snsUser, Object vaccineType, Object dateDay, Object slotChoice)
    {
        return this.m_oVaccinationCenter.createVaccineSchedule(snsUser,(VaccineType) vaccineType,(Date) dateDay,(Slot) slotChoice);
    }


    /**
     * Gets sns user by number.
     *
     * @param snsUserNumber the sns user number
     * @return the sns user by number
     */
    public SNSUser getSNSUserByNumber(String snsUserNumber) {
        return this.m_oPlatform.getRegisterSNSUser().getSNSUserBySNSUserNumber(snsUserNumber);
    }

    /**
     * Select vaccine type list.
     *
     * @return the list
     */
    public List<VaccineType> selectVaccineType() {
        return this.m_oVaccinationCenter.getListVaccineTypes();
    }

    /**
     * Schedule vaccination boolean.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean scheduleVaccination(Object vaccineSchedule) {
        return this.m_oVaccinationCenter.scheduleVaccine((VaccineSchedule) vaccineSchedule);
    }

    /**
     * Receive sms notification boolean.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean receiveSMSNotification(Object vaccineSchedule){
        SMSNotifier sms = this.m_oPlatform.getSmsNotifier();
        return sms.addVaccineSchedule(this.m_oVaccinationCenter, (VaccineSchedule) vaccineSchedule);
    }
}
