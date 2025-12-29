package app.controller;

import app.controller.Nurse.WaitingRoomController;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitingRoomControllerTest {

    @Test
    void checkLogin() {

        WaitingRoomController rsc = new WaitingRoomController();
        App.getInstance().doLogin("nurse@lei.sem2.pt","123456");


        assertTrue(rsc.checkLogin());

    }



    @Test
    void getWorkingVaccinationCenter() {
       /*WaitingRoomController rsc = new WaitingRoomController();
        App.getInstance().doLogin("10@isep.ipp.pt","123456");
        SelectVaccinationCenterController svcc = new SelectVaccinationCenterController();
        svcc.setWorkingVaccinationCenter(svcc.getListVaccinationCenter().get(0));

        VaccinationCenter vacCentreA = new CommunityMassVaccinationCenter("s joao", "porto","916597487","1181478@isep.ipp.pt","2525","www.sns.pt",10,0,20,0,10,10);


        assertTrue(rsc.getWorkingVaccinationCenter().toString().contains(vacCentreA.toString()));
        App.getInstance().doLogout();*/
    }

    @Test
    void getWorkingVaccinationCenterNULL() {

        WaitingRoomController rsc = new WaitingRoomController();

        assertEquals(null,rsc.getWorkingVaccinationCenter());
    }


    @Test
    void getListUsersInWaitingRoom() {
        WaitingRoomController rsc = new WaitingRoomController();
        VaccinationCenter vacCentreA = new CommunityMassVaccinationCenter("s joao", "porto","916597487","1181478@isep.ipp.pt","2525","www.sns.pt",10,0,20,0,10,10);
        VaccinationCenter vacCentreB = new HealthCareCenter("s joao", "maia","916597777","1211154@isep.ipp.pt","2525","www.sns.pt",10,30,20,0,10,10);
        rsc.getListUsersInWaitingRoom(vacCentreA);
        assertEquals(rsc.getListUsersInWaitingRoom(vacCentreA),rsc.getListUsersInWaitingRoom(vacCentreB));
    }
}