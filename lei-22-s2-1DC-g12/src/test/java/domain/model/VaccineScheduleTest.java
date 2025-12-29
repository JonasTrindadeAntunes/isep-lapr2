package domain.model;

import app.domain.model.SNSUser;
import app.domain.model.Vaccine.VaccineType;
import app.domain.model.VaccineSchedule;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VaccineScheduleTest {


    @Test
    void getDate() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","bbirth@att.net",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);

        assertEquals(date1.getTime().toString(),vs.getDate().toString());


    }

    @Test
    void setDate() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","matloff@mac.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);
        vs.setDate(date1.getTime());
        assertEquals(date1.getTime(),vs.getDate());
    }

    @Test
    void getSnsUser() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","jfmulder@yahoo.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);

        assertEquals(user,vs.getSnsUser());
    }

    @Test
    void isValidateEntrance() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","sumdumass@aol.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);

        assertEquals(false,vs.isValidateEntrance());
    }

    @Test
    void setValidateEntrance() {

        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","tubajon@yahoo.ca",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);
        vs.setValidateEntrance(true);

        assertEquals(true,vs.isValidateEntrance());
    }

    @Test
    void testEquals() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);


        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","dprice@optonline.net",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);
        VaccineSchedule vs1 =  new VaccineSchedule(date.getTime(),vt, user);


        assertTrue(vs.equals(vs1));
    }

    @Test
    void testEquals1() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2011);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2051);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2051);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","bmcmahon@icloud.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);
        VaccineSchedule vs1 =  new VaccineSchedule(date1.getTime(),vt, user);


        assertEquals(true,vs.equals(vs1));
    }

    @Test
    void testEquals2() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","jmmuller@me.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);
        VaccineSchedule vs1 =  null;


        assertEquals(false,vs.equals(vs1));
    }

    @Test
    void testEqualsFalse() {
        /*Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        VaccineType vT = new VaccineType("6666","Tifoide","Mrna");
        VaccineType vT2 = new VaccineType("6665","Febre","Live-attenuated vaccines");
        SNSUser user1 = new SNSUser("punkis@yahoo.com","912345876","55544551","12113232");
        SNSUser user2= new SNSUser("pizza@outlook.com","952345876","53322551","12255232");
        VaccineSchedule vS = new VaccineSchedule(date11.getTime(),vT,user1);
        VaccineSchedule vS2 = new VaccineSchedule(date.getTime(),vT2,user2);
        assertFalse(vS.equals(vS2));*/
    }

    @Test
    void getVaccineType() {
        Calendar date11 = Calendar.getInstance();
        date11.set(Calendar.DAY_OF_MONTH,1);
        date11.set(Calendar.MONTH,1);
        date11.set(Calendar.YEAR,2012);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.MONTH,1);
        date.set(Calendar.YEAR,2050);

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH,1);
        date1.set(Calendar.MONTH,1);
        date1.set(Calendar.YEAR,2050);

        VaccineType vt = new VaccineType("2341","Covit","Lav");
        SNSUser user = new SNSUser("rui","Rua do Joao","Male","910304050","tmccarth@hotmail.com",date11.getTime(),"10202040","10202040");

        VaccineSchedule vs =  new VaccineSchedule(date.getTime(),vt, user);

        assertEquals(vt,vs.getVaccineType());
    }
}