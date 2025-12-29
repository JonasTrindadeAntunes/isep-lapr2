package app.ui.view;

import app.controller.SelectVaccinationCenterController;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.view.RoleUI.NurseGUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectWorkingVaccinationCenterGUI implements Initializable {
    private SelectVaccinationCenterController controller;
    private NurseGUI nurseGui;


    @FXML
    ChoiceBox<VaccinationCenter> choiceBox;


    public SelectWorkingVaccinationCenterGUI(){
        controller = new SelectVaccinationCenterController();
    }

    public void setNurseGui(NurseGUI nurseGui) {
        this.nurseGui = nurseGui;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().addAll(controller.getListVaccinationCenter());
    }

    public void btnConfirm(ActionEvent actionEvent) {
        controller.setWorkingVaccinationCenter(choiceBox.getValue());
        nurseGui.runNurseUI();
    }

}
