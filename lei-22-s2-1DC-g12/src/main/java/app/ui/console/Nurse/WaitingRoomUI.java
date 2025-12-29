package app.ui.console.Nurse;

import app.controller.Nurse.WaitingRoomController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


/**
 * The type Waiting room ui.
 */
public class WaitingRoomUI implements Runnable{



    private WaitingRoomController m_controller;


    /**
     * Instantiates a new Waiting room ui.
     */
    public WaitingRoomUI(){this.m_controller = new WaitingRoomController();}

    public void run() {
        System.out.println(MessageBundle.getString("listsnsuserwaitingroom"));

        if(!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        if (m_controller.getWorkingVaccinationCenter() == null) {
            System.out.println(MessageBundle.getString("backtonursemenu"));
        } else {
            showData();

            if (Utils.confirm(MessageBundle.getString("confirmdata") +"(S/N)"))
            {
                System.out.println(MessageBundle.getString("operationsucessful"));
            }
            else
                run();
        }


    }

    private void showData() {
        Utils.showList(m_controller.getListUsersInWaitingRoom(m_controller.getWorkingVaccinationCenter()), MessageBundle.getString("waitingroomsnsuserorder"));
    }

}
