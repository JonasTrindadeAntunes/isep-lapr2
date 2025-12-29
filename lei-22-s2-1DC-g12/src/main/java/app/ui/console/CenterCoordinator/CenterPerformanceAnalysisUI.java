package app.ui.console.CenterCoordinator;

import app.controller.CenterCoordinator.CenterPerformanceAnalysisController;
import app.domain.shared.MessageBundle;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterPerformanceAnalysisUI implements Runnable{

    private CenterPerformanceAnalysisController m_controller;

    public CenterPerformanceAnalysisUI(){
        this.m_controller = new CenterPerformanceAnalysisController();
    }

    public void run(){

        if (!m_controller.checkLogin()) {
            System.out.println(MessageBundle.getString("unauthorizeduser"));
            return;
        }

        if(m_controller.getVaccinationCenter())
        {

            Object date ;
            boolean finishedTimeIntervals = false;
            while(!finishedTimeIntervals)
            {
                System.out.println("Specify time intervals of m minutes");
                boolean done = false;
                List<Integer> timeIntervals = new ArrayList<>();
                while(!done)
                {
                    int interval = Utils.readIntegerFromConsole("Interval of m minutes");
                    if( 720 % interval != 0)
                    {
                        System.out.println("The remainder of the division 720/m needs to be 0");
                    }else{
                        timeIntervals.add(interval);
                        done = !Utils.confirm("Do you want to add more intervals? (S/N)");
                    }
                }

                date = Utils.readDateFromConsole("Specify the day to do the analysis");

                int count =0;
                while(count != timeIntervals.size())
                {
                    System.out.println(m_controller.getListPerformance(date,timeIntervals.get(count)));
                    count ++;
                }

                finishedTimeIntervals = !Utils.confirm("Do you want to repeat the process for different days and time intervals?(S/N)");


            }

            if(Utils.confirm("Do you want to see the complexity analysis for predetermined list of time intervals ( m = 1,5,10,20,30) ? (S/N)")) {
                date = Utils.readDateFromConsole("Specify the day to do the analysis");
                System.out.println(m_controller.complexityAnalysis(date));
            }

            if(Utils.confirm("Confirms data? (S/N)"))
                System.out.println("Successful operation");
            else
                run();

        }else
        {
            System.out.println("null vaccination center");
            run();
        }

    }
}
