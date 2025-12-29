package app.ui.view.Nurse.AdministerVaccine;

import app.domain.model.SNSUser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministerVaccine_1 implements Initializable {

    AdminsterVaccineGUI adminsterVaccineGUI;

    @FXML
    Label snsUserLabel;

    @FXML
    ListView<SNSUser> listView;

    public void confirmButtonAction(ActionEvent actionEvent) {
        this.adminsterVaccineGUI.getAvaliableVaccines(listView.getSelectionModel().getSelectedItem());
    }

    public void listViewClick(MouseEvent mouseEvent) {
        SNSUser user = (SNSUser) listView.getSelectionModel().getSelectedItem();
        snsUserLabel.setText(user.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAdminsterVaccineGUI(AdminsterVaccineGUI adminsterVaccineGUI) {
        this.adminsterVaccineGUI = adminsterVaccineGUI;
        listView.getItems().addAll(adminsterVaccineGUI.controller.getListOfWaitingRoomUsers());
        if(listView.getItems().size() == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No users present in the waiting room!");
            a.show();
            this.adminsterVaccineGUI.nurseOptionsMenu.getNurseGUI().runNurseUI();
            return;
        }

    }
}
