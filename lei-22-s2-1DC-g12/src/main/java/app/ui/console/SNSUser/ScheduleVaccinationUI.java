package app.ui.console.SNSUser;

import app.controller.SNSUser.ScheduleVaccinationController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


public class ScheduleVaccinationUI implements Runnable{

    private ScheduleVaccinationController controller;

    @Override
    public void run() {
        controller = new ScheduleVaccinationController();

        if (!controller.validateUserSession()) {
            System.out.println(MessageBundle.getString("invaliddata"));
            return;
        }

        Object vaccinationCenter = Utils.showAndSelectOne(controller.getVaccinationCenterList(), MessageBundle.getString("selectvaccinationcenter"));

        if (vaccinationCenter == null) return;

        Object vaccineType = Utils.showAndSelectOne(controller.getAvailableVaccineTypes(vaccinationCenter), MessageBundle.getString("choosevaccinetype"));

        if (vaccineType == null) return;

        Object date = Utils.readDateFromConsole(MessageBundle.getString("insertscheduledate"));

        if (controller.getAvailableSlots(vaccinationCenter, date) == null) {
            System.out.println(MessageBundle.getString("invaliddateornoslotavaliable"));
            return;
        }

        Object slot = Utils.showAndSelectOne(controller.getAvailableSlots(vaccinationCenter, date), MessageBundle.getString("avaliableslots"));

        if (slot == null) return;

        Object vaccineSchedule = null;

        try {
            vaccineSchedule = controller.createVaccineSchedule(vaccinationCenter, vaccineType, date, slot);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        if (vaccineSchedule == null) {
            System.out.println(MessageBundle.getString("errouseralreadyhasavaccineofthistypecheduled"));
            return;
        }

        System.out.println(vaccineSchedule);
        boolean confirm = Utils.confirm(MessageBundle.getString("registerschedulevaccine"));

        if (confirm) {
            if (controller.scheduleVaccine(vaccinationCenter, vaccineSchedule)) {
                System.out.println(MessageBundle.getString("operationsucessful"));
            } else {
                System.out.println(MessageBundle.getString("errorvaccinaschedule"));
                return;
            }
        }

        boolean sms = Utils.confirm(MessageBundle.getString("receivesmsnotification") + "(S/N)");

        if (sms) {
            boolean success = controller.receiveSMSNotification(vaccinationCenter, vaccineSchedule);
            if (success) {
                System.out.println(MessageBundle.getString("operationsucessful"));
                return;
            }
            System.out.println(MessageBundle.getString("errorcreatingsms"));
        }
        return;
    }
}
