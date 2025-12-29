package app.ui.console.Admin;

import app.controller.Admin.SerializationController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


public class SerializationUI implements Runnable{

    private SerializationController controller;


    @Override
    public void run() {
        controller = new SerializationController();

        int option = -1;
        do {
            option = Utils.readIntegerFromConsole(MessageBundle.getString("serializationoptions"));
        }while(option<0 && option <= 2);

        boolean success = controller.serialize(option);

        if(success) {
            System.out.println(MessageBundle.getString("opsuccessful"));
        }else{
            System.out.println("error");
        }
        return;
    }
}
