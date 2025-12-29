package app.controller.SNSUser;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.Slot;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.UserSession;

import java.util.Date;
import java.util.List;

public class ScheduleVaccinationController {

    private App app;

    public ScheduleVaccinationController(){
        this.app = App.getInstance();
    }

    public boolean validateUserSession(){
        UserSession session = this.app.getCurrentUserSession();
        return session.isLoggedInWithRole(Constants.ROLE_SNS_USER);
    }

    public List<VaccinationCenter> getVaccinationCenterList(){
        return this.app.getCompany().getPlatform().getRegisterVaccinationCenter().getListVaccinationCenters();
    }


    public List<VaccineType> getAvailableVaccineTypes(Object vaccinationCenter){
        VaccinationCenter vc = (VaccinationCenter)  vaccinationCenter;
        return vc.getListVaccineTypes();
    }

    public List<Slot> getAvailableSlots(Object vaccinationCenter, Object day){
        VaccinationCenter vc = (VaccinationCenter)  vaccinationCenter;
        Date d = (Date) day;
        return vc.getAllAvailableSlotsInVaccinationCenter(d);
    }

    public VaccineSchedule createVaccineSchedule(Object vaccinationCenter, Object vaccineType, Object date, Object slot){
        VaccinationCenter vc = (VaccinationCenter)  vaccinationCenter;
        UserSession session = this.app.getCurrentUserSession();
        SNSUser user = this.app.getCompany().getPlatform().getRegisterSNSUser().getSNSUserByEmail(session.getUserId().getEmail());
        return vc.createVaccineSchedule(user,(VaccineType) vaccineType,(Date) date,(Slot) slot);
    }

    public boolean scheduleVaccine(Object vaccinationCenter, Object vaccineSchedule){
        VaccinationCenter vc = (VaccinationCenter)  vaccinationCenter;
        VaccineSchedule vs = (VaccineSchedule) vaccineSchedule;
        return vc.scheduleVaccine(vs);
    }

    public boolean receiveSMSNotification(Object vaccinationCenter, Object vaccineSchedule){
        SMSNotifier sms = this.app.getCompany().getPlatform().getSmsNotifier();
        return sms.addVaccineSchedule((VaccinationCenter) vaccinationCenter, (VaccineSchedule) vaccineSchedule);
    }


    public boolean emailNotifier(Object vaccinationCenter, Object vaccineSchedule) {
        EmailNotifier emailNotifier = this.app.getCompany().getPlatform().getEmailNotifier();
        return emailNotifier.addVaccineSchedule((VaccinationCenter) vaccinationCenter, (VaccineSchedule) vaccineSchedule);

    }
}
