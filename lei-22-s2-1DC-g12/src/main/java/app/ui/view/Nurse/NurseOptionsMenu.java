package app.ui.view.Nurse;

import app.ui.view.Base;
import app.ui.view.Nurse.AdministerVaccine.AdminsterVaccineGUI;
import app.ui.view.Nurse.RegisterAdverseReaction.RegisterAdverseReactionGUI;
import app.ui.view.RoleUI.NurseGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class NurseOptionsMenu implements Initializable {
    @FXML
    public Label welcomeLabel;
    private NurseGUI nurseGUI;

    public NurseOptionsMenu(){

    }

    public NurseGUI getNurseGUI() {
        return nurseGUI;
    }

    public void setNurseGUI(NurseGUI nurseGUI) {
        this.nurseGUI = nurseGUI;
        welcomeLabel.setText("Welcome logged in as " + nurseGUI.getMainApp().getApp().getCurrentUserSession().getUserRoles().get(0).getId() + ".");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void logoutMenuItemAction(ActionEvent actionEvent) {
        this.nurseGUI.getMainApp().getApp().doLogout();
        this.nurseGUI.getMainApp().toMainScene();
    }

    public void exitMenuItemAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void administerVaccineAction(ActionEvent actionEvent) {
        AdminsterVaccineGUI adminsterVaccineGUI = new AdminsterVaccineGUI(this);
        adminsterVaccineGUI.start();
    }

    public void registerAdverseReactionAction(ActionEvent actionEvent) {
        RegisterAdverseReactionGUI registerAdverseReactionGUI = new RegisterAdverseReactionGUI(this);
        registerAdverseReactionGUI.start();
    }
}
