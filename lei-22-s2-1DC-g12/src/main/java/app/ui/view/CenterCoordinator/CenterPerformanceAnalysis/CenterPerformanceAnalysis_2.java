package app.ui.view.CenterCoordinator.CenterPerformanceAnalysis;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CenterPerformanceAnalysis_2 implements Initializable {


    public Label dataLabel;
    private CenterPerformanceAnalysisGUI centerPerformanceAnalysisGUI;
    private boolean cont = false;


    public void setCenterPerformanceAnalysisGUI(CenterPerformanceAnalysisGUI centerPerformanceAnalysisGUI) {
        this.centerPerformanceAnalysisGUI = centerPerformanceAnalysisGUI;
    }

    public void setText(String text) {
        dataLabel.setText(text);
    }

    public void continueButtonAction(ActionEvent actionEvent) {
        if(cont == true) {
            centerPerformanceAnalysisGUI.backToCoordinatorMenu();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Complexity analysis");
        alert.setContentText("Do you want to see the complexity analysis for predetermined list of time intervals ( m = 24,36,72,144,720) ?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                cont = true;
                this.setText(centerPerformanceAnalysisGUI.getComplexityAnalysis());
            } else if (type == noButton) {
                centerPerformanceAnalysisGUI.backToCoordinatorMenu();
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
