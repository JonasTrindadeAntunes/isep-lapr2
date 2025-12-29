package domain.model;

import app.domain.model.Vaccine.RegisterVaccineType;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeTest {

    @Test
    public void ensureWrongFormatCodeNotAllowed() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("123","Moderna","Viral vector vaccines");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureDesignationCannotBeEmpty() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("1243","","Viral vector vaccines");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void ensureDesignationMAXChars() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new VaccineType("1243","asdsadsadsadsadasdsadsadsaddasdsadsadasdsadsadasdsadsadsadasdsadsad","Viral vector vaccines");
        });

        String expectedMessage = "Code must be between 4 to 8 chars and designation max 40 chars";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }



    @Test
    void getCode() {
        VaccineType vt = new VaccineType("2111","banana","Viral vector vaccines");
        assertEquals(vt.getCode(),"2111");
    }

    @Test
    void setCode() {
        VaccineType vt = new VaccineType("2111","banana","Viral vector vaccines");
        vt.setCode("2121");
        assertEquals(vt.getCode(),"2121");
    }

    @Test
    void getDesignation() {
        VaccineType vt = new VaccineType("2111","banana","Viral vector vaccines");
        assertEquals(vt.getDesignation(),"banana");
    }

    @Test
    void setDesignation() {
        VaccineType vt = new VaccineType("2111","banana","Viral vector vaccines");
        vt.setDesignation("bana");
        assertEquals(vt.getDesignation(),"bana");
    }





    @Test
    void testHashCode() {
        VaccineType vtx = new VaccineType("1111","dilan","Viral vector vaccines");

        assertEquals(vtx.hashCode(),1508577);
    }

    @Test
    void testEquals() {
        VaccineType vtx = new VaccineType("1212","desi","Viral vector vaccines");
        VaccineType vty = new VaccineType("1212","desi","Viral vector vaccines");

        assertEquals(vtx.equals(vty),true);
    }

    @Test
    void testEquals1() {
        VaccineType vtx = new VaccineType("1212","desi","Viral vector vaccines");


        assertEquals(vtx.equals(vtx),true);
    }

    @Test
    void testEquals2() {
        VaccineType vtx = new VaccineType("1212","desi","Viral vector vaccines");
        VaccineType vty = null;

        assertEquals(vtx.equals(vty),false);
    }

    @Test
    void testAddVaccine(){
        VaccineType vtx = new VaccineType("1212","grow","Viral vector vaccines");
        vtx.addVaccine(VaccineType.newVaccine("12345678","pfizer"));

    }

    @Test

    void testvalidateVaccine(){
        RegisterVaccineType rvt = new RegisterVaccineType();
        VaccineType vtx = new VaccineType("2112","wwww","Viral vector vaccines");
        Vaccine vx = new Vaccine("12345678","pfixer");
        vtx.validateVaccine(vx);

        boolean expresult = true;
        boolean result = vtx.validateVaccine(vx);

        assertEquals(expresult, result);



    }


    @Test
    void getVaccine() {

        Vaccine vac = new Vaccine("963", "Bayer");
         List<Vaccine> vaccineList = new ArrayList<>();
        vaccineList.add(vac);

        VaccineType vt = new VaccineType("1234","Covid19","Viral vector vaccines");
        vt.addVaccine(vac);



        assertEquals( vt.getVaccine(vac.getId()),vac);

    }

    @Test
    void getVaccine1() {

        Vaccine vac = new Vaccine("963", "Bayer");
        List<Vaccine> vaccineList = new ArrayList<>();
        vaccineList.add(vac);

        VaccineType vt = new VaccineType("1234","Covid19","Viral vector vaccines");




        assertEquals( vt.getVaccine(vac.getId()),null);

    }

    @Test
    void testToString() {

        VaccineType vtx = new VaccineType("1111","desa","Viral vector vaccines");
        System.out.println(vtx);
        assertFalse(vtx.toString().contains("VaccineType{" +
                "Code:'" + 1111 + '\'' +
                ", Designation:'" + "desa" + '\'' +
                ", Type of vaccine:'" + "Viral vector vaccines" + '\'' +
                '}' ));
    }
}