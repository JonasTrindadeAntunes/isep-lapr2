package app.ui.console.RoleUI;


import app.ui.console.CenterCoordinator.CenterPerformanceAnalysisUI;
import app.ui.console.CenterCoordinator.ExportVaccinationStatisticsUI;
import app.ui.console.CenterCoordinator.ImportDataUI;
import app.ui.console.CenterCoordinator.SortDataUI;
import app.ui.console.MenuItem;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable{
    public CenterCoordinatorUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Import data from a legacy system", new ImportDataUI()));
        options.add(new MenuItem("Sorting Algorithms for performance", new SortDataUI()));
        options.add(new MenuItem("Analyse the Center Performance", new CenterPerformanceAnalysisUI()));
        options.add(new MenuItem("Export Vaccination Statistics", new ExportVaccinationStatisticsUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\n" + "Center Coordinator Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }

        }
        while (option != -1 );
    }
}
