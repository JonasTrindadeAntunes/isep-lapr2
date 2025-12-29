package app.domain.model;

import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Sns user.
 */
public class SNSUser implements Serializable {
    private String name;

    private String address;

    private String gender;

    private String phoneNumber;

    private String email;

    private Date birthDate;

    private String snsUserNumber;

    private String citizenCardNumber;

    private List<VaccineSchedule> scheduledVaccinations;

    private List<AdverseReaction> adverseReactions;
    private List<VaccineAdministration> vaccineAdministrations;

    /**
     * Used to validate the unique fields for a sns user.
     *
     * @param email             the user's email.
     * @param phoneNumber       the user's phone number.
     * @param snsUserNumber     the user's sns user number.
     * @param citizenCardNumber the user's citizen card number.
     */
    public SNSUser(String email, String phoneNumber, String snsUserNumber, String citizenCardNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
    }



    /**
     * The enum Gender.
     */
    public enum Gender {
        /**
         * Male gender.
         */
        Male("Male"),
        /**
         * Female gender.
         */
        Female("Female"),
        /**
         * Other gender.
         */
        Other("Other");

        private final String label;

        private Gender(String label){
            this.label = label;
        }

        public String toString(){
            return this.label;
        }
    }


    /**
     * Instantiates a new Sns user.
     *
     * @param name              the name
     * @param address           the address
     * @param gender            the gender
     * @param phoneNumber       the phone number
     * @param email             the email
     * @param birthDate         the birth date
     * @param snsUserNumber     the sns user number
     * @param citizenCardNumber the citizen card number
     */
    public SNSUser(String name, String address, String gender, String phoneNumber, String email, Date birthDate, String snsUserNumber, String citizenCardNumber) {
        validateUser(name,address,gender,phoneNumber,email,birthDate,snsUserNumber,citizenCardNumber);
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.scheduledVaccinations = new ArrayList<>();
        this.adverseReactions = new ArrayList<>();
        this.vaccineAdministrations = new ArrayList<>();
    }

    private boolean validateUser(String name, String address, String gender, String phoneNumber, String email, Date birthDate, String snsUserNumber, String citizenCardNumber){
        if(name == null || address == null || gender == null || phoneNumber == null || email == null || birthDate == null || snsUserNumber == null || citizenCardNumber == null){
            throw new IllegalArgumentException(MessageBundle.getString("noargumentcanbenull"));
        }
        if(name.isEmpty() || address.isEmpty() || gender.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || snsUserNumber.isEmpty() || citizenCardNumber.isEmpty()){
            String error = "";
            if(name.isEmpty())
                error += MessageBundle.getString("nameempty");
            if(address.isEmpty())
                error += MessageBundle.getString("addressempty");
            if(gender.isEmpty())
                error += MessageBundle.getString("genderempty");
            if(phoneNumber.isEmpty())
                error += MessageBundle.getString("phonenumberempty");
            if(email.isEmpty())
                error += MessageBundle.getString("emailempty");
            if(snsUserNumber.isEmpty())
                error += MessageBundle.getString("snsusernumberempty");
            if(citizenCardNumber.isEmpty())
                error += MessageBundle.getString("citizencardnumberempty");
            throw new IllegalArgumentException(error + MessageBundle.getString("noargumentcanbenull"));
        }
        if(phoneNumber.length() != 9){
            throw new IllegalArgumentException(MessageBundle.getString("phonenumberptformat"));
        }
        if(citizenCardNumber.length()!=8){
            throw new IllegalArgumentException(MessageBundle.getString("citizencardptformat"));
        }
        return true;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets sns user number.
     *
     * @return the sns user number
     */
    public String getSnsUserNumber() {
        return snsUserNumber;
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
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return MessageBundle.getString("snsuser") + "{" +
                MessageBundle.getString("name") + "='" + name + '\'' +
                ", " + MessageBundle.getString("address") + "='" + address + '\'' +
                ", " + MessageBundle.getString("gender") + "='" + gender + '\'' +
                ", " + MessageBundle.getString("phonenumber") + "='" + phoneNumber + '\'' +
                ", " + MessageBundle.getString("email") + "='" + email + '\'' +
                ", " + MessageBundle.getString("birthdate") + "=" + birthDate +
                ", " + MessageBundle.getString("snsusernumber") + "='" + snsUserNumber + '\'' +
                ", " + MessageBundle.getString("citizencardnumber") + "='" + citizenCardNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SNSUser snsUser = (SNSUser) o;
        if(getPhoneNumber().equals(snsUser.getPhoneNumber()))
            return true;
        if(getEmail().equals(snsUser.getEmail()))
            return true;
        if(getSnsUserNumber().equals(snsUser.getSnsUserNumber()))
            return true;
        return (getCitizenCardNumber().equals(snsUser.getCitizenCardNumber()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNumber(), getEmail(), getSnsUserNumber(), getCitizenCardNumber());
    }

    /**
     * Add vaccine schedule boolean.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean addVaccineSchedule(VaccineSchedule vaccineSchedule){
        return scheduledVaccinations.add(vaccineSchedule);
    }

    /**
     * Gets scheduled vaccinations.
     *
     * @return the scheduled vaccinations
     */
    public List<VaccineSchedule> getScheduledVaccinations() {
        return scheduledVaccinations;
    }


    public boolean registerAdverseReaction(AdverseReaction adverseReaction){
        return this.adverseReactions.add(adverseReaction);
    }

    public List<AdverseReaction> getAdverseReactions(){
        return this.adverseReactions;
    }

    public boolean registerVaccineAdministration(VaccineAdministration vaccineAdministration) {
        return this.vaccineAdministrations.add(vaccineAdministration);
    }

    public List<VaccineAdministration> getVaccineAdministrations() {
        return vaccineAdministrations;
    }
}
