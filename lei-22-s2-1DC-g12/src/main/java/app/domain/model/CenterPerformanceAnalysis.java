package app.domain.model;



import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The type Center performance analysis.
 */
public class CenterPerformanceAnalysis implements Serializable {

    /**
     * The list of arrivals.
     */
    private List<Date> listOfArrivals = new ArrayList<>();
    /**
     * The list of departures.
     */
    private List<Date> listOfDepartures = new ArrayList<>();


    /**
     * Instantiates a new Center performance analysis.
     */
    public CenterPerformanceAnalysis()
    {

    }


    /**
     * Sets lists of arrivals and departures.
     *
     * @param performanceDataListString the performance data list string
     */
    public void setListsOfArrivalsAndDepartures(List<String[]> performanceDataListString)
    {
        listOfArrivals.clear();
        listOfDepartures.clear();

        for(int i=0 ; i < performanceDataListString.size() ; i++)
        {
            String[] listElement = performanceDataListString.get(i);

            try
            {

                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                Date listElementArrivalTime = format1.parse(listElement[5]);
                format2.format(listElementArrivalTime);

                Date listElementLeavingTime = format1.parse(listElement[7]);
                format2.format(listElementLeavingTime);

                listOfArrivals.add(listElementArrivalTime);
                listOfDepartures.add(listElementLeavingTime);

            } catch (ParseException e)
            {
                System.out.println("Date invalid format");
                e.getMessage();
            }
        }
    }


    /**
     * Difference between arrivals and departures int [ ].
     *
     * @param day          the day
     * @param timeInterval the time interval
     * @return the int [ ]
     */
    public int[] differenceBetweenArrivalsAndDepartures(Date day , int timeInterval)
    {
        int[] diff = new int[720 / timeInterval];

        int[] arrivals = countArrivalsOrDeparturesInTimeInterval(day,timeInterval,listOfArrivals);
        int[] departures = countArrivalsOrDeparturesInTimeInterval(day,timeInterval,listOfDepartures);

        for(int i = 0 ; i < diff.length; i++)
        {
            diff[i] = arrivals[i] - departures[i];
        }

         return diff;
    }

    /**
     * Count arrivals or departures in time interval int [ ].
     *
     * @param day          the day
     * @param timeInterval the time interval
     * @param listOfTimes  the list of times
     * @return the int [ ]
     */
//returns the number of arrivals or departures in each timeInterval
    public int[] countArrivalsOrDeparturesInTimeInterval(Date day , int timeInterval,List<Date> listOfTimes ) {


        Calendar startsWork = Calendar.getInstance();
        startsWork.set(Calendar.HOUR_OF_DAY, 8);
        startsWork.set(Calendar.MINUTE, 0);

        Calendar leavesWork = Calendar.getInstance();
        leavesWork.set(Calendar.HOUR_OF_DAY, 20);
        leavesWork.set(Calendar.MINUTE, 0);

        Calendar chosenDay = Calendar.getInstance();
        chosenDay.setTime(day);


        int[] seq = new int[720 / timeInterval];

        for (Date dateLine : listOfTimes)
        {
            Calendar dayLine = Calendar.getInstance();
            dayLine.setTime(dateLine);

            int year = Integer.compare(chosenDay.get(Calendar.YEAR), dayLine.get(Calendar.YEAR));
            int month = Integer.compare(chosenDay.get(Calendar.MONTH), dayLine.get(Calendar.MONTH));
            int day_of_month = Integer.compare(chosenDay.get(Calendar.DAY_OF_MONTH), dayLine.get(Calendar.DAY_OF_MONTH));

            if (year == 0 && month == 0 && day_of_month == 0)
            {
                int startsWorkInMinutes = startsWork.get(Calendar.HOUR_OF_DAY) * 60 + startsWork.get(Calendar.MINUTE) ;
                int leavesWorkInMinutes = leavesWork.get(Calendar.HOUR_OF_DAY) * 60 + leavesWork.get(Calendar.MINUTE);
                int dateLineInMinutes = dayLine.get(Calendar.HOUR_OF_DAY) * 60 + dayLine.get(Calendar.MINUTE);
                int diff = dateLineInMinutes - startsWorkInMinutes;

                if(dateLineInMinutes >= leavesWorkInMinutes)
                    continue;
                else
                {
                    double division = diff / timeInterval;
                    int floor = (int) Math.floor(division);
                    seq[floor] = seq[floor] + 1;

                }
            }
        }

        return seq;
    }

    /**
     * Process all data before showing.
     *
     * @param seq                 the sequence
     * @param bruteForceAlgorithm the brute force algorithm
     * @param timeInterval        the time interval
     */
    public String processAllDataBeforeShowing(int[] seq,int[] bruteForceAlgorithm, int timeInterval)
    {
        int sum = 0;
        for(int i=bruteForceAlgorithm[0]; i <= bruteForceAlgorithm[1]; i++)
        {
            sum = sum + seq[i];
        }

        int indexStart = bruteForceAlgorithm[0];
        int indexEnd = bruteForceAlgorithm[1];


        Calendar startsWork = Calendar.getInstance();
        startsWork.set(Calendar.HOUR_OF_DAY, 8);
        startsWork.set(Calendar.MINUTE, 0);


        long timeInSecs = startsWork.getTimeInMillis();
        Date beginDate = new Date(timeInSecs + (indexStart * timeInterval * 60 * 1000));
        Date endDate = new Date(timeInSecs + ((indexEnd+1) * timeInterval * 60 * 1000));

        Calendar beginD = Calendar.getInstance();
        beginD.setTime(beginDate);
        Calendar endD = Calendar.getInstance();
        endD.setTime(endDate);

        return getData(seq, Arrays.copyOfRange(seq,indexStart,indexEnd + 1),sum,beginD, endD);
    }

    /**
     * Show data.
     *
     * @param seq                 the sequence
     * @param bruteForceAlgorithm the brute force algorithm
     * @param sum                 the sum
     * @param beginDate           the begin date
     * @param endDate             the end date
     * @return
     */
    public String getData(int[] seq, int[] bruteForceAlgorithm, int sum, Calendar beginDate, Calendar endDate)
    {
        String data = "";

        data+="Input List" +"\n";
        data+=Arrays.toString(seq)+"\n";
        data+="Maximum sum contiguous sublist"+"\n";
        data+=Arrays.toString(bruteForceAlgorithm)+"\n";
        data+="Sum of maximum contiguous sublist"+"\n";
        data+=sum+"\n";
        data+="Vaccination Center was less effective from " + beginDate.get(Calendar.HOUR_OF_DAY) + " to " + endDate.get(Calendar.HOUR_OF_DAY)+"\n";
        return data;

    }

}
