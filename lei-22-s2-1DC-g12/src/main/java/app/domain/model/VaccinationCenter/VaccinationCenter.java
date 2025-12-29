package app.domain.model.VaccinationCenter;

import app.domain.model.CenterPerformanceAnalysis;
import app.domain.model.Employee.CenterCoordinator;
import app.domain.model.SNSUser;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineSchedule;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * The type Vaccination center.
 */
public abstract class VaccinationCenter implements Serializable {

    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String faxNumber;
    private String websiteAddress;
    private Integer openingHours;
    private Integer openingMinutes;
    private Integer closingHours;
    private Integer closingMinutes;
    private Integer slotDuration;
    private Integer maxNumberVaccines;
    private Integer numTotalVaccinesDay;
    private List<Day> operatingDays;
    private WaitingRoom waitingRoom;
    private List<Vaccine> listVaccines;
    private List<VaccineType> listVaccineTypes;
    private RecoveryRoom recoveryRoom;
    private CenterPerformanceAnalysis centerPerformanceAnalysis;
    private CenterCoordinator centerCoordinator;

    /**
     * Instantiates a new Vaccination center.
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
    public VaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String websiteAddress,Integer openingHours, Integer openingMinutes, Integer closingHours, Integer closingMinutes,Integer slotDuration, Integer maxNumberVaccines) {

        if((name == null) || (name.isEmpty()) || (address == null) || (address.isEmpty()) || (phoneNumber == null) || (phoneNumber.isEmpty()) ||
            (emailAddress == null) || (emailAddress.isEmpty()) || (faxNumber == null) || (faxNumber.isEmpty()) || (websiteAddress == null) || (websiteAddress.isEmpty()) ||
                (openingHours == null) || (openingMinutes == null || (closingHours == null) || closingMinutes == null ||
                    (slotDuration == null) || (maxNumberVaccines == null) ||
                       (phoneNumber.length() != 9) || !StringUtils.isNumeric(phoneNumber)))
                    throw new IllegalArgumentException("None of the arguments can be null or empty");

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHours = openingHours;
        this.openingMinutes = openingMinutes;
        this.closingHours = closingHours;
        this.closingMinutes = closingMinutes;
        this.slotDuration = slotDuration;
        this.maxNumberVaccines = maxNumberVaccines;
        this.numTotalVaccinesDay = 0;
        this.operatingDays = new ArrayList<>();
        this.waitingRoom = new WaitingRoom();
        this.listVaccines = new ArrayList<>();
        this.listVaccineTypes = new ArrayList<>();
        this.recoveryRoom = new RecoveryRoom();
        this.centerPerformanceAnalysis = new CenterPerformanceAnalysis();
    }


    /**
     * Sets manage vaccination center.
     *
     * @param centerCoordinator the center coordinator
     */
    public void setManageVaccinationCenter(CenterCoordinator centerCoordinator)
    {
        this.centerCoordinator = centerCoordinator;
    }

