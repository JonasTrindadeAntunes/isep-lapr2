package domain.model;

import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.Slot;
import app.domain.model.Vaccine.VaccineType;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    private Date day;

    @Test
    void allSlots() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void scheduleVaccineNull() {
        Date date = new Date();
        Day newDay = new Day(date,10,0,20,0,10,10);
        SNSUser user1 = new SNSUser("gordonjcp@gmail.com","919912129","66554433","12050880");
        VaccineType vt = new VaccineType("1233","Covid","mRna");
        assertFalse(newDay.scheduleVaccine(user1,vt,10,32));
    }
// In progress.Currently, not able to understand s.scheduleVaccine
    @Test
    void scheduleVaccineSlot() {
        /*Date date = new Date();
        Date date1 = new Date();
        Date hour = new Time(10,32,2);
        Date date2 = new Date();
        Day day = new Day(date2,0,0,23,59,10,10);
        Day newDay = new Day(date,10,0,20,0,10,10);
        SNSUser user1 = new SNSUser("1233314@isep.ipp.pt","919912129","66554433","12050880");
        VaccineType vt = new VaccineType("1233","Covid","mRna");
        VaccineSchedule vS = new VaccineSchedule(date,vt,user1);
        Slot s = new Slot(hour);
        newDay.scheduleVaccine(user1,vt,10,32);
        //s.scheduledVaccination(date2,vt,user1);
        System.out.println(s.getScheduledVaccinations());
       // assertEquals(,newDay.scheduleVaccine(user1,vt,10,32)); */
    }

    @Test
    void getAllVaccineSchedules() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void getAllAvailableSlots() {
    }

    @Test
    void createVaccineSchedule() {
    }

    @Test
    void testScheduleVaccine() {
    }

    @Test
    void getSlot() {
    }
}