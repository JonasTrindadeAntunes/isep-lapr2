package app.ui.view.CenterCoordinator.SortData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SortData_1 implements Initializable {

    @FXML
    public ChoiceBox<String> fieldChoiceBox;
    @FXML
    public ChoiceBox<String> algorithmChoiceBox;

    private SortDataGUI sortDataGUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void confirmButtonAction(ActionEvent actionEvent) {
        sortDataGUI.getData(fieldChoiceBox.getValue(),algorithmChoiceBox.getValue());
    }


    public void setSortDataGUI(SortDataGUI sortDataGUI) {
        this.sortDataGUI = sortDataGUI;
        this.fieldChoiceBox.getItems().setAll(sortDataGUI.getController().selectFieldToSortBy());
        this.algorithmChoiceBox.getItems().setAll(sortDataGUI.getController().selectSortingAlgorithm());
    }
}
