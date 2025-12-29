package app.domain.shared;

import app.domain.model.SNSUser;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.VaccineAdministration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The type Algorithms.
 */
public class Algorithms {

    /**
     * Bubble sort dates list.
     *
     * @param sortElementOfArray        the sort element of array
     * @param performanceDataListString the performance data list string
     * @return the list
     */
    public static List<String[]> bubbleSortDates(int sortElementOfArray, List<String[]> performanceDataListString) {

        int n = performanceDataListString.size();
        String[] temp;

        for (int i = 0; i < n - 1; i++)
        {

            for(int j = 0 ; j< n-i-1;j++)
            {

                String[] listElement = performanceDataListString.get(j);
                String[] listNextElement = performanceDataListString.get(j+1);

                try
                {

                    SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                    SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                    Date listElementData = format1.parse(listElement[sortElementOfArray]);
                    format2.format(listElementData);

                    Date listNextElementData = format1.parse(listNextElement[sortElementOfArray]);
                    format2.format(listNextElementData);

                    if(listElementData.after(listNextElementData))
                    {
                        temp = performanceDataListString.get(j);
                        performanceDataListString.set(j,performanceDataListString.get(j+1));
                        performanceDataListString.set(j+1,temp);

                    }

                } catch (ParseException e)
                {
                    System.out.println("Date invalid format");
                    e.getMessage();
                }
            }


        }


        return performanceDataListString;
    }

    /**
     * Quick sort list.
     *
     * @param sortElementOfArray the sort element of array
     * @param arr                the arr
     * @param begin              the begin
     * @param end                the end
     * @return the list
     */
    public static List<String[]> quickSort(int sortElementOfArray, List<String[]> arr, int begin, int end)
    {
        if (begin < end) {
            int partitionIndex = partition(sortElementOfArray, arr, begin, end);

            quickSort(sortElementOfArray,arr, begin, partitionIndex-1);
            quickSort(sortElementOfArray,arr, partitionIndex+1, end);
        }

        return arr;
    }

    private static int partition(int sortElementOfArray, List<String[]> arr, int begin, int end) {
        String[] pivot = arr.get(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++)
        {

            try
            {
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                Date listElementData = format1.parse(arr.get(j)[sortElementOfArray]);
                format2.format(listElementData);

                Date listLastElementData = format1.parse(pivot[sortElementOfArray]);
                format2.format(listLastElementData);

                if (listElementData.before(listLastElementData) || listElementData.equals(listLastElementData))
                {
                    i++;

                    String[] swapTemp = arr.get(i);
                    arr.set(i,arr.get(j));
                    arr.set(j,swapTemp);
                }

            }  catch (ParseException e)
            {
                System.out.println("Date invalid format");
                e.getMessage();
            }


        }

        String[] swapTemp = arr.get(i+1);
        arr.set(i+1,arr.get(end));
        arr.set(end,swapTemp);

        return i+1;
    }

    /**
     * Brute force algorithm int [ ].
     *
     * @param seq the seq
     * @return the int [ ]
     */
    public static int[] bruteForceAlgorithm(int[] seq)
    {

        int[] result = Max(seq);


        return result;
    }

    /**
     * Max int [ ].
     *
     * @param seq the seq
     * @return the int [ ]
     */
    public static int[] Max(int[] seq) {
            int start = 0;
            int end = 0;
            int max = Integer.MIN_VALUE;
            int currentSum = 0;
            for (int i = 0; i < seq.length; i++) {
                for (int j = seq.length-1; j >= i; j--) {
                    for (int k = i; k <= j; k++) {
                        currentSum += seq[k];
                    }
                    if (currentSum > max) {
                        max = currentSum;
                        start = i;
                        end = j;
                    }
                    currentSum= 0;

                }
            }
            int[] arr = new int[2];
            arr[0] = start;
            arr[1] = end;
            return arr;
        }


    /**
     * Checks whether a user has a fully vaccinated status for a particular vaccine
     *
     * @param user    the user in question
     * @param vaccine the vaccine to check
     * @return true if the user is fully vaccinated, false if not.
     */
    public static boolean validateVaccinationStatus(SNSUser user, Vaccine vaccine){
        List<VaccineAdministration> list = user.getVaccineAdministrations();
        int numberOfDoses = vaccine.getListAdministrationProcess().get(0).getDosesToBeAdministered();
        int n = 0;
        for (VaccineAdministration va: list){
            if(va.getVaccine().equals(vaccine))
                n++;
        }
        return n==numberOfDoses;
    }

}
