package app.ui.view.CenterCoordinator.SortData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SortData_2 implements Initializable {
    @FXML
    public ListView<String> listView;

    private SortDataGUI sortDataGUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sortButtonAction(ActionEvent actionEvent) {
        this.sortDataGUI.start();
    }

    public void exitButtonAction(ActionEvent actionEvent) {
        this.sortDataGUI.backToCoordinatorMenu();
    }

    public void setSortDataGUI(SortDataGUI sortDataGUI) {
        this.sortDataGUI = sortDataGUI;
    }

    public void setData(List<String> list) {
        listView.getItems().addAll(list);
    }
}
