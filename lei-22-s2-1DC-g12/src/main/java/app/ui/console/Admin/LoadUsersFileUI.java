package app.ui.console.Admin;

import app.controller.Admin.LoadUsersFileController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.List;


/**
 * The type Load users file ui.
 */
public class LoadUsersFileUI implements Runnable{

    private LoadUsersFileController m_controller;

    /**
     * Instantiates a new Load users file ui.
     */
    public LoadUsersFileUI(){this.m_controller = new LoadUsersFileController();}


    public void run() {

        System.out.println(MessageBundle.getString("loadusersfromcsv"));

        if(!m_controller.checkLogin()) {
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


                if (choice == null) {
                    System.out.println(MessageBundle.getString("backtoadminmenu"));

                } else if (Utils.confirm(MessageBundle.getString("importdatafromcvsfile") + " (S/N)")) {

                    if (m_controller.loadUsersFromCSV(choice.toString()))
                        m_controller.showData();
                    else
                        run();

                    if (Utils.confirm(MessageBundle.getString("confirmdata") + " (S/N)")) {
                        m_controller.registerListStringUsersInSystem();
                        printSystemUsers();
                        System.out.println(MessageBundle.getString("operationsucessful"));
                        System.out.println(MessageBundle.getString("backtoadminmenu"));

                    } else
                        System.out.println(MessageBundle.getString("backtoadminmenu"));

                } else
                    run();

            } else {
                System.out.println(MessageBundle.getString("thereisnocvstoimport"));
                System.out.println(MessageBundle.getString("backtoadminmenu"));
            }
        }catch (NullPointerException ex)
            {
                System.out.println(MessageBundle.getString("filepathdoesntexist"));
                run();
            }

        }catch (NumberFormatException ex)
        {
            System.out.println(MessageBundle.getString("optionneedstobeanumber"));
            run();
        }

    }

    /**
     * Print system users.
     */
    public void printSystemUsers(){
        System.out.println("\n" + MessageBundle.getString("listofusersinthesystemafteradding"));

        List<UserDTO> usersMapper = m_controller.printSystemUsers();

        for(UserDTO user : usersMapper)
        {
            System.out.println(String.format(MessageBundle.getString("name") + ":%s  " + MessageBundle.getString("email") + " :%s "+  MessageBundle.getString("role") + ":%s ", user.getName(), user.getId()  ,user.getRoles().get(0).getDescription()));
        }
    }
}

