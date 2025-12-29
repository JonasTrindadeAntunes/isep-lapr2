package app.domain.model.VaccinationCenter;

import app.domain.model.SNSUser;
import app.domain.model.VaccineSchedule;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.*;

/**
 * The type Slot.
 */
public class Slot implements Serializable, Comparable<Slot>{


    private Date hour;

    private List<VaccineSchedule> scheduledVaccinations;


    /**
     * Instantiates a new Slot.
     *
     * @param hour the hour
     */
    public Slot(Date hour){
        this.hour = hour;
        this.scheduledVaccinations = new ArrayList<>();
    }

    /**
     * Checks if the slots' date is before the current system time
     * @return true if slot's date is before the current system time
     */
    public boolean beforeCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return this.hour.before(cal.getTime());
    }

    /**
     * Get current number of vaccinations int.
     *
     * @return the int
     */
    public int getCurrentNumberOfVaccinations(){
        return scheduledVaccinations.size();
    }

    /**
     * Get scheduled vaccinations list.
     *
     * @return the list
     */
    public List<VaccineSchedule> getScheduledVaccinations(){
        return this.scheduledVaccinations;
    }

    @Override
    public int compareTo(Slot o) {
        Calendar hour1 = Calendar.getInstance();
        hour1.setTime(this.hour);
        Calendar hour2 = Calendar.getInstance();
        hour2.setTime(o.hour);
        int diff = Integer.compare(hour1.get(Calendar.HOUR_OF_DAY),hour2.get(Calendar.HOUR_OF_DAY));

        if(diff != 0)
            return diff;

        return Integer.compare(hour1.get(Calendar.MINUTE),hour2.get(Calendar.MINUTE));
    }

    /**
     * Returns the string of the current object or instance
     */
    @Override
    public String toString() {
        return "Slot{" +
                MessageBundle.getString("hour") + "=" + hour +
                MessageBundle.getString("scheduledvaccines") + ":" + this.getCurrentNumberOfVaccinations() +
                '}';
    }

    /**
     * Scheduled vaccination boolean.
     *
     * @param day         the day
     * @param vaccineType the vaccine type
     * @param snsUser     the sns user
     * @return the boolean
     */
    public boolean scheduledVaccination(Date day, VaccineType vaccineType, SNSUser snsUser) {
        VaccineSchedule vaccineSchedule = createVaccineSchedule(day,vaccineType,snsUser);
        snsUser.addVaccineSchedule(vaccineSchedule);
        return scheduledVaccinations.add(vaccineSchedule);
    }

    /**
     * Create vaccine schedule.
     *
     * @param day         the day
     * @param vaccineType the vaccine type
     * @param snsUser     the sns user
     * @return the vaccine schedule
     */
    public VaccineSchedule createVaccineSchedule(Date day,VaccineType vaccineType, SNSUser snsUser) {
        return new VaccineSchedule(createDate(day),vaccineType,snsUser);
    }

    /**
     * Creates the vaccination schedule date combining the day information with the slot time.
     * @param date the day for the vaccine schedule
     * @return date and time of the vaccine schedule
     */
    private Date createDate(Date date){
        Calendar dateTime = Calendar.getInstance();
        Calendar hour = Calendar.getInstance();
        hour.setTime(this.hour);
        Calendar scheduleDay = Calendar.getInstance();
        scheduleDay.setTime(date);
        dateTime.set(Calendar.YEAR, scheduleDay.get(Calendar.YEAR));
        dateTime.set(Calendar.MONTH, scheduleDay.get(Calendar.MONTH));
        dateTime.set(Calendar.DAY_OF_MONTH, scheduleDay.get(Calendar.DAY_OF_MONTH));
        dateTime.set(Calendar.HOUR_OF_DAY, hour.get(Calendar.HOUR_OF_DAY));
        dateTime.set(Calendar.MINUTE, hour.get(Calendar.MINUTE));
        dateTime.set(Calendar.SECOND,0);
        dateTime.set(Calendar.MILLISECOND,0);
        return dateTime.getTime();
    }

    /**
     * Schedule vaccination.
     *
     * @param vaccineSchedule the vaccine schedule
     * @return the boolean
     */
    public boolean scheduleVaccination(VaccineSchedule vaccineSchedule) {
        vaccineSchedule.getSnsUser().addVaccineSchedule(vaccineSchedule);
        return this.scheduledVaccinations.add(vaccineSchedule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return Objects.equals(hour, slot.hour) && Objects.equals(scheduledVaccinations, slot.scheduledVaccinations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, scheduledVaccinations);
    }
}
