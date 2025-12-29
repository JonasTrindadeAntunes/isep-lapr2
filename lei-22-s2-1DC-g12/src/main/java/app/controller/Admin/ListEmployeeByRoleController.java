package app.controller.Admin;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Employee.Employee;
import app.domain.model.Platform.Platform;
import java.util.List;

/**
 * The type List employee by role controller.
 */
public class ListEmployeeByRoleController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;
    /**
     * The Company
     */
    private Company m_oCompany;

    /**
     * Instantiates a new List employee by role controller.
     */
    public ListEmployeeByRoleController() {
        this.m_oApp = App.getInstance();
        this.m_oCompany = m_oApp.getCompany();
        this.m_oPlatform = m_oCompany.getPlatform();

    }

    /**
     * Get list of roles.
     *
     * @return the list
     */
    public List<String> getRoles(){return this.m_oPlatform.getRegisterEmployee().getRoles();}

    /**
     * Get list employee by role.
     *
     * @param roleChoice the role choice
     * @return the list
     */
    public List<Employee> getListEmployeeByRole(int roleChoice){return this.m_oPlatform.getRegisterEmployee().getListEmployeeByRole(roleChoice);}
}
