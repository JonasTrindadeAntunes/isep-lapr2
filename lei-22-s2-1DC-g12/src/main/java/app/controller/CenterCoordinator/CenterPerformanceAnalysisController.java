package app.controller.CenterCoordinator;

import app.controller.App;
import app.domain.model.Employee.Employee;
import app.domain.model.Platform.Platform;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.Algorithms;
import app.domain.shared.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Center performance analysis controller.
 */
public class CenterPerformanceAnalysisController {

    /**
     * The App.
     */
    private App m_oApp;

    /**
     * The Platform.
     */
    private Platform m_oPlatform;

    /**
     * The VaccinationCenter.
     */
    private VaccinationCenter vaccinationCenter;


    /**
     * Instantiates a new Center performance analysis controller.
     */
    public CenterPerformanceAnalysisController(){
        this.m_oApp = App.getInstance();
        this.m_oPlatform = m_oApp.getCompany().getPlatform();
    }

    /**
     * Check login .
     *
     * @return the boolean
     */
    public boolean checkLogin() {
        return m_oApp.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CENTER_COORDINATOR);
    }

    /**
     * Gets vaccination center.
     *
     * @return the boolean
     */
    public boolean getVaccinationCenter()
    {
        try{
            Employee emp =  this.m_oPlatform.getRegisterEmployee().getEmployeeByEmail(m_oApp.getCurrentUserSession().getUserId().getEmail());
            this.vaccinationCenter = emp.getWorkingVaccinationCenter();

        }catch (NullPointerException ex)
        {
            System.out.println("null vaccination center or null employee");
            return false;
        }

        return true;
    }

    /**
     * Gets list performance.
     *
     * @param day          the day
     * @param timeInterval the time interval
     */
    public String getListPerformance(Object day, int timeInterval)
    {
        Date d = (Date) day;



        List<String[]> performanceDataListString = this.m_oPlatform.getImportPerformanceFromCSV().getPerformanceDataListString();
        vaccinationCenter.getCenterPerformanceAnalysis().setListsOfArrivalsAndDepartures(performanceDataListString);
        int[] seq = vaccinationCenter.getCenterPerformanceAnalysis().differenceBetweenArrivalsAndDepartures(d,timeInterval);
        int[] bruteForceAlgorithm  = Algorithms.bruteForceAlgorithm(seq);

        return processAllDataBeforeShowing(seq,bruteForceAlgorithm,timeInterval);
    }

    /**
     * Process all data before showing.
     *
     * @param seq                 the seq
     * @param bruteForceAlgorithm the brute force algorithm
     * @param timeInterval        the time interval
     */
    public String processAllDataBeforeShowing(int[] seq,int[] bruteForceAlgorithm, int timeInterval)
    {
        return this.vaccinationCenter.getCenterPerformanceAnalysis().processAllDataBeforeShowing(seq,bruteForceAlgorithm,timeInterval);
    }

    /**
     * Complexity analysis.
     *
     * @param day the day
     */
    public String complexityAnalysis(Object day)
    {
        Date d = (Date) day;

        List<Integer> timesIntervals = new ArrayList<>(){{
            add(24);
            add(36);
            add(72);
            add(144);
            add(720);
        }};

        String s = "";
        for(Integer timeInterval : timesIntervals)
        {
            int[] seq = this.vaccinationCenter.getCenterPerformanceAnalysis().differenceBetweenArrivalsAndDepartures(d,timeInterval);

            long start = System.nanoTime();
            int[] bruteForceAlgorithm  = Algorithms.bruteForceAlgorithm(seq);
            long end = System.nanoTime();
            s+=("For m=" +timeInterval +" Elapsed Time in nano seconds= "+ (end-start) ) + "\n";

        }
        return s;
    }
}
