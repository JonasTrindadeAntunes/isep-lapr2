package app.ui.console.Admin;

import app.controller.Admin.ExportNotificationsController;
import app.domain.shared.MessageBundle;

/**
 * The type Export notifications ui.
 */
public class ExportNotificationsUI implements Runnable {

    private ExportNotificationsController controller;

    /**
     * Instantiates a new Export notifications ui.
     */
    public ExportNotificationsUI(){

    }

    @Override
    public void run() {
        controller = new ExportNotificationsController();

        boolean success = controller.exportNotifications();

        if(success){
            System.out.println(MessageBundle.getString("fileexportedto") + controller.exportLocation());
        }else{
            System.out.println(MessageBundle.getString("errorexporting"));
        }

        return;
    }
}
