package app.domain.shared;

import java.util.ResourceBundle;
/**
 * Class used to store the current message bundle so that every class has global access.
 * All messages should be retrived by the getString method.
 */
public class MessageBundle {

    private static ResourceBundle messageBundle;

    public static void setMessageBundle(ResourceBundle messageBundle) {
        MessageBundle.messageBundle = messageBundle;
    }

    public static ResourceBundle getMessageBundle(){
        return messageBundle;
    }

    public static String getString(String key) {
        return messageBundle.getString(key);
    }
}
