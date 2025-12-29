package app.controller.SNSUser;

import app.controller.App;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleVaccinationControllerTest {

    @Test
    void validateUserSession() {
        ScheduleVaccinationController svc = new ScheduleVaccinationController();
        App.getInstance().doLogin("centercoordinator@lei.sem2.pt","123456");
        System.out.println(""+ App.getInstance().getCurrentUserSession().getUserId().getEmail());
        System.out.println(""+svc.validateUserSession());

        assertEquals(false,svc.validateUserSession());

    }

    @Test
    void getVaccinationCenterList() {
        ScheduleVaccinationController svc = new ScheduleVaccinationController();
        VaccinationCenter vs = new CommunityMassVaccinationCenter("s joao", "porto","916597487","vaccinationcenterLAPR2@gmail.com","2525","www.sns.pt",8,0,20,0,10,10);

        assertTrue(svc.getVaccinationCenterList().toString().contains(vs.toString()));
    }

    @Test
    void getAvailableVaccineTypes() {
        ScheduleVaccinationController svc = new ScheduleVaccinationController();
        //System.out.println(""+svc.getAvailableVaccineTypes(svc.getVaccinationCenterList().get(0)));

        VaccineType vt = new VaccineType("6655","flu-A","mRna");

            assertTrue(svc.getAvailableVaccineTypes(svc.getVaccinationCenterList().get(0)).toString().contains(vt.toString()));

    }

    @Test
    void getAvailableSlots() {
        ScheduleVaccinationController svc = new ScheduleVaccinationController();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 23);
        date.set(Calendar.MONTH, 4);
        date.set(Calendar.YEAR, 2022);

        //System.out.println(""+svc.getAvailableSlots(svc.getVaccinationCenterList().get(0),date.getTime()));

        assertEquals(svc.getAvailableSlots(svc.getVaccinationCenterList().get(0),date.getTime()),null);
    }

    @Test
    void createVaccineSchedule() {
        ScheduleVaccinationController svc = new ScheduleVaccinationController();
        App.getInstance().doLogin("user@lei.sem2.pt","123456");
        svc.getVaccinationCenterList().get(0);
        svc.getAvailableVaccineTypes(svc.getVaccinationCenterList().get(0));
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 23);
        date.set(Calendar.MONTH, 4);
        date.set(Calendar.YEAR, 2022);
        svc.getAvailableSlots(svc.getVaccinationCenterList().get(0),date.getTime());

        //svc.createVaccineSchedule(svc.getVaccinationCenterList().get(0),)
    }

    @Test
    void scheduleVaccine() {
    }

    @Test
    void receiveSMSNotification() {
    }
}