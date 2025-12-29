package app.ui.console.CenterCoordinator;

import app.controller.CenterCoordinator.ImportDataController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

public class ImportDataUI implements Runnable {

    private ImportDataController m_controller;


    public ImportDataUI() {
        this.m_controller = new ImportDataController();
    }

    public void run() {
        System.out.println(MessageBundle.getString("loaddatafromcsv"));


        if (!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        try {


            String path = Utils.readLineFromConsole(MessageBundle.getString("introducethepathtothefiles") + "( src/main/resources ) : ");
            while (path.isEmpty()) {
                System.out.println(MessageBundle.getString("pathcantbeempty"));
                path = Utils.readLineFromConsole(MessageBundle.getString("introducethepathtothefiles") + "( src/main/resources ) : ");

            }
            try {


                if (!m_controller.getListOfFiles(path).isEmpty()) {
                    Object choice = Utils.showAndSelectOne(m_controller.getListOfFiles(path), MessageBundle.getString("selectcvsfile"));


                    if (choice == null)
                    {
                        System.out.println("Back to Receptionist menu");

                    } else if (Utils.confirm(MessageBundle.getString("importdatafromcvsfile") + " (S/N)")) {

                         m_controller.importPerformanceFromCSV(choice.toString());


                    }


                }
            } catch (NullPointerException ex) {
                System.out.println(MessageBundle.getString("filepathdoesntexist"));
                run();
            }

        } catch (NumberFormatException ex) {
            System.out.println(MessageBundle.getString("optionneedstobeanumber"));
            run();
        }

        System.out.println("Successful operation");


    }
}