package app.domain.model.VaccinationCenter;


import app.domain.model.SNSUser;
import app.domain.model.VaccineSchedule;

import java.io.Serializable;
import java.util.*;

/**
 * The type Waiting room.
 */
public class WaitingRoom implements Serializable  {

    /**
     * The Map.
     */
    TreeMap<Date,VaccineSchedule> map = new TreeMap<Date,VaccineSchedule>();


    /**
     * Instantiates a new Waiting room.
     */
    public WaitingRoom() {
    }


    /**
     * Register arrival boolean.
     *
     * @param vaccineSchedule    the vaccineSchedule
     * @param arrival the arrival
     * @return the boolean
     */
    boolean registerArrival(VaccineSchedule vaccineSchedule, Date arrival) {return (map.put(arrival,vaccineSchedule) == null);}


    /**
     * Get users by arrival date .
     *
     * @return the list
     */
    List<SNSUser> getUsersByArrivalDate() {
        List<VaccineSchedule> list =  new ArrayList<>(map.values());
        List<SNSUser> userList = new ArrayList<>();
        for (VaccineSchedule vs : list){
            userList.add(vs.getSnsUser());
        }
        return userList;
    }

    public VaccineSchedule getVaccineScheduleFromUser(SNSUser snsUser){
        for(VaccineSchedule vs : map.values()){
            if(vs.getSnsUser().equals(snsUser)){
                return vs;
            }
        }
        return null;
    }

    public void remove(SNSUser snsUser) {
        for (Date d : map.keySet()){
            if(map.get(d).getSnsUser().equals(snsUser)){
                map.remove(d);
            }
        }
    }
}






