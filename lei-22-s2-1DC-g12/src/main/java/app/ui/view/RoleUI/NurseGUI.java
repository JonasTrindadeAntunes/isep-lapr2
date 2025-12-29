package app.ui.view.RoleUI;

import app.domain.model.Employee.Nurse;
import app.ui.view.Base;
import app.ui.view.Nurse.NurseOptionsMenu;
import app.ui.view.ScreenGUI;
import app.ui.view.SelectWorkingVaccinationCenterGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class NurseGUI implements ScreenGUI {
    private Base mainApp;
    private NurseOptionsMenu optionsMenu;

    @FXML
    private MenuItem logoutMenuItem;


    public NurseGUI(Base mainApp){
        this.mainApp = mainApp;
    }

    public Base getMainApp() {
        return this.mainApp;
    }


    @Override
    public void run() {
        try {
            SelectWorkingVaccinationCenterGUI selectWorkingVaccinationCenterGUI
                    = (SelectWorkingVaccinationCenterGUI) this.mainApp.replaceSceneContent("/fxml/SelectWorkingVaccinationCenter.fxml");
            selectWorkingVaccinationCenterGUI.setNurseGui(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void runNurseUI(){
        try {
            NurseOptionsMenu nurseOptionsMenu = (NurseOptionsMenu) this.mainApp.
                      replaceSceneContent("/fxml/NurseUI.fxml");
            nurseOptionsMenu.setNurseGUI(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
