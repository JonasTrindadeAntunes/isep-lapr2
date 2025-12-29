package app.domain.model.VaccinationCenter;

import app.domain.shared.MessageBundle;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Register vaccination center.
 */
public class RegisterVaccinationCenter implements Serializable {

    int recoveryTime;

    public RegisterVaccinationCenter(String recoveryTime){
        this.recoveryTime = Integer.parseInt(recoveryTime);
    }

    private List<VaccinationCenter>listVaccinationCenters = new ArrayList<>();

    /**
     * New vaccination center.
     *
     * @param name                    the name
     * @param address                 the address
     * @param phoneNumber             the phone number
     * @param emailAddress            the email address
     * @param faxNumber               the fax number
     * @param websiteAddress          the website address
     * @param openingHours            the opening hours
     * @param openingMinutes          the opening minutes
     * @param closingHours            the closing hours
     * @param closingMinutes          the closing minutes
     * @param slotDuration            the slot duration
     * @param maxNumberVaccines       the max number vaccines
     * @param typeOfVaccinationCenter the type of vaccination center
     * @return the vaccination center
     */
    public VaccinationCenter newVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress, Integer openingHours,Integer openingMinutes, Integer closingHours,Integer closingMinutes, Integer slotDuration, Integer maxNumberVaccines,int typeOfVaccinationCenter)
    {
        if(typeOfVaccinationCenter == 0)
            return new CommunityMassVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,openingMinutes,closingHours,closingMinutes,slotDuration,maxNumberVaccines);
        else if(typeOfVaccinationCenter == 1)
            return new HealthCareCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,openingMinutes,closingHours,closingMinutes,slotDuration,maxNumberVaccines);;

        return null;
    }


    /**
     * Register vaccination center.
     *
     * @param center the center
     * @return the boolean
     */
    public boolean registerVaccinationCenter(VaccinationCenter center)
    {
        if(this.validateVaccinationCenter(center))
            return addVaccinationCenter(center);
        else
            return false;
    }

    private boolean addVaccinationCenter(VaccinationCenter center)
    {
        center.getRecoveryRoom().setRecoveryTime(recoveryTime);
        return this.listVaccinationCenters.add(center);
    }

    /**
     * Validate vaccination center boolean.
     *
     * @param center the center
     * @return the boolean
     */
    public boolean validateVaccinationCenter(VaccinationCenter center)
    {

        if(center.getEmailAddress() != null )
        {

            for (VaccinationCenter cent : listVaccinationCenters) {
                if (cent.equals(center))
                    return false;
            }
            return true;
        }
        else
            return false;

    }


    /**
     * Gets types of vaccination center.
     *
     * @return the types of vaccination center
     */
    public List<String> getTypesOfVaccinationCenter()
    {
        List<String> listOfVaccinationCenter = new ArrayList<>();
        listOfVaccinationCenter.add(MessageBundle.getString("communitymassvaccinationcenter"));
        listOfVaccinationCenter.add(MessageBundle.getString("healthcarecenter"));

        return listOfVaccinationCenter;
    }


    /**
     * Gets list vaccination centers.
     *
     * @return the list vaccination centers
     */
    public List<VaccinationCenter> getListVaccinationCenters(){
        return listVaccinationCenters;
    }


    /**
     * Gets vaccination center.
     *
     * @param emailAddress the email address
     * @return the vaccination center
     */
    public VaccinationCenter getVaccinationCenter(String emailAddress)
    {
        for(VaccinationCenter vaccinationCenter : listVaccinationCenters)
        {
            if(vaccinationCenter.getEmailAddress().compareTo(emailAddress) == 0)
                return vaccinationCenter;
        }

        return null;
    }


    public int getRecoveryTime() {
        return recoveryTime;
    }
}
