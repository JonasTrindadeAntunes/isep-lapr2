package app.ui.view.Nurse.RegisterAdverseReaction;

import app.domain.model.SNSUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAdverseReaction_2 implements Initializable {
    @FXML
    public Label vaccineAdministrationLabel;
    @FXML
    public ListView<String> listView;

    private RegisterAdverseReactionGUI registerAdverseReactionGUI;

    public void confirmButtonAction(ActionEvent actionEvent) {
        this.registerAdverseReactionGUI.getAdverseReactionDescription(listView.getSelectionModel().getSelectedIndex());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setRegisterAdverseReactionGUI(RegisterAdverseReactionGUI registerAdverseReactionGUI) {
        this.registerAdverseReactionGUI = registerAdverseReactionGUI;
    }

    public void getSNSUserVaccineAdministrations(SNSUser user){
        listView.getItems().addAll(registerAdverseReactionGUI.controller.getVaccineAdministrations(user));
    }

    public void listViewClick(MouseEvent mouseEvent) {
        this.vaccineAdministrationLabel.setText(listView.getSelectionModel().getSelectedItem());
    }
}
