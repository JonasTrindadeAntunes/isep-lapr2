package app.ui.view.CenterCoordinator.CenterPerformanceAnalysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class CenterPerformanceAnalysis_1 implements Initializable {
    @FXML
    public TextField timeIntervalTextField;
    @FXML
    public DatePicker reportDate;

    private CenterPerformanceAnalysisGUI centerPerformanceAnalysisGUI;

    public void confirmButtonAction(ActionEvent actionEvent) {
        Date date = Date.from(reportDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        centerPerformanceAnalysisGUI.showData(date,Integer.parseInt(timeIntervalTextField.getText()));
    }

    public void setCenterPerformanceAnalysisGUI(CenterPerformanceAnalysisGUI centerPerformanceAnalysisGUI) {
        this.centerPerformanceAnalysisGUI = centerPerformanceAnalysisGUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
