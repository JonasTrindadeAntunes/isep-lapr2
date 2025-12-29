package app.domain.model.VaccinationCenter;

import java.io.Serializable;

/**
 * The type Health care center.
 */
public class HealthCareCenter extends VaccinationCenter implements Serializable  {

    /**
     * Instantiates a new Health care center.
     *
     * @param name              the name
     * @param address           the address
     * @param phoneNumber       the phone number
     * @param emailAddress      the email address
     * @param faxNumber         the fax number
     * @param websiteAddress    the website address
     * @param openingHours      the opening hours
     * @param openingMinutes    the opening minutes
     * @param closingHours      the closing hours
     * @param closingMinutes    the closing minutes
     * @param slotDuration      the slot duration
     * @param maxNumberVaccines the max number vaccines
     */
    public HealthCareCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Integer openingHours,Integer openingMinutes, Integer closingHours,Integer closingMinutes,Integer slotDuration, Integer maxNumberVaccines) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHours, openingMinutes,closingHours,closingMinutes, slotDuration, maxNumberVaccines);
    }
}
