package app.controller;

import app.controller.Receptionist.RegisterSNSUserArrivalController;
import app.controller.Receptionist.RegisterSNSUserController;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.RegisterVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterSNSArrivalControllerTest {



    @Test
    void getWorkingVaccinationCenterTest(){
       /* RegisterSNSUserArrivalController rsc = new RegisterSNSUserArrivalController();
        App.getInstance().doLogin("10@isep.ipp.pt","123456");
        SelectVaccinationCenterController svcc = new SelectVaccinationCenterController();
        svcc.setWorkingVaccinationCenter(svcc.getListVaccinationCenter().get(0));

        VaccinationCenter vacCentreA = new CommunityMassVaccinationCenter("s joao", "porto","916597487","1181478@isep.ipp.pt","2525","www.sns.pt",10,0,20,0,10,10);

        assertTrue(rsc.getWorkingVaccinationCenter().toString().contains(vacCentreA.toString()));
        App.getInstance().doLogout();*/


    }


   @Test

    void addUserArrivalTest(){

        /*RegisterSNSUserArrivalController rsac = new RegisterSNSUserArrivalController();
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");
       Calendar date = Calendar.getInstance();
       date.add(Calendar.DAY_OF_MONTH,1);
        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,1930);

        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "porto","916597487","scottlee@comcast.net","2525","www.sns.pt",10,0,20,0,10,10);
        VaccineType vt = new VaccineType("1234","Covid","mRna");
        SNSUser user = new SNSUser("jonas","Rua do Joao","Male","916597487","gilmoure@live.com",date1.getTime(),"12345677","12345677");
        vc.scheduleVaccine(user,vt,date.getTime(), 10, 10);
        String snsNumber = user.getSnsUserNumber();
        rsac.getListVaccinationCenter();


        Boolean exp = rsac.validateUserArrival(vc,snsNumber);
        Boolean result = rsac.registerUserArrival(vc,user,date.getTime());
        assertEquals(exp,result);               
        App.getInstance().doLogout();*/
    }






    @Test
    void getSNSUserTest(){
        RegisterSNSUserArrivalController rsac = new RegisterSNSUserArrivalController();
        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,1930);

        SNSUser user1 = new SNSUser("jonas","Rua do Joao","Male","916597487","brainless@aol.com",date1.getTime(),"12345677","12345677");
        SNSUser user2;
        String snsNumber = user1.getSnsUserNumber();
        user2 = rsac.getSNSUser(snsNumber);

        assertEquals(user1,user2);

    }



    @Test
    void checkWaitingListTest(){
        RegisterSNSUserArrivalController rsac = new RegisterSNSUserArrivalController();
        RegisterVaccinationCenter rvc = new RegisterVaccinationCenter("5");
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        VaccinationCenter vc = rvc.newVaccinationCenter("s joao", "porto","916597487","1181478@isep.ipp.pt","2525","www.sns.pt",10,0,20,0,10,10,0);
        rvc.registerVaccinationCenter(vc);
        rsac.getListVaccinationCenter();
        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,1930);

        rsc.newSNSUser("rui","Rua do Joao","Male","916597489","yumpy@me.com",date1.getTime(),"12345679","12345679");
        rsc.addUser();

        Boolean result = rsac.checkIfExistsSNSUser("12345679");
        Boolean exp = true;
        assertEquals(exp,result);
    }


   /* @Test
    void checkLoginTest(){
        RegisterSNSUserArrivalController rsac = new RegisterSNSUserArrivalController();
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");


        assertTrue(rsac.checkLogin());
    }

    */
}
