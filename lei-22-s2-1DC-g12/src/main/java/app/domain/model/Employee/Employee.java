package app.domain.model.Employee;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Employee.
 */
public abstract class Employee implements Serializable {

    private int id;
    private static int count_num_employees = 0;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String citizenCardNumber;
    private VaccinationCenter vaccinationCenter;


    /**
     * Instantiates a new Employee.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     */
    public Employee(String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber) {
        if((name == null) || (name.isEmpty()) || (address == null) || (address.isEmpty())
            || (phoneNumber == null) || (phoneNumber.isEmpty()) || (emailAddress == null) || (emailAddress.isEmpty())
            || (citizenCardNumber == null) || (citizenCardNumber.isEmpty()))
            throw new IllegalArgumentException(MessageBundle.getString("noargumentcanbenull"));

        if(citizenCardNumber.length()!=8 || !StringUtils.isNumeric(citizenCardNumber))
            throw new IllegalArgumentException(MessageBundle.getString("citizencardptformat"));

        if(phoneNumber.length() != 9 || !StringUtils.isNumeric(phoneNumber))
            throw new IllegalArgumentException(MessageBundle.getString("phonenumberptformat"));

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.citizenCardNumber = citizenCardNumber;

        this.id = count_num_employees;
        count_num_employees++;

    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets citizen card number.
     *
     * @return the citizen card number
     */
    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * Sets citizen card number.
     *
     * @param citizenCardNumber the citizen card number
     */
    public void setCitizenCardNumber(String citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getID()
    {
        return this.id;
    }

    /**
     * Gets total employees.
     *
     * @return the total employees
     */
    public static int getTotalEmployees()
    {
        return count_num_employees;
    }

    /**
     * Returns the hash code value of an object
     *
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.citizenCardNumber);
        return hash;
    }

    /**
     * Compares the current object with another object with current parameters
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null ) {
            return false;
        }

        Employee emp = (Employee) o;
        if(this.citizenCardNumber.compareToIgnoreCase(emp.getCitizenCardNumber()) == 0)
        {
            System.out.println(MessageBundle.getString("citizencardexists"));
            return true;
        }
        else if(this.getEmailAddress().compareToIgnoreCase(emp.getEmailAddress()) == 0)
        {
            {
                System.out.println(MessageBundle.getString("emailaddressexists"));
                return true;
            }
        }
        else if( this.phoneNumber.compareToIgnoreCase(emp.getPhoneNumber()) == 0)
        {
            {
                System.out.println(MessageBundle.getString("phonenumberexists"));
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the String of this current object
     */
    @Override
    public String toString() {
        return String.format(MessageBundle.getString("employee") + ": %s - %s - %s - %s - %s ", this.name, this.address, this.phoneNumber, this.emailAddress, this.citizenCardNumber);

    }


    /**
     * Sets working vaccination center.
     *
     * @param vaccinationCenter the vaccination center
     */
    public void setWorkingVaccinationCenter(VaccinationCenter vaccinationCenter)
    {
        this.vaccinationCenter = vaccinationCenter;
    }

    /**
     * Gets working vaccination center.
     *
     * @return the working vaccination center
     */
    public VaccinationCenter getWorkingVaccinationCenter()
    {
        return vaccinationCenter;
    }


}
