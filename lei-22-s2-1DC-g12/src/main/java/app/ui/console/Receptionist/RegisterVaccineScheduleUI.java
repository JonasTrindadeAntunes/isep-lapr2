package app.ui.console.Receptionist;

import app.controller.Receptionist.RegisterVaccineScheduleController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import org.apache.commons.lang3.StringUtils;


/**
 * The type Register vaccine schedule ui.
 */
public class RegisterVaccineScheduleUI implements Runnable{

    private RegisterVaccineScheduleController m_controller;

    /**
     * Instantiates a new Register vaccine schedule ui.
     */
    public RegisterVaccineScheduleUI() { this.m_controller = new RegisterVaccineScheduleController();}



    public void run() {
        System.out.println("\n" + MessageBundle.getString("schedulevaccination"));

        if(!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        if (!m_controller.getWorkingVaccinationCenter() ) {
            System.out.println(MessageBundle.getString("couldntfindcenter"));
            return;
        }

        System.out.println(MessageBundle.getString("insertscheduleinformation"));

        String snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("snsusernumber"));
        while (!StringUtils.isNumeric(snsUserNumber)) {
            System.out.println(MessageBundle.getString("snsusernumbermustbenumeric"));
            snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
        }

        if (!m_controller.checkIfExistsSNSUser(snsUserNumber)) {
            System.out.println(MessageBundle.getString("snsusernotfound"));
            return;
        }

        Object dateDay = Utils.readDateFromConsole(MessageBundle.getString("insertscheduledate"));
        if (m_controller.getAllAvailableSlotsInVaccinationCenter(dateDay) == null) {
            System.out.println(MessageBundle.getString("invaliddateornoslotavaliable"));
            return;
        }
        Object slot =  Utils.showAndSelectOne(m_controller.getAllAvailableSlotsInVaccinationCenter(dateDay),MessageBundle.getString("avaliableslots"));
        if (slot==null)return;
        Object vaccineType = Utils.showAndSelectOne(m_controller.selectVaccineType(), MessageBundle.getString("avaliablevaccinetypes"));
        if (vaccineType==null)return;
        Object vaccineSchedule = null;

        try{
            vaccineSchedule = m_controller.createSchedule(m_controller.getSNSUserByNumber(snsUserNumber),vaccineType,dateDay,slot);
        }catch(IllegalArgumentException e){
            System.out.println(e);
        }

        if(vaccineSchedule == null) {
            System.out.println(MessageBundle.getString("errouseralreadyhasavaccineofthistypecheduled"));
            return;
        }

        System.out.println(vaccineSchedule);
        boolean confirm = Utils.confirm(MessageBundle.getString("registerschedulevaccine"));

        if(!confirm) {
            System.out.println(MessageBundle.getString("schedulecanceling"));
            return;
        }
        if(m_controller.scheduleVaccination(vaccineSchedule)){
            System.out.println(MessageBundle.getString("successfulschedule"));
            boolean sms = Utils.confirm(MessageBundle.getString("receivesmsnotification"));
            if(sms){
                if(m_controller.receiveSMSNotification(vaccineSchedule)){
                    System.out.println(MessageBundle.getString("successfulNotificationcreated"));
                }else{
                    System.out.println(MessageBundle.getString("errorcreatingsms"));
                }
            }
            return;
        }
        System.out.println(MessageBundle.getString("vaccinescheduleerror"));
        return;
    }
}





