package app.ui.view.CenterCoordinator.ExportVaccinationStatistics;

import app.controller.CenterCoordinator.ExportVaccinationStatisticsController;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class ExportVaccinationStatisticsGUI {

    private ExportVaccinationStatisticsController controller;

    private CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu;

    public ExportVaccinationStatisticsGUI(CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu){
        this.centerCoordinatorOptionsMenu = centerCoordinatorOptionsMenu;
        controller = new ExportVaccinationStatisticsController();
    }

    public void start() {
        if(!controller.getVaccinationCenter()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The center coordinator doesn't have a vaccination center assinged!");
            a.show();
            backToCoordinatorMenu();
            return;
        }
        try {
            ExportVaccinationStatistics_1 exportVaccinationStatistics_1 = (ExportVaccinationStatistics_1) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/ExportVaccinationStatistics_1.fxml");
            exportVaccinationStatistics_1.setExportVaccinationStatisticsGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    public List<String> getData() {
        return controller.getData();
    }

    public void exportFile(String selectedDirectory) {
        boolean success = controller.exportVaccinationStatistics(selectedDirectory);
        if(success){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("File sucessfully exported!");
            a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error exporting file!");
            a.show();
        }
        backToCoordinatorMenu();
        return;

    }

    public void backToCoordinatorMenu() {
        this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().runCenterCoordinatorGUI();
    }

    public Stage getMainStage(){
        return this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().getStage();
    }

    public void getStatistics(Date start, Date end) {
        if(!controller.addFullyVaccinatedUsers(start,end)){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error creating data!");
            a.show();
            backToCoordinatorMenu();
            return;
        }
        try {
            ExportVaccinationStatistics_2 exportVaccinationStatistics_2 = (ExportVaccinationStatistics_2) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/ExportVaccinationStatistics_2.fxml");
            exportVaccinationStatistics_2.setExportVaccinationStatisticsGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
