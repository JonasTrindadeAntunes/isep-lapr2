package domain.model;

import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.RegisterVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterVaccinationCenterTest {

    @Test
    void validateVaccinationCenter() {
        RegisterVaccinationCenter rvc = new RegisterVaccinationCenter("5");
        VaccinationCenter vc = new HealthCareCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",10,00,20,00,5,10);
        boolean expresult = true;
        boolean result =  rvc.validateVaccinationCenter(vc);

        assertEquals(expresult,result);
    }

    @Test
    void validateVaccinationCenter1() {
        RegisterVaccinationCenter rvc = new RegisterVaccinationCenter("5");
        VaccinationCenter vc = new HealthCareCenter("m","m","914567823","1181478@isep.ipp.pt","12345","www.www.www",10,00,20,00,5,12);
        vc.setEmailAddress(null);

        boolean expresult = false;
        boolean result =  rvc.validateVaccinationCenter(vc);

        assertEquals(expresult,result);
    }

    @Test
    void validateVaccinationCenter2() {
        RegisterVaccinationCenter rvc = new RegisterVaccinationCenter("5");
        VaccinationCenter vc = new HealthCareCenter("m","m","914567823","1181478@isep.ipp.pt","12345","www.www.www",10,0,20,0,5,12);
        vc.setEmailAddress(null);

        boolean expresult = false;
        boolean result =  rvc.registerVaccinationCenter(vc);

        assertEquals(expresult,result);
    }


}