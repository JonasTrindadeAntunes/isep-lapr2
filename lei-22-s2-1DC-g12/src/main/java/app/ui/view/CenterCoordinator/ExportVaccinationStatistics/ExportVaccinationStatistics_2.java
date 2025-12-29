package app.ui.view.CenterCoordinator.ExportVaccinationStatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExportVaccinationStatistics_2 implements Initializable {

    private ExportVaccinationStatisticsGUI exportVaccinationStatisticsGUI;

    @FXML
    public ListView<String> listView;

    public void setExportVaccinationStatisticsGUI(ExportVaccinationStatisticsGUI exportVaccinationStatisticsGUI) {
        this.exportVaccinationStatisticsGUI = exportVaccinationStatisticsGUI;
        listView.getItems().addAll(exportVaccinationStatisticsGUI.getData());
    }

    public void confirmButtonAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the folder to export the file to:");
        File selectedDirectory = directoryChooser.showDialog(exportVaccinationStatisticsGUI.getMainStage());
        TextInputDialog textInput = new TextInputDialog();
        textInput.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
        textInput.setContentText("Select file name: (.csv will be added automatically)");
        Optional<String> input;
        do{
            input = textInput.showAndWait();
        }while(input == null || input.isEmpty());
        String path = selectedDirectory + "\\" + input.get() + ".csv";
        exportVaccinationStatisticsGUI.exportFile(path);
    }

    public void cancelButtonAction(ActionEvent actionEvent) {
        exportVaccinationStatisticsGUI.backToCoordinatorMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
