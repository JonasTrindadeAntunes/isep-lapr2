package app.controller;

import app.controller.Admin.SpecifyNewVaccineTypeController;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecifyNewVaccineTypeControllerTest {

    @Test
    void newVaccineType() {
        SpecifyNewVaccineTypeController snvtc = new SpecifyNewVaccineTypeController();

        boolean expResult = true;
        boolean result = snvtc.newVaccineType("1111","pokemon","Viral vector vaccines");

        assertEquals(expResult,result);
    }

    @Test
    void registerVaccineType() {
        SpecifyNewVaccineTypeController snvct = new SpecifyNewVaccineTypeController();
        snvct.newVaccineType("2122","digimon","Inactivated vaccines");

        boolean expResult = true;
        boolean result = snvct.registerVaccineType();

        assertEquals(expResult,result);
    }

    @Test
    void getVaccineTypeString() {
        SpecifyNewVaccineTypeController snvct = new SpecifyNewVaccineTypeController();

        VaccineType vacy = new VaccineType("2111","qwerty","Live-attenuated vaccines");

        snvct.newVaccineType("2111","qwerty","Live-attenuated vaccines");
        snvct.registerVaccineType();
        snvct.getVaccineTypeString();

        String expResult = vacy.toString();
        String result = snvct.getVaccineTypeString();

        assertEquals(expResult,result);
    }

    @Test
    void getListVaccineType() {
        SpecifyNewVaccineTypeController snvtc = new SpecifyNewVaccineTypeController();
        VaccineType vacy = new VaccineType("2111","fisher","Subunit vaccines ");

        snvtc.newVaccineType("2111","fisher","Subunit vaccines ");
        snvtc.registerVaccineType();

        SpecifyNewVaccineTypeController l = new SpecifyNewVaccineTypeController();

        List<VaccineType> vtx = l.getListVaccineType();
        List<VaccineType> vt = new ArrayList<>();

        vt.add(vacy);

        assertEquals(vt.get(0).toString().contains(vtx.get(0).toString()),false);

    }
    //Não está completo, em desenvolvimento
    @Test
    void getTypesOfVaccine() {
        SpecifyNewVaccineTypeController snvtc = new SpecifyNewVaccineTypeController();
        VaccineType vacy = new VaccineType("2111","fisher","Subunit vaccines");
        for (int i = 0; i < 6; i++) {
            snvtc.getTypesOfVaccine().get(i);
        }
    }

    @Test
    public void ensureNullIsNotAllowed() throws RuntimeException{

        SpecifyNewVaccineTypeController rec = new SpecifyNewVaccineTypeController();
        rec.newVaccineType("j","j","Toxoid vaccines");


    }
}