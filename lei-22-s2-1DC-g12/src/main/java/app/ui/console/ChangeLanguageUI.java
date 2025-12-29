package app.ui.console;

import app.controller.ChangeLanguageController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


public class ChangeLanguageUI implements Runnable{
    ChangeLanguageController controller;

    @Override
    public void run() {
        controller = new ChangeLanguageController();


        Object locale = Utils.showAndSelectOne(controller.getLanguageOptions(), MessageBundle.getString("selectlanguage"));

        if(locale == null) {
            System.out.println(MessageBundle.getString("operationcanceled"));
            return;
        }
        controller.chooseLanguage(locale);

        System.out.println(MessageBundle.getString("operationsucessful"));
        return;
    }
}
