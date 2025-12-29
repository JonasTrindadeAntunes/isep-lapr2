package app.ui.console.Admin;

import app.controller.Admin.SpecifyNewVaccineController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Specify new vaccine ui.
 */
public class SpecifyNewVaccineUI implements Runnable{

    private SpecifyNewVaccineController controller;

    /**
     * Instantiates a new Specify new vaccine ui.
     */
    public SpecifyNewVaccineUI(){ this.controller = new SpecifyNewVaccineController();}

    public void run(){

        System.out.println(MessageBundle.getString("specifyvaccine"));

        Object type = Utils.showAndSelectOne(controller.getVaccineType(), MessageBundle.getString("choosevaccinetype"));

        controller.setVaccineType(type);

        String id = Utils.readLineFromConsole(MessageBundle.getString("id"));
        String name = Utils.readLineFromConsole(MessageBundle.getString("name"));

        if(!controller.newVaccine(id,name)){
            System.out.println(MessageBundle.getString("invaliddata"));
            return;
        }

        boolean finishedAdministrationProcesses = false;

        while(!finishedAdministrationProcesses) {
            System.out.println(MessageBundle.getString("specifyvaccineadministrationprocess"));
            boolean done = false;
            List<Integer[]> ageGroups = new ArrayList<>();
            while (!done) {
                int lowerAgeLimit = Utils.readIntegerFromConsole(MessageBundle.getString("loweragelimit"));
                int upperAgeLimit = Utils.readIntegerFromConsole(MessageBundle.getString("upperAgeLimit"));
                if (lowerAgeLimit > upperAgeLimit) {
                    System.out.println(MessageBundle.getString("loweragelimitcannotbebiggerthenupperlimit"));
                } else {
                    Integer[] arr = new Integer[2];
                    arr[0] = lowerAgeLimit;
                    arr[1] = upperAgeLimit;
                    ageGroups.add(arr);
                    done = !Utils.confirm(MessageBundle.getString("doyouwanttospecifymoreagegroups") + "(S/N)");
                }
            }

            int dosesToBeAdministered = Utils.readIntegerFromConsole(MessageBundle.getString("insertnumberofdoses"));

            double dosage = Utils.readDoubleFromConsole(MessageBundle.getString("insertdosage") + "(in ml)");

            boolean differentTimeIntervals = false;

            if (dosesToBeAdministered > 2) {
                differentTimeIntervals = Utils.confirm(MessageBundle.getString("arethereanydifferencesintimeinterval") + "(S-yes,N-no)" +
                        "\n" +  MessageBundle.getString("datedifferenceexample"));
            }

            List<Integer[]> listTimeInterval = new ArrayList<>();
            if (differentTimeIntervals) { //if there are any special time intervals in between doses
                for (int i = 1; i < dosesToBeAdministered; i++) {
                    int doseTimeInterval = Utils.readIntegerFromConsole(MessageBundle.getString("afterdosen") + i + MessageBundle.getString("timeperiodbeforedosen") + (i+1) + ".");
                    Integer[] arr = new Integer[2];
                    arr[0] = (i+1);
                    arr[1] = doseTimeInterval;
                    listTimeInterval.add(arr);
                }
            } else if (!differentTimeIntervals && dosesToBeAdministered == 2) { //if all time intervals are the same or there is only 2 doses to be administered
                int timeInterval = Utils.readIntegerFromConsole(MessageBundle.getString("timeintervalinbetweendoses"));
                for (int i = 0; i < dosesToBeAdministered; i++) {
                    Integer[] arr = new Integer[2];
                    arr[0] = i + 1;
                    arr[1] = timeInterval;
                    listTimeInterval.add(arr);
                }
            } //if there is only 1 dose no time interval is requested

            if(!controller.newAdministrationProcess(ageGroups, dosesToBeAdministered, dosage, listTimeInterval)){
                System.out.println(MessageBundle.getString("invaliddata"));
            }

            finishedAdministrationProcesses = !Utils.confirm(MessageBundle.getString("moreadministrationprocesses"));
        }


        System.out.println(controller.getVaccineInfo());

        if(Utils.confirm(MessageBundle.getString("confirmdata") + "(S/N)" )){
            if(controller.addVaccine()){
                System.out.println(MessageBundle.getString("registrationsucessful"));
            }else{
                System.out.println(MessageBundle.getString("registrationunsuccessful"));
            }
        }
    }


}
