package app.ui.console.RoleUI;

import app.domain.shared.MessageBundle;
import app.ui.console.Admin.*;
import app.ui.console.MenuItem;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem(MessageBundle.getString("registervaccinationcenter"), new RegisterVaccinationCenterUI()));
        options.add(new MenuItem(MessageBundle.getString("registeremployee"), new RegisterEmployeeUI()));
        options.add(new MenuItem(MessageBundle.getString("listemployeerole"), new ListEmployeeByRoleUI()));
        options.add(new MenuItem(MessageBundle.getString("specifyvaccinetype"), new SpecifyNewVaccineTypeUI()));
        options.add(new MenuItem(MessageBundle.getString("specifyvaccine"), new SpecifyNewVaccineUI()));
        options.add(new MenuItem(MessageBundle.getString("loadusersfromcsv"), new LoadUsersFileUI()));
        options.add(new MenuItem(MessageBundle.getString("exportsmsnotification"), new ExportNotificationsUI()));
        options.add(new MenuItem(MessageBundle.getString("serialization"), new SerializationUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\n" + MessageBundle.getString("adminmenu"));

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );
    }
}
