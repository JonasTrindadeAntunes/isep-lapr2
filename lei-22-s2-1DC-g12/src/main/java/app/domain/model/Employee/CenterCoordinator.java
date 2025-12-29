package app.domain.model.Employee;

import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.io.Serializable;

/**
 * The type Center coordinator.
 */
public class CenterCoordinator extends Employee implements Serializable {


    private VaccinationCenter vaccinationCenter;

    /**
     * Instantiates a new Center coordinator.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param citizenCardNumber the citizen card number
     */
    public CenterCoordinator (String name, String address, String phoneNumber, String emailAddress, String citizenCardNumber){
        super(name,address,phoneNumber,emailAddress,citizenCardNumber);
    }



}
