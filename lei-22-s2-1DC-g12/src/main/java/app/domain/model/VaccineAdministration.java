package app.domain.model;


import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Vaccine administration.
 */
public class VaccineAdministration implements Serializable {

    /**
     * The Sns user.
     */
    SNSUser snsUser;

    /**
     * The Vaccine type.
     */
    VaccineType vaccineType;

    /**
     * The Vaccine.
     */
    Vaccine vaccine;

    /**
     * The Lot number.
     */
    String lotNumber;

    /**
     * The Date.
     */
    Date date;

    /**
     * Instantiates a new Vaccine administration.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param vaccine     the vaccine
     * @param lotNumber   the lot number
     */
    public VaccineAdministration(SNSUser snsUser, VaccineType vaccineType ,Vaccine vaccine, String lotNumber) {
        this.snsUser = snsUser;
        this.vaccineType = vaccineType;
        this.vaccine = vaccine;
        this.lotNumber = lotNumber;
        Calendar cal = Calendar.getInstance();
        date = cal.getTime();
    }

    /**
     * Instantiates a new Vaccine administration.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param vaccine     the vaccine
     * @param lotNumber   the lot number
     * @param date the date
     */
    public VaccineAdministration(SNSUser snsUser, VaccineType vaccineType ,Vaccine vaccine, String lotNumber,Date date) {
        this.snsUser = snsUser;
        this.vaccineType = vaccineType;
        this.vaccine = vaccine;
        this.lotNumber = lotNumber;
        this.date = date;
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
     * Get vaccine.
     *
     * @return the vaccine
     */
    public Vaccine getVaccine(){
        return vaccine;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get lot number string.
     *
     * @return the string
     */
    public String getLotNumber(){return lotNumber;}


    @Override
    public String toString() {
        return "VaccineAdministration{" +
                "snsUser=" + snsUser +
                ", vaccineType=" + vaccineType +
                ", vaccine=" + vaccine +
                ", lotNumber='" + lotNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
