package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.ExportVaccinationStatistics;
import app.domain.model.Platform.Platform;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.CommunityMassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExportVaccinationStatisticsControllerTest {

    App m_oApp = App.getInstance();
    ExportVaccinationStatisticsController controller = new ExportVaccinationStatisticsController();


    @Test
    void testCheckLogin() {
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

    @Test
    void addFullyVaccinatedUsers() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = "10-06-2022";
        String strDate1 = "30-07-2022";

        try {
            Date date = df.parse(strDate);
            Date date1 = df.parse(strDate1);

            m_oApp.doLogin("centercoordinator@lei.sem2.pt","123456");
            Employee emp = m_oApp.getCompany().getPlatform().getRegisterEmployee().newEmployee("quim","rua","931093109","centercoordinator@lei.sem2.pt","15902210",1);
            m_oApp.getCompany().getPlatform().getRegisterEmployee().registerEmployee(emp);
            VaccinationCenter vac = m_oApp.getCompany().getPlatform().getRegisterVaccinationCenter().getVaccinationCenter("vaccinationcenterLAPR2@gmail.com");
            vac.setManageVaccinationCenter((CenterCoordinator) emp);
            emp.setWorkingVaccinationCenter(vac);

            controller.getVaccinationCenter();
            controller.addFullyVaccinatedUsers(date,date1);
            Assertions.assertEquals(true,controller.addFullyVaccinatedUsers(date,date1));
            m_oApp.doLogout();

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    void getData() {
    }

    @Test
    void testExportVaccinationStatistics() {
        ExportVaccinationStatisticsController evsc = new ExportVaccinationStatisticsController();
        boolean result = true;

        assertEquals(result,evsc.exportVaccinationStatistics("VaccinationStatistics.csv"));
    }
}