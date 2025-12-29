package app.controller.Admin;

import app.controller.App;
import app.domain.model.SMSNotifier;

/**
 * The type Export notifications controller.
 */
public class ExportNotificationsController {
    private App app;

    /**
     * Instantiates a new Export notifications controller.
     */
    public ExportNotificationsController(){
        app = App.getInstance();
    }

    /**
     * Export notifications boolean.
     *
     * @return the boolean
     */
    public boolean exportNotifications(){
        return this.app.getCompany().getPlatform().getSmsNotifier().exportNotifications();
    }

    /**
     * Export location string.
     *
     * @return the string
     */
    public String exportLocation(){
        return SMSNotifier.fileName;
    }
}
