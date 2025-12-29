package app.ui;

import app.controller.App;
import app.ui.console.MainMenuUI;
import app.ui.view.Base;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

//Teste
public class Main {

    public static void main(String[] args)
    {
        App app = App.getInstance();
        app.deserialize();
        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
            Base.launch(Base.class);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
