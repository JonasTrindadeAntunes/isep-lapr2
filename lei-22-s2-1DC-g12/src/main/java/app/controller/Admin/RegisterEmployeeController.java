package app.controller.Admin;


import app.controller.App;
import app.domain.model.Employee.Employee;
import app.domain.model.Platform.Platform;

import java.util.List;

/**
 * The type Register employee controller.
 */
public class RegisterEmployeeController {

    /**
     * The App.
     */
    private App m_oApp;
    /**
     * The Platform
     */
    private Platform m_oPlatform;
    /**
     * The Employee
     */
    private Employee m_oEmployee;


    /**
     * Instantiates a new Register employee controller.
     */
    public RegisterEmployeeController() {
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Get roles list.
     *
     * @return the list
     */
    public List<String> getRoles(){return this.m_oPlatform.getRegisterEmployee().getRoles();}

    /**
     * New employee boolean.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     * @param roleChoice        the role choice
     * @return the boolean
     */
    public boolean newEmployee(String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber ,int roleChoice)
    {
        try
        {
            this.m_oEmployee = this.m_oPlatform.getRegisterEmployee().newEmployee(name, address, phoneNumber, emailAddress, citizenCardNumber,roleChoice);
            return this.m_oPlatform.getRegisterEmployee().validateEmployee(m_oEmployee);
        }
        catch(RuntimeException ex)
        {
            System.out.println(ex.getMessage());
            this.m_oEmployee = null;
            return false;
        }
    }

    /**
     * Register employee boolean.
     *
     * @return the boolean
     */
    public boolean registerEmployee(){return this.m_oPlatform.getRegisterEmployee().registerEmployee(m_oEmployee);}

    /**
     * Get employee string.
     *
     * @return the string
     */
    public String getEmployeeString(){return this.m_oEmployee.toString();}


}
