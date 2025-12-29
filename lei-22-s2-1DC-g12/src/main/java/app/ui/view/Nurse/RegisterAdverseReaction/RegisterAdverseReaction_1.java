package app.ui.view.Nurse.RegisterAdverseReaction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAdverseReaction_1 implements Initializable {

    public TextField textFieldSNSUserNumber;
    private RegisterAdverseReactionGUI registerAdverseReactionGUI;

    @FXML
    public Label snsUserLabel;


    public void setRegisterAdverseReactionGUI(RegisterAdverseReactionGUI registerAdverseReactionGUI) {
        this.registerAdverseReactionGUI = registerAdverseReactionGUI;
    }

    public void buttonConfirmAction(ActionEvent actionEvent) {
        registerAdverseReactionGUI.getSNSUser(textFieldSNSUserNumber.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
