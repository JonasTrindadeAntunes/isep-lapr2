package app.domain.model.Platform;

import app.domain.model.PasswordGenerator;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Register sns user.
 */
public class RegisterSNSUser implements Serializable, PasswordGenerator {


    private List<SNSUser> listSNSUser;
    private RegisterUsers registerUsers;

    /**
     * Instantiates a new Register sns user.
     *
     * @param registerUsers the user register
     */
    public RegisterSNSUser(RegisterUsers registerUsers) {
        listSNSUser = new ArrayList<>();
        this.registerUsers = registerUsers;
    }

    private boolean validateSNSUser(String email, String phoneNumber, String snsUserNumber, String citizenCardNumber){
        SNSUser user = new SNSUser(email,phoneNumber,snsUserNumber,citizenCardNumber);

        if(existsSNSUser(user)){
            throw new IllegalArgumentException(String.format(MessageBundle.getString("userwithsameemailphonesnsusercitizencard"),
                            email,phoneNumber,snsUserNumber,citizenCardNumber));
        }



        registerUsers.authFacade.existsUser(email);


        return true;
    }

    private boolean existsSNSUser(SNSUser user) {
        for (SNSUser u : this.listSNSUser){
            if(u.equals(user)){
                return true;
            }
        }
        return false;
    }

    /**
     * New sns user.
     *
     * @param name              the name
     * @param address           the address
     * @param gender            the gender
     * @param phoneNumber       the phone number
     * @param email             the email
     * @param date              the date
     * @param snsUserNumber     the sns user number
     * @param citizenCardNumber the citizen card number
     * @return the sns user
     */
    public SNSUser newSNSUser(String name, String address, String gender, String phoneNumber, String email, Date date, String snsUserNumber, String citizenCardNumber) {
        try {
            if (!validateSNSUser(email, phoneNumber, snsUserNumber, citizenCardNumber))
                return null;


            return new SNSUser(name, address, gender, phoneNumber, email, date, snsUserNumber, citizenCardNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add sns user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addSNSUser(SNSUser user){
        this.listSNSUser.add(user);
        return registerUsers.addUser(user.getName(), user.getEmail(), this.passwordGenerator(user.getEmail()) , Constants.ROLE_SNS_USER);
    }

    /**
     * Get sns user by email.
     *
     * @param email the email
     * @return the sns user
     */
    public SNSUser getSNSUserByEmail(String email){
        for (SNSUser user : listSNSUser){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    /**
     * Get sns user by phone number.
     *
     * @param phoneNumber the phone number
     * @return the sns user
     */
    public SNSUser getSNSUserByPhoneNumber(String phoneNumber){
        for (SNSUser user : listSNSUser){
            if(user.getPhoneNumber().equals(phoneNumber)){
                return user;
            }
        }
        return null;
    }

    /**
     * Get sns user by sns user number.
     *
     * @param snsUserNumber the sns user number
     * @return the sns user
     */
    public SNSUser getSNSUserBySNSUserNumber(String snsUserNumber){
        for (SNSUser user : listSNSUser){
            if(user.getSnsUserNumber().equals(snsUserNumber)){
                return user;
            }
        }
        return null;
    }

    /**
     * Get sns user by citizen card number.
     *
     * @param citizenCardNumber the citizen card number
     * @return the sns user
     */
    public SNSUser getSNSUserByCitizenCardNumber(String citizenCardNumber){
        for (SNSUser user : listSNSUser){
            if(user.getCitizenCardNumber().equals(citizenCardNumber)){
                return user;
            }
        }
        return null;
    }
    /**
     * Returns the password
     */
    @Override
    public String passwordGenerator(String email) {
        return new PasswordGenerator.PasswordGeneratorRandomly().passwordGenerator(email);

    }

    public List<SNSUser> getListSNSUser()
    {
        return this.listSNSUser;
    }



}


