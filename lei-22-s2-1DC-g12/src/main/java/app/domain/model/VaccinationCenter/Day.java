package app.domain.model.VaccinationCenter;

import app.domain.model.SNSUser;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineSchedule;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.Algorithms;

import java.io.Serializable;
import java.util.*;

/**
 * The type Day.
 */
public class Day implements Serializable, Comparable<Day> {

    private Date day;

    private List<Slot> slots;

    private int fullyVaccinated;

    private List<VaccineAdministration> vaccineAdministrations;

    private int maxNumberVaccines;

    /**
     * Instantiates a new Day.
     *
     * @param day               the day
     * @param openingHours      the opening hours
     * @param openingMinutes    the opening minutes
     * @param closingHours      the closing hours
     * @param closingMinutes    the closing minutes
     * @param slotDuration      the slot duration
     * @param maxNumberVaccines the max number vaccines
     */
    public Day(Date day, int openingHours,int openingMinutes, int closingHours,int closingMinutes, int slotDuration, int maxNumberVaccines){
        this.day = day;
        this.maxNumberVaccines = maxNumberVaccines;
        this.fullyVaccinated = 0;
        this.vaccineAdministrations = new ArrayList<>();

        Calendar opening = Calendar.getInstance();
        opening.setTime(day);

        Calendar closing = Calendar.getInstance();
        closing.setTime(day);

        opening.set(Calendar.HOUR_OF_DAY, openingHours);
        closing.set(Calendar.HOUR_OF_DAY, closingHours);

        opening.set(Calendar.MINUTE, openingMinutes);
        closing.set(Calendar.MINUTE, closingMinutes);

        slots = new ArrayList<>();
        while(opening.before(closing)){
            slots.add(new Slot(opening.getTime()));
            opening.add(Calendar.MINUTE, slotDuration);
        }
    }

    /**
     * All slots list.
     *
     * @return the list
     */
    public List<Slot> allSlots(){
        return this.slots;
    }

    @Override
    public int compareTo(Day o) {
        Calendar day1 = Calendar.getInstance();
        day1.setTime(this.day);
        Calendar day2 = Calendar.getInstance();
        day2.setTime(o.day);

        int diff = Integer.compare(day1.get(Calendar.YEAR),day2.get(Calendar.YEAR));
        if(diff != 0)
            return diff;

        diff = Integer.compare(day1.get(Calendar.MONTH),day2.get(Calendar.MONTH));

        if(diff != 0)
            return diff;

        return Integer.compare(day1.get(Calendar.DAY_OF_MONTH),day2.get(Calendar.DAY_OF_MONTH));

    }

    /**
     * Schedule vaccine boolean.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param hour        the hour
     * @param minutes     the minutes
     * @return the boolean
     */
    public boolean scheduleVaccine(SNSUser snsUser,VaccineType vaccineType,int hour, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);

        Slot slot = getSlot(hour,minutes);

        if(slot == null)
            return false;

        if(slot.getCurrentNumberOfVaccinations() < this.maxNumberVaccines) {
            return slot.scheduledVaccination(this.day,vaccineType,snsUser);
        }

        return false;
    }

    /**
     * Get slot.
     *
     * @return the slot
     */
    private Slot getSlot(int hour, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);
        Date date = cal.getTime();

        Slot s = new Slot(date);

        int n = Collections.binarySearch(allSlots(),s);

        if(n < 0)
            return null;

        return this.slots.get(n);

    }

    /**
     * Get all vaccine schedules list.
     *
     * @return the list
     */
    public List<VaccineSchedule> getAllVaccineSchedules(){
        List<VaccineSchedule> list = new ArrayList<>();
        for (Slot s : slots){
            list.addAll(s.getScheduledVaccinations());
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day comparingDay = (Day) o;
        Calendar day1 = Calendar.getInstance();
        day1.setTime(this.day);
        Calendar day2 = Calendar.getInstance();
        day2.setTime(comparingDay.day);

        if(day1.get(Calendar.YEAR) != day2.get(Calendar.YEAR))
            return false;

        if(day1.get(Calendar.MONTH) != day2.get(Calendar.MONTH))
            return false;

        return day1.get(Calendar.DAY_OF_MONTH) == day2.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public int hashCode() {
        Calendar day = Calendar.getInstance();
        day.setTime(this.day);
        return Objects.hash(day.get(Calendar.YEAR),day.get(Calendar.MONTH),day.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Gets all available slots.
     *
     * @return the all available slots
     */
    public List<Slot> getAllAvailableSlots() {
        List<Slot> list = new ArrayList<>();
        for ( Slot s : allSlots()){
            if(s.getCurrentNumberOfVaccinations() < this.maxNumberVaccines && !s.beforeCurrentDate())
                list.add(s);
        }
        return list;
    }

    /**
     * Create vaccine schedule.
     *
     * @param snsUser     the sns user
     * @param vaccineType the vaccine type
     * @param slot        the slot
     * @return the vaccine schedule
     */
    public VaccineSchedule createVaccineSchedule(SNSUser snsUser,VaccineType vaccineType, Slot slot) {
        int pos = Collections.binarySearch(this.slots,slot);

        if(pos < 0)
            return null;

        Slot schedulingSlot = slots.get(pos);

        if(schedulingSlot == null)
            return null;

        return schedulingSlot.createVaccineSchedule(this.day,vaccineType,snsUser);
    }

    /**
     * Schedule vaccine boolean.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean scheduleVaccine(VaccineSchedule vaccineSchedule) {
        Slot slot = this.getSlot(vaccineSchedule.getDate());
        return slot.scheduleVaccination(vaccineSchedule);
    }

    /**
     * Get slot slot.
     *
     * @param hour the hour
     * @return the slot
     */
    public Slot getSlot(Date hour){
        int pos = Collections.binarySearch(slots,new Slot(hour));

        if(pos<0)
            return null;

        return slots.get(pos);
    }

    /**
     * Register vaccine administration.
     *
     * @param vaccineAdministration the vaccine administration
     * @return the boolean
     */
    public boolean registerVaccineAdministration(VaccineAdministration vaccineAdministration) {
        if(Algorithms.validateVaccinationStatus(vaccineAdministration.getSnsUser(),vaccineAdministration.getVaccine())){
            this.fullyVaccinated++;
        }
        return this.vaccineAdministrations.add(vaccineAdministration);
    }

    /**
     * Get count of fully vaccinated users.
     *
     * @return the int
     */
    public int getFullyVaccinated(){
        return fullyVaccinated;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public Date getDay()
    {
        return this.day;
    }

    /**
     * Get number of vaccinations int.
     *
     * @return the int
     */
    public int getNumberOfVaccinations(){
        return this.vaccineAdministrations.size();
    }

}
