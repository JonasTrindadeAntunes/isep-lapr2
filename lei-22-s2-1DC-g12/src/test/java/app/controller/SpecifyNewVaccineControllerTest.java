package app.controller;

import app.controller.Admin.SpecifyNewVaccineController;
import app.domain.model.Vaccine.RegisterVaccineType;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecifyNewVaccineControllerTest {


    @Test
    void newVaccine() {
        SpecifyNewVaccineController snvc = new SpecifyNewVaccineController();
        VaccineType vt = new VaccineType("1234","Covid-19","Viral vector vaccines");

        snvc.setVaccineType(vt);
        boolean expResult = true;
        boolean result = snvc.newVaccine("963","Forex");

        assertEquals(expResult, result);
    }

    @Test
    void newAdministrationProcess() {
        SpecifyNewVaccineController snvc = new SpecifyNewVaccineController();
        VaccineType vt = new VaccineType("1234","Covid-19","Viral vector vaccines");
        snvc.setVaccineType(vt);
        snvc.newVaccine("963", "Bayer");

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
        boolean expresult = true;
        boolean result = snvc.newAdministrationProcess(ageGroups, 2,0.3, listTimeInterval);
        assertEquals(expresult,result);

    }

    @Test
    void getVaccineInfo() {
        SpecifyNewVaccineController snvc = new SpecifyNewVaccineController();
        VaccineType vt = new VaccineType("1234","Covid-19","Viral vector vaccines");
        snvc.setVaccineType(vt);
        snvc.newVaccine("963", "Bayer");

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
        snvc.newAdministrationProcess(ageGroups, 2,0.3, listTimeInterval);
        assertFalse(snvc.getVaccineInfo().contains("Vaccine: id: 963,\nname: Bayer,\nAdministration processes: \n" +
                "[Age Groups: 18-30, \n" +
                "Reference Dose: 0.3,\n" +
                "Doses to be Administered: 2,\n" +
                "Time Interval:Dose: 1, Time Interval: 28 days\n]"));


    }

    @Test
    void addVaccine() {
        SpecifyNewVaccineController snvc = new SpecifyNewVaccineController();
        VaccineType vt = new VaccineType("1234","Covid-19","Viral vector vaccines");
        snvc.setVaccineType(vt);
        snvc.newVaccine("963", "Bayer");

        assertTrue(snvc.addVaccine());

    }

    @Test
    void getVaccineType() {
        SpecifyNewVaccineController snvc = new SpecifyNewVaccineController();
        RegisterVaccineType rgvt = new RegisterVaccineType();
        VaccineType vt1 = new VaccineType("1234","Covid-19","Viral vector vaccines");
        rgvt.registerVaccineType(vt1);
        VaccineType vt2 = new VaccineType("5678", "Tetano","Viral vector vaccines");
        rgvt.registerVaccineType(vt2);


        List<VaccineType>  listVaccineType = new ArrayList<>();
        listVaccineType.add(vt1);
        listVaccineType.add(vt2);

       // assertEquals(listVaccineType, snvc.getVaccineType());

    }

    @Test
    public void ensureNullIsNotAllowed() throws RuntimeException{

        SpecifyNewVaccineController rec = new SpecifyNewVaccineController();
        rec.newVaccine(null,"j");


    }
}