package app.ui.console.Admin;

import app.controller.Admin.RegisterVaccinationCenterController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

/**
 * The type Register vaccination center ui.
 */
public class RegisterVaccinationCenterUI implements Runnable{

    private RegisterVaccinationCenterController m_controller;

    /**
     * Instantiates a new Register vaccination center ui.
     */
    public RegisterVaccinationCenterUI(){this.m_controller = new RegisterVaccinationCenterController();}

    public void run() {

        System.out.println(MessageBundle.getString("registervaccinationcenter"));


        int typeOfVaccinationCenter = Utils.showAndSelectIndex(this.m_controller.getTypesOfVaccinationCenter(), MessageBundle.getString("selectvaccinationcentertype"));

        if (typeOfVaccinationCenter >= 0 && typeOfVaccinationCenter < 2)
        {

            if (enterData(typeOfVaccinationCenter))
            {
                showData();

                if (Utils.confirm(MessageBundle.getString("confirmdata") + " (S/N)"))
                {
                    if (m_controller.registerVaccinationCenter())
                    {
                        System.out.println(MessageBundle.getString("registrationsucessful"));
                    }
                    else
                        run();

                }
                else
                {
                    run();
                }
            }


        }else {
            System.out.println(MessageBundle.getString("backtoadminmenu"));
        }
    }

    private boolean enterData(int typeOfVaccinationCenter){

        String name = Utils.readLineFromConsole(MessageBundle.getString("name"));
        String address = Utils.readLineFromConsole(MessageBundle.getString("address"));
        String phoneNumber = Utils.readLineFromConsole(MessageBundle.getString("phonenumber"));
        String emailAddress = Utils.readLineFromConsole(MessageBundle.getString("email"));
        String faxNumber = Utils.readLineFromConsole(MessageBundle.getString("faxnumber"));
        String websiteAddress = Utils.readLineFromConsole(MessageBundle.getString("websiteaddress"));
        Integer openingHours;
        do {
            openingHours = Utils.readIntegerFromConsole(MessageBundle.getString("openinghours24"));
        }while(!(0<=openingHours && openingHours < 24));

        Integer openingMinutes;
        do{
            openingMinutes = Utils.readIntegerFromConsole(MessageBundle.getString("openingminutes"));
        }while(!(0<=openingMinutes && openingMinutes < 60));

        Integer closingHours;
        do {
            closingHours = Utils.readIntegerFromConsole(MessageBundle.getString("closinghours24"));
        }while(!(0<=closingHours && closingHours < 24));

        Integer closingMinutes;
        do {
            closingMinutes = Utils.readIntegerFromConsole(MessageBundle.getString("closingminutes"));
        }while(!(0<=closingMinutes && closingMinutes < 60));
        Integer slotDuration = Utils.readIntegerFromConsole(MessageBundle.getString("slotduration"));
        int maxNumberVaccines = Utils.readIntegerFromConsole(MessageBundle.getString("maxnumbervaccines"));


        return m_controller.newVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,websiteAddress,openingHours,openingMinutes,closingHours,closingMinutes,slotDuration,maxNumberVaccines,typeOfVaccinationCenter);

    }

    private void showData(){System.out.println(m_controller.getVaccinationCenterString());}


}
