package app.tasks;

import app.controller.App;
import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.shared.MessageBundle;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class RecordDailyTask extends TimerTask {

    /**
     * The App.
     */
    private App m_oApp;

    public RecordDailyTask(App app){

        this.m_oApp = app;
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();

        try {
            File file = new File("dailyTotalVac.csv");
            PrintWriter writeFile = new PrintWriter(file);
            String name;
            List<VaccinationCenter> listVaccinationCenter = m_oApp.getCompany().getPlatform().getRegisterVaccinationCenter().getListVaccinationCenters();
            for (VaccinationCenter vacCent : listVaccinationCenter) {
                name = vacCent.getName();
                for (Day day : vacCent.getOperatingDays())
                    writeFile.printf(toString(day.getDay(),name, day.getFullyVaccinated()));
            }
            writeFile.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public String toString(Date date, String name, int numTotalVac){
        return String.format(MessageBundle.getString("totalnumvactoday") + "%s: %d;\n",date.toString(),name,numTotalVac);
    }
}
