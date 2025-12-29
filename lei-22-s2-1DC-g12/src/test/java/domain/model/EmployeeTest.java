package domain.model;

import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.Employee.Nurse;
import app.domain.model.Employee.Receptionist;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {



    @Test
    public void ensureNullIsNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
         new Nurse(null, null, null, null, null);
        });

        String expectedMessage = "None of the arguments can be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureWrongFormatPhoneNumberNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Nurse("Jonas", "Rua de Penalves", "9", "seebs@gmail.com", "12345678");
        });

        String expectedMessage = "The phone number must be in PT format and contain only 9 digits.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureWrongFormatCitizenCardNumberNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Nurse("Jonas", "Rua de Penalves", "916597487", "leakin@mac.com", "1");
        });

        String expectedMessage = "The citizen card number must be in PT format and contain only 8 digits.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void getName() {

        Employee emp = new Receptionist("j","j","916597487","starstuff@yahoo.ca","13765405");
        assertEquals(emp.getName(),"j");
    }

    @Test
    void setName() {
        Employee emp = new CenterCoordinator("j","j","916597487","nichoj@me.com","13765405");
        emp.setName("ja");

        assertEquals("ja",emp.getName());
    }

    @Test
    void getAddress() {
        Employee emp = new CenterCoordinator("j","jd","916597487","rcwil@mac.com","13765405");
        assertEquals(emp.getAddress(),"jd");
    }

    @Test
    void setAddress() {
        Employee emp = new Nurse("j","j","916597487","iapetus@me.com","13765405");
        emp.setAddress("jt");

        assertEquals("jt",emp.getAddress());
    }

    @Test
    void getPhoneNumber() {
        Employee emp = new CenterCoordinator("j","jd","916597487","xtang@gmail.com","13765405");
        assertEquals(emp.getPhoneNumber(),"916597487");
    }

    @Test
    void setPhoneNumber() {
        Employee emp = new Receptionist("j","j","916597487","seebs@icloud.com","13765405");
        emp.setPhoneNumber("916597480");

        assertEquals("916597480",emp.getPhoneNumber());
    }

    @Test
    void getEmailAddress() {
        Employee emp = new Receptionist("j","jd","916597487","cremonini@live.com","13765405");
        assertEquals(emp.getEmailAddress(),"cremonini@live.com");

    }

    @Test
    void setEmailAddress() {
        Employee emp = new Receptionist("j","j","916597487","tangsh@yahoo.ca","13765405");
        emp.setEmailAddress("1181428@isep.ipp.pt");

        assertEquals("1181428@isep.ipp.pt",emp.getEmailAddress());
    }

    @Test
    void getCitizenCardNumber() {
        Employee emp = new Receptionist("j","jd","916597487","bader@hotmail.com","13765405");
        assertEquals(emp.getCitizenCardNumber(),"13765405");

    }

    @Test
    void setCitizenCardNumber() {
        Employee emp = new Nurse("j","j","916597487","martyloo@yahoo.com","13765405");
        emp.setCitizenCardNumber("13765400");

        assertEquals("13765400",emp.getCitizenCardNumber());
    }
//NOTES
   /* @Test
    void getID() {

        Employee emp = new Receptionist("j","jd","916597487","1181478@isep.ipp.pt","13765405");
        assertEquals(emp.getID(),15);
    }

    @Test
    void getTotalEmployees() {
        Employee emp = new Receptionist("j","jd","916597487","1181478@isep.ipp.pt","13765405");
        assertEquals(Employee.getTotalEmployees() ,12);
    }*/

    @Test
    void testHashCode() {
        Employee vacx = new Receptionist("j","j","916597487","oevans@mac.com","13765405");

        assertEquals(vacx.hashCode(),-857487994);

    }

    @Test
    void testEquals() {

        Employee vacx = new Receptionist("j","j","916597487","aegreene@live.com","13765405");
        Employee vacy = new Receptionist("j","j","916597287","aegreene@live.com","13765405");

        assertEquals(vacx.equals(vacy),true);
    }

    @Test
    void testEquals1() {

        Employee vacx = new Receptionist("j","j","916597487","oster@verizon.net","13765405");

        assertEquals(vacx.equals(vacx),true);
    }

    @Test
    void testEquals2() {

        Employee vacx = new Receptionist("j","j","916597487","janusfury@att.net","13764405");
        Employee vacy = new Receptionist("j","j","916597487","janusfury@att.net","13765405");

        assertEquals(vacx.equals(vacy),true);
    }

    @Test
    void testEquals3() {

        Employee vacx = new Receptionist("j","j","916597487","jimxugle@comcast.net","13765405");
        Employee vacy = new Receptionist("j","j","916597487","parsimony@hotmail.com","13265405");

        assertEquals(vacx.equals(vacy),true);
    }

    @Test
    void testEquals4() {

        Employee vacx = new Receptionist("j","j","916597487","sscorpio@icloud.com","13765405");
        //CommunityMassVaccinationCenter vacy = new CommunityMassVaccinationCenter("j", "j", "916597487", "j", "j","j",20.0,10.0,10.0,10);
        CommunityMassVaccinationCenter vact = null;
        assertEquals(vacx.equals(vact),false);
    }

    @Test
    void testEquals5() {

        Employee vacx = new Receptionist("j","j","916597487","bwcarty@att.net","13765405");
        Employee vacy = new Receptionist("j","j","916597487","bwcarty@att.net","13765405");

        assertEquals(vacx.equals(vacy),true);
    }

    @Test
    void testToString() {

        Employee vacx = new Receptionist("j","j","916597487","jsmith@gmail.com","13765405");

        assertTrue(vacx.toString().contains("Employee: j - j - 916597487 - jsmith@gmail.com - 13765405"));
    }
}