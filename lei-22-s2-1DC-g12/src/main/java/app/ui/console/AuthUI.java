package app.ui.console;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;
import app.ui.console.RoleUI.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AuthUI implements Runnable{
    private AuthController ctrl;

    public AuthUI()
    {
        ctrl = new AuthController();
    }

    public void run()
    {
        boolean success = doLogin();

        if (success)
        {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ( (roles == null) || (roles.isEmpty()) )
            {
                System.out.println(MessageBundle.getString("usernoroleassigned"));
            }
            else
            {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role))
                {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI,role);
                }
                else
                {
                    System.out.println(MessageBundle.getString("usernoroleselected"));
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles()
    {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(Constants.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(Constants.ROLE_RECEPTIONIST, new ReceptionistUI()));
        rolesUI.add(new MenuItem(Constants.ROLE_NURSE, new NurseUI()));
        rolesUI.add(new MenuItem(Constants.ROLE_SNS_USER, new SNSUserUI()));
        rolesUI.add(new MenuItem(Constants.ROLE_CENTER_COORDINATOR, new CenterCoordinatorUI()));

        // To complete with other user roles and related RoleUI

        //
        return rolesUI;
    }

    private boolean doLogin()
    {
        System.out.println("\n" + MessageBundle.getString("loginui"));

        int maxAttempts = 3;
        boolean success = false;
        do
        {
            maxAttempts--;
            String id = Utils.readLineFromConsole(MessageBundle.getString("insertidoremail"));
            String pwd = Utils.readLineFromConsole(MessageBundle.getString("enterpassword"));

            success = ctrl.doLogin(id, pwd);
            if (!success)
            {
                System.out.println(MessageBundle.getString("invalidlogin") + "\n" + String.format(MessageBundle.getString("youhavexmoretries"), maxAttempts));
            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout()
    {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role)
    {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found)
        {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found)
                item.run();
        }
        if (!found)
            System.out.println(MessageBundle.getString("nouiforrole") + "'" + role.getDescription() + "'");
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles)
    {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, MessageBundle.getString("selectroletoadopt"));
    }


}
