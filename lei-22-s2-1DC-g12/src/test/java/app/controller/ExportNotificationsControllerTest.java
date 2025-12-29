package app.controller;

import app.controller.Admin.ExportNotificationsController;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExportNotificationsControllerTest {

    @Test //May need to be revised
    void ExportNotifications(){

        ExportNotificationsController enc = new ExportNotificationsController();

        enc.exportNotifications();
        String filename = enc.exportLocation();

        assertEquals("SMSNotifications.txt",filename);
    }
}
