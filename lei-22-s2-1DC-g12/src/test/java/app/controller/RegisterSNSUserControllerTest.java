package app.controller;

import app.controller.Receptionist.RegisterSNSUserController;
import app.domain.model.SNSUser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSNSUserControllerTest {


    @Test
    void checkLogin() {
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        App.getInstance().doLogin("receptionist@lei.sem2.pt","123456");


        assertTrue(rsc.checkLogin());
    }



   /* @Test
    void newSNSUser() {
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        Boolean result = rsc.newSNSUser("User","rua do user","Male","923456789","user@gmail.com",d,"12345678","13700000");

        assertTrue(result);
        rsc.addUser();
        //user com o mesmo citizen card number
        result = rsc.newSNSUser("User","rua do user","Male","913456789","user1@gmail.com",d,"12345677","13700000");

        assertFalse(result);

        //user com o mesmo sns user number
        result = rsc.newSNSUser("User","rua do user","Male","913456789","user1@gmail.com",d,"12345678","13700001");

        assertFalse(result);

        //user com o mesmo email
        result = rsc.newSNSUser("User","rua do user","Male","913456789","user@gmail.com",d,"12345677","13700000");
        assertFalse(result);

        //user com o mesmo phone number
        result = rsc.newSNSUser("User","rua do user","Male","923456789","user1@gmail.com",d,"12345678","13700000");
        assertFalse(result);

    }*/
//NOta
   /* @Test
    void getSNSUser() {
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        rsc.newSNSUser("User","rua do user","Male","923456789","user@gmail.com",d,"12345678","13700000");
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","user@gmail.com",d,"12345678","13700000");
        assertEquals(rsc.getSNSUser(), user);
    }*/


    @Test
     void getGenderList() {
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        assertEquals(rsc.getGenderList(), Arrays.asList(SNSUser.Gender.values()));
    }

    @Test
    void addUser1()
    {
        RegisterSNSUserController rsc = new RegisterSNSUserController();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        Boolean result = rsc.newSNSUser(null,"rua do user","Male","923456789","teverett@me.com",d,"12345678","13700000");
        rsc.addUser();

        assertEquals(null,rsc.getSNSUser());
    }
}