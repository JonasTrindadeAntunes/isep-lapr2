package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.Platform.Platform;
import app.domain.shared.Algorithms;
import app.domain.shared.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sort data controller.
 */
public class SortDataController {

    /**
     * The App.
     */
    private App m_oApp;

    /**
     * The Platform.
     */
    private Platform m_oPlatform;

    /**
     * The Performance data string list.
     */
    private List<String[]> performanceDataListString;


    /**
     * Instantiates a new Sort data controller.
     */
    public SortDataController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();

    }

    /**
     * Check login .
     *
     * @return the boolean
     */
    public boolean checkLogin(){
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CENTER_COORDINATOR);
    }

    /**
     * Select type of data to sort list.
     *
     * @return the list
     */
    public List<String> selectTypeOfDataToSort()
    {
        List<String> listOfDataToSort = new ArrayList<>();

        listOfDataToSort.add("Sort data by arrival time using Bubble sort");
        listOfDataToSort.add("Sort data by arrival time using QuickSort");
        listOfDataToSort.add("Sort data by center leaving time using Bubble sort");
        listOfDataToSort.add("Sort data by center leaving time using QuickSort");

        return listOfDataToSort;
    }

    public List<String> selectFieldToSortBy(){
        List<String> listOfDataToSort = new ArrayList<>();

        listOfDataToSort.add("Arrival time");
        listOfDataToSort.add("Leaving time");

        return listOfDataToSort;
    }

    public List<String> selectSortingAlgorithm(){
        List<String> listOfDataToSort = new ArrayList<>();

        listOfDataToSort.add("Bubble sort");
        listOfDataToSort.add("Quick sort");

        return listOfDataToSort;
    }

    public List<String> sortBy(int field,int algorithm){
        int typeOfData = field * 2 + algorithm;
        return sort(typeOfData);
    }

    public List<String> sortBy(int typeOfData){
        return sort(typeOfData);
    }
    /**
     * Sort by arrival time.
     *
     * @param typeOfData the type of data
     */
    public List<String> sort(int typeOfData)
    {

        if(typeOfData == 0)
        {
            performanceDataListString = this.m_oPlatform.getImportPerformanceFromCSV().getPerformanceDataListString();
            long start = System.nanoTime();
            Algorithms.bubbleSortDates(5,performanceDataListString);
            long end = System.nanoTime();

            return getData();
        }
        else if(typeOfData == 1)
        {
            performanceDataListString = this.m_oPlatform.getImportPerformanceFromCSV().getPerformanceDataListString();
            long start = System.nanoTime();
            Algorithms.quickSort(5,performanceDataListString,0,performanceDataListString.size()-1);
            long end = System.nanoTime();

            return getData();
        }else if(typeOfData == 2 )
        {

            performanceDataListString = this.m_oPlatform.getImportPerformanceFromCSV().getPerformanceDataListString();
            long start = System.nanoTime();
            Algorithms.bubbleSortDates(7,performanceDataListString);
            long end = System.nanoTime();

            return getData();

        }
        else if(typeOfData == 3)
        {
            performanceDataListString = this.m_oPlatform.getImportPerformanceFromCSV().getPerformanceDataListString();
            long start = System.nanoTime();
            Algorithms.quickSort(7,performanceDataListString,0,performanceDataListString.size()-1);
            long end = System.nanoTime();

            return getData();
        }
        return  null;
    }

    /**
     * Show data.
     */
    public List<String> getData()
    {
        List<String> list = new ArrayList<>();
        list.add("SNSUSerNumber VaccineName Dose LotNumber ScheduledDateTime ArrivalDateTime NurseAdministrationDateTime LeavingDateTime");
        int i=0;
        for (String[] line : performanceDataListString)
        {
            list.add(line[0]+" "+line[1]+" "+line[2]+" "+line[3]+" "+line[4]+" "+line[5]+" "+line[6]+" "+line[7] +" "+ line[8] +" "+ line[9] + "\n");
        }
        return list;
    }
}
