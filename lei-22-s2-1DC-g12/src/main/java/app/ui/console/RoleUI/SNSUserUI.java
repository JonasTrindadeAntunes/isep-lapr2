package app.ui.console.RoleUI;

import app.domain.shared.MessageBundle;
import app.ui.console.MenuItem;
import app.ui.console.SNSUser.ScheduleVaccinationUI;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sns user ui.
 */
public class SNSUserUI implements  Runnable{

    /**
     * Instantiates a new Sns user ui.
     */
    public SNSUserUI(){

    }

    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem(MessageBundle.getString("schedulevaccination"), new ScheduleVaccinationUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\n" + MessageBundle.getString("snsmenu"));

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );



    }
}
