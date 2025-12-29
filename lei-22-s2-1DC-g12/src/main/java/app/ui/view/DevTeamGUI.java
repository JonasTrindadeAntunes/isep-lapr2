package app.ui.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DevTeamGUI implements Initializable {

    MainUI mainUI;


    public void returnButtonAction(ActionEvent actionEvent) {
        mainUI.toMainScene();
    }

    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
