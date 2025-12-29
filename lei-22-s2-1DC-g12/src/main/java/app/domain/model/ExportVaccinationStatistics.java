package app.domain.model;


import app.domain.model.VaccinationCenter.Day;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

/**
 * Export vaccination statistics.
 */
public class ExportVaccinationStatistics implements Serializable {

    /**
     * The list of vaccination statistics.
     */
    private List<String> listVaccinationStatistics;


    /**
     * Instantiates a new Export vaccination statistics.
     */
    public ExportVaccinationStatistics()
    {
        this.listVaccinationStatistics = new ArrayList<>();
    }


    /**
     * Add fully vaccinated sns users.
     *
     * @param vaccinationCenter the vaccination center
     * @param beginDate         the begin date
     * @param endDate           the end date
     */
    public void addFullyVaccinatedSNSUsers(VaccinationCenter vaccinationCenter, Date beginDate, Date endDate) {
        List<String> listVaccinationStatistics = new ArrayList<>();
        List<Day> operatingDays = vaccinationCenter.getOperatingDays();


        for(Day day : operatingDays)
        {
            if(beginDate.getTime() <= day.getDay().getTime() && day.getDay().getTime() <= endDate.getTime())
                addFullyVaccination(day.getFullyVaccinated(),day.getDay());
        }

    }

    /**
     * Add fully vaccination.
     *
     * @param countTotalVaccinations the count total vaccinations
     * @param day                    the day
     * @return the boolean
     */
    public boolean addFullyVaccination(int countTotalVaccinations, Date day)
    {
        return this.listVaccinationStatistics.add(buildString(countTotalVaccinations,day));
    }

    /**
     * build a string with total number of people fully vaccinated in each day.
     */
    private String buildString(int countTotalVaccinations, Date day)
    {
        return "Total vaccinated people=" + countTotalVaccinations + " in day " + day;
    }


    /**
     * Export vaccination statistics boolean.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean exportVaccinationStatistics(String fileName){
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
            for (String s : listVaccinationStatistics){
                out.println(s);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }


    /**
     * Gets data.
     *
     * @return the data
     */
    public List<String> getData()
    {
        List<String> list = new ArrayList<>();
        for(String fullyVaccinated: listVaccinationStatistics)
        {
            list.add(fullyVaccinated);
        }
        return list;
    }

}
