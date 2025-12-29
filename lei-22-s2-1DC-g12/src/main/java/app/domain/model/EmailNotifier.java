package app.domain.model;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.MessageBundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * The type Email notifier.
 */
public class EmailNotifier implements Serializable {

    private List<String> listEmailsSchedules;

    /**
     * Instantiates a new Email notifier.
     */
    public EmailNotifier() {
        this.listEmailsSchedules = new ArrayList<>();
    }

    /**
     * Add vaccine schedule boolean.
     *
     * @param vaccinationCenter the vaccination center
     * @param vaccineSchedule   the vaccine schedule
     * @return the boolean
     */
    public boolean addVaccineSchedule(VaccinationCenter vaccinationCenter, VaccineSchedule vaccineSchedule) {
        SendEmail(vaccinationCenter, vaccineSchedule);
        return this.listEmailsSchedules.add(buildString(vaccinationCenter, vaccineSchedule));
    }

    private String buildString(VaccinationCenter vc, VaccineSchedule vs) {
        return MessageBundle.getString("to") + ":" + vs.getSnsUser().getPhoneNumber() + MessageBundle.getString("message") + MessageBundle.getString("theuser") + vs.getSnsUser().getName() + MessageBundle.getString("hasavaccinationscheduledfor") + vs.getDate() + MessageBundle.getString("atthevaccinationcenter") + vc.getName() + MessageBundle.getString("foravaccineofthetype") + vs.getVaccineType().getDesignation() + ".";
    }

    /**
     * Sent email.
     *
     * @param vaccinationCenter the vaccination center
     * @param vaccineSchedule   the vaccine schedule
     */
    private static void SendEmail(VaccinationCenter vaccinationCenter, VaccineSchedule vaccineSchedule) {


        String emailMessage = String.format(MessageBundle.getString("theuser") + vaccineSchedule.getSnsUser().getName() + MessageBundle.getString("hasavaccinationscheduledfor") + vaccineSchedule.getDate() + MessageBundle.getString("atthevaccinationcenter") + vaccinationCenter.getName() + MessageBundle.getString("foravaccineofthetype") + vaccineSchedule.getVaccineType().getDesignation() + ".");

        SendEmail.SendEmail(vaccineSchedule.getSnsUser().getEmail(),emailMessage,MessageBundle.getString("vaccineschedule"));

    }
}

