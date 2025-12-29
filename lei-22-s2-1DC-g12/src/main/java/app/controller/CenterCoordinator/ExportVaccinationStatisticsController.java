package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.Employee.Employee;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.Constants;

import java.util.Date;
import java.util.List;


/**
 * The type Export vaccination statistics controller.
 */
public class ExportVaccinationStatisticsController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform.
     */
    private Platform m_oPlatform;

    /**
     * The VaccinationCenter.
     */
    private VaccinationCenter vaccinationCenter;


    /**
     * Instantiates a new Export vaccination statistics controller.
     */
    public ExportVaccinationStatisticsController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();
    }

    /**
     * Check login .
     *
     * @return the boolean
     */
    public boolean checkLogin() {
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CENTER_COORDINATOR);
    }

    /**
     * Gets vaccination center.
     *
     * @return the boolean
     */
    public boolean getVaccinationCenter()
    {
        try{
            Employee emp =  this.m_oPlatform.getRegisterEmployee().getEmployeeByEmail(m_oApp.getCurrentUserSession().getUserId().getEmail());
            this.vaccinationCenter = emp.getWorkingVaccinationCenter();

        }catch (NullPointerException ex)
        {
            System.out.println("null vaccination center or null employee");
            return false;
        }

        return true;
    }


    /**
     * Add fully vaccinated users .
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the boolean
     */
    public boolean addFullyVaccinatedUsers(Date beginDate, Date endDate)
    {

        try {
            this.m_oPlatform.getExportVaccinationStatistics().addFullyVaccinatedSNSUsers(this.vaccinationCenter,beginDate,endDate);
        }catch (NullPointerException ex)
        {
            System.out.println(ex.getMessage());
        }

        return true;
    }


    /**
     * Gets data.
     *
     * @return the data
     */
    public List<String> getData()
    {
         return this.m_oPlatform.getExportVaccinationStatistics().getData();
    }


    /**
     * Export vaccination statistics .
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean exportVaccinationStatistics(String fileName)
    {
        return this.m_oPlatform.getExportVaccinationStatistics().exportVaccinationStatistics(fileName);
    }

}
