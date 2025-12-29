package app.domain.model.Employee;

import java.io.Serializable;

/**
 * The type Receptionist.
 */
public class Receptionist extends Employee implements Serializable {

    /**
     * Instantiates a new Receptionist.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     */
    public Receptionist (String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber){
        super(name,address,phoneNumber,emailAddress,citizenCardNumber);
    }


}
