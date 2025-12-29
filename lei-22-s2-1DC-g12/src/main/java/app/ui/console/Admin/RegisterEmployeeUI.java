package app.ui.console.Admin;

import app.controller.Admin.RegisterEmployeeController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


/**
 * The type Register employee ui.
 */
public class RegisterEmployeeUI implements Runnable{

    private RegisterEmployeeController m_controller;

    /**
     * Instantiates a new Register employee ui.
     */
    public RegisterEmployeeUI(){
        this.m_controller = new RegisterEmployeeController();
    }

    public void run(){

        System.out.println(MessageBundle.getString("registeremployee"));



        int choice = Utils.showAndSelectIndex(this.m_controller.getRoles(),MessageBundle.getString("selectrole"));


        if(choice >= 0 && choice < 3)
        {

            if(enterData(choice))
            {
                showData();

                if (Utils.confirm(MessageBundle.getString("confirmdata")))
                {
                    m_controller.registerEmployee();
                    System.out.println(MessageBundle.getString("registrationsucessful"));
                }
                else
                    run();
            }
            else
            {
                run();
            }



        }else{
        System.out.println(MessageBundle.getString("backtoadminmenu"));
    }


    }

    private boolean enterData(int roleChoice){

        String name = Utils.readLineFromConsole(MessageBundle.getString("name"));
        String address = Utils.readLineFromConsole(MessageBundle.getString("address"));
        String phoneNumber = Utils.readLineFromConsole(MessageBundle.getString("phonenumber"));
        String emailAddress = Utils.readLineFromConsole(MessageBundle.getString("email"));
        String citizenCardNumber = Utils.readLineFromConsole( MessageBundle.getString("citizencardnumber"));


        return m_controller.newEmployee(name, address, phoneNumber, emailAddress, citizenCardNumber ,roleChoice);

    }

    private void showData(){
        System.out.println("\n" + MessageBundle.getString("employee") + ":\n" + m_controller.getEmployeeString());
    }
}
