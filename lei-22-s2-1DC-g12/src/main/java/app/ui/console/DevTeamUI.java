package app.ui.console;

import app.domain.shared.MessageBundle;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.println("\n");
        System.out.printf(MessageBundle.getString("develpmentteam") +"\n");
        System.out.printf("\t Jonas Antunes - 1181478@isep.ipp.pt \n");
        System.out.printf("\t Rui Franco - 1191038@isep.ipp.pt \n");
        System.out.printf("\t Guilherme Melo - 1211008@isep.ipp.pt \n");
        System.out.printf("\t Guilherme Coelho - 1211154@isep.ipp.pt \n");
        System.out.printf("\t Pedro Gomes - 1211690@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
