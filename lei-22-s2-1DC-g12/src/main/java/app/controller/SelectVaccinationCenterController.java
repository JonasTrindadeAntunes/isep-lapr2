package app.controller;


import app.domain.model.Employee.Employee;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.VaccinationCenter;


import java.util.List;

/**
 * The type Select vaccination center controller.
 */
public class SelectVaccinationCenterController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;

    /**
     * Instantiates a new Select Vaccination Center Controller
     */
    public SelectVaccinationCenterController() {
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }


    /**
     * Get list vaccination center list.
     *
     * @return the list
     */
    public List<VaccinationCenter> getListVaccinationCenter(){
        return this.m_oPlatform.getRegisterVaccinationCenter().getListVaccinationCenters();
    }

    /**
     * Sets working vaccination center.
     *
     * @param vaccinationCenter the vaccination center
     */
    public void setWorkingVaccinationCenter(VaccinationCenter vaccinationCenter)
    {
        try {
            Employee emp = m_oPlatform.getRegisterEmployee().getEmployeeByEmail(m_oApp.getCurrentUserSession().getUserId().getEmail());
            emp.setWorkingVaccinationCenter(vaccinationCenter);
        }catch (NullPointerException ex)
        {
            System.out.println("null employee");
        }
    }

}
