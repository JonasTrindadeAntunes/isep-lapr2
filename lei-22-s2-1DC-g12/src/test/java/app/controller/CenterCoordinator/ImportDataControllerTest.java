package app.controller.CenterCoordinator;

import app.domain.model.LoadFromCSV;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportDataControllerTest {

    @Test
    void checkLogin() {
    }

    @Test
    void getListOfFiles() {
        ImportDataController idc = new ImportDataController();
        String path = "src/main/resources";
        idc.getListOfFiles(path);

        assertTrue(idc.getListOfFiles(path).contains("SNSUserDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv"));
    }

    @Test
    void importPerformanceFromCSV() {
        ImportDataController idc = new ImportDataController();
        String filename = "SNSUserDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv";

        assertTrue(idc.importPerformanceFromCSV(filename));
    }

    @Test
    void getListOfFilesException() {
        ImportDataController idc = new ImportDataController();
        String path = "SNS";
        assertNull(idc.getListOfFiles(path));
    }

    @Test
    void importPerformanceFromCSVException() {
        ImportDataController idc = new ImportDataController();
        String filename = "SNSTest";

        assertFalse(idc.importPerformanceFromCSV(filename));
    }
}