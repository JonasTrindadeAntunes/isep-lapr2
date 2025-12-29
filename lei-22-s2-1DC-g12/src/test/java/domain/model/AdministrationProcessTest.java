package domain.model;

import app.domain.model.Vaccine.AdministrationProcess;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministrationProcessTest {

    @Test
    void testToString() {
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

        assertFalse(adp.toString().contains("Age Groups: 18-30, \n" +
                "Reference Dose: 0.3,\n" +
                "Doses to be Administered: 2,\n" +
                "Time Interval:Dose: 1, Time Interval: 28 days"));
    }

    @Test
    void administratitionProcessConstructor() {
        Integer[] arrAge = new Integer[2];
        List<Integer[]> ageGroups = new ArrayList<>();
        arrAge[0] = 18;
        arrAge[1] = 30;

        ageGroups.add(arrAge);

        AdministrationProcess adp = new AdministrationProcess(ageGroups, 0.3,1);


        assertEquals(adp.getClass().getName(),"app.domain.model.Vaccine.AdministrationProcess");
    }
}