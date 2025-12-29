package app.domain.model;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.MessageBundle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Sms notifier.
 */
public class SMSNotifier implements Serializable {

    private List<String> list;

    /**
     * The constant fileName.
     */
    public static final String fileName = "SMSNotifications.txt";

    /**
     * Instantiates a new Sms notifier.
     */
    public SMSNotifier (){
        this.list = new ArrayList<>();
    }

    /**
     * Add vaccine schedule boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @param vaccineSchedule   the vaccine schedule
     * @return the boolean
     */
    public boolean addVaccineSchedule(VaccinationCenter vaccinationCenter, VaccineSchedule vaccineSchedule){
        this.list.add(buildString(vaccinationCenter,vaccineSchedule));
        return exportNotifications();
    }

    private String buildString(SNSUser user, Date time){
        return "to:" + user.getPhoneNumber() + " Message: The user: " + user.getName() + " can leave the recovery room at " + time.toString();
    }
    private String buildString(VaccinationCenter vc, VaccineSchedule vs){
        return MessageBundle.getString("to") + ": " + vs.getSnsUser().getPhoneNumber() + " " + MessageBundle.getString("message") + MessageBundle.getString("theuser") + " " + vs.getSnsUser().getName() + " " + MessageBundle.getString("hasavaccinationscheduledfor") + " " + vs.getDate() + " " + MessageBundle.getString("atthevaccinationcenter") + " " + vc.getName() + " " + MessageBundle.getString("foravaccineofthetype") + " " + vs.getVaccineType().getDesignation() + ".";
    }

    /**
     * Export notifications boolean.
     *
     * @return the boolean
     */
    public boolean exportNotifications(){
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
            for (String s : list){
                out.println(s);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public boolean addRecoveryRoom(SNSUser user, Date time) {
        list.add(buildString(user,time));
        return exportNotifications();
    }
}
