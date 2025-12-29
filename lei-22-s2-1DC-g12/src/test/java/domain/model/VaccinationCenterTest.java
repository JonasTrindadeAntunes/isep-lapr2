package domain.model;

import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.Vaccine;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationCenterTest {


    @Test
    public void ensureNullIsNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CommunityMassVaccinationCenter(null, null, null, null, null,null,null,null,null,null,null,null);
        });

        String expectedMessage = "None of the arguments can be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void getName() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getName(),"s joao");
    }

    @Test
    void setName() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setName("sao joao");

        assertEquals("sao joao",vc.getName());

    }

    @Test
    void getAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getAddress(),"RuaR");
    }

    @Test
    void setAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setAddress("Rua S");

        assertEquals("Rua S",vc.getAddress());
    }

    @Test
    void getPhoneNumber() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getPhoneNumber(),"916587487");
    }

    @Test
    void setPhoneNumber() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);

        vc.setPhoneNumber("913578964");

        assertEquals("913578964",vc.getPhoneNumber());
    }

    @Test
    void getEmailAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getEmailAddress(),"1211690@isep.ipp.pt");
    }

    @Test
    void setEmailAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setEmailAddress("ghij");

        assertEquals("ghij",vc.getEmailAddress());
    }

    @Test
    void getFaxNumber() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getFaxNumber(),"67890");
    }

    @Test
    void setFaxNumber() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setFaxNumber("67890");

        assertEquals("67890",vc.getFaxNumber());
    }

    @Test
    void getWebsiteAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getWebsiteAddress(),"www.isep.ipp.pt");
    }

    @Test
    void setWebsiteAddress() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setWebsiteAddress("www.vac.pt");

        assertEquals("www.vac.pt",vc.getWebsiteAddress());
    }

    @Test
    void getOpeningHours() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getOpeningHours(),10);
    }

    @Test
    void setOpeningHours() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setOpeningHours(11);

        assertEquals(11,vc.getOpeningHours());
    }

    @Test
    void getClosingHours() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getClosingHours(),20);
    }

    @Test
    void setClosingHours() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setClosingHours(21);

        assertEquals(21,vc.getClosingHours());
    }

    @Test
    void getSlotDuration() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getSlotDuration(),5);
    }

    @Test
    void setSlotDuration() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setSlotDuration(6);

        assertEquals(6,vc.getSlotDuration());
    }

    @Test
    void getMaxNumberVaccines() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        assertEquals(vc.getMaxNumberVaccines(),10);
    }

    @Test
    void setMaxNumberVaccines() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        vc.setMaxNumberVaccines(3);

        assertEquals(3,vc.getMaxNumberVaccines());
    }

    @Test
    void testEquals() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);

        assertEquals(true,vc.equals(vc));
    }

    @Test
    void testEqual1() {
        VaccinationCenter vy = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        VaccinationCenter vc = null;


        assertEquals(false,vy.equals(vc));
    }

    @Test
    void testEqual2() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        VaccinationCenter vy = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);


        assertEquals(true,vy.equals(vc));
    }

    @Test
    void testEqual3() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        VaccinationCenter vy = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,0,20,0,5,10);


        assertEquals(true,vy.equals(vc));
    }

    @Test
    void testEqual4() {
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        VaccinationCenter vy = new HealthCareCenter("s joao", "a","915912875","sddadsd","12345","www.www.www",10,0,20,0,5,10);


        assertEquals(false,vy.equals(vc));
    }

    @Test
    void registerUserArrival() {
// WaitingRoom's methods are already covered
    }

   /* @Test
    void getWaitingRoomSNSUsers() {
        VaccinationCenter vs = new CommunityMassVaccinationCenter("s joao", "Ruax","916507487","1211090@isep.ipp.pt","60890","www.isep.ipp.pt",10,0,20,0,5,10);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser userx = new SNSUser("User","rua do user","Male","923450089","u2ser@gmail.com",d,"12340678","13700300");
        vs.registerUserArrival(userx,d);

        String expectedResult = userx.toString();
        String result = vs.getWaitingRoomSNSUsers().toString();


        assertTrue(result.contains(expectedResult)); TODO change to vaccineSchedule
    }
*/
    @Test
    void addVaccineTest() {
        VaccinationCenter vCx = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        Vaccine v = new Vaccine("123","Bayer");
        vCx.addVaccine(v);
        assertTrue(vCx.addVaccine(v));
    }
}