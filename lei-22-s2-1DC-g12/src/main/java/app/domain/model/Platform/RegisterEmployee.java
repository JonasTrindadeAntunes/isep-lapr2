package app.domain.model.Platform;

import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.Employee.Employee;
import app.domain.model.Employee.Nurse;
import app.domain.model.Employee.Receptionist;
import app.domain.model.PasswordGenerator;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Register employee.
 */
public class RegisterEmployee implements Serializable, PasswordGenerator {

    private final List<Employee> listEmployee ;
    private RegisterUsers registerUsers;

    /**
     * Instantiates a new Register employee.
     *
     * @param registerUsers the user register
     */
    public RegisterEmployee(RegisterUsers registerUsers)
    {
        listEmployee = new ArrayList<>();
        this.registerUsers = registerUsers;
    }



    /**
     * New employee .
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     * @param roleChoice        the role choice
     * @return the employee
     */
    public Employee newEmployee(String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber, int roleChoice)
    {

        if(roleChoice == 0)
            return new Receptionist(name, address, phoneNumber, emailAddress, citizenCardNumber);
        else if(roleChoice == 1)
            return new CenterCoordinator(name, address, phoneNumber, emailAddress, citizenCardNumber);
        else if(roleChoice == 2)
            return new Nurse(name, address, phoneNumber, emailAddress, citizenCardNumber);

        return null;
    }

    /**
     * Register employee boolean.
     *
     * @param emp the emp
     * @return the boolean
     */
    public boolean registerEmployee(Employee emp)
    {

        String pwd = passwordGenerator(emp.getEmailAddress());

        if(emp instanceof Receptionist)
        {
            if (registerUsers.addUser(emp.getName(), emp.getEmailAddress(),pwd, Constants.ROLE_RECEPTIONIST))
                return addEmployee(emp);

        }else if(emp instanceof Nurse)
        {
            if (registerUsers.addUser(emp.getName(), emp.getEmailAddress(),pwd,Constants.ROLE_NURSE))
                return addEmployee(emp);

        }else if(emp instanceof CenterCoordinator)
        {
            if (registerUsers.addUser(emp.getName(), emp.getEmailAddress(),pwd, Constants.ROLE_CENTER_COORDINATOR))
                return addEmployee(emp);

        }

        return false;
    }
    /**
     * Add employee boolean.
     *
     * @param emp the employee
     * @return the boolean
     */
    private boolean addEmployee(Employee emp){return this.listEmployee.add(emp);}

    /**
     * Validate employee boolean.
     *
     * @param employee the employee
     * @return the boolean
     */
    public boolean validateEmployee(Employee employee) {



        if(registerUsers.getAuthFacade().existsUser(employee.getEmailAddress()))
        {
            System.out.println(MessageBundle.getString("emailaddressexists"));
            return false;
        }
        else {
            for(Employee emp : listEmployee)
            {
                if(emp.equals(employee))
                    return false;

            }
            return true;
        }

    }

    /**
     * Generates password String.
     *
     * @return the String
     */
    @Override
    public String passwordGenerator(String email){

        //Nested class declared within interface body. And this nested class implements outer interface.
        return new PasswordGenerator.PasswordGeneratorRandomly().passwordGenerator(email);

    }


    /**
     * Get roles list.
     *
     * @return the list
     */
    public List<String> getRoles(){

        List<UserRoleDTO> lRolesDTO;
        List<String> listOfRoles = new ArrayList<>();
        lRolesDTO = registerUsers.getAuthFacade().getUserRoles();

        for (int i =0; i < lRolesDTO.size() ; i++) {
            if( lRolesDTO.get(i).getDescription().contains(Constants.ROLE_RECEPTIONIST)
                    ||lRolesDTO.get(i).getDescription().contains(Constants.ROLE_CENTER_COORDINATOR)
                        ||lRolesDTO.get(i).getDescription().contains(Constants.ROLE_NURSE))

                        listOfRoles.add(lRolesDTO.get(i).getDescription());
        }

        return listOfRoles;
    }


    /**
     * Gets list employee by role.
     *
     * @param roleChoice the role choice
     * @return the list employee by role
     */
    public List<Employee> getListEmployeeByRole( int roleChoice)
    {

        List<Employee> listEmployeeTemp = new ArrayList<>();



        for (Employee emp : listEmployee)
        {
            if (roleChoice == 0 && emp instanceof Receptionist)
            {
                listEmployeeTemp.add(emp);
            }
            else if (roleChoice == 1 && emp instanceof CenterCoordinator)
            {
                listEmployeeTemp.add(emp);
            }
            else if (roleChoice == 2 && emp instanceof Nurse)
            {
                listEmployeeTemp.add(emp);
            }



        }
        return listEmployeeTemp;
    }


    /**
     * Gets employee by email.
     *
     * @param email the email
     * @return the employee by email
     */
    public Employee getEmployeeByEmail(String email)
    {
        for(Employee emp : listEmployee)
            if(emp.getEmailAddress().compareTo(email) == 0)
                return emp;

        return null;
    }

    /**
     * Gets working vaccination center.
     *
     * @param email the email
     * @return the working vaccination center
     */
    public VaccinationCenter getWorkingVaccinationCenter(String email)
    {
        for(Employee emp : listEmployee)
            if(emp.getEmailAddress().compareTo(email) == 0)
                return emp.getWorkingVaccinationCenter();

        return null;
    }



}