package app.ui.console.Admin;

import app.controller.Admin.ListEmployeeByRoleController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;


/**
 * The type List employee by role ui.
 */
public class ListEmployeeByRoleUI implements Runnable{

    private ListEmployeeByRoleController m_controller;

    /**
     * Instantiates a new List employee by role ui.
     */
    public ListEmployeeByRoleUI(){
        this.m_controller = new ListEmployeeByRoleController();
    }

    public void run(){

        System.out.println(MessageBundle.getString("employeebyrole"));

        int choice = Utils.showAndSelectIndex(this.m_controller.getRoles(),MessageBundle.getString("selectrole"));

        enterData(choice);

    }

    private void enterData(int roleChoice){

        Utils.showList(m_controller.getListEmployeeByRole(roleChoice),MessageBundle.getString("list"));

    }


}
