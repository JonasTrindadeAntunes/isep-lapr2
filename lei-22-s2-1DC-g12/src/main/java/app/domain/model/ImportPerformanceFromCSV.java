package app.domain.model;

import app.domain.model.Platform.RegisterSNSUser;
import app.domain.model.Platform.RegisterUsers;
import app.domain.model.VaccinationCenter.RegisterVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine.RegisterVaccineType;
import app.domain.model.Vaccine.Vaccine;
import app.domain.model.Vaccine.VaccineType;
import app.domain.shared.Constants;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Import performance from csv.
 */
public class ImportPerformanceFromCSV implements Serializable, LoadFromCSV {

    /**
     * The list of all elements imported from the csv file.
     */
    private List<String[]> performanceDataListString = new ArrayList<>();
    /**
     * The AuthFacade
     */
    private transient RegisterUsers registerUsers;

    /**
     * The register of Sns users.
     */
    private RegisterSNSUser m_oRegisterSNSUser;

    /**
     * The register of vaccine types.
     */
    private RegisterVaccineType m_oRegisterVaccineType;
    private RegisterVaccinationCenter registerVaccinationCenter;


    /**
     * Instantiates a new Import performance from csv.
     *
     * @param registerUsers          the register users
     * @param m_oRegisterSNSUser     the m o register sns user
     * @param m_oRegisterVaccineType the m o register vaccine type
     */
    public ImportPerformanceFromCSV(RegisterUsers registerUsers, RegisterSNSUser m_oRegisterSNSUser, RegisterVaccineType m_oRegisterVaccineType, RegisterVaccinationCenter registerVaccinationCenter)
    {
        this.registerUsers = registerUsers;
        this.m_oRegisterSNSUser = m_oRegisterSNSUser;
        this.m_oRegisterVaccineType = m_oRegisterVaccineType;
        this.registerVaccinationCenter = registerVaccinationCenter;
    }


    /**
     * Get list of files.
     *
     * @param path the path
     * @return the list
     */
    public List <String> getListOfFiles(String path){
        return LoadFromCSV.getListOfFiles(path);
    }

    /**
     * Import performance from csv.
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean importPerformanceFromCSV (String fileName)
    {
        try {
            InputStream inputStream = LoadFromCSV.loadFromCSV(fileName);
            convertCSV(inputStream);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }


    public boolean importPerformanceFromCSV (File path)
    {
        try {
            InputStream inputStream = LoadFromCSV.loadFromCSV(path);
            convertCSV(inputStream);
        }catch (IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }


    /**
     * Converts all data imported from csv .
     */
    private void convertCSV(InputStream inputStream) {

        String delimiterSemicolon = Constants.DELIMITER_SEMICOLON;
        VaccinationCenter vc = this.registerVaccinationCenter.getListVaccinationCenters().get(0);
        try (InputStreamReader streamReader =new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader))
        {

            String header = reader.readLine();
            String row;

                while ((row = reader.readLine()) != null) {
                    if ((row.trim()).length() > 0)
                    {

                        String[] splitRowData = row.split(delimiterSemicolon);

                        if (splitRowData.length == 0 || Arrays.asList(splitRowData).contains("") || splitRowData.length != 8)
                            continue;
                        else
                        {
                            SNSUser user = m_oRegisterSNSUser.getSNSUserBySNSUserNumber(splitRowData[0].trim());
                            if(user == null)
                                continue;
                            else
                            {

                                int i = splitRowData.length;
                                int n = i+2;
                                String[] newArray = new String[n];
                                for(int cnt=0;cnt<i;cnt++)
                                {
                                    newArray[cnt] = splitRowData[cnt];
                                }
                                newArray[i] = user.getName();

                                for(VaccineType vaccineType :  m_oRegisterVaccineType.getListVaccineType())
                                {
                                    for(Vaccine vaccine : vaccineType.getVaccineList())
                                    {
                                        if (vaccine.getName().compareTo(splitRowData[1]) == 0 && vaccine.getId().equals("4455")){
                                            SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                                            SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                            Date date = format1.parse(splitRowData[4].trim());
                                            format2.format(date);
                                            Date date1 = format1.parse(splitRowData[6].trim());
                                            format2.format(date1);
                                            VaccineSchedule vs = new VaccineSchedule(vaccineType,user,date);
                                            vc.addVaccineSchedule(vs);
                                            VaccineAdministration va = new VaccineAdministration(user, vaccineType, vaccine, splitRowData[3],date1);
                                            user.addVaccineSchedule(vs);
                                            user.registerVaccineAdministration(va);
                                            vc.addVaccineAdministration(va);
                                            newArray[i+1] = vaccineType.getDesignation();
                                        }
                                    }

                                }

                                performanceDataListString.add(newArray);
                            }
                        }

                    }

                }




        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Gets performance data list .
     *
     * @return the performance data list string
     */
    public List<String[]> getPerformanceDataListString()
    {
        return this.performanceDataListString;
    }


}
