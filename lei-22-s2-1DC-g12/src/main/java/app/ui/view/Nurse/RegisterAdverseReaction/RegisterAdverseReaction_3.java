package app.ui.view.Nurse.RegisterAdverseReaction;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterAdverseReaction_3 implements Initializable {
    @FXML
    public TextArea textArea;

    private RegisterAdverseReactionGUI registerAdverseReactionGUI;

    public void setRegisterAdverseReactionGUI(RegisterAdverseReactionGUI registerAdverseReactionGUI) {
        this.registerAdverseReactionGUI = registerAdverseReactionGUI;
    }

    public void btnConfirmAction(ActionEvent actionEvent) {
        registerAdverseReactionGUI.registerAdverseReaction(textArea.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