    /**
     * Gets center performance analysis.
     *
     * @return the center performance analysis
     */
    public CenterPerformanceAnalysis getCenterPerformanceAnalysis()
    {
        return  this.centerPerformanceAnalysis;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets fax number.
     *
     * @return the fax number
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets fax number.
     *
     * @param faxNumber the fax number
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * Gets website address.
     *
     * @return the website address
     */
    public String getWebsiteAddress() {
        return websiteAddress;
    }

    /**
     * Sets website address.
     *
     * @param websiteAddress the website address
     */
    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    /**
     * Gets opening hours.
     *
     * @return the opening hours
     */
    public Integer getOpeningHours() {
        return openingHours;
    }

    /**
     * Sets opening hours.
     *
     * @param openingHours the opening hours
     */
    public void setOpeningHours(Integer openingHours) {
        this.openingHours = openingHours;
    }

    /**
     * Gets closing hours.
     *
     * @return the closing hours
     */
    public Integer getClosingHours() {
        return closingHours;
    }

    /**
     * Sets closing hours.
     *
     * @param closingHours the closing hours
     */
    public void setClosingHours(Integer closingHours) {
        this.closingHours = closingHours;
    }

    /**
     * Gets slot duration.
     *
     * @return the slot duration
     */
    public Integer getSlotDuration() {
        return slotDuration;
    }

    /**
     * Gets list vaccine types.
     *
     * @return the list vaccine types
     */
    public List<VaccineType> getListVaccineTypes() {
        return listVaccineTypes;
    }

    /**
     * Sets slot duration.
     *
     * @param slotDuration the slot duration
     */
    public void setSlotDuration(Integer slotDuration) {
        this.slotDuration = slotDuration;
    }

    /**
     * Gets max number vaccines.
     *
     * @return the max number vaccines
     */
    public Integer getMaxNumberVaccines() {
        return maxNumberVaccines;
    }

    /**
     * Sets max number vaccines.
     *
     * @param maxNumberVaccines the max number vaccines
     */
    public void setMaxNumberVaccines(Integer maxNumberVaccines) {
        this.maxNumberVaccines = maxNumberVaccines;
    }

    /**
     * Gets number total of vaccines administered that day.
     *
     * @return the number total of vaccines administered that day
     */
    public Integer getNumTotalVaccinesDay() { return numTotalVaccinesDay; }

    /**
     * Sets number total of vaccines administered that day.
     *
     * @param numTotalVaccinesDay the number total of vaccines administered that day
     */
    public void setNumTotalVaccinesDay(Integer numTotalVaccinesDay) { this.numTotalVaccinesDay = numTotalVaccinesDay; }

    /**
     * Schedule vaccine boolean.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param day         the day
     * @param hour        the hour
     * @param minutes     the minutes
     * @return the boolean
     */
    public boolean scheduleVaccine(SNSUser snsUser, VaccineType vaccineType,Date day, int hour, int minutes){
        if(!validateScheduleDate(day))
            return false;

        if(this.getDay(day) == null){
            this.operatingDays.add(new Day(day, openingHours, openingMinutes,  closingHours, closingMinutes,  slotDuration,  maxNumberVaccines ));
            Collections.sort(operatingDays);
        }
        Day schedulingDay = getDay(day);

        return schedulingDay.scheduleVaccine(snsUser,vaccineType,hour,minutes);
    }

    /**
     * Schedule vaccine boolean.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean scheduleVaccine(VaccineSchedule vaccineSchedule){
        Day day = getDay(vaccineSchedule.getDate());
        return day.scheduleVaccine(vaccineSchedule);
    }

    /**
     * Create vaccine schedule vaccine schedule.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param day         the day
     * @param s           the s
     * @return the vaccine schedule
     */
    public VaccineSchedule createVaccineSchedule(SNSUser snsUser,VaccineType vaccineType, Date day, Slot s){
        if(!validateScheduleDate(day))
            return null;

        if(this.getDay(day) == null){
            this.operatingDays.add(new Day(day, openingHours, openingMinutes,  closingHours, closingMinutes,  slotDuration,  maxNumberVaccines ));
            Collections.sort(operatingDays);
        }
        Day schedulingDay = getDay(day);

        return schedulingDay.createVaccineSchedule(snsUser,vaccineType,s);
    }

    /**
     * Validates a vaccine schedule date
     * @param day the day to validate
     * @return true if the date is valid.
     */
    private boolean validateScheduleDate(Date day){
        Day operatingDay = new Day(day,0,0,0,0,0,0);

        Day currentDay = new Day(new Date(System.currentTimeMillis()),0,0,0,0,0,0);

        if(operatingDay.compareTo(currentDay) < 0 ){
            return false;
        }

        return true;
    }

    /**
     * Get day day.
     *
     * @param day the day
     * @return the day
     */
    public Day getDay(Date day){
        int n = Collections.binarySearch(operatingDays,new Day(day,0,0,0,0,0,0));
        if(n < 0)
            return null;

        return operatingDays.get(n);
    }

    /**
     * Used to import data from legacy files
     * @param day the day
     * @return the day
     */
    public Day getOrAddDay(Date day){
        int n = Collections.binarySearch(operatingDays,new Day(day,0,0,0,0,0,0));
        if(n < 0) {
            Day day1 = new Day(day, openingHours, openingMinutes, closingHours, closingMinutes, 15, maxNumberVaccines);
            this.operatingDays.add(day1);
            Collections.sort(operatingDays);
            return day1;
        }

        return operatingDays.get(n);
    }

    /**
     * Register user arrival.
     *
     * @param vaccineSchedule the vaccineSchedule
     * @param dateOfArrival the date of arrival
     * @return the boolean
     */
    public boolean registerUserArrival(VaccineSchedule vaccineSchedule, Date dateOfArrival)
    {
        this.numTotalVaccinesDay ++;
       return waitingRoom.registerArrival(vaccineSchedule, dateOfArrival);
    }

    /**
     * Get waiting room sns users list.
     *
     * @return the list
     */
    public List<SNSUser> getWaitingRoomSNSUsers(){

        return this.waitingRoom.getUsersByArrivalDate();
    }


    /**
     * Add vaccine boolean.
     *
     * @param vaccine the vaccine
     * @return the boolean
     */
    public boolean addVaccine(Vaccine vaccine){
        return this.listVaccines.add(vaccine);
    }

    /**
     * Add vaccine type boolean.
     *
     * @param vt the vt
     * @return the boolean
     */
    public boolean addVaccineType (VaccineType vt){
        return this.listVaccineTypes.add(vt);
    }

    /**
     * Returns the hashcode value of an object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.address);
        return hash;
    }
    /**
     *  Compares the current object with the other object passed as paremeter
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null ) {
            return false;
        }

        VaccinationCenter center = (VaccinationCenter) o;
        if(this.address.compareToIgnoreCase(center.address) == 0)
        {
            System.out.println(MessageBundle.getString("addressexists"));
            return true;
        }
        if( this.phoneNumber.compareToIgnoreCase(center.phoneNumber) == 0)
        {
            System.out.println(MessageBundle.getString("phonenumberexists"));
            return true;
        }
        if(this.emailAddress.compareToIgnoreCase(center.emailAddress) == 0)
        {
            System.out.println(MessageBundle.getString("emailaddressexists"));
            return true;
        }
        if(this.faxNumber.compareToIgnoreCase(center.faxNumber) == 0)
        {
            System.out.println(MessageBundle.getString("faxnumberexists"));
            return true;
        }
        return false;
    }

    /**
     * Gets all available slots in vaccination center.
     *
     * @param dateDay the date day
     * @return the all available slots in vaccination center
     */
    public List<Slot> getAllAvailableSlotsInVaccinationCenter(Date dateDay) {
        if(!this.validateScheduleDate(dateDay))
            return null;

        if(this.getDay(dateDay) == null){
            this.operatingDays.add(new Day(dateDay, openingHours, openingMinutes,  closingHours, closingMinutes,  slotDuration,  maxNumberVaccines ));
            Collections.sort(operatingDays);
        }

        return this.getDay(dateDay).getAllAvailableSlots();
    }

    /**
     * Validate user arrival boolean.
     *
     * @param snsUserNumber the sns user number
     * @return the boolean
     */
    public VaccineSchedule validateUserArrival(String snsUserNumber)
    {



        //Day is the current day when running the program
        Day arrivalUserDay = this.getDay(Calendar.getInstance().getTime());
        if(arrivalUserDay == null){
            return null;
        }
        //Gets the list of all vaccine Schedule for today
        List<VaccineSchedule> listOfVaccineScheduleToday = arrivalUserDay.getAllVaccineSchedules();


        for (VaccineSchedule vs : listOfVaccineScheduleToday)
        {
            if(vs.getSnsUser().getSnsUserNumber().compareTo(snsUserNumber) == 0 && vs.isValidateEntrance() == false) {
                System.out.println(MessageBundle.getString("SCHEDULE") + "\n" + vs);
                vs.setValidateEntrance(true);
                return vs;
            }


        }

        return null;
    }

    public VaccineSchedule getVaccineScheduleFromUser(SNSUser snsUser){
        return this.waitingRoom.getVaccineScheduleFromUser(snsUser);
    }

    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        String str = String.format(MessageBundle.getString("namevaccinationcenter") + ": %s\n" + MessageBundle.getString("address") + ": %s\n" + MessageBundle.getString("phonenumber") + ": %s\n" + MessageBundle.getString("email") + ": %s\n" + MessageBundle.getString("faxnumber") + ": %s\n" + MessageBundle.getString("websiteaddress") + ": %s\n" + MessageBundle.getString("openinghours") + ": %s\n" + MessageBundle.getString("closinghours") + ": %s\n" + MessageBundle.getString("slotduration") + ": %s\n" + MessageBundle.getString("maxnumbervaccines") + ": %s\n",
                                    this.name,this.address,this.phoneNumber,this.emailAddress,this.faxNumber,this.websiteAddress,this.openingHours,this.closingHours,this.slotDuration,this.maxNumberVaccines);
        return str;
    }

