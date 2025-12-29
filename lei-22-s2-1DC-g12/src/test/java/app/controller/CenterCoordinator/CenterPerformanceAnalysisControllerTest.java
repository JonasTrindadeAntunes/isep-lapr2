package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.CenterPerformanceAnalysis;
import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CenterPerformanceAnalysisControllerTest {

    App m_oApp = App.getInstance();
    CenterPerformanceAnalysisController controller = new CenterPerformanceAnalysisController();

    @Test
    void checkLogin() {
        m_oApp.doLogin("centercoordinator@lei.sem2.pt","123456");
        Assertions.assertEquals(true,controller.checkLogin());
        m_oApp.doLogout();
    }

    @Test
    void getVaccinationCenter() {
        m_oApp.doLogin("centercoordinator@lei.sem2.pt","123456");
        Employee emp = m_oApp.getCompany().getPlatform().getRegisterEmployee().newEmployee("quim","rua","931093109","centercoordinator@lei.sem2.pt","15902210",1);
        m_oApp.getCompany().getPlatform().getRegisterEmployee().registerEmployee(emp);
        VaccinationCenter vac = m_oApp.getCompany().getPlatform().getRegisterVaccinationCenter().getVaccinationCenter("vaccinationcenterLAPR2@gmail.com");
        vac.setManageVaccinationCenter((CenterCoordinator) emp);
        emp.setWorkingVaccinationCenter(vac);

        controller.getVaccinationCenter();
        Assertions.assertEquals(true,controller.getVaccinationCenter());
        m_oApp.doLogout();
    }

   /* @Test
    void getVaccinationCenter2() {
        CenterPerformanceAnalysisController cPAC = new CenterPerformanceAnalysisController();
        VaccinationCenter vc = new CommunityMassVaccinationCenter("s joao", "RuaR","916587487","1211690@isep.ipp.pt","67890","www.isep.ipp.pt",8,00,20,00,5,10);
        CenterCoordinator cc = new CenterCoordinator("jj","Berlengas","919902332","jorgejesus@lei.sem2.pt","12740660");
        cc.setWorkingVaccinationCenter(vc);


        assertTrue(true);
    }*/

    @Test
    void getListPerformance() {
        /*CenterPerformanceAnalysisController cPAC = new CenterPerformanceAnalysisController();
        Date date = new Date();


        cPAC.getListPerformance(date.getTime(),30);*/

    }

    @Test
    void processAllDataBeforeShowing() {
    }

    @Test
    void complexityAnalysis() {
    }
}