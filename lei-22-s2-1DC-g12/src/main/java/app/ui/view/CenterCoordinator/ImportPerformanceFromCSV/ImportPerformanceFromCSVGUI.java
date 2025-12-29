package app.ui.view.CenterCoordinator.ImportPerformanceFromCSV;

import app.controller.CenterCoordinator.ImportDataController;
import app.ui.view.CenterCoordinator.CenterCoordinatorOptionsMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportPerformanceFromCSVGUI  {
    CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu;

    ImportDataController controller;



    public ImportPerformanceFromCSVGUI(CenterCoordinatorOptionsMenu centerCoordinatorOptionsMenu)
    {
        this.centerCoordinatorOptionsMenu = centerCoordinatorOptionsMenu;
        controller = new ImportDataController();
    }

    public void start(){
        try{
            ImportPerformanceFromCSV_1 importPerformanceFromCSV_1 = (ImportPerformanceFromCSV_1) this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().replaceSceneContent("/fxml/ImportPerformanceFromCSV_1.fxml");
            importPerformanceFromCSV_1.setImportPerformanceFromCSVGUI(this);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Window getMainStage() {
        return this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().getMainApp().getStage();
    }

    public void importPerformanceFromCSV(File fileName){
        boolean success = controller.importPerformanceFromCSV(fileName);
        if(success){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("File sucessfully imported!");
            a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error importing file!");
            a.show();
        }
        backToCoordinatorMenu();
    }

    public void backToCoordinatorMenu() {
        this.centerCoordinatorOptionsMenu.getCenterCoordinatorGUI().runCenterCoordinatorGUI();
    }}
