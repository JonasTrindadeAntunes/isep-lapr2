package app.controller.Receptionist;

import app.controller.App;
import app.domain.model.Employee.Receptionist;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterVaccineScheduleControllerTest {


    @Test
    void checkLogin() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");

        assertTrue(rvsc.checkLogin());
    }


//In progress, not able to true
    @Test
    void getWorkingVaccinationCenterTrue() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();

        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",8,00,20,00,5,10);
        Receptionist rec = new Receptionist("Sara", "Porto","911222333","receptionist@lei.sem2.pt","15920550");
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");
        rec.setWorkingVaccinationCenter(vc);
        //System.out.println(""+ rvsc.getWorkingVaccinationCenter());
    }

    @Test
    void getWorkingVaccinationCenterFalse() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();
        assertFalse(rvsc.getWorkingVaccinationCenter());
    }

    @Test
    void checkIfExistsSNSUser() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();
        SNSUser user1 = new SNSUser("1211154@isep.ipp.pt","911222333","12345678","15750789");
        rvsc.checkIfExistsSNSUser(user1.getSnsUserNumber());
        assertTrue(rvsc.checkIfExistsSNSUser(user1.getSnsUserNumber()));
    }

    @Test
    void checkIfExistsSNSUserFalse() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();
        SNSUser user1 = new SNSUser("121114@isep.ipp.pt","911542333","12388678","15734789");
        assertFalse(rvsc.checkIfExistsSNSUser(user1.getSnsUserNumber()));
    }

//In progress, not able to set the vaccination center into the register
    @Test
    void getAllAvailableSlotsInVaccinationCenter() {
        RegisterVaccineScheduleController ctrl = new RegisterVaccineScheduleController();

        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        Receptionist rec = new Receptionist("Sara", "Porto","911222333","receptionist@lei.sem2.pt","15920550");
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");
        rec.setWorkingVaccinationCenter(vc);
        VaccinationCenter vaccnt = rec.getWorkingVaccinationCenter();

        Date date = new Date(2022,5,25);


    }
// Doing
    @Test
    void createSchedule() {
    }


    @Test
    void getSNSUserByNumber() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();
        SNSUser user1 = new SNSUser("121114@isep.ipp.pt","911542333","12388678","15734789");

        assertNull(rvsc.getSNSUserByNumber("12388678"));
    }

//In progress, not able to set the vaccination center into the register
    @Test
    void selectVaccineType() {
        RegisterVaccineScheduleController rvsc = new RegisterVaccineScheduleController();

        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        Receptionist rec = new Receptionist("Sara", "Porto","911222333","receptionist@lei.sem2.pt","15920550");
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");
        rec.setWorkingVaccinationCenter(vc);

        //System.out.println(rec.getWorkingVaccinationCenter());

        VaccineType vt = new VaccineType("12345","covid","Mrna");
        vc.addVaccineType(vt);

        List<VaccineType> list = new ArrayList<>();
        list.add(vt);

        System.out.println(rvsc.getWorkingVaccinationCenter());
        
        //assertEquals(list,rvsc.selectVaccineType());





    }

    @Test
    void scheduleVaccination() {
    }

    @Test
    void receiveSMSNotification() {
    }
}