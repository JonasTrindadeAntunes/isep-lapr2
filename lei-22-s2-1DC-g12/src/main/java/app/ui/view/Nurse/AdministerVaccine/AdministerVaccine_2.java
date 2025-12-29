package app.ui.view.Nurse.AdministerVaccine;

import app.domain.model.SNSUser;
import app.domain.model.Vaccine.Vaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdministerVaccine_2 implements Initializable {
    private AdminsterVaccineGUI adminsterVaccineGUI;
    private SNSUser user;
    @FXML
    ChoiceBox<Vaccine> choiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAdminsterVaccineGUI(AdminsterVaccineGUI adminsterVaccineGUI) {
        this.adminsterVaccineGUI = adminsterVaccineGUI;
    }

    public void setUser(SNSUser user){
        this.user = user;
        choiceBox.getItems().addAll(adminsterVaccineGUI.controller.getAvaliableVaccines(user));
    }

    public void btnConfirm(ActionEvent actionEvent) {
        adminsterVaccineGUI.getDosageAndAdverReactions(this.user,choiceBox.getValue());
    }
}
