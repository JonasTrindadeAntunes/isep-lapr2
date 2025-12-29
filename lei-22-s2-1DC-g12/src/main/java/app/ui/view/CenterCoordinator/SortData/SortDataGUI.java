package app.ui.view.CenterCoordinator.SortData;

import app.controller.CenterCoordinator.SortDataController;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import app.ui.view.CenterCoordinator.CenterPerformanceAnalysis.CenterPerformanceAnalysis_1;
import javafx.scene.control.Alert;

import java.util.Collections;
import java.util.List;

public class SortDataGUI {

    private CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu;
    private SortDataController controller;


    public SortDataGUI(CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu){
        this.centerCoordinatorOptionsMenu = centerCoordinatorOptionsMenu;
        controller = new SortDataController();
    }

    public void start(){
        try{
            SortData_1 sortData_1 = (SortData_1) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/SortData_1.fxml");
            sortData_1.setSortDataGUI(this);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public SortDataController getController() {
        return controller;
    }

    public void getData(String field, String algorithm) {
        if(field == null||algorithm == null || field.isEmpty() || algorithm.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("One of the fields wasn't selected!");
            a.show();
            return;
        }
        List<String> list = controller.sortBy(controller.selectFieldToSortBy().indexOf(field),controller.selectSortingAlgorithm().indexOf(algorithm));
        try{
            SortData_2 sortData_2 = (SortData_2) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/SortData_2.fxml");
            sortData_2.setSortDataGUI(this);
            sortData_2.setData(list);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void backToCoordinatorMenu() {
        this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().runCenterCoordinatorGUI();
    }

}
