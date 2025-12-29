package domain.model;

import app.domain.model.Vaccine.AdministrationProcess;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {

    @Test
    void getId() {
        Vaccine vac = new Vaccine("963", "Bayer");

        String expresult = "963";
        String result = vac.getId();

        assertEquals(expresult, result);
    }

    @Test
    void setId() {
        Vaccine vac = new Vaccine("963", "Bayer");
        vac.setId("147");

        String expresult = "147";
        String result = vac.getId();

        assertEquals(expresult, result);
    }

    @Test
    void getName() {
        Vaccine vac = new Vaccine("963", "Bayer");

        String expresult = "Bayer";
        String result = vac.getName();

        assertEquals(expresult, result);
    }

    @Test
    void setName() {
        Vaccine vac = new Vaccine("963", "Bayer");
        vac.setName("Domerna");

        String expresult = "Domerna";
        String result = vac.getName();

        assertEquals(expresult, result);
    }

    @Test
    void testHashCode() {
        Vaccine vac = new Vaccine("963", "Bayer");

        assertEquals(vac.hashCode(),56663);
    }

    @Test
    void testEquals() {
        Vaccine vac1 = new Vaccine("963", "Bayer");
        Vaccine vac2 = new Vaccine("963", "Bayer");
        Vaccine vac3 = new Vaccine("147", "Forex");

        assertEquals(vac1.equals(vac2),true);
        assertEquals(vac1.equals(vac3),false);
    }

    @Test
    void testsetListAdministrationProcess() {
        Vaccine vac1 = new Vaccine("963", "Bayer");

        List<AdministrationProcess> listAdminProc = new ArrayList<>();
        Integer[] arrAge = new Integer[2];
        Integer[] arrInterval = new Integer[4];
        List<Integer[]> ageGroups = new ArrayList<>();
        List<Integer[]> listTimeInterval = new ArrayList<>();
        arrAge[0] = 18;
        arrAge[1] = 30;
        arrInterval[0] = 1;
        arrInterval[1] = 28;
        arrInterval[2] = 2;
        arrInterval[3] = 28;
        ageGroups.add(arrAge);
        listTimeInterval.add(arrInterval);
        AdministrationProcess adp = new AdministrationProcess(ageGroups, 0.3,2, listTimeInterval);

        listAdminProc.add(adp);
        vac1.setListAdministrationProcess(listAdminProc);
        List<AdministrationProcess> result = vac1.getListAdministrationProcess();
        List<AdministrationProcess> expresult = listAdminProc;

        assertEquals(expresult,result);
    }

    @Test
    void testToString() {
        Vaccine vac1 = new Vaccine("963", "Bayer");
        List<AdministrationProcess> listAdminProc = new ArrayList<>();
        Integer[] arrAge = new Integer[2];
        Integer[] arrInterval = new Integer[4];
        List<Integer[]> ageGroups = new ArrayList<>();
        List<Integer[]> listTimeInterval = new ArrayList<>();
        arrAge[0] = 18;
        arrAge[1] = 30;
        arrInterval[0] = 1;
        arrInterval[1] = 28;
        arrInterval[2] = 2;
        arrInterval[3] = 28;
        ageGroups.add(arrAge);
        listTimeInterval.add(arrInterval);
        AdministrationProcess adp = new AdministrationProcess(ageGroups, 0.3,2, listTimeInterval);
        listAdminProc.add(adp);
        vac1.setListAdministrationProcess(listAdminProc);


        assertFalse(vac1.toString().contains("Vaccine: id: 963,\nname: Bayer,\nAdministration processes: \n" +
                        "[Age Groups: 18-30, \n" +
                        "Reference Dose: 0.3,\n" +
                        "Doses to be Administered: 2,\n" +
                        "Time Interval:Dose: 1, Time Interval: 28 days\n]"));
    }


    @Test
    void testEquals1() {
        Vaccine vtx = new Vaccine("1212","desi");
        VaccineType vty = null;

        assertEquals(vtx.equals(vty),false);
    }

    @Test
    void testvalidateAdministrationProcessFalse() {
        Vaccine vac1 = new Vaccine("963", "Bayer");
        List<AdministrationProcess> listAdminProc = new ArrayList<>();
        Integer[] arrAge = new Integer[2];
        Integer[] arrInterval = new Integer[4];
        List<Integer[]> ageGroups = new ArrayList<>();
        List<Integer[]> listTimeInterval = new ArrayList<>();
        arrAge[0] = 18;
        arrAge[1] = 30;
        arrInterval[0] = 1;
        arrInterval[1] = 28;
        arrInterval[2] = 2;
        arrInterval[3] = 28;
        ageGroups.add(arrAge);
        listTimeInterval.add(arrInterval);
        AdministrationProcess adp = new AdministrationProcess(ageGroups, 0.3,2, listTimeInterval);
        listAdminProc.add(adp);
        vac1.setListAdministrationProcess(listAdminProc);
        AdministrationProcess adpr = new AdministrationProcess(ageGroups, 0.3,2, listTimeInterval);
        listAdminProc.add(adpr);
        assertFalse(vac1.validateAdministrationProcess(adpr));
    }

}