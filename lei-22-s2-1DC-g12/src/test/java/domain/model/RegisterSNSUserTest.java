package domain.model;

import app.controller.App;
import app.domain.model.Platform.RegisterSNSUser;
import app.domain.model.SNSUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RegisterSNSUserTest {

    static RegisterSNSUser register = new RegisterSNSUser(App.getInstance().getCompany().getUserRoleStore());

    @BeforeAll
     static void setUp() {

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2000);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            SNSUser user = register.newSNSUser("User", "rua do user", "Male", "923456789", "seano@aol.com", d, "12345678", "13700000");
            register.addSNSUser(user);
        }

    @Test
    void newSNSUser() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser result = register.newSNSUser("User","rua do user","Male","923456789","ajlitt@sbcglobal.net",d,"12345679","13700001");
        assertEquals(null,result); //user tem o mesmo phone number
        result = register.newSNSUser("User","rua do user","Male","913456789","ajlitt@sbcglobal.net",d,"12345677","13700000");
        assertEquals(null,result); //user tem o mesmo citizen card number
        result = register.newSNSUser("User","rua do user","Male","913456789","ajlitt@sbcglobal.net",d,"12345678","13700001");
        assertEquals(null,result); //user tem o mesmo sns user number
        result = register.newSNSUser("User","rua do user","Male","913456789","tromey@sbcglobal.net",d,"12345677","13700000");
        assertEquals(null,result); //user tem o mesmo email
        result = register.newSNSUser("User","rua do user","Male","923456789","ajlitt@sbcglobal.net",d,"12345678","13700000");
        assertEquals(null,result); //user tem o mesmo phone number

        result = register.newSNSUser("User","rua do user","Male","923456777","ebassi@att.net",d,"12666678","13700666");
        assertEquals(new SNSUser("User","rua do user","Male","923456777","ebassi@att.net",d,"12666678","13700666"),result);
    }

    @Test
    void addSNSUser() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = register.newSNSUser("User","rua do user","Male","923456757","violinhi@optonline.net",d,"12665678","13700566");
        assertEquals(true, register.addSNSUser(user));
    }

    @Test
    void getSNSUserByEmail() {
        String email = "seano@aol.com";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","seano@aol.com",d,"12345678","13700000");

        assertEquals(user, register.getSNSUserByEmail(email));
    }

    @Test
    void getSNSUserByEmail1() {
        String email = "userx@gmail.com";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","airship@optonline.net",d,"12345678","13700000");

        assertEquals(null, register.getSNSUserByEmail(email));
    }

    @Test
    void getSNSUserByPhoneNumber() {
        String phoneNumber = "923456789";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","citizenl@outlook.com",d,"12345678","13700000");

        assertEquals(user, register.getSNSUserByPhoneNumber(phoneNumber));
    }

    @Test
    void getSNSUserByPhoneNumber1() {
        String phoneNumber = "913456789";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","alastair@live.com",d,"12345678","13700000");

        assertEquals(null, register.getSNSUserByPhoneNumber(phoneNumber));
    }

    @Test
    void getSNSUserBySNSUserNumber() {
        String SNSUserNumber = "12345678";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","ajohnson@msn.com",d,"12345678","13700000");

        assertEquals(user, register.getSNSUserBySNSUserNumber(SNSUserNumber));
    }

    @Test
    void getSNSUserBySNSUserNumber1() {
        String SNSUserNumber = "22345678";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","goresky@hotmail.com",d,"12345678","13700000");

        assertEquals(null, register.getSNSUserBySNSUserNumber(SNSUserNumber));
    }

    @Test
    void getSNSUserByCitizenCardNumber() {
        String citizenCardNumber = "13700000";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","speeves@comcast.net",d,"12345678","13700000");

        assertEquals(user, register.getSNSUserByCitizenCardNumber(citizenCardNumber));
    }

    @Test
    void getSNSUserByCitizenCardNumber1() {
        String citizenCardNumber = "13500000";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user = new SNSUser("User","rua do user","Male","923456789","speeves@comcast.net",d,"12345678","13700000");

        assertEquals(null, register.getSNSUserByCitizenCardNumber(citizenCardNumber));
    }
}