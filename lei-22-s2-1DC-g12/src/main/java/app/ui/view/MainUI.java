package app.ui.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import app.ui.view.RoleUI.CenterCoordinatorGUI;
import app.ui.view.RoleUI.NurseGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

public class MainUI implements Initializable {

    private Base mainApp;


    @FXML
    private Label labelTop;

    @FXML
    private TextField emailLabel;
    @FXML
    private PasswordField passwordLabel;

    public MainUI() {
    }

    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainApp(Base mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        try {
            boolean success = false;

            success = mainApp.getApp().doLogin(emailLabel.getText(), passwordLabel.getText());
            if (success) {
                emailLabel.setText("");
                passwordLabel.setText("");
                List<UserRoleDTO> roles = mainApp.getApp().getCurrentUserSession().getUserRoles();
                if ((roles == null) || (roles.isEmpty()) || roles.size() > 1) {
                    labelTop.setText(MessageBundle.getString("usernoroleassigned"));
                    labelTop.setTextFill(Color.color(1, 0, 0));
                } else {
                    UserRoleDTO role = roles.get(0);
                    if (!Objects.isNull(role)) {
                        this.redirectToRoleUI(role);
                    } else {
                        labelTop.setText(MessageBundle.getString("usernoroleselected"));
                        labelTop.setTextFill(Color.color(1, 0, 0));
                    }
                }
            }else{
                labelTop.setText(MessageBundle.getString("invalidlogin"));
                labelTop.setTextFill(Color.color(1, 0, 0));
            }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    private void redirectToRoleUI(UserRoleDTO role) {

        ScreenGUI screenGUI = returnMenuItem(role);
        if (screenGUI == null) {
            labelTop.setText(MessageBundle.getString("nouiforrole") + "'" + role.getDescription() + "'");
            labelTop.setTextFill(Color.color(1, 0, 0));
        }

        screenGUI.run();
    }




    private List<MenuItemUI> getMenuItemForRoles() {
        List<MenuItemUI> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItemUI(Constants.ROLE_NURSE, new NurseGUI(mainApp)));
        rolesUI.add(new MenuItemUI(Constants.ROLE_CENTER_COORDINATOR, new CenterCoordinatorGUI(mainApp)));
        return rolesUI;
    }

    private ScreenGUI returnMenuItem(UserRoleDTO role)
    {
        for (MenuItemUI item : getMenuItemForRoles())
            if(role.getDescription().equals(item.getDescription()))
                return item.getScreenGUI();

        return null;
    }

    public void devTeamButtonAction(ActionEvent actionEvent) {
        try{
        DevTeamGUI devTeamGUI = (DevTeamGUI) this.mainApp.
                replaceSceneContent("/fxml/DevTeamUI.fxml");
        devTeamGUI.setMainUI(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exitButtonAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void toMainScene(){
        this.mainApp.toMainScene();
    }
}
