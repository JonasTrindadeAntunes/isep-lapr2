package app.ui.console.RoleUI;

import app.domain.shared.MessageBundle;
import app.ui.console.MenuItem;
import app.ui.console.Receptionist.RegisterSNSUserArrivalUI;
import app.ui.console.Receptionist.RegisterSNSUserUI;
import app.ui.console.Receptionist.RegisterVaccineScheduleUI;
import app.ui.console.SelectVaccinationCenterUI;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Receptionist ui.
 */
public class ReceptionistUI implements Runnable {


    /**
     * Instantiates a new Receptionist ui.
     */
    public ReceptionistUI()
        {
        }

        public void run()
        {
            List<MenuItem> options = new ArrayList<MenuItem>();

            new SelectVaccinationCenterUI().run();

            options.add(new MenuItem(MessageBundle.getString("registersnsuser"), new RegisterSNSUserUI()));

            options.add(new MenuItem(MessageBundle.getString("schedulevaccination"), new RegisterVaccineScheduleUI()));

            options.add(new MenuItem(MessageBundle.getString("registersnsuserarrival"), new RegisterSNSUserArrivalUI()));

            int option = 0;
            do
            {
                option = Utils.showAndSelectIndex(options, "\n\n" + MessageBundle.getString("receptionistmenu"));

                if ( (option >= 0) && (option < options.size()))
                {
                    options.get(option).run();
                }

            }
            while (option != -1 );
        }
    }


