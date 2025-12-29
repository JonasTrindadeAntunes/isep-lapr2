package app.ui.view.CenterCoordinator.ExportVaccinationStatistics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class ExportVaccinationStatistics_1 implements Initializable {

    @FXML
    public DatePicker dateStart;
    @FXML
    public DatePicker dateEnd;

    private ExportVaccinationStatisticsGUI exportVaccinationStatisticsGUI;


    public void setExportVaccinationStatisticsGUI(ExportVaccinationStatisticsGUI exportVaccinationStatisticsGUI) {
        this.exportVaccinationStatisticsGUI = exportVaccinationStatisticsGUI;
    }

    public void confirmButtonAction(ActionEvent actionEvent) {
        if(dateEnd.getValue().isBefore(dateStart.getValue())){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The start date can't be after the end date!");
            a.show();
            return;
        }
        Date start = Date.from(dateStart.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(dateEnd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        exportVaccinationStatisticsGUI.getStatistics(start,end);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
