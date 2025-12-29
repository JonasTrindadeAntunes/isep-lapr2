package app.controller;

import app.domain.model.Employee.Employee;
import app.domain.model.Employee.Nurse;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectVaccinationCenterControllerTest {

    @Test
    void getVaccinationList() {
        SelectVaccinationCenterController svcc = new SelectVaccinationCenterController();
        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "porto", "916597487", "1181478@isep.ipp.pt", "2525", "www.sns.pt", 10, 00, 20, 00, 5, 10);

        assertTrue(svcc.getListVaccinationCenter().contains(vc));

    }


    @Test
    void chooseWorkingVaccinationCenter() {
        SelectVaccinationCenterController svcc = new SelectVaccinationCenterController();

        Employee emp = new Nurse("j", "j", "912222227", "1185678@isep.ipp.pt", "13000405");
        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "RuaX", "916597487", "1181378@isep.ipp.pt", "12345", "www.www.www", 10, 00, 20, 00, 5, 10);
        emp.setWorkingVaccinationCenter(vc);
        svcc.setWorkingVaccinationCenter(vc);

        assertTrue(emp.getWorkingVaccinationCenter().toString().contains(vc.toString()));
    }
}
