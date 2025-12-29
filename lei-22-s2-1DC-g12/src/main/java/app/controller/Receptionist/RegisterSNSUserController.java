package app.controller.Receptionist;

import app.controller.App;
import app.domain.model.Platform.Platform;
import app.domain.model.Platform.RegisterSNSUser;
import app.domain.model.SNSUser;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Register sns user controller.
 */
public class RegisterSNSUserController {

    /**
     * The App.
     */
    App app;
    /**
     * The User.
     */
    SNSUser user;

    /**
     * Instantiates a new Register sns user controller.
     */
    public RegisterSNSUserController(){
        app = App.getInstance();
    }

    /**
     * Check login boolean.
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return app.getCurrentUserSession().isLoggedInWithRole("Receptionist");
    }

    /**
     * New sns user boolean.
     *
     * @param name              the name
     * @param address           the address
     * @param gender            the gender
     * @param phoneNumber       the phone number
     * @param email             the email
     * @param date              the date
     * @param snsUserNumber     the sns user number
     * @param citizenCardNumber the citizen card number
     * @return the boolean
     */
    public boolean newSNSUser(String name, String address, String gender, String phoneNumber, String email, Date date, String snsUserNumber, String citizenCardNumber) {
        Platform plat = app.getCompany().getPlatform();
        RegisterSNSUser register = plat.getRegisterSNSUser();
        user = register.newSNSUser(name,address,gender,phoneNumber,email,date,snsUserNumber,citizenCardNumber);
        return user != null;
    }

    /**
     * Get sns user sns user.
     *
     * @return the sns user
     */
    public SNSUser getSNSUser(){
        return user;
    }

    /**
     * Add user boolean.
     *
     * @return the boolean
     */
    public boolean addUser() {
        Platform plat = app.getCompany().getPlatform();
        RegisterSNSUser register = plat.getRegisterSNSUser();
        if(user == null)
            return false;

        return register.addSNSUser(this.user);
    }

    /**
     * Get gender list .
     *
     * @return the list
     */
    public List<SNSUser.Gender> getGenderList(){
        SNSUser.Gender[] arr = SNSUser.Gender.values();
        return Arrays.asList(arr);
    }
}
