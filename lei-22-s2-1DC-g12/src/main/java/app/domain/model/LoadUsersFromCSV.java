package app.domain.model;

import app.domain.model.Platform.RegisterSNSUser;
import app.domain.model.Platform.RegisterUsers;
import app.domain.shared.Constants;
import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


/**
 * The type Load users from csv.
 */
public class LoadUsersFromCSV implements Serializable, LoadFromCSV{
    /**
     * List of String array to save the user in file
     */
    private List<String[]> usersListString = new ArrayList<>();
    /**
     * The AuthFacade
     */
    private transient RegisterUsers registerUsers;
    /**
     * The Register SNSUser , to add and validate the SNSUsers imported from file
     */
    private RegisterSNSUser m_oRegisterSNSUser;

    /**
     * Instantiates a new Load users from csv.
     *
     * @param registerUsers         the user register
     * @param m_oRegisterSNSUser the register sns user
     */
    public LoadUsersFromCSV(RegisterUsers registerUsers, RegisterSNSUser m_oRegisterSNSUser)
    {
        this.registerUsers = registerUsers;
        this.m_oRegisterSNSUser = m_oRegisterSNSUser;
    }

    /**
     * Gets list of files.
     *
     * @param path the path
     * @return the list of files
     */
    public List<String> getListOfFiles(String path) {

        return LoadFromCSV.getListOfFiles(path);
    }

    /**
     * Load users from csv .
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean loadUsersFromCSV (String fileName)
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

    /**
     * Starts to read line by line of the file to get and process the data that is stored
     */
    private void convertCSV(InputStream inputStream) {

        String delimiterComma = Constants.DELIMITER_COMMA;
        String delimiterSemicolon = Constants.DELIMITER_SEMICOLON;

        try (InputStreamReader streamReader =new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader))
        {

            String header = reader.readLine();

            if(header.compareToIgnoreCase("Name;Sex;BirthDate;Address;PhoneNumber;Email;SNSUserNumber;CitizenCardNumber") == 0)
                convertFromBufferedReaderToStringSNSUser(reader,delimiterSemicolon);
            else
            {
                String[] splitHeader = header.split(delimiterComma);
                if(splitHeader[1].compareToIgnoreCase("NA") == 0 || splitHeader[1].compareTo("") == 0)
                    splitHeader[1] = "Other";

                if (!(splitHeader.length == 0 || Arrays.asList(splitHeader).contains("") || splitHeader.length != 8))
                {

                    try{
                        new Email(splitHeader[5]);

                        if(!StringUtils.isNumeric(splitHeader[4]) || !StringUtils.isNumeric(splitHeader[6]) || !StringUtils.isNumeric(splitHeader[7]))
                            convertFromBufferedReaderToStringSNSUser(reader, delimiterComma);
                        else
                        {
                            usersListString.add(splitHeader);
                            convertFromBufferedReaderToStringSNSUser(reader, delimiterComma);
                        }

                    }catch (IllegalArgumentException ex)
                    {
                        convertFromBufferedReaderToStringSNSUser(reader, delimiterComma);
                    }

                }
                else
                    convertFromBufferedReaderToStringSNSUser(reader,delimiterComma);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Finish the reading of the file and finally adds the information about the SNSUser to a list of Strings array
     */
    private void  convertFromBufferedReaderToStringSNSUser(BufferedReader reader,String delimiter) {

        String row;

        try {
            while ((row = reader.readLine()) != null) {
                if ((row.trim()).length() > 0) {
                    String[] splitRowData = row.split(delimiter);
                    if(splitRowData[1].compareToIgnoreCase("NA") == 0 || splitRowData[1].compareTo("") == 0)
                        splitRowData[1] = "Other";

                    if (splitRowData.length == 0 || Arrays.asList(splitRowData).contains("") || splitRowData.length != 8)
                        continue;
                    else
                    {
                        try{
                            new Email(splitRowData[5]);

                            if(!StringUtils.isNumeric(splitRowData[4]) || !StringUtils.isNumeric(splitRowData[6]) || !StringUtils.isNumeric(splitRowData[7]))
                                continue;
                            else
                                usersListString.add(splitRowData);

                        }catch (IllegalArgumentException ex)
                        {
                            continue;
                        }


                    }
                }

            }
        } catch (IOException e) {
            e.getMessage();
        }

    }


    /**
     * Register list string SNSUsers and shows if SNSUser was added or already exists
     *
     * @return the boolean
     */
    public boolean registerListStringUsersInSystem() {


        System.out.println(MessageBundle.getString("listofsnsusersthatwillbeaddedunlesstheyexist"));

        for(String[] user: usersListString)
        {
            if(convertFromUserStringToSNSUser(user))
                System.out.println(String.format(MessageBundle.getString("userwithemailaddedtosystem"), user[0], user[5]));
            else
                    System.out.println(String.format(MessageBundle.getString("userwithemailalreadyexistsinthesystem"), user[0], user[5]));

        }


        usersListString.clear();

        return true;
    }

    /**
     * Convert from SNSuser String to object SNSuser and add the object to list SNSUser in RegisterSNSUser list
     *
     * @return the boolean
     */
    private boolean convertFromUserStringToSNSUser(String[] userString) {

        try {

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(userString[2]);
            SNSUser tempUser = m_oRegisterSNSUser.newSNSUser(userString[0], userString[3], userString[1], userString[4], userString[5], date1  , userString[6], userString[7]);

            if(tempUser == null)
                return false;
            else
                return m_oRegisterSNSUser.addSNSUser(tempUser);

        } catch (ParseException e) {
            System.out.println("Date invalid format");
            e.getMessage();
        }

        return false;
    }


    /**
     * Show SNSUsers data that was stored in file
     *
     * @return the boolean
     */
    public boolean showData() {

        System.out.println(MessageBundle.getString("listofusersbeforeaddingtosystem"));

        int i=0;
        for(String[] user : usersListString)
        {
            System.out.println(MessageBundle.getString("name") + ":" + user[i] + " " +  MessageBundle.getString("address") + ":" + user[i+3] + " " + MessageBundle.getString("gender") + ":"+ user[i+1] + " " +
                    MessageBundle.getString("phonenumber") + ":" + user[i+4] + " " + MessageBundle.getString("email") + ":" + user[i+5] + " " + MessageBundle.getString("birthdate") + ":" + user[i+2] + " " +
                    MessageBundle.getString("snsusernumber") + ":" + user[i+6] + " " + MessageBundle.getString("citizencardnumber") + ":" + user[i+7]);
        }


        return true;
    }

    /**
     * Print system users
     *
     * @return the List<UserDTO>
     */
    public List<UserDTO> printSystemUsers() {

        return this.registerUsers.authFacade.getUsers();
    }




}
