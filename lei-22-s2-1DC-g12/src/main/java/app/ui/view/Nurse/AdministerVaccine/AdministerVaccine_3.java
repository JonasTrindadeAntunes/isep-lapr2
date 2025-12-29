package app.ui.view.Nurse.AdministerVaccine;

import app.domain.model.AdverseReaction;
import app.domain.model.SNSUser;
import app.domain.model.Vaccine.Vaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministerVaccine_3 implements Initializable {
    AdminsterVaccineGUI adminsterVaccineGUI;

    @FXML
    public Label dosageLabel;
    @FXML
    public TextField lotNumberTextField;
    @FXML
    public ListView<AdverseReaction> listView;

    private SNSUser user;

    private Vaccine vaccine;

    public void btnConfirm(ActionEvent actionEvent) {
        adminsterVaccineGUI.confirmVaccineAdministration(user,vaccine,lotNumberTextField.getText());
    }

    public void setAdminsterVaccineGUI(AdminsterVaccineGUI adminsterVaccineGUI) {
        this.adminsterVaccineGUI = adminsterVaccineGUI;
    }

    public void setFields(Vaccine vaccine, SNSUser user){
        this.vaccine = vaccine;
        this.user = user;
        dosageLabel.setText(adminsterVaccineGUI.controller.getDosages(vaccine).toString());
        listView.getItems().addAll(adminsterVaccineGUI.controller.getAdverReactions(user));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
