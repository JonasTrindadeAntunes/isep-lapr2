package app.ui.view.CenterCoordinator.ImportPerformanceFromCSV;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportPerformanceFromCSV_1 implements Initializable {

    ImportPerformanceFromCSVGUI importPerformanceFromCSVGUI;
    @FXML
    public ChoiceBox<String> choiceBox;

    private File selectedDirectory;

    public void confirmButtonAction(ActionEvent actionEvent) {
        if(selectedDirectory == null || selectedDirectory.toString().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Invalid file path!");
            a.show();
            return;
        }
        importPerformanceFromCSVGUI.importPerformanceFromCSV(selectedDirectory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setImportPerformanceFromCSVGUI(ImportPerformanceFromCSVGUI importPerformanceFromCSVGUI) {
        this.importPerformanceFromCSVGUI = importPerformanceFromCSVGUI;
    }

    public void choiceBoxClicked(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file to import:");
        selectedDirectory = fileChooser.showOpenDialog(importPerformanceFromCSVGUI.getMainStage());
        choiceBox.getItems().add(selectedDirectory.getName());
        choiceBox.setValue(selectedDirectory.getName());
    }
}
