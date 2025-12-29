package app.controller;

import app.controller.Admin.RegisterVaccinationCenterController;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.RegisterVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterVaccinationCenterControllerTest {

    @Test
    void testNewVaccinationCenter() {

        RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();

        boolean expResult = true;
        boolean result = rvcc.newVaccinationCenter("s joao", "Rua","916500007","sfoskett@msn.com","12345","www.www.www",10,00,20,00,5,10,1);

        assertEquals(expResult,result);

    }

    @Test
    void testRegisterVaccinationCenter() {
        RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();
        rvcc.newVaccinationCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10,1);

        boolean expResult = true;
        boolean result = rvcc.registerVaccinationCenter();

        assertEquals(expResult,result);
    }

    @Test
    void testRegisterVaccinationCenter1() {


        RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();
        rvcc.newVaccinationCenter("s joao", "RuaX","916597487","1181378@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,10,0);
        rvcc.registerVaccinationCenter();
        rvcc.newVaccinationCenter("s joao", "RuaX","916597487","1181378@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,10,0);


        boolean expResult = false;
        boolean result = rvcc.registerVaccinationCenter();

        assertEquals(expResult,result);
    }

    @Test
    void testRegisterVaccinationCenter2() {

        RegisterVaccinationCenter rvc = new RegisterVaccinationCenter("5");
        VaccinationCenter vcc = rvc.newVaccinationCenter("s joao", "RuaX","916597487","1181378@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,10,4);



        assertEquals(null,vcc);
    }

    @Test
    void testGetTypesOfVaccinationCenter() {
        RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();

        List<String> listOfRolesx = new ArrayList<>();
        listOfRolesx.add("Community Mass Vaccination Center");
        listOfRolesx.add("Healthcare center");


        assertLinesMatch(rvcc.getTypesOfVaccinationCenter(),listOfRolesx);
    }

    @Test
    void testGetVaccinationCenterString() {
        RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();

        VaccinationCenter vacc = new HealthCareCenter("s joao", "Rua","916597487","1181478@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,10);

        rvcc.newVaccinationCenter("s joao", "Rua","916597487","1181478@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,10,1);
        rvcc.registerVaccinationCenter();
        rvcc.getVaccinationCenterString();

        String expResult = vacc.toString();
        String result = rvcc.getVaccinationCenterString();

        assertEquals(expResult,result);

    }

    @Test
    public void ensureNullIsNotAllowed() throws NullPointerException{

            RegisterVaccinationCenterController rvcc = new RegisterVaccinationCenterController();
            rvcc.newVaccinationCenter("s joao", "Rua", "916597487", "1181478@isep.ipp.pt", "12345", "www.www.www", 10,00, 20,00, 5, 10, 10);

    }

    /*@Test
    void getWaitingRoomSNSUsers() {
        RegisterVaccinationCenter rvs = new RegisterVaccinationCenter();

        VaccinationCenter vs = new CommunityMassVaccinationCenter("s joao", "Ruax","916507487","1211090@isep.ipp.pt","60890","www.isep.ipp.pt",10,0,20,0,5,10);
        rvs.registerVaccinationCenter(vs);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser userx = new SNSUser("User","rua do user","Male","923450089","u2ser@gmail.com",d,"12340678","13700300");


        vs.registerUserArrival(userx,d);


        String expectedResult = userx.toString();
        String result = rvs.getWaitingRoomSNSUsers(vs).toString();


        assertTrue(result.contains(expectedResult));
    }

    @Test
    void getWaitingRoomSNSUsers1() {
        RegisterVaccinationCenter rvs = new RegisterVaccinationCenter();

        VaccinationCenter vs = new CommunityMassVaccinationCenter("s joao", "Ruax","916507487","1211090@isep.ipp.pt","60890","www.isep.ipp.pt",10,0,20,0,5,10);



        assertEquals(null,rvs.getWaitingRoomSNSUsers(vs));
    }*/
}