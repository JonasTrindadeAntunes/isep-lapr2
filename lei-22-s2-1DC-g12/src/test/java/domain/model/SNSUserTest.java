package domain.model;

import app.domain.model.SNSUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserTest {

    SNSUser user;
    @BeforeEach
    void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        user = new SNSUser("User","rua do user","Male","923456789","killmenow@live.com",d,"12345678","13700000");

    }

    @Test
    void getEmail() {
        assertEquals("killmenow@live.com",user.getEmail());
    }

    @Test
    void getName() {
        assertEquals("User",user.getName());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("923456789",user.getPhoneNumber());
    }

    @Test
    void getSnsUserNumber() {
        assertEquals("12345678",user.getSnsUserNumber());
    }

    @Test
    void getCitizenCardNumber() {
        assertEquals("13700000",user.getCitizenCardNumber());
    }


    @Test
    void testEquals() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2010);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 21);
        Date d = cal.getTime();
        SNSUser user1 = new SNSUser("Usered","rua do useedr","Female","923456789","rohitm@live.com",d,"12345677","13700001");

        assertTrue(user.equals(user1)); //same phone number

        SNSUser user2 = new SNSUser("Usered","rua do useedr","Female","923456788","killmenow@live.com",d,"12345677","13700001");

        assertTrue(user.equals(user2)); //same email

        SNSUser user3 = new SNSUser("Usered","rua do useedr","Female","923456788","rohitm@live.com",d,"12345678","13700001");

        assertTrue(user.equals(user3)); //same sns user number

        SNSUser user4 = new SNSUser("Usered","rua do useedr","Female","923456788","rohitm@live.com",d,"12345677","13700000");

        assertTrue(user.equals(user4)); //same citizen card number

        cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        d = cal.getTime();
        SNSUser user5 = new SNSUser("User","rua do user","Male","923456788","doormat@verizon.net",d,"12345677","13700001");


        assertFalse(user.equals(user5)); //same name, address, date of birth and gender
    }

    @Test
    void testHashCode() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2003);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser user1 = new SNSUser("Badabadabing","Badabadabong","Female","923456789","killmenow@live.com",d,"12345678","13700000");

        assertEquals(user1.hashCode(),user.hashCode());
    }

    @Test
    public void ensureWrongFormatCitizenCardNumberNotAllowed()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("Badabadabing","Badabadabong","Female","923456789","jimxugle@sbcglobal.net",d,"12345678","137777050");

        });

        String expectedMessage = "The citizen card number must be in PT format and contain only 8 digits.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureWrongFormatPhoneNumberNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("Badabadabing","Badabadabong","Female","92345689","milton@yahoo.com",d,"12345678","13777705");

        });

        String expectedMessage = "The phone number must be in PT format and contain only 9 digits.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("","Badabadabong","Female","922226895","philen@aol.com",d,"12345678","13222705");
        });

        String expectedMessage = "Name is emptyNone of the arguments can be null";
        String actualMessage = exception.getMessage();
        System.out.println(exception.getMessage());
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed1() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","","Female","923456895","ganter@me.com",d,"12345678","13777705");
        });

        String expectedMessage = "Address is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed2() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","a","","923456895","tlinden@yahoo.ca",d,"12345678","13777705");
        });

        String expectedMessage = "Gender is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed3() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","a","Female","","lstaf@aol.com",d,"12345678","13777705");
        });

        String expectedMessage = "Phone number is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed4() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","a","Female","916597487","",d,"12345678","13777705");
        });

        String expectedMessage = "Email is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed5() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","a","Female","916597487","squirrel@me.com",d,"","13777705");
        });

        String expectedMessage = "Sns user number is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureNullIsNotAllowed6() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2003);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Date d = cal.getTime();
            new SNSUser("a","a","Female","916597487","claypool@icloud.com",d,"12345678","");
        });

        String expectedMessage = "Citizen card number is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void toStringx() {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2003);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date d = cal.getTime();
        SNSUser su = new SNSUser("a","a","Female","916597487","mosses@optonline.net",d,"12345678","13777705");


        assertFalse(su.toString().contains("SNSUser{" +
                "name='" + su.getName() + '\'' +
                ", address='" + "a" + '\'' +
                ", gender='" + "Female" + '\'' +
                ", phoneNumber='" + su.getPhoneNumber() + '\'' +
                ", email='" + su.getEmail() + '\'' +
                ", birthDate=" + d +
                ", snsUserNumber='" + 12345678 + '\'' +
                ", citizenCardNumber='" + 13777705 + '\'' +
                '}'));

    }

}