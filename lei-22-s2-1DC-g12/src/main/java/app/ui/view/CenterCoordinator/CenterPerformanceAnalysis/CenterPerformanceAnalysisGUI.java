package app.ui.view.CenterCoordinator.CenterPerformanceAnalysis;

import app.controller.CenterCoordinator.CenterPerformanceAnalysisController;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import javafx.scene.control.Alert;

import java.util.Date;

public class CenterPerformanceAnalysisGUI {

    private CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu;
    private CenterPerformanceAnalysisController controller;
    private Date day;

    public CenterPerformanceAnalysisGUI(CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu){
        this.centerCoordinatorOptionsMenu = centerCoordinatorOptionsMenu;
        controller = new CenterPerformanceAnalysisController();
    }

    public void start(){
        if(!controller.getVaccinationCenter()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The center coordinator doesn't have a vaccination center assinged!");
            a.show();
            backToCoordinatorMenu();
            return;
        }
        try{
            CenterPerformanceAnalysis_1 centerPerformanceAnalysis_1 = (CenterPerformanceAnalysis_1) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/CenterPerformanceAnalysis_1.fxml");
            centerPerformanceAnalysis_1.setCenterPerformanceAnalysisGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void showData(Date day, int interval){
        if(interval < 1  || interval > 720){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The time interval must be between 1 and 720 minutes!");
            a.show();
            backToCoordinatorMenu();
            return;
        }
        this.day = day;
        try{
            CenterPerformanceAnalysis_2 centerPerformanceAnalysis_2 = (CenterPerformanceAnalysis_2) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/CenterPerformanceAnalysis_2.fxml");
            centerPerformanceAnalysis_2.setCenterPerformanceAnalysisGUI(this);
            centerPerformanceAnalysis_2.setText(controller.getListPerformance(day,interval));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void backToCoordinatorMenu() {
        this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().runCenterCoordinatorGUI();
    }

    public String getComplexityAnalysis() {
        if(day == null)
            return "No day selected.";
        return this.controller.complexityAnalysis(day);
    }
}
