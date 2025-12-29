package app.ui.console.RoleUI;

import app.domain.shared.MessageBundle;
import app.ui.console.MenuItem;
import app.ui.console.Nurse.RecordAdverseReactionUI;
import app.ui.console.Nurse.WaitingRoomUI;
import app.ui.console.SelectVaccinationCenterUI;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Nurse ui.
 */
public class NurseUI implements Runnable{

    /**
     * Instantiates a new Nurse ui.
     */
    public NurseUI()
    {
    }

    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        new SelectVaccinationCenterUI().run();

        options.add(new MenuItem(MessageBundle.getString("listsnsuserwaitingroom"), new WaitingRoomUI()));
        options.add(new MenuItem(("Record adverse reactions of the SNS user"), new RecordAdverseReactionUI()));


        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\n"+ MessageBundle.getString("nursemenu"));

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );
    }


}




