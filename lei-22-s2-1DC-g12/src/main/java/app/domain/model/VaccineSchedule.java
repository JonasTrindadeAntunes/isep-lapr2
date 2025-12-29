package app.domain.model;

import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Vaccine schedule.
 */
public class VaccineSchedule implements Serializable {

    private Date vaccinationDate;

    private SNSUser snsUser;

    private VaccineType vaccineType;

    private boolean validateEntrance;


    /**
     * Instantiates a new Vaccine schedule.
     *
     * @param vaccinationDate the vaccination date
     * @param vaccineType     the vaccine type
     * @param snsUser         the sns user
     */
    public VaccineSchedule(Date vaccinationDate,  VaccineType vaccineType,SNSUser snsUser) {
        validateVaccineSchedule(vaccinationDate,vaccineType,snsUser);
        this.vaccinationDate = vaccinationDate;
        this.vaccineType = vaccineType;
        this.snsUser = snsUser;
        this.validateEntrance = false;
    }

    /**
     * Used to import data from legacy systems, skips validations
     * @param vaccineType the vaccine type
     * @param snsUser the sns user
     * @param vaccinationDate the scheduled vaccination date
     */
    public VaccineSchedule(VaccineType vaccineType,SNSUser snsUser,Date vaccinationDate){
        this.vaccinationDate = vaccinationDate;
        this.vaccineType = vaccineType;
        this.snsUser = snsUser;
        this.validateEntrance = false;
    }

    private boolean validateVaccineSchedule(Date vaccinationDate, VaccineType vaccineType,SNSUser snsUser){

        if(vaccinationDate == null || vaccineType == null || snsUser == null){
            throw new IllegalArgumentException(MessageBundle.getString("noargumentcanbenull"));
        }

        for (VaccineSchedule vs : snsUser.getScheduledVaccinations()){
            if(vs.getVaccineType().equals(vaccineType) && !vs.isValidateEntrance()){ //if there is a vaccine of this type already scheduled
                throw new IllegalArgumentException(MessageBundle.getString("snsuseralreadyhasavaccineofthistypescheduled"));
            }
        }

        if(vaccinationDate.before(new Date(System.currentTimeMillis()))){
            throw new IllegalArgumentException(MessageBundle.getString("vaccineschedulecantbeinpast"));
        }
        return true;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return vaccinationDate;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        vaccinationDate = date;
    }

    /**
     * Gets sns user.
     *
     * @return the sns user
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * Is validate entrance boolean.
     *
     * @return the boolean
     */
    public boolean isValidateEntrance() {
        return validateEntrance;
    }

    /**
     * Sets validate entrance.
     *
     * @param validateEntrance the validate entrance
     */
    public void setValidateEntrance(boolean validateEntrance) {
        this.validateEntrance = validateEntrance;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;

        if (o == null ) {
            return false;
        }

        VaccineSchedule vaccineSchedule = (VaccineSchedule) o;
        if(this.vaccinationDate.compareTo(vaccineSchedule.vaccinationDate) == 0
                && this.snsUser.getSnsUserNumber().compareToIgnoreCase(vaccineSchedule.snsUser.getSnsUserNumber()) == 0 )
        {
            System.out.println(MessageBundle.getString("vaccineschedulewiththesamealreadyexists"));
            return true;
        }


        return false;

    }

    /**
     * Gets vaccine type.
     *
     * @return the vaccine type
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }


    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return MessageBundle.getString("vaccineschedule") + "{" +
                MessageBundle.getString("vaccinationdate") + "=" + vaccinationDate +
                ", " + MessageBundle.getString("snsusernumber") + "='" + snsUser.getSnsUserNumber() + '\'' +
                ", " + MessageBundle.getString("validateentrance") + "=" + validateEntrance +
                '}';
    }
}