    /**
     * Gets available vaccines.
     *
     * @param vaccineType the vaccine type
     * @return the available vaccines
     */
    public List<Vaccine> getAvaliableVaccines(VaccineType vaccineType) {
        List<Vaccine> list = new ArrayList<>();
        for (Vaccine v : this.listVaccines){
            if(vaccineType.getVaccineList().contains(v)){
                list.add(v);
            }
        }
        return list;
    }

    /**
     * Create vaccine administration vaccine .
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param vaccine     the vaccine
     * @param lotNumber   the lot number
     * @return the vaccine administration
     */
    public VaccineAdministration createVaccineAdministration(SNSUser snsUser,VaccineType vaccineType, Vaccine vaccine, String lotNumber) {
        return new VaccineAdministration(snsUser,vaccineType, vaccine,lotNumber);
    }

    /**
     * Register vaccine administration .
     *
     * @param vaccineAdministration the vaccine administration
     * @return the boolean
     */
    public boolean registerVaccineAdministration(VaccineAdministration vaccineAdministration) {
        Day day = getDay(vaccineAdministration.getDate());
        this.waitingRoom.remove(vaccineAdministration.getSnsUser());
        this.recoveryRoom.sentSNSUserToRecoveryRoom(vaccineAdministration.getSnsUser(),Calendar.getInstance().getTime());
        return day.registerVaccineAdministration(vaccineAdministration);
    }

    /**
     * Get operating days .
     *
     * @return the list
     */
    public List<Day> getOperatingDays(){return this.operatingDays;}

    public RecoveryRoom getRecoveryRoom() {
        return this.recoveryRoom;
    }

    public void addVaccineSchedule(VaccineSchedule vs) {
        Day day = this.getOrAddDay(vs.getDate());
        day.scheduleVaccine(vs);
    }

    public void addVaccineAdministration(VaccineAdministration va){
        Day day = this.getOrAddDay(va.getDate());
        day.registerVaccineAdministration(va);
    }
}
