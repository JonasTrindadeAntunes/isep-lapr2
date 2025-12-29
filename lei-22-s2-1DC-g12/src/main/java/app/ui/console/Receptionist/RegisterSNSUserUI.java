package app.ui.console.Receptionist;

import app.controller.Receptionist.RegisterSNSUserController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * The type Register sns user ui.
 */
public class RegisterSNSUserUI implements Runnable{

        private final RegisterSNSUserController controller;

    /**
     * Instantiates a new Register sns user ui.
     */
    public RegisterSNSUserUI(){
            this.controller =  new RegisterSNSUserController();
        }

        public void run()
        {
            System.out.println(MessageBundle.getString("registersnsuser"));

            if(!controller.checkLogin()) {
                System.out.println(MessageBundle.getString("unauthorizeduser"));
                return;
            }

            System.out.println(MessageBundle.getString("insertuserinformation"));
            String name = Utils.readLineFromConsole(MessageBundle.getString("insertname"));
            String address = Utils.readLineFromConsole(MessageBundle.getString("insertadress"));
            String gender = Utils.showAndSelectOne(controller.getGenderList(), MessageBundle.getString("genderorskip")).toString();
            String phoneNumber = Utils.readLineFromConsole(MessageBundle.getString("insertphonenumber"));
            while(!StringUtils.isNumeric(phoneNumber)){
                System.out.println(MessageBundle.getString("phonenumbermustbenumeric"));
                phoneNumber = Utils.readLineFromConsole(MessageBundle.getString("insertphonenumber"));
            }
            String email = Utils.readLineFromConsole(MessageBundle.getString("insertemail"));
            Date date = Utils.readDateFromConsole(MessageBundle.getString("insertbirthdate") + "(Format dd-MM-yyyy):");
            String snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
            while(!StringUtils.isNumeric(snsUserNumber)){
                System.out.println(MessageBundle.getString("snsusernumbermustbenumeric"));
                snsUserNumber = Utils.readLineFromConsole(MessageBundle.getString("insertsnsusernumber"));
            }
            String citizenCardNumber = Utils.readLineFromConsole(MessageBundle.getString("insertcitizencard"));
            while(!StringUtils.isNumeric(citizenCardNumber)){
                System.out.println(MessageBundle.getString("citizencardnumbermustbenumeric"));
                citizenCardNumber = Utils.readLineFromConsole(MessageBundle.getString("insertcitizencard"));
            }


            if(!controller.newSNSUser(name,address,gender,phoneNumber,email,date,snsUserNumber,citizenCardNumber)){
                System.out.println(MessageBundle.getString("invalidinfo"));
                return;
            }

            System.out.println(controller.getSNSUser());
            boolean cont = Utils.confirm(MessageBundle.getString ("confirmusercreation")+"(S/N)");

            if(cont){
                if(controller.addUser()){
                    System.out.println(MessageBundle.getString("opsuccessful"));
                    return;
                }else{
                    System.out.println(MessageBundle.getString("error"));
                }
            }
        }


}


