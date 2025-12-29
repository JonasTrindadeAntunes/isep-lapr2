package app.ui.view.Nurse.AdministerVaccine;

import app.domain.model.VaccineAdministration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministerVaccine_4 implements Initializable {

    @FXML
    public Label userLabel;
    @FXML
    public Label vaccineLabel;
    @FXML
    public Label lotNumberLabel;
    @FXML
    public Label dateLabel;

    private VaccineAdministration vaccineAdministration;

    AdminsterVaccineGUI adminsterVaccineGUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAdminsterVaccineGUI(AdminsterVaccineGUI adminsterVaccineGUI) {
        this.adminsterVaccineGUI = adminsterVaccineGUI;
    }

    public void presetVaccineAdministration(VaccineAdministration vaccineAdministration){
        this.vaccineAdministration = vaccineAdministration;
        this.userLabel.setText(vaccineAdministration.getSnsUser().getName());
        this.vaccineLabel.setText(vaccineAdministration.getVaccine().getName());
        this.lotNumberLabel.setText(vaccineAdministration.getLotNumber());
        this.dateLabel.setText(vaccineAdministration.getDate().toString());
    }

    public void confirmButtonAction(ActionEvent actionEvent) {
        adminsterVaccineGUI.confirmVaccineAdministration(vaccineAdministration);
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        adminsterVaccineGUI.cancel();
    }
}
