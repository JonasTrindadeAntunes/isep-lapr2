package app.ui.console.Admin;

import app.controller.Admin.SpecifyNewVaccineTypeController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

/**
 * The type Specify new vaccine type ui.
 */
public class SpecifyNewVaccineTypeUI implements Runnable{


    private SpecifyNewVaccineTypeController m_controller;

    /**
     * Instantiates a new Specify new vaccine type ui.
     */
    public SpecifyNewVaccineTypeUI(){ this.m_controller = new SpecifyNewVaccineTypeController();}

    public void run(){

        System.out.println(MessageBundle.getString("specifyvaccinetype"));

        if(enterData()) {

            showData();

            if (Utils.confirm(MessageBundle.getString("confirmdata") + " (S/N)"))
            {
                if (m_controller.registerVaccineType()) {
                    System.out.println(MessageBundle.getString("registrationsucessful"));
                }
                else
                {
                    run();
                    System.out.println(MessageBundle.getString("registrationunsuccessful"));
                }
            }
        }else{
            System.out.println(MessageBundle.getString("backtoadminmenu"));
        }


    }

    private boolean enterData(){

        String typeOfVaccine = Utils.showAndSelectOne(m_controller.getTypesOfVaccine(),MessageBundle.getString("typeofvaccineinsert")).toString();
        String code = Utils.readLineFromConsole(MessageBundle.getString("code"));
        String designation = Utils.readLineFromConsole(MessageBundle.getString("designation"));


        return m_controller.newVaccineType(code,designation,typeOfVaccine);
    }

    private void showData() {System.out.println(m_controller.getVaccineTypeString());}


    private void getListVaccineType(){
        Utils.showList(m_controller.getListVaccineType(),MessageBundle.getString("listvaccinestype"));
    }
}
