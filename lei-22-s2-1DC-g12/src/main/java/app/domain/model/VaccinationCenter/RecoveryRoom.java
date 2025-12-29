package app.domain.model.VaccinationCenter;

import app.controller.App;
import app.domain.model.SMSNotifier;
import app.domain.model.SNSUser;
import java.io.Serializable;
import java.util.*;

/**
 * The type Recovery room.
 */
public class RecoveryRoom implements Serializable {

    private int recoveryPeriod;
    private static String configName = "Recovery.Time";
    /**
     * The Map.
     */
    TreeMap<Date, SNSUser> map = new TreeMap<Date,SNSUser>();


    /**
     * Instantiates a new Recovery room.
     *
     */
    public RecoveryRoom() {
    }

    /**
     * Gets recovery period.
     *
     * @return the recovery period
     */
    public int getRecoveryPeriod() {
        return recoveryPeriod;
    }

    /**
     * Sets recovery period.
     *
     * @param recoveryPeriod the recovery period
     */
    public void setRecoveryPeriod(int recoveryPeriod) {
        this.recoveryPeriod = recoveryPeriod;
    }


    /**
     * when the user takes the vaccine we register that date that he took the vaccine as "admissionInRoom"
     * because that is the date we consider that he was sent to RecoveryRoom
     *
     * @param user            the user
     * @param admissionInRoom the admission in room
     * @return the boolean
     */
    boolean sentSNSUserToRecoveryRoom(SNSUser user, Date admissionInRoom) {
        return (map.put(admissionInRoom,user) == null);
    }


    //List<SNSUser> getUsersByArrivalDate() {return new ArrayList<>(map.values());}


    /**
     * If returns null it means the user already left the room
     *
     * @param snsUserNumber the sns user number
     * @return the sns user
     */
    public SNSUser getSNSUser(String snsUserNumber)
    {
        List<SNSUser> listSNSUsers = getSNSUserStillInsideRecoveryRoom();

        for(SNSUser user : listSNSUsers)
        {
            if(user.getSnsUserNumber().equals(snsUserNumber))
                return user;
        }

        return null;
    }


    /**
     * The idea here is to get only the SNSUser that dont exceeded the recovery period
     *  we use the date of admission inside recovery room and the current date
     *  and if the difference between those two are bigger then
     *  the recovery period it means the user already left the room
     *
     * @return the sns user still inside recovery room
     */
    //
    public List<SNSUser> getSNSUserStillInsideRecoveryRoom()
    {
        // Get entry set of the TreeMap using entrySet
        Set<Map.Entry<Date, SNSUser> > entrySet = map.entrySet();

        // Convert entrySet to Array using toArray method
        Map.Entry<Date, SNSUser>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);


        List<SNSUser> listSNSUsers = new ArrayList<>();

        for(int i = 0; i < entrySet.size() ; i++)
        {
            if(System.currentTimeMillis() - entryArray[i].getKey().getTime() < this.recoveryPeriod  )
            {
                listSNSUsers.add(entryArray[i].getValue());
            }
        }

        return listSNSUsers;
    }

    public void setRecoveryTime(int recoveryTime) {
        this.recoveryPeriod = recoveryTime;
    }
}
