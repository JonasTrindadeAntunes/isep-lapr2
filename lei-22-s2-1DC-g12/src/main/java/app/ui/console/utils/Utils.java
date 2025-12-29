package app.ui.console.utils;

import app.domain.shared.MessageBundle;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt)
    {
        try
        {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = "";
                do {
                    input = readLineFromConsole(prompt);
                }while(!StringUtils.isNumeric(input));

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = "";
                do {
                    input = readLineFromConsole(prompt);
                }while(!StringUtils.isNumeric(input));
                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = "";
                do{
                    strDate = readLineFromConsole(prompt + ". Format dd-MM-yyyy");
                }while(!strDate.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$"));

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readHourFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("HH:mm");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }
    static public int showAndSelectIndex(List list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }

    static public int showAndSelectIndexMainMenu(List list, String header)
    {
        showListMainMenu(list,header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 -" + MessageBundle.getString("cancel"));
    }

    static public void showListMainMenu(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Press 0 to launch the javaFX application");
    }

    static public Object selectsObject(List list)
    {
        String input;
        Integer value;
        do
        {
            do {
                input = Utils.readLineFromConsole(MessageBundle.getString("typeoption"));
            }while(!StringUtils.isNumeric(input));
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list)
    {
        String input;
        Integer value;
        do
        {
            do {
                input = Utils.readLineFromConsole(MessageBundle.getString("typeoption"));
            }while(!StringUtils.isNumeric(input));
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }
}
