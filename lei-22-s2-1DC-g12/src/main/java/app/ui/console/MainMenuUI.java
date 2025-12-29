package app.ui.console;

import app.controller.App;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The type Main menu ui.
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {

    /**
     * Instantiates a new Main menu ui.
     */
    public MainMenuUI()
    {
    }

    public void run() throws IOException
    {
        List<MenuItem> options;
        int option = 0;
        do
        {
            options = refreshOptions();
            option = Utils.showAndSelectIndexMainMenu(options, "\n" + MessageBundle.getString("mainmenu"));
            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }

    private List<MenuItem> refreshOptions(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem(MessageBundle.getString("login"), new AuthUI()));
        options.add(new MenuItem(MessageBundle.getString("develpmentteam"),new DevTeamUI()));
        options.add(new MenuItem(MessageBundle.getString("languageoptions"),new ChangeLanguageUI()));
        return options;
    }

}
