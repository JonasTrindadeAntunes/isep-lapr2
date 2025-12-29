package app.domain.model.Employee;

import java.io.Serializable;

/**
 * The type Nurse.
 */
public class Nurse extends Employee implements Serializable {

    /**
     * Instantiates a new Nurse.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     */
    public Nurse (String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber){
        super(name,address,phoneNumber,emailAddress,citizenCardNumber);
    }


}
