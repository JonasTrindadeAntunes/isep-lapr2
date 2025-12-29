package app.ui.console.CenterCoordinator;
import app.controller.CenterCoordinator.ExportVaccinationStatisticsController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import java.util.Date;


public class ExportVaccinationStatisticsUI implements Runnable{

    private ExportVaccinationStatisticsController m_controller;

    public ExportVaccinationStatisticsUI(){
        m_controller = new ExportVaccinationStatisticsController();
    }

    public void run() {

        System.out.println("Export Vaccination Statistics");

        if (!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        if(m_controller.getVaccinationCenter())
        {

            Object beginDate = Utils.readDateFromConsole("Specify the beginning date to do the vaccination statistics");
            Object endDate = Utils.readDateFromConsole("Specify the end date to do the vaccination statistics");

           if(m_controller.addFullyVaccinatedUsers((Date) beginDate,(Date) endDate))
           {
               for (String s : m_controller.getData())
                   System.out.println(s);

               if (Utils.confirm(MessageBundle.getString("confirmdata") + " (S/N)")) {

                   String fileName = Utils.readLineFromConsole("Introduce the file name(ex:VaccinationStatistics.csv):");
                   boolean success = m_controller.exportVaccinationStatistics(fileName);

                   if (success) {
                       System.out.println(MessageBundle.getString("fileexportedto") + fileName);
                       System.out.println(MessageBundle.getString("operationsucessful"));
                   } else {
                       System.out.println(MessageBundle.getString("errorexporting"));
                   }
               } else
                   run();
           }else
               run();


        }else
        {
            System.out.println("null vaccination center");
            run();
        }






    }
}
