package app.controller.CenterCoordinator;

import app.domain.model.ImportPerformanceFromCSV;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortDataControllerTest {

    @Test
    void checkLogin() {
    }

    @Test
    void selectTypeOfDataToSort() {
        SortDataController sdc = new SortDataController();
        List<String> listOfDataToSort = new ArrayList<>();

        listOfDataToSort.add("Sort data by arrival time using Bubble sort");
        listOfDataToSort.add("Sort data by arrival time using QuickSort");
        listOfDataToSort.add("Sort data by center leaving time using Bubble sort");
        listOfDataToSort.add("Sort data by center leaving time using QuickSort");

        assertTrue(sdc.selectTypeOfDataToSort().toString().contains(listOfDataToSort.toString()));
    }

   /* @Test
   // In progress
    void sortByArrivalTime() {
        SortDataController sdc = new SortDataController();
        ImportPerformanceFromCSV ipfc = new ImportPerformanceFromCSV()
        sdc.sortByArrivalTime(0);
        assertEquals();
        sdc.sortByArrivalTime(1);
    }

    */

    @Test
    void sortByLeavingTime() {
    }

}