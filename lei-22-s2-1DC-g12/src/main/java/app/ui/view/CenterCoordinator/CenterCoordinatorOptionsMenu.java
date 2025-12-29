package app.ui.view.CenterCoordinator;

import app.ui.view.CenterCoordinator.CenterPerformanceAnalysis.CenterPerformanceAnalysisGUI;
import app.ui.view.CenterCoordinator.ExportVaccinationStatistics.ExportVaccinationStatisticsGUI;
import app.ui.view.CenterCoordinator.ImportPerformanceFromCSV.ImportPerformanceFromCSVGUI;
import app.ui.view.CenterCoordinator.SortData.SortDataGUI;
import app.ui.view.RoleUI.CenterCoordinatorGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CenterCoordinatorOptionsMenu implements Initializable {

    @FXML
    public Label welcomeLabel;

    private CenterCoordinatorGUI centerCoordinatorGUI;

    public CenterCoordinatorOptionsMenu(){

    }

    public CenterCoordinatorGUI getCenterCoordinatorGUI() {
        return centerCoordinatorGUI;
    }

    public void setCenterCoordinatorGUI(CenterCoordinatorGUI centerCoordinatorGUI) {
        this.centerCoordinatorGUI = centerCoordinatorGUI;
        this.welcomeLabel.setText("Welcome logged in as a " + centerCoordinatorGUI.getMainApp().getApp().getCurrentUserSession().getUserRoles().get(0).getId() +".");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void exportVaccinationStatistics(ActionEvent event) {
        ExportVaccinationStatisticsGUI exportVaccinationStatisticsGUI = new ExportVaccinationStatisticsGUI(this);
        exportVaccinationStatisticsGUI.start();
    }

    @FXML
    void importPerformanceFromCSV(ActionEvent event) {
        ImportPerformanceFromCSVGUI importPerformanceFromCSVGUI = new ImportPerformanceFromCSVGUI(this);
        importPerformanceFromCSVGUI.start();
    }

    @FXML
    public void logoutMenuItemAction(ActionEvent actionEvent) {
        this.centerCoordinatorGUI.getMainApp().getApp().doLogout();
        this.centerCoordinatorGUI.getMainApp().toMainScene();
    }

    public void exitMenuItemAction(ActionEvent actionEvent) {
        Platform.exit();
    }


    public void centerPerformanceAnalysis(ActionEvent actionEvent) {
        CenterPerformanceAnalysisGUI centerPerformanceAnalysisGUI = new CenterPerformanceAnalysisGUI(this);
        centerPerformanceAnalysisGUI.start();
    }

    public void sortData(ActionEvent actionEvent) {
        SortDataGUI sortDataGUI = new SortDataGUI(this);
        sortDataGUI.start();
    }
}
